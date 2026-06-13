package gay.beegirl.skyislands.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

public class CactusFruitBlock extends HorizontalDirectionalBlock implements BonemealableBlock {
    public static final MapCodec<CactusFruitBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            TreeGrower.CODEC.fieldOf("tree").forGetter((block) -> block.treeGrower),
            propertiesCodec()).apply(instance, CactusFruitBlock::new));
    private static final VoxelShape EAST_BUDDING_SHAPE;
    private static final VoxelShape WEST_BUDDING_SHAPE;
    private static final VoxelShape NORTH_BUDDING_SHAPE;
    private static final VoxelShape SOUTH_BUDDING_SHAPE;
    private static final VoxelShape EAST_HANGING_SHAPE;
    private static final VoxelShape WEST_HANGING_SHAPE;
    private static final VoxelShape NORTH_HANGING_SHAPE;
    private static final VoxelShape SOUTH_HANGING_SHAPE;
    private static final VoxelShape SHAPE;
    public static final BooleanProperty HANGING;
    public static final IntegerProperty STAGE;
    protected final TreeGrower treeGrower;

    @Override
    protected MapCodec<CactusFruitBlock> codec() { return CODEC; }

    public CactusFruitBlock(TreeGrower treeGrower, Properties properties) {
        super(properties);
        this.treeGrower = treeGrower;
        this.registerDefaultState((((this.stateDefinition.any())
                .setValue(FACING, Direction.NORTH))
                .setValue(HANGING, false))
                .setValue(STAGE, 0));
    }

    protected boolean isRandomlyTicking(BlockState blockState) {
        return !blockState.getValue(HANGING) || blockState.getValue(STAGE) < 1;
    }

    protected void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        if (serverLevel.random.nextInt(7) == 0) {
            performBonemeal(serverLevel, randomSource, blockPos, blockState);
        }
    }

    public boolean canSurvive(BlockState blockState, LevelReader levelReader, BlockPos blockPos) {
        if (blockState.getValue(HANGING)) {
            return true;
            //BlockState blockState2 = levelReader.getBlockState(blockPos.relative(blockState.getValue(FACING)));
            //return blockState2.is(ModTags.Blocks.ARBOREAL_CACTUS_STEMS);
        } else {
            BlockState blockState2 = levelReader.getBlockState(blockPos.below());
            return blockState2.is(BlockTags.DIRT) || blockState.is(Blocks.FARMLAND) || blockState.is(BlockTags.SAND);
        }
    }

    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (!blockState.getValue(HANGING)) {
            return SHAPE;
        } else if (blockState.getValue(STAGE) < 1) {
            return switch (blockState.getValue(FACING)) {
                default -> NORTH_BUDDING_SHAPE;
                case EAST -> EAST_BUDDING_SHAPE;
                case SOUTH -> SOUTH_BUDDING_SHAPE;
                case WEST -> WEST_BUDDING_SHAPE;
            };
        } else {
            return switch (blockState.getValue(FACING)) {
                default -> NORTH_HANGING_SHAPE;
                case EAST -> EAST_HANGING_SHAPE;
                case SOUTH -> SOUTH_HANGING_SHAPE;
                case WEST -> WEST_HANGING_SHAPE;
            };
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        BlockState blockState = this.defaultBlockState();
        LevelReader levelReader = blockPlaceContext.getLevel();
        BlockPos blockPos = blockPlaceContext.getClickedPos();

        for(Direction direction : blockPlaceContext.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockState = blockState.setValue(FACING, direction);
                if (blockState.canSurvive(levelReader, blockPos)) {
                    return blockState;
                }
            }
        }

        return null;
    }

    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing == state.getValue(FACING) && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return !blockState.getValue(HANGING) || blockState.getValue(STAGE) < 1;
    }

    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        if (blockState.getValue(STAGE) == 0) {
            serverLevel.setBlock(blockPos, blockState.cycle(STAGE), 260);
        } else if (!blockState.getValue(HANGING)) {
            this.treeGrower.growTree(serverLevel, serverLevel.getChunkSource().getGenerator(), blockPos, blockState, randomSource);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, STAGE, HANGING);
    }

    protected boolean isPathfindable(BlockState blockState, PathComputationType pathComputationType) {
        return false;
    }

    static {
        EAST_BUDDING_SHAPE = Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F);
        WEST_BUDDING_SHAPE = Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F);
        NORTH_BUDDING_SHAPE = Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F);
        SOUTH_BUDDING_SHAPE = Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F);
        EAST_HANGING_SHAPE = Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F);
        WEST_HANGING_SHAPE = Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F);
        NORTH_HANGING_SHAPE = Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F);
        SOUTH_HANGING_SHAPE = Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F);
        SHAPE = Block.box(4.0F, 0.0F, 4.0F, 12.0F, 10.0F, 12.0F);
        HANGING = BlockStateProperties.HANGING;
        STAGE = BlockStateProperties.STAGE;
    }
}
