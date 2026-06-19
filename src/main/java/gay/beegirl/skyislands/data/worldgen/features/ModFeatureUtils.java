package gay.beegirl.skyislands.data.worldgen.features;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class ModFeatureUtils {
	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		ModOreFeatures.bootstrap(context);
		ModTreeFeatures.bootstrap(context);
	}

	public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
		return ResourceKey.create(Registries.CONFIGURED_FEATURE, SkysSkyIslands.createId(name));
	}
}
