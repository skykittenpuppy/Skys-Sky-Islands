package gay.beegirl.skyislands.data.worldgen.features;

import gay.beegirl.skyislands.tags.ModBlockTags;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModOreFeatures {
	public static final ResourceKey<ConfiguredFeature<?, ?>> LAND_ALEXANDRITE_ORE =	ModFeatureUtils.createKey("land_alexandrite_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SKY_ALEXANDRITE_ORE =	ModFeatureUtils.createKey("sky_alexandrite_ore");

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		List<OreConfiguration.TargetBlockState> landAlexandriteOres = List.of(
				OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.STONE_ALEXANDRITE_ORE.get().defaultBlockState()),
				OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get().defaultBlockState())
		);
		FeatureUtils.register(context, LAND_ALEXANDRITE_ORE, Feature.ORE, new OreConfiguration(landAlexandriteOres, 4));
		List<OreConfiguration.TargetBlockState> skyAlexandriteOres = List.of(
				OreConfiguration.target(new TagMatchTest(ModBlockTags.CLOUDSHALE_ORE_REPLACEABLE), ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE.get().defaultBlockState())
		);
		FeatureUtils.register(context, SKY_ALEXANDRITE_ORE, Feature.ORE, new OreConfiguration(skyAlexandriteOres, 12));
	}
}
