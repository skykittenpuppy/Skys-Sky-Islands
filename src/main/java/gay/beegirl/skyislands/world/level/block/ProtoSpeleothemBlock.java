package gay.beegirl.skyislands.world.level.block;

import com.google.common.annotations.VisibleForTesting;
import gay.beegirl.skyislands.sounds.ModSoundEvents;
import gay.beegirl.skyislands.tags.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public abstract class ProtoSpeleothemBlock extends Block implements SimpleWaterloggedBlock, Fallable {
	public static final DirectionProperty TIP_DIRECTION = BlockStateProperties.VERTICAL_DIRECTION;
	public static final EnumProperty<DripstoneThickness> THICKNESS = BlockStateProperties.DRIPSTONE_THICKNESS;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final int DELAY_BEFORE_FALLING = 2;
	private static final double MIN_TRIDENT_VELOCITY_TO_BREAK_DRIPSTONE = 0.6;
	private static final float STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE = 1.0F;
	private static final int STALACTITE_MAX_DAMAGE = 40;
	private static final int MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION = 6;
	private static final VoxelShape TIP_MERGE_SHAPE = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 16.0F, 11.0F);
	private static final VoxelShape TIP_SHAPE_UP = Block.box(5.0F, 0.0F, 5.0F, 11.0F, 11.0F, 11.0F);
	private static final VoxelShape TIP_SHAPE_DOWN = Block.box(5.0F, 5.0F, 5.0F, 11.0F, 16.0F, 11.0F);
	private static final VoxelShape FRUSTUM_SHAPE = Block.box(4.0F, 0.0F, 4.0F, 12.0F, 16.0F, 12.0F);
	private static final VoxelShape MIDDLE_SHAPE = Block.box(3.0F, 0.0F, 3.0F, 13.0F, 16.0F, 13.0F);
	private static final VoxelShape BASE_SHAPE = Block.box(2.0F, 0.0F, 2.0F, 14.0F, 16.0F, 14.0F);
	private static final float MAX_HORIZONTAL_OFFSET = (float)BASE_SHAPE.min(Direction.Axis.X);
	private static final float AVERAGE_DAYS_PER_GROWTH = 5.0F;
	private static final float GROWTH_PROBABILITY_PER_RANDOM_TICK = 0.011377778F;
	private static final int MAX_GROWTH_LENGTH = 7;
	private static final int MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING = 10;
	protected final BlockState blockToGrowOn;

	public ProtoSpeleothemBlock(BlockState blockToGrowOn, Properties properties) {
		super(properties);
		this.blockToGrowOn = blockToGrowOn;
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(TIP_DIRECTION, Direction.UP)
				.setValue(THICKNESS, DripstoneThickness.TIP)
				.setValue(WATERLOGGED, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(TIP_DIRECTION, THICKNESS, WATERLOGGED);
	}

	@Override
	protected boolean canSurvive(BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
		return this.isValidSpeleothemPlacement(level, pos, state.getValue(TIP_DIRECTION));
	}

	@Override
	protected @NotNull BlockState updateShape(BlockState state, @NotNull Direction directionToNeighbour, @NotNull BlockState neighborState, @NotNull LevelAccessor level, @NotNull BlockPos pos, @NotNull BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}

		if (directionToNeighbour != Direction.UP && directionToNeighbour != Direction.DOWN) {
			return state;
		} else {
			Direction tipDirection = state.getValue(TIP_DIRECTION);
			if (tipDirection == Direction.DOWN && level.getBlockTicks().hasScheduledTick(pos, this)) {
				return state;
			} else if (directionToNeighbour == tipDirection.getOpposite() && !this.canSurvive(state, level, pos)) {
				if (tipDirection == Direction.DOWN) {
					level.scheduleTick(pos, this, DELAY_BEFORE_FALLING);
				} else {
					level.scheduleTick(pos, this, 1);
				}

				return state;
			} else {
				boolean mergeOpposingTips = state.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
				DripstoneThickness newThickness = this.calculateSpeleothemThickness(level, pos, tipDirection, mergeOpposingTips);
				return state.setValue(THICKNESS, newThickness);
			}
		}
	}

	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
		LevelAccessor level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		Direction defaultTipDirection = context.getNearestLookingVerticalDirection().getOpposite();
		Direction tipDirection = this.calculateTipDirection(level, pos, defaultTipDirection);
		if (tipDirection == null) {
			return null;
		} else {
			boolean mergeOpposingTips = !context.isSecondaryUseActive();
			DripstoneThickness thickness = this.calculateSpeleothemThickness(level, pos, tipDirection, mergeOpposingTips);
			return this.defaultBlockState().setValue(TIP_DIRECTION, tipDirection)
					.setValue(THICKNESS, thickness)
					.setValue(WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER));
		}
	}

	private @Nullable Direction calculateTipDirection(LevelReader level, BlockPos pos, Direction defaultTipDirection) {
		Direction tipDirection;
		if (this.isValidSpeleothemPlacement(level, pos, defaultTipDirection)) {
			tipDirection = defaultTipDirection;
		} else {
			if (!this.isValidSpeleothemPlacement(level, pos, defaultTipDirection.getOpposite())) {
				return null;
			}

			tipDirection = defaultTipDirection.getOpposite();
		}

		return tipDirection;
	}

	private DripstoneThickness calculateSpeleothemThickness(LevelReader level, BlockPos pos, Direction tipDirection, boolean mergeOpposingTips) {
		Direction baseDirection = tipDirection.getOpposite();
		BlockState inFrontState = level.getBlockState(pos.relative(tipDirection));
		if (isSpeleothemWithDirection(inFrontState, baseDirection) && inFrontState.is(this)) {
			return !mergeOpposingTips && inFrontState.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE
					? DripstoneThickness.TIP
					: DripstoneThickness.TIP_MERGE;
		} else if (!isSpeleothemWithDirection(inFrontState, tipDirection)) {
			return DripstoneThickness.TIP;
		} else {
			DripstoneThickness inFrontThickness = inFrontState.getValue(THICKNESS);
			if (inFrontThickness != DripstoneThickness.TIP && inFrontThickness != DripstoneThickness.TIP_MERGE) {
				BlockState behindState = level.getBlockState(pos.relative(baseDirection));
				return !isSpeleothemWithDirection(behindState, tipDirection) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
			} else {
				return DripstoneThickness.FRUSTUM;
			}
		}
	}

	private boolean isValidSpeleothemPlacement(LevelReader level, BlockPos pos, Direction tipDirection) {
		BlockPos behindPos = pos.relative(tipDirection.getOpposite());
		BlockState behindState = level.getBlockState(behindPos);
		return behindState.isFaceSturdy(level, behindPos, tipDirection) || isSpeleothemWithDirection(behindState, tipDirection) && behindState.is(this);
	}

	private static boolean isSpeleothemWithDirection(BlockState blockState, Direction tipDirection) {
		return blockState.is(ModBlockTags.PROTO_SPELEOTHEMS) && blockState.getValue(TIP_DIRECTION) == tipDirection;
	}

	@Override
	protected void onProjectileHit(Level level, @NotNull BlockState state, @NotNull BlockHitResult blockHit, @NotNull Projectile projectile) {
		if (!level.isClientSide) {
			BlockPos blockPos = blockHit.getBlockPos();
			if (level instanceof ServerLevel serverLevel
					&& projectile.mayInteract(serverLevel, blockPos)
					&& projectile.mayBreak(serverLevel)
					&& projectile instanceof ThrownTrident
					&& projectile.getDeltaMovement().length() > MIN_TRIDENT_VELOCITY_TO_BREAK_DRIPSTONE) {
				level.destroyBlock(blockPos, true);
			}
		}

	}

	@Override
	protected void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
		if (isStalagmite(state) && !this.canSurvive(state, level, pos)) {
			level.destroyBlock(pos, true);
		} else {
			spawnFallingStalactite(state, level, pos);
		}
	}

	private static void spawnFallingStalactite(BlockState state, ServerLevel level, BlockPos pos) {
		MutableBlockPos fallPos = pos.mutable();
		BlockState fallState = state;

		while (isStalactite(fallState)) {
			FallingBlockEntity entity = FallingBlockEntity.fall(level, fallPos, fallState);
			if (isTip(fallState, true)) {
				int size = Math.max(1 + pos.getY() - fallPos.getY(), MAX_STALACTITE_HEIGHT_FOR_DAMAGE_CALCULATION);
				float damagePerFallDistance = STALACTITE_DAMAGE_PER_FALL_DISTANCE_AND_SIZE * size;
				entity.setHurtsEntities(damagePerFallDistance, STALACTITE_MAX_DAMAGE);
				break;
			}

			fallPos.move(Direction.DOWN);
			fallState = level.getBlockState(fallPos);
		}
	}

	private static boolean isStalactite(BlockState state) {
		return isSpeleothemWithDirection(state, Direction.DOWN);
	}

	private static boolean isStalagmite(BlockState state) {
		return isSpeleothemWithDirection(state, Direction.UP);
	}

	private static boolean isTip(BlockState state, boolean includeMergedTip) {
		if (!state.is(ModBlockTags.PROTO_SPELEOTHEMS)) {
			return false;
		} else {
			DripstoneThickness thickness = state.getValue(THICKNESS);
			return thickness == DripstoneThickness.TIP || includeMergedTip && thickness == DripstoneThickness.TIP_MERGE;
		}
	}

	@Override
	public void onBrokenAfterFall(@NotNull Level level, @NotNull BlockPos pos, FallingBlockEntity entity) {
		if (!entity.isSilent()) {
			level.playSound(entity, pos, getStalactiteLandingSound(), SoundSource.BLOCKS, 2.0F, level.getRandom().nextFloat() * 0.1F + 0.9F);
		}
	}

	protected abstract SoundEvent getStalactiteLandingSound();

	@Override
	public @NotNull DamageSource getFallDamageSource(Entity entity) {
		return entity.damageSources().fallingStalactite(entity);
	}

	@Override
	protected @NotNull FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	protected @NotNull VoxelShape getShape(BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		VoxelShape shape = switch (state.getValue(THICKNESS)) {
			case TIP_MERGE -> TIP_MERGE_SHAPE;
			case TIP -> state.getValue(TIP_DIRECTION) == Direction.DOWN ? TIP_SHAPE_DOWN : TIP_SHAPE_UP;
			case FRUSTUM -> FRUSTUM_SHAPE;
			case MIDDLE -> MIDDLE_SHAPE;
			case BASE -> BASE_SHAPE;
		};
		Vec3 vec3 = state.getOffset(level, pos);
		return shape.move(vec3.x, vec3.y, vec3.z);
	}

	@Override
	protected boolean isCollisionShapeFullBlock(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos) {
		return false;
	}

	@Override
	protected float getMaxHorizontalOffset() {
		return MAX_HORIZONTAL_OFFSET;
	}

	@Override
	protected boolean isPathfindable(final @NotNull BlockState state, final @NotNull PathComputationType type) {
		return false;
	}

	@Override
	protected void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, RandomSource random) {
		if (random.nextFloat() < GROWTH_PROBABILITY_PER_RANDOM_TICK && isStalactiteStartPos(state, level, pos)) {
			growStalactiteOrStalagmiteIfPossible(state, level, pos, random);
		}
	}

	private static boolean isStalactiteStartPos(BlockState state, LevelReader level, BlockPos pos) {
		return isStalactite(state) && !level.getBlockState(pos.above()).is(Blocks.POINTED_DRIPSTONE);
	}

	@VisibleForTesting
	public void growStalactiteOrStalagmiteIfPossible(BlockState stalactiteStartState, ServerLevel level, BlockPos stalactiteStartPos, RandomSource random) {
		if (this.canGrow(level, stalactiteStartPos)) {
			BlockPos stalactiteTipPos = findTip(stalactiteStartState, level, stalactiteStartPos, this.getMaxGrowthLength(), false);
			if (stalactiteTipPos != null) {
				BlockState stalactiteTipState = level.getBlockState(stalactiteTipPos);
				if (isFreeHangingStalactite(stalactiteTipState) && this.canTipGrow(stalactiteTipState, level, stalactiteTipPos)) {
					if (random.nextBoolean()) {
						this.grow(level, stalactiteTipPos, Direction.DOWN);
					} else {
						this.growStalagmiteBelow(level, stalactiteTipPos);
					}
				}
			}
		}
	}

	protected boolean canGrow(LevelReader level, BlockPos pos) {
		return level.getBlockState(pos.above()).is(this.blockToGrowOn.getBlock());
	}

	protected static @Nullable BlockPos findTip(BlockState speleothemState, LevelAccessor level, BlockPos speleothemPos, int maxSearchLength, boolean includeMergedTip) {
		if (isTip(speleothemState, includeMergedTip)) {
			return speleothemPos;
		} else {
			Direction searchDirection = speleothemState.getValue(TIP_DIRECTION);
			BiPredicate<BlockPos, BlockState> pathPredicate = (pos, state) -> state.is(speleothemState.getBlock())
					&& state.getValue(TIP_DIRECTION) == searchDirection;
			return findBlockVertical(
					level, speleothemPos, searchDirection.getAxisDirection(), pathPredicate, speleothem -> isTip(speleothem, includeMergedTip), maxSearchLength
			)
					.orElse(null);
		}
	}

	protected static Optional<BlockPos> findBlockVertical(LevelAccessor level, BlockPos pos, Direction.AxisDirection axisDirection, BiPredicate<BlockPos, BlockState> pathPredicate, Predicate<BlockState> targetPredicate, int maxSteps) {
		Direction direction = Direction.get(axisDirection, Direction.Axis.Y);
		MutableBlockPos mutablePos = pos.mutable();

		for (int i = 1; i < maxSteps; i++) {
			mutablePos.move(direction);
			BlockState state = level.getBlockState(mutablePos);
			if (targetPredicate.test(state)) {
				return Optional.of(mutablePos.immutable());
			}

			if (level.isOutsideBuildHeight(mutablePos.getY()) || !pathPredicate.test(mutablePos, state)) {
				return Optional.empty();
			}
		}

		return Optional.empty();
	}

	private boolean canTipGrow(BlockState tipState, ServerLevel level, BlockPos tipPos) {
		Direction growDirection = tipState.getValue(TIP_DIRECTION);
		BlockPos growPos = tipPos.relative(growDirection);
		BlockState stateAtGrowPos = level.getBlockState(growPos);
		if (!stateAtGrowPos.getFluidState().isEmpty()) {
			return false;
		} else {
			return stateAtGrowPos.isAir() || isUnmergedTipWithDirection(stateAtGrowPos, growDirection.getOpposite());
		}
	}

	private boolean isUnmergedTipWithDirection(BlockState state, Direction tipDirection) {
		return isTip(state, false) && state.getValue(TIP_DIRECTION) == tipDirection && state.is(this);
	}

	private void grow(ServerLevel level, BlockPos growFromPos, Direction growToDirection) {
		BlockPos targetPos = growFromPos.relative(growToDirection);
		BlockState existingStateAtTargetPos = level.getBlockState(targetPos);
		if (this.isUnmergedTipWithDirection(existingStateAtTargetPos, growToDirection.getOpposite())) {
			this.createMergedTips(existingStateAtTargetPos, level, targetPos);
		} else if (existingStateAtTargetPos.isAir() || existingStateAtTargetPos.is(Blocks.WATER)) {
			this.createSpeleothem(level, targetPos, growToDirection, DripstoneThickness.TIP);
		}
	}

	private void createSpeleothem(LevelAccessor level, BlockPos pos, Direction direction, DripstoneThickness thickness) {
		BlockState state = this.defaultBlockState().setValue(TIP_DIRECTION, direction).setValue(THICKNESS, thickness)
				.setValue(WATERLOGGED, level.getFluidState(pos).is(Fluids.WATER));
		level.setBlock(pos, state, 3);
	}

	private void createMergedTips(BlockState tipState, LevelAccessor level, BlockPos tipPos) {
		BlockPos stalactitePos;
		BlockPos stalagmitePos;
		if (tipState.getValue(TIP_DIRECTION) == Direction.UP) {
			stalagmitePos = tipPos;
			stalactitePos = tipPos.above();
		} else {
			stalactitePos = tipPos;
			stalagmitePos = tipPos.below();
		}

		this.createSpeleothem(level, stalactitePos, Direction.DOWN, DripstoneThickness.TIP_MERGE);
		this.createSpeleothem(level, stalagmitePos, Direction.UP, DripstoneThickness.TIP_MERGE);
	}

	private void growStalagmiteBelow(ServerLevel level, BlockPos posAboveStalagmite) {
		MutableBlockPos pos = posAboveStalagmite.mutable();

		for (int i = 0; i < MAX_STALAGMITE_SEARCH_RANGE_WHEN_GROWING; i++) {
			pos.move(Direction.DOWN);
			BlockState state = level.getBlockState(pos);
			if (!state.getFluidState().isEmpty()) {
				return;
			}

			if (this.isUnmergedTipWithDirection(state, Direction.UP) && this.canTipGrow(state, level, pos)) {
				this.grow(level, pos, Direction.UP);
				return;
			}

			if (this.isValidSpeleothemPlacement(level, pos, Direction.UP) && !level.isWaterAt(pos.below())) {
				this.grow(level, pos.below(), Direction.UP);
				return;
			}
		}
	}

	protected static boolean isFreeHangingStalactite(BlockState state) {
		return isStalactite(state) && state.getValue(THICKNESS) == DripstoneThickness.TIP && !(Boolean)state.getValue(WATERLOGGED);
	}

	protected int getMaxGrowthLength() {
		return MAX_GROWTH_LENGTH;
	}
}
