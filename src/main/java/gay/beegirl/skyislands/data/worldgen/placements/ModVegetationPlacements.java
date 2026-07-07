package gay.beegirl.skyislands.data.worldgen.placements;

import gay.beegirl.skyislands.data.worldgen.features.ModFeatureUtils;
import gay.beegirl.skyislands.data.worldgen.features.ModTreeFeatures;
import gay.beegirl.skyislands.data.worldgen.features.ModVegetationFeatures;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModVegetationPlacements {
	public static final ResourceKey<PlacedFeature> FLOWER_SAKURA = ModPlacementUtils.createKey("flower_sakura");

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		PlacementUtils.register(context, FLOWER_SAKURA, configuredFeatures.getOrThrow(ModVegetationFeatures.FLOWER_SAKURA), NoiseThresholdCountPlacement.of(-0.8, 5, 10), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
	}
}
