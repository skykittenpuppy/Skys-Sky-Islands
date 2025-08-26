package gay.beegirl.item;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.block.ModBlock;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab SKY_ISLANDS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sky_islands"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItem.ALEXANDRITE))
                    .title(Component.translatable("itemGroup.sky-islands.sky_islands"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlock.CLOUDSHALE_GRASS);
                        output.accept(ModBlock.CLOUDSHALE_CHERRY_GRASS);
                        output.accept(ModBlock.CLOUDSHALE);
                        output.accept(ModBlock.COBBLED_CLOUDSHALE);
                        output.accept(ModBlock.MOSSY_COBBLED_CLOUDSHALE);
                        output.accept(ModBlock.CHERRY_COBBLED_CLOUDSHALE);
                        output.accept(ModBlock.STONE_ALEXANDRITE_ORE);
                        output.accept(ModBlock.DEEPSLATE_ALEXANDRITE_ORE);
                        output.accept(ModBlock.CLOUDSHALE_ALEXANDRITE_ORE);
                        output.accept(ModBlock.RAW_ALEXANDRITE_BLOCK);
                        output.accept(ModBlock.ALEXANDRITE_BLOCK);

                        output.accept(ModBlock.GOLDENLEAF_LOG);
                        output.accept(ModBlock.GOLDENLEAF_WOOD);
                        output.accept(ModBlock.STRIPPED_GOLDENLEAF_LOG);
                        output.accept(ModBlock.STRIPPED_GOLDENLEAF_WOOD);
                        output.accept(ModBlock.GOLDENLEAF_PLANKS);
                        output.accept(ModBlock.GOLDENLEAF_STAIRS);
                        output.accept(ModBlock.GOLDENLEAF_SLAB);
                        output.accept(ModBlock.GOLDENLEAF_BUTTON);
                        output.accept(ModBlock.GOLDENLEAF_PRESSURE_PLATE);
                        output.accept(ModBlock.GOLDENLEAF_FENCE);
                        output.accept(ModBlock.GOLDENLEAF_FENCE_GATE);
                        output.accept(ModBlock.GOLDENLEAF_DOOR);
                        output.accept(ModBlock.GOLDENLEAF_TRAPDOOR);
                        output.accept(ModItem.GOLDENLEAF_BOAT);
                        output.accept(ModItem.GOLDENLEAF_CHEST_BOAT);
                        output.accept(ModBlock.GOLDENLEAF_LEAVES);
                        output.accept(ModBlock.GOLDENLEAF_SAPLING);

                        output.accept(ModBlock.SAKURA_LOG);
                        output.accept(ModBlock.SAKURA_WOOD);
                        output.accept(ModBlock.STRIPPED_SAKURA_LOG);
                        output.accept(ModBlock.STRIPPED_SAKURA_WOOD);
                        output.accept(ModBlock.SAKURA_PLANKS);
                        output.accept(ModBlock.SAKURA_STAIRS);
                        output.accept(ModBlock.SAKURA_SLAB);
                        output.accept(ModBlock.SAKURA_BUTTON);
                        output.accept(ModBlock.SAKURA_PRESSURE_PLATE);
                        output.accept(ModBlock.SAKURA_FENCE);
                        output.accept(ModBlock.SAKURA_FENCE_GATE);
                        output.accept(ModBlock.SAKURA_DOOR);
                        output.accept(ModBlock.SAKURA_TRAPDOOR);
                        output.accept(ModItem.SAKURA_BOAT);
                        output.accept(ModItem.SAKURA_CHEST_BOAT);
                        output.accept(ModBlock.SAKURA_LEAVES);
                        output.accept(ModBlock.SAKURA_SAPLING);

                        output.accept(ModBlock.FRIGID_LOG);
                        output.accept(ModBlock.FRIGID_WOOD);
                        output.accept(ModBlock.STRIPPED_FRIGID_LOG);
                        output.accept(ModBlock.STRIPPED_FRIGID_WOOD);
                        output.accept(ModBlock.FRIGID_PLANKS);
                        output.accept(ModBlock.FRIGID_STAIRS);
                        output.accept(ModBlock.FRIGID_SLAB);
                        output.accept(ModBlock.FRIGID_BUTTON);
                        output.accept(ModBlock.FRIGID_PRESSURE_PLATE);
                        output.accept(ModBlock.FRIGID_FENCE);
                        output.accept(ModBlock.FRIGID_FENCE_GATE);
                        output.accept(ModBlock.FRIGID_DOOR);
                        output.accept(ModBlock.FRIGID_TRAPDOOR);
                        output.accept(ModItem.FRIGID_BOAT);
                        output.accept(ModItem.FRIGID_CHEST_BOAT);
                        output.accept(ModBlock.FRIGID_LEAVES);
                        output.accept(ModBlock.FRIGID_SAPLING);

                        output.accept(ModBlock.ARBOREAL_CACTUS_STEM);
                        output.accept(ModBlock.ARBOREAL_CACTUS_HYPHAE);
                        output.accept(ModBlock.STRIPPED_ARBOREAL_CACTUS_STEM);
                        output.accept(ModBlock.STRIPPED_ARBOREAL_CACTUS_HYPHAE);
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS);
                        output.accept(ModBlock.ARBOREAL_CACTUS_STAIRS);
                        output.accept(ModBlock.ARBOREAL_CACTUS_SLAB);
                        output.accept(ModBlock.ARBOREAL_CACTUS_BUTTON);
                        output.accept(ModBlock.ARBOREAL_CACTUS_PRESSURE_PLATE);
                        output.accept(ModBlock.ARBOREAL_CACTUS_FENCE);
                        output.accept(ModBlock.ARBOREAL_CACTUS_FENCE_GATE);
                        output.accept(ModBlock.ARBOREAL_CACTUS_DOOR);
                        output.accept(ModItem.ARBOREAL_CACTUS_BOAT);
                        output.accept(ModItem.ARBOREAL_CACTUS_CHEST_BOAT);
                        output.accept(ModBlock.ARBOREAL_CACTUS_TRAPDOOR);
                        output.accept(ModBlock.ARBOREAL_CACTUS_FRUIT);

                        output.accept(ModItem.RAW_ALEXANDRITE);
                        output.accept(ModItem.ALEXANDRITE);
                        output.accept(ModItem.GLIDER);
                    }).build());

    public static void registerCreativeModeTabs() {
        SkysSkyIslands.LOGGER.info("Registering Creative Mode Tabs for " + SkysSkyIslands.MOD_ID);
    }
}
