package gay.beegirl.skyislands.world.item;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.core.component.ModDataComponents;
import gay.beegirl.skyislands.world.item.gliderdesign.GliderDesign;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SkysSkyIslands.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SKY_ISLANDS = CREATIVE_MODE_TABS.register("sky_islands", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.skyislands.sky_islands"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(ModItems.ALEXANDRITE.get()::getDefaultInstance)
            .displayItems((parameters, output) -> {
                output.accept(ModBlocks.GOLDENLEAF_LOGS.log());
                output.accept(ModBlocks.GOLDENLEAF_LOGS.wood());
                output.accept(ModBlocks.GOLDENLEAF_LOGS.strippedLog());
                output.accept(ModBlocks.GOLDENLEAF_LOGS.strippedWood());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.base());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.stairs());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.slab());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.fence());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.fenceGate());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.door());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.trapdoor());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.pressurePlate());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.button());
                output.accept(ModBlocks.GOLDENLEAF_LEAVES);
                output.accept(ModBlocks.GOLDENLEAF_SAPLING);
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.standingSign());
                output.accept(ModBlocks.GOLDENLEAF_PLANKS.hangingSign());
                output.accept(ModItems.GOLDENLEAF_BOAT);
                output.accept(ModItems.GOLDENLEAF_CHEST_BOAT);

                output.accept(ModBlocks.SAKURA_LOGS.log());
                output.accept(ModBlocks.SAKURA_LOGS.wood());
                output.accept(ModBlocks.SAKURA_LOGS.strippedLog());
                output.accept(ModBlocks.SAKURA_LOGS.strippedWood());
                output.accept(ModBlocks.SAKURA_PLANKS.base());
                output.accept(ModBlocks.SAKURA_PLANKS.stairs());
                output.accept(ModBlocks.SAKURA_PLANKS.slab());
                output.accept(ModBlocks.SAKURA_PLANKS.fence());
                output.accept(ModBlocks.SAKURA_PLANKS.fenceGate());
                output.accept(ModBlocks.SAKURA_PLANKS.door());
                output.accept(ModBlocks.SAKURA_PLANKS.trapdoor());
                output.accept(ModBlocks.SAKURA_PLANKS.pressurePlate());
                output.accept(ModBlocks.SAKURA_PLANKS.button());
                output.accept(ModBlocks.SAKURA_LEAVES);
                output.accept(ModBlocks.SAKURA_SAPLING);
                output.accept(ModBlocks.SAKURA_PLANKS.standingSign());
                output.accept(ModBlocks.SAKURA_PLANKS.hangingSign());
                output.accept(ModItems.SAKURA_BOAT);
                output.accept(ModItems.SAKURA_CHEST_BOAT);

                output.accept(ModBlocks.FRIGID_LOGS.log());
                output.accept(ModBlocks.FRIGID_LOGS.wood());
                output.accept(ModBlocks.FRIGID_LOGS.strippedLog());
                output.accept(ModBlocks.FRIGID_LOGS.strippedWood());
                output.accept(ModBlocks.FRIGID_PLANKS.base());
                output.accept(ModBlocks.FRIGID_PLANKS.stairs());
                output.accept(ModBlocks.FRIGID_PLANKS.slab());
                output.accept(ModBlocks.FRIGID_PLANKS.fence());
                output.accept(ModBlocks.FRIGID_PLANKS.fenceGate());
                output.accept(ModBlocks.FRIGID_PLANKS.door());
                output.accept(ModBlocks.FRIGID_PLANKS.trapdoor());
                output.accept(ModBlocks.FRIGID_PLANKS.pressurePlate());
                output.accept(ModBlocks.FRIGID_PLANKS.button());
                output.accept(ModBlocks.FRIGID_LEAVES);
                output.accept(ModBlocks.FRIGID_SAPLING);
                output.accept(ModBlocks.FRIGID_PLANKS.standingSign());
                output.accept(ModBlocks.FRIGID_PLANKS.hangingSign());
                output.accept(ModItems.FRIGID_BOAT);
                output.accept(ModItems.FRIGID_CHEST_BOAT);

                output.accept(ModBlocks.ARBOREAL_CACTUSES.cactus());
                output.accept(ModBlocks.ARBOREAL_CACTUSES.despinedCactus());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.base());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.stairs());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.slab());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.fence());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.fenceGate());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.door());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.trapdoor());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.pressurePlate());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.button());
                output.accept(ModItems.ARBOREAL_CACTUS_FRUIT);
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANT);
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.standingSign());
                output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingSign());
                output.accept(ModItems.ARBOREAL_CACTUS_BOAT);
                output.accept(ModItems.ARBOREAL_CACTUS_CHEST_BOAT);

                output.accept(ModBlocks.CLOUDSHALE_GRASS);
                output.accept(ModBlocks.CLOUDSHALE_CHERRY_GRASS);

                output.accept(ModBlocks.CLOUDSHALE);
                output.accept(ModBlocks.POINTED_CLOUDSHALE);

                output.accept(ModBlocks.COBBLED_CLOUDSHALE.base());
                output.accept(ModBlocks.COBBLED_CLOUDSHALE.stairs());
                output.accept(ModBlocks.COBBLED_CLOUDSHALE.slab());
                output.accept(ModBlocks.COBBLED_CLOUDSHALE.wall());

                output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.base());
                output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.stairs());
                output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.slab());
                output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.wall());

                output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.base());
                output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.stairs());
                output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.slab());
                output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.wall());

                output.accept(ModBlocks.ALEXANDRITE_BLOCK);
                output.accept(ModBlocks.STONE_ALEXANDRITE_ORE);
                output.accept(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
                output.accept(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE);
                output.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK);
                output.accept(ModItems.RAW_ALEXANDRITE);
                output.accept(ModItems.ALEXANDRITE);

                output.accept(ModItems.GLIDER);
                ItemStack testing_glider = new ItemStack(ModItems.GLIDER.get());
                testing_glider.set(ModDataComponents.SEWING_PATTERN.get(), new GliderDesign(ModItems.TESTING_GLIDER_DESIGN_SEWING_TEMPLATE, SkysSkyIslands.createId("testing"), Component.translatable("glider_design.skyislands.testing")));
                output.accept(testing_glider);
                ItemStack testing2_glider = new ItemStack(ModItems.GLIDER.get());
                testing2_glider.set(ModDataComponents.SEWING_PATTERN.get(), new GliderDesign(ModItems.TESTING2_GLIDER_DESIGN_SEWING_TEMPLATE, SkysSkyIslands.createId("testing2"), Component.translatable("glider_design.skyislands.testing2")));
                output.accept(testing2_glider);

                output.accept(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE);
                output.accept(ModItems.TESTING_GLIDER_DESIGN_SEWING_TEMPLATE);
                output.accept(ModItems.TESTING2_GLIDER_DESIGN_SEWING_TEMPLATE);
            }).build());

    public static void registerCreativeModeTabs(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Creative Mode Tabs for " + SkysSkyIslands.MOD_ID);

        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
