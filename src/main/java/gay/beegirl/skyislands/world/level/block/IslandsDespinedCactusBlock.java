package gay.beegirl.skyislands.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class IslandsDespinedCactusBlock extends RotatedPillarBlock {
    public static final MapCodec<IslandsDespinedCactusBlock> CODEC = simpleCodec(IslandsDespinedCactusBlock::new);
    protected static final VoxelShape COLLISION_SHAPE_X;
    protected static final VoxelShape COLLISION_SHAPE_Y;
    protected static final VoxelShape COLLISION_SHAPE_Z;
    protected static final VoxelShape OUTLINE_SHAPE_X;
    protected static final VoxelShape OUTLINE_SHAPE_Y;
    protected static final VoxelShape OUTLINE_SHAPE_Z;

    public MapCodec<IslandsDespinedCactusBlock> codec() {
        return CODEC;
    }

    public IslandsDespinedCactusBlock(Properties properties) {
        super(properties);
    }

    protected VoxelShape getCollisionShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        switch (blockState.getValue(AXIS)) {
            default:
            case Y:
                return COLLISION_SHAPE_Y;
            case Z:
                return COLLISION_SHAPE_Z;
            case X:
                return COLLISION_SHAPE_X;
        }
    }

    protected VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        switch (blockState.getValue(AXIS)) {
            default:
            case Y:
                return OUTLINE_SHAPE_Y;
            case Z:
                return OUTLINE_SHAPE_Z;
            case X:
                return OUTLINE_SHAPE_X;
        }
    }

    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    static {
        COLLISION_SHAPE_X = Block.box(0.0F, 1.0F, 1.0F, 16.0F, 14.0F, 15.0F);
        COLLISION_SHAPE_Y = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 15.0F, 15.0F);
        COLLISION_SHAPE_Z = Block.box(1.0F, 1.0F, 0.0F, 15.0F, 14.0F, 16.0F);
        OUTLINE_SHAPE_X = Block.box(0.0F, 1.0F, 1.0F, 16.0F, 15.0F, 15.0F);
        OUTLINE_SHAPE_Y = Block.box(1.0F, 0.0F, 1.0F, 15.0F, 16.0F, 15.0F);
        OUTLINE_SHAPE_Z = Block.box(1.0F, 1.0F, 0.0F, 15.0F, 15.0F, 16.0F);
    }
}
