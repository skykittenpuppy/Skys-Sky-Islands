package gay.beegirl.skyislands.data.worldgen.features;

import gay.beegirl.skyislands.world.level.block.ModBlocks;
import gay.beegirl.skyislands.world.level.levelgen.feature.treedecorators.CactusFruitDecorator;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;

import java.util.List;

public class ModTreeFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENLEAF =				ModFeatureUtils.createKey("goldenleaf");
	//public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENLEAF_BEES_005 =	ModFeatureUtils.createKey("goldenleaf_bees_005");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA =					ModFeatureUtils.createKey("sakura");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_BEES_005 =			ModFeatureUtils.createKey("sakura_bees_005");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGID_PINE =				ModFeatureUtils.createKey("frigid_pine");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGID_SPRUCE =			ModFeatureUtils.createKey("frigid_spruce");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGID_PINE_BEES_005 =		ModFeatureUtils.createKey("frigid_pine_bees_005");
	public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGID_SPRUCE_BEES_005 =	ModFeatureUtils.createKey("frigid_spruce_bees_005");
	public static final ResourceKey<ConfiguredFeature<?, ?>> ARBOREAL_CACTUS =			ModFeatureUtils.createKey("arboreal_cactus");

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		FeatureUtils.register(context, GOLDENLEAF, Feature.TREE, goldenleaf().build());
		//FeatureUtils.register(context, GOLDENLEAF_BEES_005, Feature.TREE, goldenleaf().decorators(List.of(new BeehiveDecorator(0.05F))).build());
		FeatureUtils.register(context, SAKURA, Feature.TREE, sakura().build());
		FeatureUtils.register(context, SAKURA_BEES_005, Feature.TREE, sakura().decorators(List.of(new BeehiveDecorator(0.05F))).build());
		FeatureUtils.register(context, FRIGID_SPRUCE, Feature.TREE, frigidSpruce().build());
		FeatureUtils.register(context, FRIGID_SPRUCE_BEES_005, Feature.TREE, frigidSpruce().decorators(List.of(new BeehiveDecorator(0.05F))).build());
		FeatureUtils.register(context, FRIGID_PINE, Feature.TREE, frigidPine().build());
		FeatureUtils.register(context, FRIGID_PINE_BEES_005, Feature.TREE, frigidPine().decorators(List.of(new BeehiveDecorator(0.05F))).build());
		FeatureUtils.register(context, ARBOREAL_CACTUS, Feature.TREE, arborealCactus().build());
	}

	private static TreeConfiguration.TreeConfigurationBuilder goldenleaf() { //TODO: tweak TreeConfiguration
		// new TreeConfiguration.TreeConfigurationBuilder(
		//		BlockStateProvider.simple(Blocks.DARK_OAK_LOG),
		//		new DarkOakTrunkPlacer(6, 2, 1),
		//		BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES),
		//		new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
		//		new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()).ignoreVines()

		// new TreeConfiguration.TreeConfigurationBuilder(
		//		BlockStateProvider.simple(Blocks.OAK_LOG),
		//		new FancyTrunkPlacer(3, 11, 0),
		//		BlockStateProvider.simple(Blocks.OAK_LEAVES),
		//		new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
		//		new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines();


		//new TreeConfiguration.TreeConfigurationBuilder(
		//		BlockStateProvider.simple(ModBlocks.SAKURA_LOGS.log().get()),
		// NO	new CherryTrunkPlacer(7, 1, 0, new WeightedListInt(new SimpleWeightedRandomList.Builder<IntProvider>().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
		//		BlockStateProvider.simple(ModBlocks.SAKURA_LEAVES.get()),
		//		new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.166666666F, 0.333333333F),
		//		new TwoLayersFeatureSize(1, 0, 2)).ignoreVines();

		//new TreeConfiguration.TreeConfigurationBuilder(
		// 		BlockStateProvider.simple(Blocks.MANGROVE_LOG),
		// 		new UpwardsBranchingTrunkPlacer(2, 1, 4, UniformInt.of(1, 4), 0.5F, UniformInt.of(0, 1), holdergetter.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
		// 		BlockStateProvider.simple(Blocks.MANGROVE_LEAVES),
		// 		new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 70),
		// 		new TwoLayersFeatureSize(2, 0, 2))

		return TreeFeatures.createStraightBlobTree(ModBlocks.GOLDENLEAF_LOGS.log().get(), ModBlocks.GOLDENLEAF_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
	}

	private static TreeConfiguration.TreeConfigurationBuilder sakura() { //TODO: tweak TreeConfiguration
		return new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.SAKURA_LOGS.log().get()),
				new CherryTrunkPlacer(7, 1, 0, new WeightedListInt(new SimpleWeightedRandomList.Builder<IntProvider>().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
				BlockStateProvider.simple(ModBlocks.SAKURA_LEAVES.get()),
				new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.166666666F, 0.333333333F),
				new TwoLayersFeatureSize(1, 0, 2)).ignoreVines();
	}

	private static TreeConfiguration.TreeConfigurationBuilder frigidSpruce() { //TODO: tweak TreeConfiguration
		return new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.FRIGID_LOGS.log().get()),
				new StraightTrunkPlacer(5, 2, 1),
				BlockStateProvider.simple(ModBlocks.FRIGID_LEAVES.get()),
				new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)),
				new TwoLayersFeatureSize(2, 0, 2)).ignoreVines();
	}
	private static TreeConfiguration.TreeConfigurationBuilder frigidPine() { //TODO: tweak TreeConfiguration
		return new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.FRIGID_LOGS.log().get()),
				new StraightTrunkPlacer(6, 4, 0),
				BlockStateProvider.simple(ModBlocks.FRIGID_LEAVES.get()),
				new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)),
				new TwoLayersFeatureSize(2, 0, 2)).ignoreVines();
	}

	private static TreeConfiguration.TreeConfigurationBuilder arborealCactus() { //TODO: tweak TreeConfiguration
		return new TreeConfiguration.TreeConfigurationBuilder(
				BlockStateProvider.simple(ModBlocks.ARBOREAL_CACTUSES.cactus().get()),
				new StraightTrunkPlacer(3, 1, 1),
				BlockStateProvider.simple(Blocks.STONE),
				new BlobFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
				new TwoLayersFeatureSize(2, 0, 2))
				.dirt(BlockStateProvider.simple(Blocks.SAND))
				.ignoreVines();
				//.decorators(List.of(new CactusFruitDecorator(0.05F)));
	}
}
