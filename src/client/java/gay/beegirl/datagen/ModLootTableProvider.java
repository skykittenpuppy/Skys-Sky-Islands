package gay.beegirl.datagen;

import gay.beegirl.block.ModBlocks;
import gay.beegirl.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK);
        dropSelf(ModBlocks.ALEXANDRITE_BLOCK);
        add(ModBlocks.STONE_ALEXANDRITE_ORE, createOreDrop(ModBlocks.STONE_ALEXANDRITE_ORE, ModItems.RAW_ALEXANDRITE));
        add(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE, createOreDrop(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE, ModItems.RAW_ALEXANDRITE));
        add(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE, createOreDrop(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE, ModItems.RAW_ALEXANDRITE));

        add(ModBlocks.CLOUDSHALE_GRASS, block -> createSingleItemTableWithSilkTouch(block, ModBlocks.COBBLED_CLOUDSHALE.base()));
        add(ModBlocks.CLOUDSHALE_CHERRY_GRASS, block -> createSingleItemTableWithSilkTouch(block, ModBlocks.COBBLED_CLOUDSHALE.base()));
        dropSelf(ModBlocks.POINTED_CLOUDSHALE);
        add(ModBlocks.CLOUDSHALE.base(), block -> createSingleItemTableWithSilkTouch(block, ModBlocks.COBBLED_CLOUDSHALE.base()));
        dropSelf(ModBlocks.CLOUDSHALE.button());
        dropSelf(ModBlocks.CLOUDSHALE.wall());
        dropSelf(ModBlocks.CLOUDSHALE.slab());
        dropSelf(ModBlocks.CLOUDSHALE.stairs());
        dropSelf(ModBlocks.CLOUDSHALE.pressurePlate());
        createStoneTypeLootTables(ModBlocks.COBBLED_CLOUDSHALE);
        createStoneTypeLootTables(ModBlocks.MOSSY_COBBLED_CLOUDSHALE);
        createStoneTypeLootTables(ModBlocks.CHERRY_COBBLED_CLOUDSHALE);

        createWoodTypeLootTables(ModBlocks.GOLDENLEAF_PLANKS);
        add(ModBlocks.GOLDENLEAF_LEAVES, createLeavesDrops(ModBlocks.GOLDENLEAF_LEAVES, ModBlocks.GOLDENLEAF_SAPLING, 0.0625f));
        dropSelf(ModBlocks.GOLDENLEAF_SAPLING);
        add(ModBlocks.POTTED_GOLDENLEAF_SAPLING, createPotFlowerItemTable(ModBlocks.GOLDENLEAF_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlocks.SAKURA_PLANKS);
        add(ModBlocks.SAKURA_LEAVES, createLeavesDrops(ModBlocks.SAKURA_LEAVES, ModBlocks.SAKURA_SAPLING, 0.0625f));
        dropSelf(ModBlocks.SAKURA_SAPLING);
        add(ModBlocks.POTTED_SAKURA_SAPLING, createPotFlowerItemTable(ModBlocks.SAKURA_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlocks.FRIGID_PLANKS);
        add(ModBlocks.FRIGID_LEAVES, createLeavesDrops(ModBlocks.FRIGID_LEAVES, ModBlocks.FRIGID_SAPLING, 0.0625f));
        dropSelf(ModBlocks.FRIGID_SAPLING);
        add(ModBlocks.POTTED_FRIGID_SAPLING, createPotFlowerItemTable(ModBlocks.FRIGID_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlocks.ARBOREAL_CACTUS_PLANKS);
        //add(ModBlock.ARBOREAL_CACTUS_FRUIT, createCropDrops(ModBlock.ARBOREAL_CACTUS_FRUIT, ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), LootItemCondition.DIRECT_CODEC.));
        add(ModBlocks.POTTED_ARBOREAL_CACTUS, createPotFlowerItemTable(ModBlocks.ARBOREAL_CACTUS_FRUIT.asItem()));
    }

    private void createStoneTypeLootTables(ModBlocks.StoneSetBlocks stoneSetBlocks) {
        dropSelf(stoneSetBlocks.base());
        dropSelf(stoneSetBlocks.button());
        dropSelf(stoneSetBlocks.wall());
        dropSelf(stoneSetBlocks.slab());
        dropSelf(stoneSetBlocks.stairs());
        dropSelf(stoneSetBlocks.pressurePlate());
    }

    private void createWoodTypeLootTables(ModBlocks.WoodSetBlocks woodSetBlocks) {
        dropSelf(woodSetBlocks.log());
        dropSelf(woodSetBlocks.wood());
        dropSelf(woodSetBlocks.strippedLog());
        dropSelf(woodSetBlocks.strippedWood());
        dropSelf(woodSetBlocks.planks());
        dropSelf(woodSetBlocks.button());
        add(woodSetBlocks.door(), createDoorTable(woodSetBlocks.door()));
        dropSelf(woodSetBlocks.fence());
        dropSelf(woodSetBlocks.fenceGate());
        dropSelf(woodSetBlocks.standingSign());
        dropSelf(woodSetBlocks.hangingSign());
        add(woodSetBlocks.slab(), createSlabItemTable(woodSetBlocks.slab()));
        dropSelf(woodSetBlocks.stairs());
        dropSelf(woodSetBlocks.pressurePlate());
        dropSelf(woodSetBlocks.trapdoor());
    }
}