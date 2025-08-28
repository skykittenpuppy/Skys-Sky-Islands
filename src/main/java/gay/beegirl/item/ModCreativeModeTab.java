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
    }
}
