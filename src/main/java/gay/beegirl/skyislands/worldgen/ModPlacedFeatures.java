package gay.beegirl.skyislands.worldgen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> LAND_ALEXANDRITE_ORE = registryKey("land_alexandrite_ore");
    public static final ResourceKey<PlacedFeature> SKY_ALEXANDRITE_ORE = registryKey("sky_alexandrite_ore");

    public static final ResourceKey<PlacedFeature> GOLDENLEAF = registryKey("goldenleaf");
    public static final ResourceKey<PlacedFeature> GOLDENLEAF_BEES_005 = registryKey("goldenleaf_bees_005");
    public static final ResourceKey<PlacedFeature> SAKURA = registryKey("sakura");
    public static final ResourceKey<PlacedFeature> SAKURA_BEES_005 = registryKey("sakura_bees_005");
    public static final ResourceKey<PlacedFeature> FRIGID = registryKey("frigid");
    public static final ResourceKey<PlacedFeature> FRIGID_BEES_005 = registryKey("frigid_bees_005");
    public static final ResourceKey<PlacedFeature> ARBOREAL_CACTUS = registryKey("arboreal_cactus");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        SkysSkyIslands.LOGGER.info("Placed Feature Bootstrap for " + SkysSkyIslands.MOD_ID);

        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, LAND_ALEXANDRITE_ORE, configuredFeatures.getOrThrow(ModConfiguredFeatures.LAND_ALEXANDRITE_ORE), OrePlacements.rareOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(256))));
        PlacementUtils.register(context, SKY_ALEXANDRITE_ORE, configuredFeatures.getOrThrow(ModConfiguredFeatures.SKY_ALEXANDRITE_ORE), OrePlacements.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(256), VerticalAnchor.TOP)));

        PlacementUtils.register(context, GOLDENLEAF, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDENLEAF), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.GOLDENLEAF_SAPLING.get())));
        PlacementUtils.register(context, GOLDENLEAF_BEES_005, configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDENLEAF_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.GOLDENLEAF_SAPLING.get())));
        PlacementUtils.register(context, SAKURA, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAKURA), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.SAKURA_SAPLING.get())));
        PlacementUtils.register(context, SAKURA_BEES_005, configuredFeatures.getOrThrow(ModConfiguredFeatures.SAKURA_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.SAKURA_SAPLING.get())));
        PlacementUtils.register(context, FRIGID, configuredFeatures.getOrThrow(ModConfiguredFeatures.FRIGID), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.FRIGID_SAPLING.get())));
        PlacementUtils.register(context, FRIGID_BEES_005, configuredFeatures.getOrThrow(ModConfiguredFeatures.FRIGID_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.FRIGID_SAPLING.get())));
        PlacementUtils.register(context, ARBOREAL_CACTUS, configuredFeatures.getOrThrow(ModConfiguredFeatures.ARBOREAL_CACTUS), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.ARBOREAL_CACTUS_FRUIT.get())));
    }

    private static ResourceKey<PlacedFeature> registryKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, SkysSkyIslands.createId(name));
    }
}
