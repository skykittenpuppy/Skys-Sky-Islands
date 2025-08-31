package gay.beegirl.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class CactusLogBlock extends RotatedPillarBlock {
    public static final IntegerProperty AGE;
    public static final EnumProperty<Direction.Axis> AXIS;

    private static final VoxelShape SHAPE_COLLISION;
    private static final VoxelShape SHAPE;

    public static final int MAX_AGE = 15;
    private static final int MAX_CACTUS_GROWING_HEIGHT = 5;
    private static final int ATTEMPT_GROW_CACTUS_FRUIT_AGE = 8;
    private static final double ATTEMPT_GROW_CACTUS_FRUIT_SMALL_CACTUS_CHANCE = 0.1F;
    private static final double ATTEMPT_GROW_CACTUS_FRUIT_TALL_CACTUS_CHANCE = 0.25F;

    public CactusLogBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((this.stateDefinition.any()).setValue(AGE, 0));
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y));
    }

    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        BlockPos blockPos2 = blockPos.above();
        if (serverLevel.isEmptyBlock(blockPos2)) {
            int i = 1;
            int j = blockState.getValue(AGE);

            while(serverLevel.getBlockState(blockPos.below(i)).is(this)) {
                ++i;
                if (i == MAX_CACTUS_GROWING_HEIGHT && j == MAX_AGE) {
                    return;
                }
            }

            boolean grewFruit = false;
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                CactusFruitBlock fruit = (CactusFruitBlock) ModBlocks.ARBOREAL_CACTUS_FRUIT;
                if (j >= ATTEMPT_GROW_CACTUS_FRUIT_AGE && fruit.canSurvive(fruit.defaultBlockState(), serverLevel, blockPos.relative(direction))) {
                    double d = i >= MAX_CACTUS_GROWING_HEIGHT ? ATTEMPT_GROW_CACTUS_FRUIT_TALL_CACTUS_CHANCE : ATTEMPT_GROW_CACTUS_FRUIT_SMALL_CACTUS_CHANCE;
                    if (randomSource.nextDouble() <= d) {
                        serverLevel.setBlockAndUpdate(blockPos.relative(direction), fruit.defaultBlockState().setValue(CactusFruitBlock.HANGING, true).setValue(CactusFruitBlock.FACING, direction.getOpposite()));
                        grewFruit = true;
                        break;
                    }
                }
            }
            if (!grewFruit && j == MAX_AGE && i < MAX_CACTUS_GROWING_HEIGHT) {
                serverLevel.setBlockAndUpdate(blockPos2, this.defaultBlockState());
                BlockState blockState2 = blockState.setValue(AGE, 0);
                serverLevel.setBlock(blockPos, blockState2, 260);
                serverLevel.neighborChanged(blockState2, blockPos2, this, null, false);
            }

            if (j < MAX_AGE) {
                serverLevel.setBlock(blockPos, blockState.setValue(AGE, j + 1), 260);
            }

        }
    }

    protected @NotNull VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE_COLLISION;
    }

    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier) {
        if (level instanceof ServerLevel serverLevel) {
            entity.hurtServer(serverLevel, serverLevel.damageSources().cactus(), 1.0F);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
        return false;
    }

    static {
        AGE = BlockStateProperties.AGE_15;
        AXIS = BlockStateProperties.AXIS;
        SHAPE_COLLISION = Block.column(14.0F, 0.0F, 15.0F);
        SHAPE = Block.column(14.0F, 0.0F, 16.0F);
    }
}
