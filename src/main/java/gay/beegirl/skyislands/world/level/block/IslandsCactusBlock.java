package gay.beegirl.skyislands.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IslandsCactusBlock extends Block {
    public static final MapCodec<IslandsCactusBlock> CODEC = simpleCodec(IslandsCactusBlock::new);
    public static final IntegerProperty AGE;
    public static final int MAX_AGE = 15;
    protected static final int MAX_CACTUS_GROWING_HEIGHT = 5;
    protected static final int ATTEMPT_GROW_CACTUS_FRUIT_AGE = 8;
    protected static final double ATTEMPT_GROW_CACTUS_FRUIT_SMALL_CACTUS_CHANCE = 0.1F;
    protected static final double ATTEMPT_GROW_CACTUS_FRUIT_TALL_CACTUS_CHANCE = 0.25F;
    protected static final VoxelShape COLLISION_SHAPE;
    protected static final VoxelShape OUTLINE_SHAPE;

    public MapCodec<IslandsCactusBlock> codec() {
        return CODEC;
    }

    public IslandsCactusBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isAreaLoaded(pos, 1)) {
            if (!state.canSurvive(level, pos)) {
                level.destroyBlock(pos, true);
            }

        }
    }

    // TODO:
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
            /*for(Direction direction : Direction.Plane.HORIZONTAL) {
                CactusFruitBlock fruit = (CactusFruitBlock) ModBlocks.ARBOREAL_CACTUS_FRUIT;
                if (j >= ATTEMPT_GROW_CACTUS_FRUIT_AGE && fruit.canSurvive(fruit.defaultBlockState(), serverLevel, blockPos.relative(direction))) {
                    double d = i >= MAX_CACTUS_GROWING_HEIGHT ? ATTEMPT_GROW_CACTUS_FRUIT_TALL_CACTUS_CHANCE : ATTEMPT_GROW_CACTUS_FRUIT_SMALL_CACTUS_CHANCE;
                    if (randomSource.nextDouble() <= d) {
                        serverLevel.setBlockAndUpdate(blockPos.relative(direction), fruit.defaultBlockState().setValue(CactusFruitBlock.HANGING, true).setValue(CactusFruitBlock.FACING, direction.getOpposite()));
                        grewFruit = true;
                        break;
                    }
                }
            }*/
            if (!grewFruit && j == MAX_AGE && i < MAX_CACTUS_GROWING_HEIGHT) {
                serverLevel.setBlockAndUpdate(blockPos2, this.defaultBlockState());
                BlockState blockState2 = blockState.setValue(AGE, 0);
                serverLevel.setBlock(blockPos, blockState2, 260);
                //serverLevel.neighborChanged(blockState2, blockPos2, this, null, false);
            }

            if (j < MAX_AGE) {
                serverLevel.setBlock(blockPos, blockState.setValue(AGE, j + 1), 260);
            }

        }
    }

    protected VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return COLLISION_SHAPE;
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return OUTLINE_SHAPE;
    }

    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos)) {
            level.scheduleTick(currentPos, this, 1);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    // TODO:
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }

    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.hurt(level.damageSources().cactus(), 1.0F);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    static {
        AGE = BlockStateProperties.AGE_15;
        COLLISION_SHAPE = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 15.0F, 15.0F);
        OUTLINE_SHAPE = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 16.0F, 15.0F);
    }
}
