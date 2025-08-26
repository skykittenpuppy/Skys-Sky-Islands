package gay.beegirl.block;

import com.mojang.serialization.MapCodec;
import gay.beegirl.util.ModTag;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
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
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ModCactusFruitBlock extends HorizontalDirectionalBlock implements BonemealableBlock {
    public static final MapCodec<CocoaBlock> CODEC = simpleCodec(CocoaBlock::new);
    private static final Map<Direction, VoxelShape> BUDDING_SHAPE;
    private static final Map<Direction, VoxelShape> HANGING_SHAPE;
    private static final VoxelShape SHAPE;
    public static final BooleanProperty HANGING;
    public static final IntegerProperty STAGE;
    protected final TreeGrower treeGrower;

    public @NotNull MapCodec<CocoaBlock> codec() {
        return CODEC;
    }

    public ModCactusFruitBlock(TreeGrower treeGrower, Properties properties) {
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
            BlockState blockState2 = levelReader.getBlockState(blockPos.relative(blockState.getValue(FACING)));
            return blockState2.is(ModTag.Blocks.ARBOREAL_CACTUS_STEMS);
        } else {
            BlockState blockState2 = levelReader.getBlockState(blockPos.below());
            return blockState2.is(BlockTags.DIRT) || blockState.is(Blocks.FARMLAND) || blockState.is(BlockTags.SAND);
        }
    }

    protected @NotNull VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        if (!blockState.getValue(HANGING)) {
            return SHAPE;
        } else if (blockState.getValue(STAGE) < 1) {
            return BUDDING_SHAPE.get(blockState.getValue(FACING));
        } else {
            return HANGING_SHAPE.get(blockState.getValue(FACING));
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

    protected @NotNull BlockState updateShape(BlockState blockState, LevelReader levelReader, ScheduledTickAccess scheduledTickAccess, BlockPos blockPos, Direction direction, BlockPos blockPos2, BlockState blockState2, RandomSource randomSource) {
        return direction == blockState.getValue(FACING) && !blockState.canSurvive(levelReader, blockPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(blockState, levelReader, scheduledTickAccess, blockPos, direction, blockPos2, blockState2, randomSource);
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
        BUDDING_SHAPE = Shapes.rotateHorizontal(Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F));
        HANGING_SHAPE = Shapes.rotateHorizontal(Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F));
        SHAPE = Block.box(4.0F, 0.0F, 4.0F, 12.0F, 10.0F, 12.0F);
        HANGING = BlockStateProperties.HANGING;
        STAGE = BlockStateProperties.STAGE;
    }
}