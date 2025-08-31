package gay.beegirl.worldgen;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.util.ModTags;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGeneration {
    public static void registerWorldGeneration() {
        SkysSkyIslands.LOGGER.info("Registering World Generation for " + SkysSkyIslands.MOD_ID);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.LAND_ALEXANDRITE_ORE); //TODO: tweak rarity
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTags.Biomes.SKY_ISLANDS), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.SKY_ALEXANDRITE_ORE); //TODO: tweak rarity
    }
}
