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
	public static final ResourceKey<ConfiguredFeature<?, ?>> LAND_ZEPHYRUM_ORE =	ModFeatureUtils.createKey("land_zephyrum_ore");
	public static final ResourceKey<ConfiguredFeature<?, ?>> SKY_ZEPHYRUM_ORE =	ModFeatureUtils.createKey("sky_zephyrum_ore");

	public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
		List<OreConfiguration.TargetBlockState> landZephyrumOres = List.of(
				OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), ModBlocks.STONE_ZEPHYRUM_ORE.get().defaultBlockState()),
				OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES), ModBlocks.DEEPSLATE_ZEPHYRUM_ORE.get().defaultBlockState())
		);
		FeatureUtils.register(context, LAND_ZEPHYRUM_ORE, Feature.ORE, new OreConfiguration(landZephyrumOres, 4));
		List<OreConfiguration.TargetBlockState> skyZephyrumOres = List.of(
				OreConfiguration.target(new TagMatchTest(ModBlockTags.CLOUDSHALE_ORE_REPLACEABLE), ModBlocks.CLOUDSHALE_ZEPHYRUM_ORE.get().defaultBlockState())
		);
		FeatureUtils.register(context, SKY_ZEPHYRUM_ORE, Feature.ORE, new OreConfiguration(skyZephyrumOres, 12));
	}
}
