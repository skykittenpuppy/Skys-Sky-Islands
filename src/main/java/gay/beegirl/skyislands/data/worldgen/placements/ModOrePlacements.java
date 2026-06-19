package gay.beegirl.skyislands.data.worldgen.placements;

import gay.beegirl.skyislands.data.worldgen.features.ModOreFeatures;
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

public class ModOrePlacements {
	public static final ResourceKey<PlacedFeature> LAND_ALEXANDRITE_ORE =	ModPlacementUtils.createKey("land_alexandrite_ore");
	public static final ResourceKey<PlacedFeature> SKY_ALEXANDRITE_ORE =	ModPlacementUtils.createKey("sky_alexandrite_ore");

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		PlacementUtils.register(context, LAND_ALEXANDRITE_ORE, configuredFeatures.getOrThrow(ModOreFeatures.LAND_ALEXANDRITE_ORE), OrePlacements.rareOrePlacement(4, HeightRangePlacement.uniform(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(256))));
		PlacementUtils.register(context, SKY_ALEXANDRITE_ORE, configuredFeatures.getOrThrow(ModOreFeatures.SKY_ALEXANDRITE_ORE), OrePlacements.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(256), VerticalAnchor.TOP)));

	}
}
