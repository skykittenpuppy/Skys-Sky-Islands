package gay.beegirl.skyislands.world.level.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class IslandsCactusPlantBlock extends Block implements BonemealableBlock {
    public static final MapCodec<IslandsCactusPlantBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            TreeGrower.CODEC.fieldOf("tree").forGetter((islandsCactusPlantBlock) -> islandsCactusPlantBlock.treeGrower),
            BlockBehaviour.propertiesCodec()).apply(instance, IslandsCactusPlantBlock::new));
    public static final IntegerProperty STAGE;
    protected static final VoxelShape SHAPE;
    protected final TreeGrower treeGrower;

    public MapCodec<IslandsCactusPlantBlock> codec() {
        return CODEC;
    }

    public IslandsCactusPlantBlock(TreeGrower treeGrower, Properties properties) {
	    super(properties);
		this.treeGrower = treeGrower;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, 0));
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isAreaLoaded(pos, 1)) {
            if (level.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
                this.advanceTree(level, pos, state, random);
            }

        }
    }

    public void advanceTree(ServerLevel level, BlockPos pos, BlockState state, RandomSource random) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
        } else {
            this.treeGrower.growTree(level, level.getChunkSource().getGenerator(), pos, state, random);
        }

    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return (double)level.random.nextFloat() < 0.45;
    }

    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        this.advanceTree(level, pos, state, random);
    }

    // TODO: Decide cactus base blocks
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return true;
    }
    
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        entity.hurt(level.damageSources().cactus(), 1.0F);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STAGE);
    }

    static {
        STAGE = BlockStateProperties.STAGE;
        SHAPE = Block.box(4.0F, 0.0F, 4.0F, 12.0F, 12.0F, 12.0F);
    }
}
