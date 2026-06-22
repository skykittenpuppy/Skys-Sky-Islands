package gay.beegirl.skyislands.world.level.block;

import com.mojang.serialization.MapCodec;
import gay.beegirl.skyislands.tags.ModBlockTags;
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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.util.TriState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class IslandsCactusFruitBlock extends HorizontalDirectionalBlock implements BonemealableBlock {
    public static final MapCodec<IslandsCactusFruitBlock> CODEC = simpleCodec(IslandsCactusFruitBlock::new);
    public static final int MAX_AGE = 2;
    public static final IntegerProperty AGE;
    private static final VoxelShape EAST_BUDDING_SHAPE;
    private static final VoxelShape WEST_BUDDING_SHAPE;
    private static final VoxelShape NORTH_BUDDING_SHAPE;
    private static final VoxelShape SOUTH_BUDDING_SHAPE;
    private static final VoxelShape EAST_HANGING_SHAPE;
    private static final VoxelShape WEST_HANGING_SHAPE;
    private static final VoxelShape NORTH_HANGING_SHAPE;
    private static final VoxelShape SOUTH_HANGING_SHAPE;

    @Override
    protected @NotNull MapCodec<IslandsCactusFruitBlock> codec() { return CODEC; }

    public IslandsCactusFruitBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(AGE, 0));
    }

    protected boolean isRandomlyTicking(BlockState state) {
        return (Integer)state.getValue(AGE) < MAX_AGE;
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int i = state.getValue(AGE);
        if (i < 2 && CommonHooks.canCropGrow(level, pos, state, level.random.nextInt(5) == 0)) {
            level.setBlock(pos, state.setValue(AGE, i + 1), 2);
            CommonHooks.fireCropGrowPost(level, pos, state);
        }

    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockstate = level.getBlockState(pos.relative(state.getValue(FACING)));
        TriState soilDecision = blockstate.canSustainPlant(level, pos.relative(state.getValue(FACING)), state.getValue(FACING).getOpposite(), state);
        return !soilDecision.isDefault() ? soilDecision.isTrue() : blockstate.is(ModBlockTags.ARBOREAL_CACTUSES);
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SOUTH_BUDDING_SHAPE;
        //int i = (Integer)state.getValue(AGE);
        //switch ((Direction)state.getValue(FACING)) {
        //    case SOUTH:
        //        //return SOUTH_AABB[i];
        //    case NORTH:
        //    default:
        //        //return NORTH_AABB[i];
        //    case WEST:
        //        //return WEST_AABB[i];
        //    case EAST:
        //        //return EAST_AABB[i];
        //}
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = this.defaultBlockState();
        LevelReader levelreader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();

        for(Direction direction : context.getNearestLookingDirections()) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.setValue(FACING, direction);
                if (blockstate.canSurvive(levelreader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }

    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return facing == state.getValue(FACING) && !state.canSurvive(level, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return state.getValue(AGE) < MAX_AGE;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        level.setBlock(pos, state.setValue(AGE, state.getValue(AGE) + 1), 2);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }

    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    static {
        AGE = BlockStateProperties.AGE_2;
        EAST_BUDDING_SHAPE = Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F);
        WEST_BUDDING_SHAPE = Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F);
        NORTH_BUDDING_SHAPE = Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F);
        SOUTH_BUDDING_SHAPE = Block.box(6.0F, 6.0F, -2.0F, 10.0F, 10.0F, 4.0F);
        EAST_HANGING_SHAPE = Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F);
        WEST_HANGING_SHAPE = Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F);
        NORTH_HANGING_SHAPE = Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F);
        SOUTH_HANGING_SHAPE = Block.box(5.0F, 5.0F, -2.0F, 11.0F, 11.0F, 6.0F);
    }
}
