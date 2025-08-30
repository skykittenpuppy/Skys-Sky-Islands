package gay.beegirl.item;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

public class ModCreativeModeTabs {
    public static final CreativeModeTab SKY_ISLANDS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sky_islands"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.ALEXANDRITE))
                    .title(Component.translatable("itemGroup.sky-islands.sky_islands"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.GOLDENLEAF_PLANKS.log());
                        output.accept(ModBlocks.GOLDENLEAF_PLANKS.wood());
                        output.accept(ModBlocks.GOLDENLEAF_PLANKS.strippedLog());
                        output.accept(ModBlocks.GOLDENLEAF_PLANKS.strippedWood());
                        output.accept(ModBlocks.GOLDENLEAF_PLANKS.planks());
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

                        output.accept(ModBlocks.SAKURA_PLANKS.log());
                        output.accept(ModBlocks.SAKURA_PLANKS.wood());
                        output.accept(ModBlocks.SAKURA_PLANKS.strippedLog());
                        output.accept(ModBlocks.SAKURA_PLANKS.strippedWood());
                        output.accept(ModBlocks.SAKURA_PLANKS.planks());
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

                        output.accept(ModBlocks.FRIGID_PLANKS.log());
                        output.accept(ModBlocks.FRIGID_PLANKS.wood());
                        output.accept(ModBlocks.FRIGID_PLANKS.strippedLog());
                        output.accept(ModBlocks.FRIGID_PLANKS.strippedWood());
                        output.accept(ModBlocks.FRIGID_PLANKS.planks());
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

                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.log());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.wood());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedLog());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedWood());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.planks());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.stairs());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.slab());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.fence());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.fenceGate());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.door());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.trapdoor());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.pressurePlate());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.button());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_FRUIT);
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.standingSign());
                        output.accept(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingSign());
                        output.accept(ModItems.ARBOREAL_CACTUS_BOAT);
                        output.accept(ModItems.ARBOREAL_CACTUS_CHEST_BOAT);

                        output.accept(ModBlocks.CLOUDSHALE_GRASS);
                        output.accept(ModBlocks.CLOUDSHALE_CHERRY_GRASS);

                        output.accept(ModBlocks.POINTED_CLOUDSHALE);
                        output.accept(ModBlocks.CLOUDSHALE.base());
                        output.accept(ModBlocks.CLOUDSHALE.stairs());
                        output.accept(ModBlocks.CLOUDSHALE.slab());
                        output.accept(ModBlocks.CLOUDSHALE.wall());
                        output.accept(ModBlocks.CLOUDSHALE.pressurePlate());
                        output.accept(ModBlocks.CLOUDSHALE.button());

                        output.accept(ModBlocks.COBBLED_CLOUDSHALE.base());
                        output.accept(ModBlocks.COBBLED_CLOUDSHALE.stairs());
                        output.accept(ModBlocks.COBBLED_CLOUDSHALE.slab());
                        output.accept(ModBlocks.COBBLED_CLOUDSHALE.wall());
                        output.accept(ModBlocks.COBBLED_CLOUDSHALE.pressurePlate());
                        output.accept(ModBlocks.COBBLED_CLOUDSHALE.button());

                        output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.base());
                        output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.stairs());
                        output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.slab());
                        output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.wall());
                        output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.pressurePlate());
                        output.accept(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.button());

                        output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.base());
                        output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.stairs());
                        output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.slab());
                        output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.wall());
                        output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.pressurePlate());
                        output.accept(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.button());

                        output.accept(ModBlocks.ALEXANDRITE_BLOCK);
                        output.accept(ModBlocks.STONE_ALEXANDRITE_ORE);
                        output.accept(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
                        output.accept(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE);
                        output.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK);
                        output.accept(ModItems.RAW_ALEXANDRITE);
                        output.accept(ModItems.ALEXANDRITE);

                        output.accept(ModItems.GLIDER);

                        output.accept(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE);
                        output.accept(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE);
                    }).build());

    public static void registerCreativeModeTabs() {
        SkysSkyIslands.LOGGER.info("Registering Creative Mode Tabs for " + SkysSkyIslands.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.PALE_OAK_BUTTON,
                    ModBlocks.GOLDENLEAF_PLANKS.log(),
                    ModBlocks.GOLDENLEAF_PLANKS.wood(),
                    ModBlocks.GOLDENLEAF_PLANKS.strippedLog(),
                    ModBlocks.GOLDENLEAF_PLANKS.strippedWood(),
                    ModBlocks.GOLDENLEAF_PLANKS.planks(),
                    ModBlocks.GOLDENLEAF_PLANKS.stairs(),
                    ModBlocks.GOLDENLEAF_PLANKS.slab(),
                    ModBlocks.GOLDENLEAF_PLANKS.fence(),
                    ModBlocks.GOLDENLEAF_PLANKS.fenceGate(),
                    ModBlocks.GOLDENLEAF_PLANKS.door(),
                    ModBlocks.GOLDENLEAF_PLANKS.trapdoor(),
                    ModBlocks.GOLDENLEAF_PLANKS.pressurePlate(),
                    ModBlocks.GOLDENLEAF_PLANKS.button(),

                    ModBlocks.SAKURA_PLANKS.log(),
                    ModBlocks.SAKURA_PLANKS.wood(),
                    ModBlocks.SAKURA_PLANKS.strippedLog(),
                    ModBlocks.SAKURA_PLANKS.strippedWood(),
                    ModBlocks.SAKURA_PLANKS.planks(),
                    ModBlocks.SAKURA_PLANKS.stairs(),
                    ModBlocks.SAKURA_PLANKS.slab(),
                    ModBlocks.SAKURA_PLANKS.fence(),
                    ModBlocks.SAKURA_PLANKS.fenceGate(),
                    ModBlocks.SAKURA_PLANKS.door(),
                    ModBlocks.SAKURA_PLANKS.trapdoor(),
                    ModBlocks.SAKURA_PLANKS.pressurePlate(),
                    ModBlocks.SAKURA_PLANKS.button(),

                    ModBlocks.FRIGID_PLANKS.log(),
                    ModBlocks.FRIGID_PLANKS.wood(),
                    ModBlocks.FRIGID_PLANKS.strippedLog(),
                    ModBlocks.FRIGID_PLANKS.strippedWood(),
                    ModBlocks.FRIGID_PLANKS.planks(),
                    ModBlocks.FRIGID_PLANKS.stairs(),
                    ModBlocks.FRIGID_PLANKS.slab(),
                    ModBlocks.FRIGID_PLANKS.fence(),
                    ModBlocks.FRIGID_PLANKS.fenceGate(),
                    ModBlocks.FRIGID_PLANKS.door(),
                    ModBlocks.FRIGID_PLANKS.trapdoor(),
                    ModBlocks.FRIGID_PLANKS.pressurePlate(),
                    ModBlocks.FRIGID_PLANKS.button());
            entries.addAfter(Blocks.WARPED_BUTTON,
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.log(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.wood(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedLog(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedWood(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.planks(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.stairs(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.slab(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.fence(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.fenceGate(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.door(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.trapdoor(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.pressurePlate(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.button());
            entries.addAfter(Blocks.PURPUR_SLAB,
                    ModBlocks.CLOUDSHALE.base(),
                    ModBlocks.CLOUDSHALE.stairs(),
                    ModBlocks.CLOUDSHALE.slab(),
                    ModBlocks.CLOUDSHALE.wall(),
                    ModBlocks.CLOUDSHALE.pressurePlate(),
                    ModBlocks.CLOUDSHALE.button(),

                    ModBlocks.COBBLED_CLOUDSHALE.base(),
                    ModBlocks.COBBLED_CLOUDSHALE.stairs(),
                    ModBlocks.COBBLED_CLOUDSHALE.slab(),
                    ModBlocks.COBBLED_CLOUDSHALE.wall(),
                    ModBlocks.COBBLED_CLOUDSHALE.pressurePlate(),
                    ModBlocks.COBBLED_CLOUDSHALE.button(),

                    ModBlocks.MOSSY_COBBLED_CLOUDSHALE.base(),
                    ModBlocks.MOSSY_COBBLED_CLOUDSHALE.stairs(),
                    ModBlocks.MOSSY_COBBLED_CLOUDSHALE.slab(),
                    ModBlocks.MOSSY_COBBLED_CLOUDSHALE.wall(),
                    ModBlocks.MOSSY_COBBLED_CLOUDSHALE.pressurePlate(),
                    ModBlocks.MOSSY_COBBLED_CLOUDSHALE.button(),

                    ModBlocks.CHERRY_COBBLED_CLOUDSHALE.base(),
                    ModBlocks.CHERRY_COBBLED_CLOUDSHALE.stairs(),
                    ModBlocks.CHERRY_COBBLED_CLOUDSHALE.slab(),
                    ModBlocks.CHERRY_COBBLED_CLOUDSHALE.wall(),
                    ModBlocks.CHERRY_COBBLED_CLOUDSHALE.pressurePlate(),
                    ModBlocks.CHERRY_COBBLED_CLOUDSHALE.button());
            entries.addAfter(Blocks.NETHERITE_BLOCK, ModBlocks.ALEXANDRITE_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.END_STONE,
                    ModBlocks.CLOUDSHALE_GRASS,
                    ModBlocks.CLOUDSHALE_CHERRY_GRASS,
                    ModBlocks.CLOUDSHALE.base(),
                    ModBlocks.POINTED_CLOUDSHALE);
            entries.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE,
                    ModBlocks.STONE_ALEXANDRITE_ORE,
                    ModBlocks.DEEPSLATE_ALEXANDRITE_ORE,
                    ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE);
            entries.addAfter(Blocks.RAW_GOLD_BLOCK,
                    ModBlocks.RAW_ALEXANDRITE_BLOCK);
            entries.addAfter(Blocks.PALE_OAK_LOG,
                    ModBlocks.GOLDENLEAF_PLANKS.log(),
                    ModBlocks.SAKURA_PLANKS.log(),
                    ModBlocks.FRIGID_PLANKS.log());
            entries.addAfter(Blocks.WARPED_STEM,
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.log());
            entries.addAfter(Blocks.FLOWERING_AZALEA_LEAVES,
                    ModBlocks.GOLDENLEAF_LEAVES,
                    ModBlocks.SAKURA_LEAVES,
                    ModBlocks.FRIGID_LEAVES);
            entries.addAfter(Blocks.FLOWERING_AZALEA,
                    ModBlocks.GOLDENLEAF_SAPLING,
                    ModBlocks.SAKURA_SAPLING,
                    ModBlocks.FRIGID_SAPLING);
            entries.addAfter(Blocks.WARPED_FUNGUS,
                    ModBlocks.ARBOREAL_CACTUS_FRUIT);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.PALE_OAK_HANGING_SIGN,
                    ModBlocks.GOLDENLEAF_PLANKS.standingSign(),
                    ModBlocks.GOLDENLEAF_PLANKS.hangingSign(),
                    ModBlocks.SAKURA_PLANKS.standingSign(),
                    ModBlocks.SAKURA_PLANKS.hangingSign(),
                    ModBlocks.FRIGID_PLANKS.standingSign(),
                    ModBlocks.FRIGID_PLANKS.hangingSign());
            entries.addAfter(Blocks.WARPED_HANGING_SIGN,
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.standingSign(),
                    ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingSign());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.addAfter(Items.ELYTRA,
                    ModItems.GLIDER);
            entries.addAfter(Items.PALE_OAK_CHEST_BOAT,
                    ModItems.GOLDENLEAF_BOAT,
                    ModItems.GOLDENLEAF_CHEST_BOAT,
                    ModItems.SAKURA_BOAT,
                    ModItems.SAKURA_CHEST_BOAT,
                    ModItems.FRIGID_BOAT,
                    ModItems.FRIGID_CHEST_BOAT);
            entries.addAfter(Items.BAMBOO_CHEST_RAFT,
                    ModItems.ARBOREAL_CACTUS_BOAT,
                    ModItems.ARBOREAL_CACTUS_CHEST_BOAT);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.addAfter(Items.GLOW_BERRIES,
                    ModBlocks.ARBOREAL_CACTUS_FRUIT);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.RAW_GOLD,
                    ModItems.RAW_ALEXANDRITE);
            entries.addAfter(Items.NETHERITE_INGOT,
                    ModItems.ALEXANDRITE);
        });
    }
}
