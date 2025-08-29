package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import gay.beegirl.util.ModTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.packs.VanillaBlockLoot;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        dropSelf(ModBlock.RAW_ALEXANDRITE_BLOCK);
        dropSelf(ModBlock.ALEXANDRITE_BLOCK);
        add(ModBlock.STONE_ALEXANDRITE_ORE, createOreDrop(ModBlock.STONE_ALEXANDRITE_ORE, ModItem.RAW_ALEXANDRITE));
        add(ModBlock.DEEPSLATE_ALEXANDRITE_ORE, createOreDrop(ModBlock.DEEPSLATE_ALEXANDRITE_ORE, ModItem.RAW_ALEXANDRITE));
        add(ModBlock.CLOUDSHALE_ALEXANDRITE_ORE, createOreDrop(ModBlock.CLOUDSHALE_ALEXANDRITE_ORE, ModItem.RAW_ALEXANDRITE));

        add(ModBlock.CLOUDSHALE_GRASS, block -> createSingleItemTableWithSilkTouch(block, ModBlock.COBBLED_CLOUDSHALE.base()));
        add(ModBlock.CLOUDSHALE_CHERRY_GRASS, block -> createSingleItemTableWithSilkTouch(block, ModBlock.COBBLED_CLOUDSHALE.base()));
        dropSelf(ModBlock.POINTED_CLOUDSHALE);
        add(ModBlock.CLOUDSHALE.base(), block -> createSingleItemTableWithSilkTouch(block, ModBlock.COBBLED_CLOUDSHALE.base()));
        dropSelf(ModBlock.CLOUDSHALE.button());
        dropSelf(ModBlock.CLOUDSHALE.wall());
        dropSelf(ModBlock.CLOUDSHALE.slab());
        dropSelf(ModBlock.CLOUDSHALE.stairs());
        dropSelf(ModBlock.CLOUDSHALE.pressurePlate());
        createStoneTypeLootTables(ModBlock.COBBLED_CLOUDSHALE);
        createStoneTypeLootTables(ModBlock.MOSSY_COBBLED_CLOUDSHALE);
        createStoneTypeLootTables(ModBlock.CHERRY_COBBLED_CLOUDSHALE);

        createWoodTypeLootTables(ModBlock.GOLDENLEAF_PLANKS);
        add(ModBlock.GOLDENLEAF_LEAVES, createLeavesDrops(ModBlock.GOLDENLEAF_LEAVES, ModBlock.GOLDENLEAF_SAPLING, 0.0625f));
        dropSelf(ModBlock.GOLDENLEAF_SAPLING);
        add(ModBlock.POTTED_GOLDENLEAF_SAPLING, createPotFlowerItemTable(ModBlock.GOLDENLEAF_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlock.SAKURA_PLANKS);
        add(ModBlock.SAKURA_LEAVES, createLeavesDrops(ModBlock.SAKURA_LEAVES, ModBlock.SAKURA_SAPLING, 0.0625f));
        dropSelf(ModBlock.SAKURA_SAPLING);
        add(ModBlock.POTTED_SAKURA_SAPLING, createPotFlowerItemTable(ModBlock.SAKURA_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlock.FRIGID_PLANKS);
        add(ModBlock.FRIGID_LEAVES, createLeavesDrops(ModBlock.FRIGID_LEAVES, ModBlock.FRIGID_SAPLING, 0.0625f));
        dropSelf(ModBlock.FRIGID_SAPLING);
        add(ModBlock.POTTED_FRIGID_SAPLING, createPotFlowerItemTable(ModBlock.FRIGID_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlock.ARBOREAL_CACTUS_PLANKS);
        //add(ModBlock.ARBOREAL_CACTUS_FRUIT, createCropDrops(ModBlock.ARBOREAL_CACTUS_FRUIT, ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), LootItemCondition.DIRECT_CODEC.));
        add(ModBlock.POTTED_ARBOREAL_CACTUS, createPotFlowerItemTable(ModBlock.ARBOREAL_CACTUS_FRUIT.asItem()));
    }

    private void createStoneTypeLootTables(ModBlock.StoneSetBlocks stoneSetBlocks) {
        dropSelf(stoneSetBlocks.base());
        dropSelf(stoneSetBlocks.button());
        dropSelf(stoneSetBlocks.wall());
        dropSelf(stoneSetBlocks.slab());
        dropSelf(stoneSetBlocks.stairs());
        dropSelf(stoneSetBlocks.pressurePlate());
    }

    private void createWoodTypeLootTables(ModBlock.WoodSetBlocks woodSetBlocks) {
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