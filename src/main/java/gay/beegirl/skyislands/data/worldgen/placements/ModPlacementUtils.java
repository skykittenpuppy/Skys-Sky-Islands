package gay.beegirl.skyislands.data.worldgen.placements;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.data.worldgen.features.ModOreFeatures;
import gay.beegirl.skyislands.data.worldgen.features.ModTreeFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacementUtils {
	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		ModOrePlacements.bootstrap(context);
		ModTreePlacements.bootstrap(context);
		ModVegetationPlacements.bootstrap(context);
	}

	public static ResourceKey<PlacedFeature> createKey(String key) {
		return ResourceKey.create(Registries.PLACED_FEATURE, SkysSkyIslands.createId(key));
	}
}
