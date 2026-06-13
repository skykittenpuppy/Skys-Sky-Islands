package gay.beegirl.skyislands.worldgen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.util.ModTags;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.BiasedToBottomInt;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAND_ALEXANDRITE_ORE = registryKey("land_alexandrite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SKY_ALEXANDRITE_ORE = registryKey("sky_alexandrite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENLEAF = registryKey("goldenleaf");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENLEAF_BEES_005 = registryKey("goldenleaf_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA = registryKey("sakura");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_BEES_005 = registryKey("sakura_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGID = registryKey("frigid");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FRIGID_BEES_005 = registryKey("frigid_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ARBOREAL_CACTUS = registryKey("arboreal_cactus");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        SkysSkyIslands.LOGGER.info("Configured Feature Bootstrap for " + SkysSkyIslands.MOD_ID);

        List<OreConfiguration.TargetBlockState> landAlexandriteOres = List.of(
                OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.STONE_ALEXANDRITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get().defaultBlockState())
        );
        FeatureUtils.register(context, LAND_ALEXANDRITE_ORE, Feature.ORE, new OreConfiguration(landAlexandriteOres, 4));
        List<OreConfiguration.TargetBlockState> skyAlexandriteOres = List.of(
                OreConfiguration.target(new TagMatchTest(ModTags.Blocks.CLOUDSHALE_ORE_REPLACEABLE), ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE.get().defaultBlockState())
        );
        FeatureUtils.register(context, SKY_ALEXANDRITE_ORE, Feature.ORE, new OreConfiguration(skyAlexandriteOres, 12));

        FeatureUtils.register(context, GOLDENLEAF, Feature.TREE, createGoldenleaf().build());
        FeatureUtils.register(context, GOLDENLEAF_BEES_005, Feature.TREE, createGoldenleaf().decorators(List.of(new BeehiveDecorator(0.05F))).build());
        FeatureUtils.register(context, SAKURA, Feature.TREE, createSakura().build());
        FeatureUtils.register(context, SAKURA_BEES_005, Feature.TREE, createSakura().decorators(List.of(new BeehiveDecorator(0.05F))).build());
        FeatureUtils.register(context, FRIGID, Feature.TREE, createFrigid().build());
        FeatureUtils.register(context, FRIGID_BEES_005, Feature.TREE, createFrigid().decorators(List.of(new BeehiveDecorator(0.05F))).build());
        FeatureUtils.register(context, ARBOREAL_CACTUS, Feature.BLOCK_COLUMN, createArborealCactus());
    }

    private static ResourceKey<ConfiguredFeature<?, ?>> registryKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, SkysSkyIslands.createId(name));
    }

    private static TreeConfiguration.TreeConfigurationBuilder createGoldenleaf() {
        return TreeFeatures.createStraightBlobTree(ModBlocks.GOLDENLEAF_PLANKS.log().get(), ModBlocks.GOLDENLEAF_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    } //TODO: tweak TreeConfiguration
    private static TreeConfiguration.TreeConfigurationBuilder createSakura() {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SAKURA_PLANKS.log().get()),
                new CherryTrunkPlacer(7, 1, 0, ConstantInt.of(1), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
                //new CherryTrunkPlacer(7, 1, 0, new WeightedListInt(WeightedList.builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
                //TODO: re-insert this?
                BlockStateProvider.simple(ModBlocks.SAKURA_LEAVES.get()),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2))).ignoreVines();
    } //TODO: tweak TreeConfiguration
    private static TreeConfiguration.TreeConfigurationBuilder createFrigid() {
        return TreeFeatures.createStraightBlobTree(ModBlocks.FRIGID_PLANKS.log().get(), ModBlocks.FRIGID_LEAVES.get(), 4, 2, 0, 2).ignoreVines();
    } //TODO: tweak TreeConfiguration
    private static BlockColumnConfiguration createArborealCactus() {
        return new BlockColumnConfiguration(
                List.of(BlockColumnConfiguration.layer(BiasedToBottomInt.of(1, 5), BlockStateProvider.simple(ModBlocks.ARBOREAL_CACTUS_PLANKS.log().get()))),
                Direction.UP,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                false);
    } //TODO: tweak BlockColumnConfiguration
}
