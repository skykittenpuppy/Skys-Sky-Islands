package gay.beegirl.worldgen;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.block.ModBlocks;
import gay.beegirl.util.ModTags;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;

import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
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
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAND_ALEXANDRITE_ORE = registerKey("land_alexandrite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SKY_ALEXANDRITE_ORE = registerKey("sky_alexandrite_ore");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA = registerKey("sakura");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SAKURA_BEES_005 = registerKey("sakura_bees_005");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ARBOREAL_CACTUS = registerKey("arboreal_cactus");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext){
        List<OreConfiguration.TargetBlockState> landAlexandriteOres =
                List.of(OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.STONE_ALEXANDRITE_ORE.defaultBlockState()),
                        OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.defaultBlockState()));
        register(bootstrapContext, LAND_ALEXANDRITE_ORE, Feature.ORE, new OreConfiguration(landAlexandriteOres, 4));
        List<OreConfiguration.TargetBlockState> skyAlexandriteOres =
                List.of(OreConfiguration.target(new TagMatchTest(ModTags.Blocks.CLOUDSHALE_ORE_REPLACEABLE), ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE.defaultBlockState()));
        register(bootstrapContext, SKY_ALEXANDRITE_ORE, Feature.ORE, new OreConfiguration(skyAlexandriteOres, 12));

        register(bootstrapContext, SAKURA, Feature.TREE, sakura().build());
        register(bootstrapContext, SAKURA_BEES_005, Feature.TREE, sakura().decorators(List.of(new BeehiveDecorator(0.05F))).build());
        register(bootstrapContext, ARBOREAL_CACTUS, Feature.BLOCK_COLUMN, new BlockColumnConfiguration(
                List.of(BlockColumnConfiguration.layer(BiasedToBottomInt.of(1, 3), BlockStateProvider.simple(ModBlocks.ARBOREAL_CACTUS_PLANKS.log()))),
                Direction.UP,
                BlockPredicate.ONLY_IN_AIR_PREDICATE,
                false));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext, ResourceKey<ConfiguredFeature<?, ?>> resourceKey, F feature, FC featureConfiguration) {
        bootstrapContext.register(resourceKey, new ConfiguredFeature<>(feature, featureConfiguration));
    }

    private static TreeConfiguration.TreeConfigurationBuilder sakura() {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SAKURA_PLANKS.log()),
                new CherryTrunkPlacer(7, 1, 0, ConstantInt.of(1), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
                BlockStateProvider.simple(ModBlocks.SAKURA_LEAVES),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2))).ignoreVines();
    }
    /*private static TreeConfiguration.TreeConfigurationBuilder sakura() {
        return (new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlock.SAKURA_PLANKS.log()),
                new CherryTrunkPlacer(7, 1, 0, new WeightedListInt(WeightedList.builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)),
                BlockStateProvider.simple(ModBlock.SAKURA_LEAVES),
                new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F),
                new TwoLayersFeatureSize(1, 0, 2))).ignoreVines();
    }*/
}