package gay.beegirl.skyislands.data.worldgen.features;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;

import java.util.List;

public class ModTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENLEAF =           ModFeatureUtils.createKey("goldenleaf");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENLEAF_BEES_005 =  ModFeatureUtils.createKey("goldenleaf_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA =               ModFeatureUtils.createKey("sakura");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_BEES_005 =      ModFeatureUtils.createKey("sakura_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGID =               ModFeatureUtils.createKey("frigid");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGID_BEES_005 =      ModFeatureUtils.createKey("frigid_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ARBOREAL_CACTUS =      ModFeatureUtils.createKey("arboreal_cactus");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, GOLDENLEAF, Feature.TREE, createGoldenleaf().build());
        FeatureUtils.register(context, GOLDENLEAF_BEES_005, Feature.TREE, createGoldenleaf().decorators(List.of(new BeehiveDecorator(0.05F))).build());
        FeatureUtils.register(context, SAKURA, Feature.TREE, createSakura().build());
        FeatureUtils.register(context, SAKURA_BEES_005, Feature.TREE, createSakura().decorators(List.of(new BeehiveDecorator(0.05F))).build());
        FeatureUtils.register(context, FRIGID, Feature.TREE, createFrigid().build());
        FeatureUtils.register(context, FRIGID_BEES_005, Feature.TREE, createFrigid().decorators(List.of(new BeehiveDecorator(0.05F))).build());
        FeatureUtils.register(context, ARBOREAL_CACTUS, Feature.BLOCK_COLUMN, createArborealCactus());
    }

    private static TreeConfiguration.TreeConfigurationBuilder createGoldenleaf() {
        return TreeFeatures.createStraightBlobTree(ModBlocks.GOLDENLEAF_LOGS.log().get(), ModBlocks.GOLDENLEAF_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    } //TODO: tweak TreeConfiguration

    private static TreeConfiguration.TreeConfigurationBuilder createSakura() {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SAKURA_LOGS.log().get()),
                new CherryTrunkPlacer(7, 1, 0, ConstantInt.of(1), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
                //new CherryTrunkPlacer(7, 1, 0, new WeightedListInt(WeightedList.builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
                //TODO: re-insert this?
                BlockStateProvider.simple(ModBlocks.SAKURA_LEAVES.get()),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2))).ignoreVines();
    } //TODO: tweak TreeConfiguration

    private static TreeConfiguration.TreeConfigurationBuilder createFrigid() {
        return TreeFeatures.createStraightBlobTree(ModBlocks.FRIGID_LOGS.log().get(), ModBlocks.FRIGID_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    } //TODO: tweak TreeConfiguration

    private static BlockColumnConfiguration createArborealCactus() {
        return new BlockColumnConfiguration(
                List.of(BlockColumnConfiguration.layer(BiasedToBottomInt.of(1, 5), BlockStateProvider.simple(ModBlocks.ARBOREAL_CACTUS.get()))),
                Direction.UP,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                false);
    } //TODO: tweak BlockColumnConfiguration
}
