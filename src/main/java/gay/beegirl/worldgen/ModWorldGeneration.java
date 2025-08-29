package gay.beegirl.worldgen;

import gay.beegirl.util.ModTag;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModWorldGeneration {
    public static void registerWorldGeneration() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.LAND_ALEXANDRITE_ORE);
        BiomeModifications.addFeature(BiomeSelectors.tag(ModTag.Biomes.SKY_ISLANDS), GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatures.SKY_ALEXANDRITE_ORE);
    }
}
