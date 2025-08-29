package gay.beegirl.item;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.block.ModBlock;
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

public class ModCreativeModeTab {
    public static final CreativeModeTab SKY_ISLANDS = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sky_islands"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItem.ALEXANDRITE))
                    .title(Component.translatable("itemGroup.sky-islands.sky_islands"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.log());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.wood());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.strippedLog());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.strippedWood());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.planks());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.stairs());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.slab());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.fence());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.fenceGate());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.door());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.trapdoor());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.pressurePlate());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.button());
                        output.accept(ModBlock.GOLDENLEAF_LEAVES);
                        output.accept(ModBlock.GOLDENLEAF_SAPLING);
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.standingSign());
                        output.accept(ModBlock.GOLDENLEAF_PLANKS.hangingSign());
                        output.accept(ModItem.GOLDENLEAF_BOAT);
                        output.accept(ModItem.GOLDENLEAF_CHEST_BOAT);

                        output.accept(ModBlock.SAKURA_PLANKS.log());
                        output.accept(ModBlock.SAKURA_PLANKS.wood());
                        output.accept(ModBlock.SAKURA_PLANKS.strippedLog());
                        output.accept(ModBlock.SAKURA_PLANKS.strippedWood());
                        output.accept(ModBlock.SAKURA_PLANKS.planks());
                        output.accept(ModBlock.SAKURA_PLANKS.stairs());
                        output.accept(ModBlock.SAKURA_PLANKS.slab());
                        output.accept(ModBlock.SAKURA_PLANKS.fence());
                        output.accept(ModBlock.SAKURA_PLANKS.fenceGate());
                        output.accept(ModBlock.SAKURA_PLANKS.door());
                        output.accept(ModBlock.SAKURA_PLANKS.trapdoor());
                        output.accept(ModBlock.SAKURA_PLANKS.pressurePlate());
                        output.accept(ModBlock.SAKURA_PLANKS.button());
                        output.accept(ModBlock.SAKURA_LEAVES);
                        output.accept(ModBlock.SAKURA_SAPLING);
                        output.accept(ModBlock.SAKURA_PLANKS.standingSign());
                        output.accept(ModBlock.SAKURA_PLANKS.hangingSign());
                        output.accept(ModItem.SAKURA_BOAT);
                        output.accept(ModItem.SAKURA_CHEST_BOAT);

                        output.accept(ModBlock.FRIGID_PLANKS.log());
                        output.accept(ModBlock.FRIGID_PLANKS.wood());
                        output.accept(ModBlock.FRIGID_PLANKS.strippedLog());
                        output.accept(ModBlock.FRIGID_PLANKS.strippedWood());
                        output.accept(ModBlock.FRIGID_PLANKS.planks());
                        output.accept(ModBlock.FRIGID_PLANKS.stairs());
                        output.accept(ModBlock.FRIGID_PLANKS.slab());
                        output.accept(ModBlock.FRIGID_PLANKS.fence());
                        output.accept(ModBlock.FRIGID_PLANKS.fenceGate());
                        output.accept(ModBlock.FRIGID_PLANKS.door());
                        output.accept(ModBlock.FRIGID_PLANKS.trapdoor());
                        output.accept(ModBlock.FRIGID_PLANKS.pressurePlate());
                        output.accept(ModBlock.FRIGID_PLANKS.button());
                        output.accept(ModBlock.FRIGID_LEAVES);
                        output.accept(ModBlock.FRIGID_SAPLING);
                        output.accept(ModBlock.FRIGID_PLANKS.standingSign());
                        output.accept(ModBlock.FRIGID_PLANKS.hangingSign());
                        output.accept(ModItem.FRIGID_BOAT);
                        output.accept(ModItem.FRIGID_CHEST_BOAT);

                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.log());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.wood());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.strippedLog());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.strippedWood());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.planks());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.stairs());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.slab());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.fence());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.fenceGate());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.door());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.trapdoor());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.pressurePlate());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.button());
                        output.accept(ModBlock.ARBOREAL_CACTUS_FRUIT);
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.standingSign());
                        output.accept(ModBlock.ARBOREAL_CACTUS_PLANKS.hangingSign());
                        output.accept(ModItem.ARBOREAL_CACTUS_BOAT);
                        output.accept(ModItem.ARBOREAL_CACTUS_CHEST_BOAT);

                        output.accept(ModBlock.CLOUDSHALE_GRASS);
                        output.accept(ModBlock.CLOUDSHALE_CHERRY_GRASS);

                        output.accept(ModBlock.POINTED_CLOUDSHALE);
                        output.accept(ModBlock.CLOUDSHALE.base());
                        output.accept(ModBlock.CLOUDSHALE.stairs());
                        output.accept(ModBlock.CLOUDSHALE.slab());
                        output.accept(ModBlock.CLOUDSHALE.wall());
                        output.accept(ModBlock.CLOUDSHALE.pressurePlate());
                        output.accept(ModBlock.CLOUDSHALE.button());

                        output.accept(ModBlock.COBBLED_CLOUDSHALE.base());
                        output.accept(ModBlock.COBBLED_CLOUDSHALE.stairs());
                        output.accept(ModBlock.COBBLED_CLOUDSHALE.slab());
                        output.accept(ModBlock.COBBLED_CLOUDSHALE.wall());
                        output.accept(ModBlock.COBBLED_CLOUDSHALE.pressurePlate());
                        output.accept(ModBlock.COBBLED_CLOUDSHALE.button());

                        output.accept(ModBlock.MOSSY_COBBLED_CLOUDSHALE.base());
                        output.accept(ModBlock.MOSSY_COBBLED_CLOUDSHALE.stairs());
                        output.accept(ModBlock.MOSSY_COBBLED_CLOUDSHALE.slab());
                        output.accept(ModBlock.MOSSY_COBBLED_CLOUDSHALE.wall());
                        output.accept(ModBlock.MOSSY_COBBLED_CLOUDSHALE.pressurePlate());
                        output.accept(ModBlock.MOSSY_COBBLED_CLOUDSHALE.button());

                        output.accept(ModBlock.CHERRY_COBBLED_CLOUDSHALE.base());
                        output.accept(ModBlock.CHERRY_COBBLED_CLOUDSHALE.stairs());
                        output.accept(ModBlock.CHERRY_COBBLED_CLOUDSHALE.slab());
                        output.accept(ModBlock.CHERRY_COBBLED_CLOUDSHALE.wall());
                        output.accept(ModBlock.CHERRY_COBBLED_CLOUDSHALE.pressurePlate());
                        output.accept(ModBlock.CHERRY_COBBLED_CLOUDSHALE.button());

                        output.accept(ModBlock.ALEXANDRITE_BLOCK);
                        output.accept(ModBlock.STONE_ALEXANDRITE_ORE);
                        output.accept(ModBlock.DEEPSLATE_ALEXANDRITE_ORE);
                        output.accept(ModBlock.CLOUDSHALE_ALEXANDRITE_ORE);
                        output.accept(ModBlock.RAW_ALEXANDRITE_BLOCK);
                        output.accept(ModItem.RAW_ALEXANDRITE);
                        output.accept(ModItem.ALEXANDRITE);

                        output.accept(ModItem.GLIDER);
                    }).build());

    public static void registerCreativeModeTabs() {
        SkysSkyIslands.LOGGER.info("Registering Creative Mode Tabs for " + SkysSkyIslands.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.PALE_OAK_BUTTON,
                    ModBlock.GOLDENLEAF_PLANKS.log(),
                    ModBlock.GOLDENLEAF_PLANKS.wood(),
                    ModBlock.GOLDENLEAF_PLANKS.strippedLog(),
                    ModBlock.GOLDENLEAF_PLANKS.strippedWood(),
                    ModBlock.GOLDENLEAF_PLANKS.planks(),
                    ModBlock.GOLDENLEAF_PLANKS.stairs(),
                    ModBlock.GOLDENLEAF_PLANKS.slab(),
                    ModBlock.GOLDENLEAF_PLANKS.fence(),
                    ModBlock.GOLDENLEAF_PLANKS.fenceGate(),
                    ModBlock.GOLDENLEAF_PLANKS.door(),
                    ModBlock.GOLDENLEAF_PLANKS.trapdoor(),
                    ModBlock.GOLDENLEAF_PLANKS.pressurePlate(),
                    ModBlock.GOLDENLEAF_PLANKS.button(),

                    ModBlock.SAKURA_PLANKS.log(),
                    ModBlock.SAKURA_PLANKS.wood(),
                    ModBlock.SAKURA_PLANKS.strippedLog(),
                    ModBlock.SAKURA_PLANKS.strippedWood(),
                    ModBlock.SAKURA_PLANKS.planks(),
                    ModBlock.SAKURA_PLANKS.stairs(),
                    ModBlock.SAKURA_PLANKS.slab(),
                    ModBlock.SAKURA_PLANKS.fence(),
                    ModBlock.SAKURA_PLANKS.fenceGate(),
                    ModBlock.SAKURA_PLANKS.door(),
                    ModBlock.SAKURA_PLANKS.trapdoor(),
                    ModBlock.SAKURA_PLANKS.pressurePlate(),
                    ModBlock.SAKURA_PLANKS.button(),

                    ModBlock.FRIGID_PLANKS.log(),
                    ModBlock.FRIGID_PLANKS.wood(),
                    ModBlock.FRIGID_PLANKS.strippedLog(),
                    ModBlock.FRIGID_PLANKS.strippedWood(),
                    ModBlock.FRIGID_PLANKS.planks(),
                    ModBlock.FRIGID_PLANKS.stairs(),
                    ModBlock.FRIGID_PLANKS.slab(),
                    ModBlock.FRIGID_PLANKS.fence(),
                    ModBlock.FRIGID_PLANKS.fenceGate(),
                    ModBlock.FRIGID_PLANKS.door(),
                    ModBlock.FRIGID_PLANKS.trapdoor(),
                    ModBlock.FRIGID_PLANKS.pressurePlate(),
                    ModBlock.FRIGID_PLANKS.button());
            entries.addAfter(Blocks.WARPED_BUTTON,
                    ModBlock.ARBOREAL_CACTUS_PLANKS.log(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.wood(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.strippedLog(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.strippedWood(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.planks(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.stairs(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.slab(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.fence(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.fenceGate(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.door(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.trapdoor(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.pressurePlate(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.button());
            entries.addAfter(Blocks.PURPUR_SLAB,
                    ModBlock.CLOUDSHALE.base(),
                    ModBlock.CLOUDSHALE.stairs(),
                    ModBlock.CLOUDSHALE.slab(),
                    ModBlock.CLOUDSHALE.wall(),
                    ModBlock.CLOUDSHALE.pressurePlate(),
                    ModBlock.CLOUDSHALE.button(),

                    ModBlock.COBBLED_CLOUDSHALE.base(),
                    ModBlock.COBBLED_CLOUDSHALE.stairs(),
                    ModBlock.COBBLED_CLOUDSHALE.slab(),
                    ModBlock.COBBLED_CLOUDSHALE.wall(),
                    ModBlock.COBBLED_CLOUDSHALE.pressurePlate(),
                    ModBlock.COBBLED_CLOUDSHALE.button(),

                    ModBlock.MOSSY_COBBLED_CLOUDSHALE.base(),
                    ModBlock.MOSSY_COBBLED_CLOUDSHALE.stairs(),
                    ModBlock.MOSSY_COBBLED_CLOUDSHALE.slab(),
                    ModBlock.MOSSY_COBBLED_CLOUDSHALE.wall(),
                    ModBlock.MOSSY_COBBLED_CLOUDSHALE.pressurePlate(),
                    ModBlock.MOSSY_COBBLED_CLOUDSHALE.button(),

                    ModBlock.CHERRY_COBBLED_CLOUDSHALE.base(),
                    ModBlock.CHERRY_COBBLED_CLOUDSHALE.stairs(),
                    ModBlock.CHERRY_COBBLED_CLOUDSHALE.slab(),
                    ModBlock.CHERRY_COBBLED_CLOUDSHALE.wall(),
                    ModBlock.CHERRY_COBBLED_CLOUDSHALE.pressurePlate(),
                    ModBlock.CHERRY_COBBLED_CLOUDSHALE.button());
            entries.addAfter(Blocks.NETHERITE_BLOCK, ModBlock.ALEXANDRITE_BLOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.END_STONE,
                    ModBlock.CLOUDSHALE_GRASS,
                    ModBlock.CLOUDSHALE_CHERRY_GRASS,
                    ModBlock.CLOUDSHALE.base(),
                    ModBlock.POINTED_CLOUDSHALE);
            entries.addAfter(Blocks.DEEPSLATE_DIAMOND_ORE,
                    ModBlock.STONE_ALEXANDRITE_ORE,
                    ModBlock.DEEPSLATE_ALEXANDRITE_ORE,
                    ModBlock.CLOUDSHALE_ALEXANDRITE_ORE);
            entries.addAfter(Blocks.RAW_GOLD_BLOCK,
                    ModBlock.RAW_ALEXANDRITE_BLOCK);
            entries.addAfter(Blocks.PALE_OAK_LOG,
                    ModBlock.GOLDENLEAF_PLANKS.log(),
                    ModBlock.SAKURA_PLANKS.log(),
                    ModBlock.FRIGID_PLANKS.log());
            entries.addAfter(Blocks.WARPED_STEM,
                    ModBlock.ARBOREAL_CACTUS_PLANKS.log());
            entries.addAfter(Blocks.FLOWERING_AZALEA_LEAVES,
                    ModBlock.GOLDENLEAF_LEAVES,
                    ModBlock.SAKURA_LEAVES,
                    ModBlock.FRIGID_LEAVES);
            entries.addAfter(Blocks.FLOWERING_AZALEA,
                    ModBlock.GOLDENLEAF_SAPLING,
                    ModBlock.SAKURA_SAPLING,
                    ModBlock.FRIGID_SAPLING);
            entries.addAfter(Blocks.WARPED_FUNGUS,
                    ModBlock.ARBOREAL_CACTUS_FRUIT);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.PALE_OAK_HANGING_SIGN,
                    ModBlock.GOLDENLEAF_PLANKS.standingSign(),
                    ModBlock.GOLDENLEAF_PLANKS.hangingSign(),
                    ModBlock.SAKURA_PLANKS.standingSign(),
                    ModBlock.SAKURA_PLANKS.hangingSign(),
                    ModBlock.FRIGID_PLANKS.standingSign(),
                    ModBlock.FRIGID_PLANKS.hangingSign());
            entries.addAfter(Blocks.WARPED_HANGING_SIGN,
                    ModBlock.ARBOREAL_CACTUS_PLANKS.standingSign(),
                    ModBlock.ARBOREAL_CACTUS_PLANKS.hangingSign());
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.addAfter(Items.ELYTRA,
                    ModItem.GLIDER);
            entries.addAfter(Items.PALE_OAK_CHEST_BOAT,
                    ModItem.GOLDENLEAF_BOAT,
                    ModItem.GOLDENLEAF_CHEST_BOAT,
                    ModItem.SAKURA_BOAT,
                    ModItem.SAKURA_CHEST_BOAT,
                    ModItem.FRIGID_BOAT,
                    ModItem.FRIGID_CHEST_BOAT);
            entries.addAfter(Items.BAMBOO_CHEST_RAFT,
                    ModItem.ARBOREAL_CACTUS_BOAT,
                    ModItem.ARBOREAL_CACTUS_CHEST_BOAT);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FOOD_AND_DRINKS).register(entries -> {
            entries.addAfter(Items.GLOW_BERRIES,
                    ModBlock.ARBOREAL_CACTUS_FRUIT);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.RAW_GOLD,
                    ModItem.RAW_ALEXANDRITE);
            entries.addAfter(Items.NETHERITE_INGOT,
                    ModItem.ALEXANDRITE);
        });
    }
}
