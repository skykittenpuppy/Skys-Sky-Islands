package gay.beegirl.worldgen;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> LAND_ALEXANDRITE_ORE = registerKey("land_alexandrite_ore");
    public static final ResourceKey<PlacedFeature> SKY_ALEXANDRITE_ORE = registerKey("sky_alexandrite_ore");

    public static final ResourceKey<PlacedFeature> GOLDENLEAF = registerKey("goldenleaf");
    public static final ResourceKey<PlacedFeature> GOLDENLEAF_BEES_005 = registerKey("goldenleaf_bees_005");
    public static final ResourceKey<PlacedFeature> SAKURA = registerKey("sakura");
    public static final ResourceKey<PlacedFeature> SAKURA_BEES_005 = registerKey("sakura_bees_005");
    public static final ResourceKey<PlacedFeature> FRIGID = registerKey("frigid");
    public static final ResourceKey<PlacedFeature> FRIGID_BEES_005 = registerKey("frigid_bees_005");
    public static final ResourceKey<PlacedFeature> ARBOREAL_CACTUS = registerKey("arboreal_cactus");

    public static void registerPlacedFeatures() {
        SkysSkyIslands.LOGGER.info("Registering Placed Features for " + SkysSkyIslands.MOD_ID);
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> bootstrapContext) {
        SkysSkyIslands.LOGGER.info("Placed Feature Bootstrap for " + SkysSkyIslands.MOD_ID);

        var configuredFeatures = bootstrapContext.lookup(Registries.CONFIGURED_FEATURE);

        register(bootstrapContext, LAND_ALEXANDRITE_ORE, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAND_ALEXANDRITE_ORE), rareOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(256))));
        register(bootstrapContext, SKY_ALEXANDRITE_ORE, configuredFeatures.getOrThrow(ModConfiguredFeatures.SKY_ALEXANDRITE_ORE), commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(256), VerticalAnchor.TOP)));

        register(bootstrapContext, GOLDENLEAF, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDENLEAF), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.GOLDENLEAF_SAPLING)));
        register(bootstrapContext, GOLDENLEAF_BEES_005, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDENLEAF_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.GOLDENLEAF_SAPLING)));
        register(bootstrapContext, SAKURA, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAKURA), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.SAKURA_SAPLING)));
        register(bootstrapContext, SAKURA_BEES_005, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAKURA_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.SAKURA_SAPLING)));
        register(bootstrapContext, FRIGID, configuredFeatures.getOrThrow(ModConfiguredFeatures.FRIGID), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.FRIGID_SAPLING)));
        register(bootstrapContext, FRIGID_BEES_005, configuredFeatures.getOrThrow(ModConfiguredFeatures.FRIGID_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.FRIGID_SAPLING)));
        register(bootstrapContext, ARBOREAL_CACTUS, configuredFeatures.getOrThrow(ModConfiguredFeatures.ARBOREAL_CACTUS), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.ARBOREAL_CACTUS_FRUIT)));
    }

    public static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<PlacedFeature> bootstrapContext, ResourceKey<PlacedFeature> resourceKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers) {
        bootstrapContext.register(resourceKey, new PlacedFeature(configuredFeature, modifiers));
    }

    private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier2) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifier2, BiomeFilter.biome());
    }
    private static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(i), placementModifier);
    }
    private static List<PlacementModifier> rareOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(i), placementModifier);
    }
}