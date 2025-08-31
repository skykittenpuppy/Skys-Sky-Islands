package gay.beegirl.util;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static void registerTags() {
        SkysSkyIslands.LOGGER.info("Registering Tags for " + SkysSkyIslands.MOD_ID);
    }

    public static class Biomes {
        public static final TagKey<Biome> SKY_ISLANDS = registerTag("sky_islands");

        private static TagKey<Biome> registerTag(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> CLOUDSHALE_ORE_REPLACEABLE = registerTag("cloudshale_ore_replaceable");
         public static final TagKey<Block> GOLDENLEAF_LOGS = registerTag("goldenleaf_logs");
        public static final TagKey<Block> SAKURA_LOGS = registerTag("sakura_logs");
        public static final TagKey<Block> FRIGID_LOGS = registerTag("frigid_logs");
        public static final TagKey<Block> ARBOREAL_CACTUS_STEMS = registerTag("arboreal_cactus_stems");

        public static TagKey<Block> registerTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> GLIDER_REPAIR_MATERIALS = registerTag("glider_repair_materials");
        public static final TagKey<Item> GOLDENLEAF_LOGS = registerTag("goldenleaf_logs");
        public static final TagKey<Item> SAKURA_LOGS = registerTag("sakura_logs");
        public static final TagKey<Item> FRIGID_LOGS = registerTag("frigid_logs");
        public static final TagKey<Item> ARBOREAL_CACTUS_STEMS = registerTag("arboreal_cactus_stems");

        private static TagKey<Item> registerTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        }
    }
}
