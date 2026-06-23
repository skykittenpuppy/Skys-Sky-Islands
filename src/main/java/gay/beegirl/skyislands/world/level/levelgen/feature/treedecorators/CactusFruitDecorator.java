package gay.beegirl.skyislands.world.level.levelgen.feature.treedecorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import gay.beegirl.skyislands.world.level.block.IslandsCactusFruitBlock;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CactusFruitDecorator /*extends TreeDecorator*/ {
	/*public static final MapCodec<CactusFruitDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(CactusFruitDecorator::new, (cactusFruitDecorator) -> cactusFruitDecorator.probability);
	private final float probability;

	public CactusFruitDecorator(float probability) {
		this.probability = probability;
	}

	protected @NotNull TreeDecoratorType<?> type() {
		return ModTreeDecoratorType.CACTUS_FRUIT;
	}

	public void place(TreeDecorator.Context context) {
		RandomSource randomsource = context.random();
		if (!(randomsource.nextFloat() >= this.probability)) {
			List<BlockPos> list = context.logs();
			int i = list.getFirst().getY();
			list.stream().filter((blockPos) -> blockPos.getY() - i <= 2).forEach((blockPos) -> {
				for(Direction direction : Direction.Plane.HORIZONTAL) {
					if (randomsource.nextFloat() <= 0.25F) {
						Direction direction1 = direction.getOpposite();
						BlockPos blockPos1 = blockPos.offset(direction1.getStepX(), 0, direction1.getStepZ());
						if (context.isAir(blockPos1)) {
							context.setBlock(blockPos1, ModBlocks.ARBOREAL_CACTUS_FRUIT.get().defaultBlockState().setValue(IslandsCactusFruitBlock.AGE, randomsource.nextInt(3)).setValue(IslandsCactusFruitBlock.FACING, direction));
						}
					}
				}

			});
		}

	}*/
}
