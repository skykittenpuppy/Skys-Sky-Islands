package gay.beegirl.skyislands.item;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SkysSkyIslands.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKY_ISLANDS = CREATIVE_MODE_TABS.register("sky_islands", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.skyislands.sky_islands"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(Items.COOKED_BEEF::getDefaultInstance)
            .displayItems((parameters, output) -> {
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.log().get());
                //output.accept(ModItems.GOLDENLEAF_BOAT.get());
                //output.accept(ModItems.GOLDENLEAF_CHEST_BOAT.get());

                //output.accept(ModItems.SAKURA_BOAT.get());
                //output.accept(ModItems.SAKURA_CHEST_BOAT.get());

                //output.accept(ModItems.FRIGID_BOAT.get());
                //output.accept(ModItems.FRIGID_CHEST_BOAT.get());

                //output.accept(ModItems.ARBOREAL_CACTUS_BOAT.get());
                //output.accept(ModItems.ARBOREAL_CACTUS_CHEST_BOAT.get());

                output.accept(ModItems.RAW_ALEXANDRITE.get());
                output.accept(ModItems.ALEXANDRITE.get());

                output.accept(ModItems.GLIDER.get());

                //output.accept(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get());
                output.accept(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE.get());
            }).build());

    public static void registerCreativeModeTabs(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Creative Mode Tabs for " + SkysSkyIslands.MOD_ID);

        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
