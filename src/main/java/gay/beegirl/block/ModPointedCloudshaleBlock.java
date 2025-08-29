package gay.beegirl.block;

import com.google.common.annotations.VisibleForTesting;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ModPointedCloudshaleBlock extends Block implements Fallable, SimpleWaterloggedBlock {
    public static final MapCodec<ModPointedCloudshaleBlock> CODEC = simpleCodec(ModPointedCloudshaleBlock::new);
    public static final EnumProperty<Direction> TIP_DIRECTION;
    public static final EnumProperty<DripstoneThickness> THICKNESS;
    public static final BooleanProperty WATERLOGGED;
    private static final int MAX_SEARCH_LENGTH_WHEN_CHECKING_DRIP_TYPE = 11;
    private static final int DELAY_BEFORE_FALLING = 2;
    private static final float DRIP_PROBABILITY_PER_ANIMATE_TICK = 0.02F;
    private static final float DRIP_PROBABILITY_PER_ANIMATE_TICK_IF_UNDER_LIQUID_SOURCE = 0.12F;
    private static final int MAX_SEARCH_LENGTH_BETWEEN_STALACTITE_TIP_AND_CAULDRON = 11;
    private static final float WATER_TRANSFER_PROBABILITY_PER_RANDOM_TICK = 0.17578125F;
    private static final float LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK = 0.05859375F;
    private static final double MIN_TRIDENT_VELOCITY_TO_BREAK_DRIPSTONE = 0.6;
    private static final float STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE = 1.0F;
    private static final int STALACTITE_MAX_DAMAGE = 40;
    private static final int MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION = 6;
    private static final float STALAGMITE_FALL_DISTANCE_OFFSET = 2.5F;
    private static final int STALAGMITE_FALL_DAMAGE_MODIFIER = 2;
    private static final float AVERAGE_DAYS_PER_GROWTH = 5.0F;
    private static final float GROWTH_PROBABILITY_PER_RANDOM_TICK = 0.011377778F;
    private static final int MAX_GROWTH_LENGTH = 7;
    private static final int MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING = 10;
    private static final VoxelShape SHAPE_TIP_MERGE;
    private static final VoxelShape SHAPE_TIP_UP;
    private static final VoxelShape SHAPE_TIP_DOWN;
    private static final VoxelShape SHAPE_FRUSTUM;
    private static final VoxelShape SHAPE_MIDDLE;
    private static final VoxelShape SHAPE_BASE;
    private static final double STALACTITE_DRIP_START_PIXEL;
    private static final float MAX_HORIZONTAL_OFFSET;
    private static final VoxelShape REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK;

    public @NotNull MapCodec<ModPointedCloudshaleBlock> codec() {
        return CODEC;
    }

    public ModPointedCloudshaleBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState((((this.stateDefinition.any()).setValue(TIP_DIRECTION, Direction.UP)).setValue(THICKNESS, DripstoneThickness.TIP)).setValue(WATERLOGGED, false));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
    }

    protected boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return isValidPointedDripstonePlacement(levelReader, blockPos, blockState.getValue(TIP_DIRECTION));
    }

    protected @NotNull BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
        if (blockState.getValue(WATERLOGGED)) {
            scheduledTickAccess.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelReader));
        }

        if (direction != Direction.UP && direction != Direction.DOWN) {
            return blockState;
        } else {
            Direction direction2 = blockState.getValue(TIP_DIRECTION);
            if (direction2 == Direction.DOWN && scheduledTickAccess.getBlockTicks().hasScheduledTick(blockPos, this)) {
                return blockState;
            } else if (direction == direction2.getOpposite() && !this.canSurvive(blockState, levelReader, blockPos)) {
                if (direction2 == Direction.DOWN) {
                    scheduledTickAccess.scheduleTick(blockPos, this, DELAY_BEFORE_FALLING);
                } else {
                    scheduledTickAccess.scheduleTick(blockPos, this, 1);
                }

                return blockState;
            } else {
                boolean bl = blockState.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
                DripstoneThickness dripstoneThickness = calculateDripstoneThickness(levelReader, blockPos, direction2, bl);
                return blockState.setValue(THICKNESS, dripstoneThickness);
            }
        }
    }

    protected void onProjectileHit(Level level, BlockState blockState, BlockHitResult blockHitResult, Projectile projectile) {
        if (!level.isClientSide) {
            BlockPos blockPos = blockHitResult.getBlockPos();
            if (level instanceof ServerLevel serverLevel) {
                if (projectile.mayInteract(serverLevel, blockPos) && projectile.mayBreak(serverLevel) && projectile instanceof ThrownTrident && projectile.getDeltaMovement().length() > MIN_TRIDENT_VELOCITY_TO_BREAK_DRIPSTONE) {
                    level.destroyBlock(blockPos, true);
                }
            }

        }
    }

    public void fallOn(Level level, BlockState blockState, BlockPos blockPos, Entity entity, double d) {
        if (blockState.getValue(TIP_DIRECTION) == Direction.UP && blockState.getValue(THICKNESS) == DripstoneThickness.TIP) {
            entity.causeFallDamage(d + (double)STALAGMITE_FALL_DISTANCE_OFFSET, 2.0F, level.damageSources().stalagmite());
        } else {
            super.fallOn(level, blockState, blockPos, entity, d);
        }

    }

    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (canDrip(blockState)) {
            float f = randomSource.nextFloat();
            if (!(f > DRIP_PROBABILITY_PER_ANIMATE_TICK_IF_UNDER_LIQUID_SOURCE)) {
                getFluidAboveStalactite(level, blockPos, blockState).filter((fluidInfo) -> f < DRIP_PROBABILITY_PER_ANIMATE_TICK || canFillCauldron(fluidInfo.fluid)).ifPresent((fluidInfo) -> spawnDripParticle(level, blockPos, blockState, fluidInfo.fluid));
            }
        }
    }

    protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (isStalagmite(blockState) && !this.canSurvive(blockState, serverLevel, blockPos)) {
            serverLevel.destroyBlock(blockPos, true);
        } else {
            spawnFallingStalactite(blockState, serverLevel, blockPos);
        }

    }

    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        maybeTransferFluid(blockState, serverLevel, blockPos, randomSource.nextFloat());
        if (randomSource.nextFloat() < GROWTH_PROBABILITY_PER_RANDOM_TICK && isStalactiteStartPos(blockState, serverLevel, blockPos)) {
            growStalactiteOrStalagmiteIfPossible(blockState, serverLevel, blockPos, randomSource);
        }

    }

    @VisibleForTesting
    public static void maybeTransferFluid(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, float f) {
        if (!(f > LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK) || !(f > WATER_TRANSFER_PROBABILITY_PER_RANDOM_TICK)) {
            if (isStalactiteStartPos(blockState, serverLevel, blockPos)) {
                Optional<ModPointedCloudshaleBlock.FluidInfo> optional = getFluidAboveStalactite(serverLevel, blockPos, blockState);
                if (optional.isPresent()) {
                    Fluid fluid = (optional.get()).fluid;
                    float g = 0.0f;
                    if (fluid == Fluids.WATER) {
                        g = WATER_TRANSFER_PROBABILITY_PER_RANDOM_TICK;
                    } else if (fluid == Fluids.LAVA) {
                        g = LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK;
                    }

                    if (!(f >= g)) {
                        BlockPos blockPos2 = findTip(blockState, serverLevel, blockPos, MAX_SEARCH_LENGTH_WHEN_CHECKING_DRIP_TYPE, false);
                        if (blockPos2 != null) {
                            if (optional.get().sourceState.is(Blocks.MUD) && fluid == Fluids.WATER) {
                                BlockState blockState2 = Blocks.CLAY.defaultBlockState();
                                serverLevel.setBlockAndUpdate(optional.get().pos, blockState2);
                                Block.pushEntitiesUp(optional.get().sourceState, blockState2, serverLevel, optional.get().pos);
                                serverLevel.gameEvent(GameEvent.BLOCK_CHANGE, optional.get().pos, GameEvent.Context.of(blockState2));
                                serverLevel.levelEvent(1504, blockPos2, 0);
                            } else {
                                BlockPos blockPos3 = findFillableCauldronBelowStalactiteTip(serverLevel, blockPos2, fluid);
                                if (blockPos3 != null) {
                                    serverLevel.levelEvent(1504, blockPos2, 0);
                                    int i = blockPos2.getY() - blockPos3.getY();
                                    int j = 50 + i;
                                    BlockState blockState3 = serverLevel.getBlockState(blockPos3);
                                    serverLevel.scheduleTick(blockPos3, blockState3.getBlock(), j);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        LevelAccessor levelAccessor = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();
        Direction direction = blockPlaceContext.getNearestLookingVerticalDirection().getOpposite();
        Direction direction2 = calculateTipDirection(levelAccessor, blockPos, direction);
        if (direction2 == null) {
            return null;
        } else {
            boolean bl = !blockPlaceContext.isSecondaryUseActive();
            DripstoneThickness dripstoneThickness = calculateDripstoneThickness(levelAccessor, blockPos, direction2, bl);
            return dripstoneThickness == null ? null : this.defaultBlockState().setValue(TIP_DIRECTION, direction2).setValue(THICKNESS, dripstoneThickness).setValue(WATERLOGGED, levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER);
        }
    }

    protected @NotNull FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    protected @NotNull VoxelShape getOcclusionShape(BlockState blockState) {
        return Shapes.empty();
    }

    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        VoxelShape var10000;
        switch (blockState.getValue(THICKNESS)) {
            case TIP_MERGE -> var10000 = SHAPE_TIP_MERGE;
            case TIP -> var10000 = blockState.getValue(TIP_DIRECTION) == Direction.DOWN ? SHAPE_TIP_DOWN : SHAPE_TIP_UP;
            case FRUSTUM -> var10000 = SHAPE_FRUSTUM;
            case MIDDLE -> var10000 = SHAPE_MIDDLE;
            case BASE -> var10000 = SHAPE_BASE;
            default -> throw new MatchException(null, null);
        }

        VoxelShape voxelShape = var10000;
        return voxelShape.move(blockState.getOffset(blockPos));
    }

    protected boolean isCollisionShapeFullBlock(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) {
        return false;
    }

    protected float getMaxHorizontalOffset() {
        return MAX_HORIZONTAL_OFFSET;
    }

    public void onBrokenAfterFall(Level level, BlockPos blockPos, FallingBlockEntity fallingBlockEntity) {
        if (!fallingBlockEntity.isSilent()) {
            level.levelEvent(1045, blockPos, 0);
        }

    }

    public @NotNull DamageSource getFallDamageSource(Entity entity) {
        return entity.damageSources().fallingStalactite(entity);
    }

    private static void spawnFallingStalactite(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos) {
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();

        for(BlockState blockState2 = blockState; isStalactite(blockState2); blockState2 = serverLevel.getBlockState(mutableBlockPos)) {
            FallingBlockEntity fallingBlockEntity = FallingBlockEntity.fall(serverLevel, mutableBlockPos, blockState2);
            if (isTip(blockState2, true)) {
                int i = Math.max(1 + blockPos.getY() - mutableBlockPos.getY(), MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION);
                float f = STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE * (float)i;
                fallingBlockEntity.setHurtsEntities(f, STALACTITE_MAX_DAMAGE);
                break;
            }

            mutableBlockPos.move(Direction.DOWN);
        }

    }

    @VisibleForTesting
    public static void growStalactiteOrStalagmiteIfPossible(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockState blockState2 = serverLevel.getBlockState(blockPos.above(1));
        BlockState blockState3 = serverLevel.getBlockState(blockPos.above(2));
        if (canGrow(blockState2, blockState3)) {
            BlockPos blockPos2 = findTip(blockState, serverLevel, blockPos, MAX_GROWTH_LENGTH, false);
            if (blockPos2 != null) {
                BlockState blockState4 = serverLevel.getBlockState(blockPos2);
                if (canDrip(blockState4) && canTipGrow(blockState4, serverLevel, blockPos2)) {
                    if (randomSource.nextBoolean()) {
                        grow(serverLevel, blockPos2, Direction.DOWN);
                    } else {
                        growStalagmiteBelow(serverLevel, blockPos2);
                    }

                }
            }
        }
    }

    private static void growStalagmiteBelow(ServerLevel serverLevel, BlockPos blockPos) {
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();

        for(int i = 0; i < MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING; ++i) {
            mutableBlockPos.move(Direction.DOWN);
            BlockState blockState = serverLevel.getBlockState(mutableBlockPos);
            if (!blockState.getFluidState().isEmpty()) {
                return;
            }

            if (isUnmergedTipWithDirection(blockState, Direction.UP) && canTipGrow(blockState, serverLevel, mutableBlockPos)) {
                grow(serverLevel, mutableBlockPos, Direction.UP);
                return;
            }

            if (isValidPointedDripstonePlacement(serverLevel, mutableBlockPos, Direction.UP) && !serverLevel.isWaterAt(mutableBlockPos.below())) {
                grow(serverLevel, mutableBlockPos.below(), Direction.UP);
                return;
            }

            if (!canDripThrough(serverLevel, mutableBlockPos, blockState)) {
                return;
            }
        }

    }

    private static void grow(ServerLevel serverLevel, BlockPos blockPos, Direction direction) {
        BlockPos blockPos2 = blockPos.relative(direction);
        BlockState blockState = serverLevel.getBlockState(blockPos2);
        if (isUnmergedTipWithDirection(blockState, direction.getOpposite())) {
            createMergedTips(blockState, serverLevel, blockPos2);
        } else if (blockState.isAir() || blockState.is(Blocks.WATER)) {
            createDripstone(serverLevel, blockPos2, direction, DripstoneThickness.TIP);
        }

    }

    private static void createDripstone(LevelAccessor levelAccessor, BlockPos blockPos, Direction direction, DripstoneThickness dripstoneThickness) {
        BlockState blockState = ModBlock.POINTED_CLOUDSHALE.defaultBlockState().setValue(TIP_DIRECTION, direction).setValue(THICKNESS, dripstoneThickness).setValue(WATERLOGGED, levelAccessor.getFluidState(blockPos).getType() == Fluids.WATER);
        levelAccessor.setBlock(blockPos, blockState, 3);
    }

    private static void createMergedTips(BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos) {
        BlockPos blockPos3;
        BlockPos blockPos2;
        if (blockState.getValue(TIP_DIRECTION) == Direction.UP) {
            blockPos2 = blockPos;
            blockPos3 = blockPos.above();
        } else {
            blockPos3 = blockPos;
            blockPos2 = blockPos.below();
        }

        createDripstone(levelAccessor, blockPos3, Direction.DOWN, DripstoneThickness.TIP_MERGE);
        createDripstone(levelAccessor, blockPos2, Direction.UP, DripstoneThickness.TIP_MERGE);
    }

    private static void spawnDripParticle(Level level, BlockPos blockPos, BlockState blockState, Fluid fluid) {
        Vec3 vec3 = blockState.getOffset(blockPos);
        double e = (double)blockPos.getX() + (double)0.5F + vec3.x;
        double f = (double)blockPos.getY() + STALACTITE_DRIP_START_PIXEL - (double)0.0625F;
        double g = (double)blockPos.getZ() + (double)0.5F + vec3.z;
        Fluid fluid2 = getDripFluid(level, fluid);
        ParticleOptions particleOptions = fluid2.is(FluidTags.LAVA) ? ParticleTypes.DRIPPING_DRIPSTONE_LAVA : ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        level.addParticle(particleOptions, e, f, g, 0.0F, 0.0F, 0.0F);
    }

    @Nullable
    private static BlockPos findTip(BlockState blockState, LevelAccessor levelAccessor, BlockPos blockPos, int i, boolean bl) {
        if (isTip(blockState, bl)) {
            return blockPos;
        } else {
            Direction direction = blockState.getValue(TIP_DIRECTION);
            BiPredicate<BlockPos, BlockState> biPredicate = (blockPosx, blockStatex) -> blockStatex.is(ModBlock.POINTED_CLOUDSHALE) && blockStatex.getValue(TIP_DIRECTION) == direction;
            return findBlockVertical(levelAccessor, blockPos, direction.getAxisDirection(), biPredicate, (blockStatex) -> isTip(blockStatex, bl), i).orElse(null);
        }
    }

    @Nullable
    private static Direction calculateTipDirection(LevelReader levelReader, BlockPos blockPos, Direction direction) {
        Direction direction2;
        if (isValidPointedDripstonePlacement(levelReader, blockPos, direction)) {
            direction2 = direction;
        } else {
            if (!isValidPointedDripstonePlacement(levelReader, blockPos, direction.getOpposite())) {
                return null;
            }

            direction2 = direction.getOpposite();
        }

        return direction2;
    }

    private static DripstoneThickness calculateDripstoneThickness(LevelReader levelReader, BlockPos blockPos, Direction direction, boolean bl) {
        Direction direction2 = direction.getOpposite();
        BlockState blockState = levelReader.getBlockState(blockPos.relative(direction));
        if (isPointedDripstoneWithDirection(blockState, direction2)) {
            return !bl && blockState.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
        } else if (!isPointedDripstoneWithDirection(blockState, direction)) {
            return DripstoneThickness.TIP;
        } else {
            DripstoneThickness dripstoneThickness = blockState.getValue(THICKNESS);
            if (dripstoneThickness != DripstoneThickness.TIP && dripstoneThickness != DripstoneThickness.TIP_MERGE) {
                BlockState blockState2 = levelReader.getBlockState(blockPos.relative(direction2));
                return !isPointedDripstoneWithDirection(blockState2, direction) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
            } else {
                return DripstoneThickness.FRUSTUM;
            }
        }
    }

    public static boolean canDrip(BlockState blockState) {
        return isStalactite(blockState) && blockState.getValue(THICKNESS) == DripstoneThickness.TIP && !(Boolean)blockState.getValue(WATERLOGGED);
    }

    private static boolean canTipGrow(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos) {
        Direction direction = blockState.getValue(TIP_DIRECTION);
        BlockPos blockPos2 = blockPos.relative(direction);
        BlockState blockState2 = serverLevel.getBlockState(blockPos2);
        if (!blockState2.getFluidState().isEmpty()) {
            return false;
        } else {
            return blockState2.isAir() || isUnmergedTipWithDirection(blockState2, direction.getOpposite());
        }
    }

    private static Optional<BlockPos> findRootBlock(Level level, BlockPos blockPos, BlockState blockState, int i) {
        Direction direction = blockState.getValue(TIP_DIRECTION);
        BiPredicate<BlockPos, BlockState> biPredicate = (blockPosx, blockStatex) -> blockStatex.is(ModBlock.POINTED_CLOUDSHALE) && blockStatex.getValue(TIP_DIRECTION) == direction;
        return findBlockVertical(level, blockPos, direction.getOpposite().getAxisDirection(), biPredicate, (blockStatex) -> !blockStatex.is(ModBlock.POINTED_CLOUDSHALE), i);
    }

    private static boolean isValidPointedDripstonePlacement(LevelReader levelReader, BlockPos blockPos, Direction direction) {
        BlockPos blockPos2 = blockPos.relative(direction.getOpposite());
        BlockState blockState = levelReader.getBlockState(blockPos2);
        return blockState.isFaceSturdy(levelReader, blockPos2, direction) || isPointedDripstoneWithDirection(blockState, direction);
    }

    private static boolean isTip(BlockState blockState, boolean bl) {
        if (!blockState.is(ModBlock.POINTED_CLOUDSHALE)) {
            return false;
        } else {
            DripstoneThickness dripstoneThickness = blockState.getValue(THICKNESS);
            return dripstoneThickness == DripstoneThickness.TIP || bl && dripstoneThickness == DripstoneThickness.TIP_MERGE;
        }
    }

    private static boolean isUnmergedTipWithDirection(BlockState blockState, Direction direction) {
        return isTip(blockState, false) && blockState.getValue(TIP_DIRECTION) == direction;
    }

    private static boolean isStalactite(BlockState blockState) {
        return isPointedDripstoneWithDirection(blockState, Direction.DOWN);
    }

    private static boolean isStalagmite(BlockState blockState) {
        return isPointedDripstoneWithDirection(blockState, Direction.UP);
    }

    private static boolean isStalactiteStartPos(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        return isStalactite(blockState) && !levelReader.getBlockState(blockPos.above()).is(ModBlock.POINTED_CLOUDSHALE);
    }

    protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
        return false;
    }

    private static boolean isPointedDripstoneWithDirection(BlockState blockState, Direction direction) {
        return blockState.is(ModBlock.POINTED_CLOUDSHALE) && blockState.getValue(TIP_DIRECTION) == direction;
    }

    @Nullable
    private static BlockPos findFillableCauldronBelowStalactiteTip(Level level, BlockPos blockPos, Fluid fluid) {
        Predicate<BlockState> predicate = (blockState) -> blockState.getBlock() instanceof AbstractCauldronBlock && ((AbstractCauldronBlock)blockState.getBlock()).canReceiveStalactiteDrip(fluid);
        BiPredicate<BlockPos, BlockState> biPredicate = (blockPosx, blockState) -> canDripThrough(level, blockPosx, blockState);
        return findBlockVertical(level, blockPos, Direction.DOWN.getAxisDirection(), biPredicate, predicate, MAX_SEARCH_LENGTH_BETWEEN_STALACTITE_TIP_AND_CAULDRON).orElse(null);
    }

    @Nullable
    public static BlockPos findStalactiteTipAboveCauldron(Level level, BlockPos blockPos) {
        BiPredicate<BlockPos, BlockState> biPredicate = (blockPosx, blockState) -> canDripThrough(level, blockPosx, blockState);
        return findBlockVertical(level, blockPos, Direction.UP.getAxisDirection(), biPredicate, ModPointedCloudshaleBlock::canDrip, MAX_SEARCH_LENGTH_BETWEEN_STALACTITE_TIP_AND_CAULDRON).orElse(null);
    }

    public static Fluid getCauldronFillFluidType(ServerLevel serverLevel, BlockPos blockPos) {
        return getFluidAboveStalactite(serverLevel, blockPos, serverLevel.getBlockState(blockPos)).map((fluidInfo) -> fluidInfo.fluid).filter(ModPointedCloudshaleBlock::canFillCauldron).orElse(Fluids.EMPTY);
    }

    private static Optional<ModPointedCloudshaleBlock.FluidInfo> getFluidAboveStalactite(Level level, BlockPos blockPos, BlockState blockState) {
        return !isStalactite(blockState) ? Optional.empty() : findRootBlock(level, blockPos, blockState, MAX_SEARCH_LENGTH_WHEN_CHECKING_DRIP_TYPE).map((blockPosx) -> {
            BlockPos blockPos2 = blockPosx.above();
            BlockState blockState2 = level.getBlockState(blockPos2);
            Fluid fluid;
            if (blockState2.is(Blocks.MUD) && !level.dimensionType().ultraWarm()) {
                fluid = Fluids.WATER;
            } else {
                fluid = level.getFluidState(blockPos2).getType();
            }

            return new ModPointedCloudshaleBlock.FluidInfo(blockPos2, fluid, blockState2);
        });
    }

    private static boolean canFillCauldron(Fluid fluid) {
        return fluid == Fluids.LAVA || fluid == Fluids.WATER;
    }

    private static boolean canGrow(BlockState blockState, BlockState blockState2) {
        return blockState.is(ModBlock.CLOUDSHALE.base()) && blockState2.is(Blocks.WATER) && blockState2.getFluidState().isSource();
    }

    private static Fluid getDripFluid(Level level, Fluid fluid) {
        if (fluid.isSame(Fluids.EMPTY)) {
            return level.dimensionType().ultraWarm() ? Fluids.LAVA : Fluids.WATER;
        } else {
            return fluid;
        }
    }

    private static Optional<BlockPos> findBlockVertical(LevelAccessor levelAccessor, BlockPos blockPos, Direction.AxisDirection axisDirection, BiPredicate<BlockPos, BlockState> biPredicate, Predicate<BlockState> predicate, int i) {
        Direction direction = Direction.get(axisDirection, Direction.Axis.Y);
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();

        for(int j = 1; j < i; ++j) {
            mutableBlockPos.move(direction);
            BlockState blockState = levelAccessor.getBlockState(mutableBlockPos);
            if (predicate.test(blockState)) {
                return Optional.of(mutableBlockPos.immutable());
            }

            if (levelAccessor.isOutsideBuildHeight(mutableBlockPos.getY()) || !biPredicate.test(mutableBlockPos, blockState)) {
                return Optional.empty();
            }
        }

        return Optional.empty();
    }

    private static boolean canDripThrough(BlockGetter blockGetter, BlockPos blockPos, BlockState blockState) {
        if (blockState.isAir()) {
            return true;
        } else if (blockState.isSolidRender()) {
            return false;
        } else if (!blockState.getFluidState().isEmpty()) {
            return false;
        } else {
            VoxelShape voxelShape = blockState.getCollisionShape(blockGetter, blockPos);
            return !Shapes.joinIsNotEmpty(REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK, voxelShape, BooleanOp.AND);
        }
    }

    static {
        TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
        THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
        WATERLOGGED = BlockStateProperties.WATERLOGGED;
        SHAPE_TIP_MERGE = Block.column(6.0F, 0.0F, 16.0F);
        SHAPE_TIP_UP = Block.column(6.0F, 0.0F, 11.0F);
        SHAPE_TIP_DOWN = Block.column(6.0F, 5.0F, 16.0F);
        SHAPE_FRUSTUM = Block.column(8.0F, 0.0F, 16.0F);
        SHAPE_MIDDLE = Block.column(10.0F, 0.0F, 16.0F);
        SHAPE_BASE = Block.column(12.0F, 0.0F, 16.0F);
        STALACTITE_DRIP_START_PIXEL = SHAPE_TIP_DOWN.min(Direction.Axis.Y);
        MAX_HORIZONTAL_OFFSET = (float)SHAPE_BASE.min(Direction.Axis.X);
        REQUIRED_SPACE_TO_DRIP_THROUGH_NON_SOLID_BLOCK = Block.column(4.0F, 0.0F, 16.0F);
    }

    record FluidInfo(BlockPos pos, Fluid fluid, BlockState sourceState) {}
}
