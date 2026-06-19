package gay.beegirl.skyislands.data.worldgen.placements;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.data.worldgen.features.ModTreeFeatures;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModTreePlacements {

    public static final ResourceKey<PlacedFeature> GOLDENLEAF =             ModPlacementUtils.createKey("goldenleaf");
    public static final ResourceKey<PlacedFeature> GOLDENLEAF_BEES_005 =    ModPlacementUtils.createKey("goldenleaf_bees_005");
    public static final ResourceKey<PlacedFeature> SAKURA =                 ModPlacementUtils.createKey("sakura");
    public static final ResourceKey<PlacedFeature> SAKURA_BEES_005 =        ModPlacementUtils.createKey("sakura_bees_005");
    public static final ResourceKey<PlacedFeature> FRIGID =                 ModPlacementUtils.createKey("frigid");
    public static final ResourceKey<PlacedFeature> FRIGID_BEES_005 =        ModPlacementUtils.createKey("frigid_bees_005");
    public static final ResourceKey<PlacedFeature> ARBOREAL_CACTUS =        ModPlacementUtils.createKey("arboreal_cactus");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        PlacementUtils.register(context, GOLDENLEAF, configuredFeatures.getOrThrow(ModTreeFeatures.GOLDENLEAF), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.GOLDENLEAF_SAPLING.get())));
        PlacementUtils.register(context, GOLDENLEAF_BEES_005, configuredFeatures.getOrThrow(ModTreeFeatures.GOLDENLEAF_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.GOLDENLEAF_SAPLING.get())));
        PlacementUtils.register(context, SAKURA, configuredFeatures.getOrThrow(ModTreeFeatures.SAKURA), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.SAKURA_SAPLING.get())));
        PlacementUtils.register(context, SAKURA_BEES_005, configuredFeatures.getOrThrow(ModTreeFeatures.SAKURA_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.SAKURA_SAPLING.get())));
        PlacementUtils.register(context, FRIGID, configuredFeatures.getOrThrow(ModTreeFeatures.FRIGID), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.FRIGID_SAPLING.get())));
        PlacementUtils.register(context, FRIGID_BEES_005, configuredFeatures.getOrThrow(ModTreeFeatures.FRIGID_BEES_005), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.FRIGID_SAPLING.get())));
        PlacementUtils.register(context, ARBOREAL_CACTUS, configuredFeatures.getOrThrow(ModTreeFeatures.ARBOREAL_CACTUS), List.of(PlacementUtils.filteredByBlockSurvival(ModBlocks.ARBOREAL_CACTUS_FRUIT.get())));
    }
}
