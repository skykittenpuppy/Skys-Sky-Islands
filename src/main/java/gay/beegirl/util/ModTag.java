package gay.beegirl.util;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTag {
    public static class Biomes {
        public static final TagKey<Biome> SKY_ISLANDS = createTag("sky_islands");

        private static TagKey<Biome> createTag(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        }
    }
    public static class Blocks {
        public static final TagKey<Block> CLOUDSHALE_ORE_REPLACEABLE = createTag("cloudshale_ore_replaceable");
         public static final TagKey<Block> GOLDENLEAF_LOGS = createTag("goldenleaf_logs");
        public static final TagKey<Block> SAKURA_LOGS = createTag("sakura_logs");
        public static final TagKey<Block> FRIGID_LOGS = createTag("frigid_logs");
        public static final TagKey<Block> ARBOREAL_CACTUS_STEMS = createTag("arboreal_cactus_stems");

        public static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> GOLDENLEAF_LOGS = createTag("goldenleaf_logs");
        public static final TagKey<Item> SAKURA_LOGS = createTag("sakura_logs");
        public static final TagKey<Item> FRIGID_LOGS = createTag("frigid_logs");
        public static final TagKey<Item> ARBOREAL_CACTUS_STEMS = createTag("arboreal_cactus_stems");

        private static TagKey<Item> createTag(String name) {
            return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        }
    }
}
