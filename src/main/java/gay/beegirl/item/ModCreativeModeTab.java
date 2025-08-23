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
                    .icon(() -> new ItemStack(ModItem.REFINED_ZONAITE))
                    .title(Component.translatable("itemGroup.sky-islands.sky_islands"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlock.RAW_ZONAITE_BLOCK);
                        output.accept(ModBlock.REFINED_ZONAITE_BLOCK);
                        output.accept(ModBlock.STONE_ZONAITE_ORE);
                        output.accept(ModBlock.DEEPSLATE_ZONAITE_ORE);
                        output.accept(ModBlock.SKYSTONE_ZONAITE_ORE);
                        output.accept(ModItem.RAW_ZONAITE);
                        output.accept(ModItem.REFINED_ZONAITE);
                    }).build());

    public static void registerCreativeModeTabs() {
        SkysSkyIslands.LOGGER.info("Registering Creative Mode Tabs for " + SkysSkyIslands.MOD_ID);
    }
}
