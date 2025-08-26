package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import gay.beegirl.util.ModTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
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

        add(ModBlock.CLOUDSHALE_GRASS, block -> createSingleItemTableWithSilkTouch(block, ModBlock.COBBLED_CLOUDSHALE));
        add(ModBlock.CLOUDSHALE_CHERRY_GRASS, block -> createSingleItemTableWithSilkTouch(block, ModBlock.COBBLED_CLOUDSHALE));
        add(ModBlock.CLOUDSHALE, block -> createSingleItemTableWithSilkTouch(block, ModBlock.COBBLED_CLOUDSHALE));
        dropSelf(ModBlock.COBBLED_CLOUDSHALE);
        dropSelf(ModBlock.MOSSY_COBBLED_CLOUDSHALE);
        dropSelf(ModBlock.CHERRY_COBBLED_CLOUDSHALE);

        createWoodTypeLootTables(ModBlock.GOLDENLEAF_LOG, ModBlock.GOLDENLEAF_WOOD, ModBlock.STRIPPED_GOLDENLEAF_LOG, ModBlock.STRIPPED_GOLDENLEAF_WOOD, ModBlock.GOLDENLEAF_PLANKS, ModBlock.GOLDENLEAF_STAIRS, ModBlock.GOLDENLEAF_SLAB, ModBlock.GOLDENLEAF_BUTTON, ModBlock.GOLDENLEAF_PRESSURE_PLATE, ModBlock.GOLDENLEAF_FENCE, ModBlock.GOLDENLEAF_FENCE_GATE, ModBlock.GOLDENLEAF_DOOR, ModBlock.GOLDENLEAF_TRAPDOOR);
        add(ModBlock.GOLDENLEAF_LEAVES, createLeavesDrops(ModBlock.GOLDENLEAF_LEAVES, ModBlock.GOLDENLEAF_SAPLING, 0.0625f));
        dropSelf(ModBlock.GOLDENLEAF_SAPLING);
        add(ModBlock.POTTED_GOLDENLEAF_SAPLING, createPotFlowerItemTable(ModBlock.GOLDENLEAF_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlock.SAKURA_LOG, ModBlock.SAKURA_WOOD, ModBlock.STRIPPED_SAKURA_LOG, ModBlock.STRIPPED_SAKURA_WOOD, ModBlock.SAKURA_PLANKS, ModBlock.SAKURA_STAIRS, ModBlock.SAKURA_SLAB, ModBlock.SAKURA_BUTTON, ModBlock.SAKURA_PRESSURE_PLATE, ModBlock.SAKURA_FENCE, ModBlock.SAKURA_FENCE_GATE, ModBlock.SAKURA_DOOR, ModBlock.SAKURA_TRAPDOOR);
        add(ModBlock.SAKURA_LEAVES, createLeavesDrops(ModBlock.SAKURA_LEAVES, ModBlock.SAKURA_SAPLING, 0.0625f));
        dropSelf(ModBlock.SAKURA_SAPLING);
        add(ModBlock.POTTED_SAKURA_SAPLING, createPotFlowerItemTable(ModBlock.SAKURA_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlock.FRIGID_LOG, ModBlock.FRIGID_WOOD, ModBlock.STRIPPED_FRIGID_LOG, ModBlock.STRIPPED_FRIGID_WOOD, ModBlock.FRIGID_PLANKS, ModBlock.FRIGID_STAIRS, ModBlock.FRIGID_SLAB, ModBlock.FRIGID_BUTTON, ModBlock.FRIGID_PRESSURE_PLATE, ModBlock.FRIGID_FENCE, ModBlock.FRIGID_FENCE_GATE, ModBlock.FRIGID_DOOR, ModBlock.FRIGID_TRAPDOOR);
        add(ModBlock.FRIGID_LEAVES, createLeavesDrops(ModBlock.FRIGID_LEAVES, ModBlock.FRIGID_SAPLING, 0.0625f));
        dropSelf(ModBlock.FRIGID_SAPLING);
        add(ModBlock.POTTED_FRIGID_SAPLING, createPotFlowerItemTable(ModBlock.FRIGID_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlock.ARBOREAL_CACTUS_STEM, ModBlock.ARBOREAL_CACTUS_HYPHAE, ModBlock.STRIPPED_ARBOREAL_CACTUS_STEM, ModBlock.STRIPPED_ARBOREAL_CACTUS_HYPHAE, ModBlock.ARBOREAL_CACTUS_PLANKS, ModBlock.ARBOREAL_CACTUS_STAIRS, ModBlock.ARBOREAL_CACTUS_SLAB, ModBlock.ARBOREAL_CACTUS_BUTTON, ModBlock.ARBOREAL_CACTUS_PRESSURE_PLATE, ModBlock.ARBOREAL_CACTUS_FENCE, ModBlock.ARBOREAL_CACTUS_FENCE_GATE, ModBlock.ARBOREAL_CACTUS_DOOR, ModBlock.ARBOREAL_CACTUS_TRAPDOOR);
        //add(ModBlock.ARBOREAL_CACTUS_FRUIT, createCropDrops(ModBlock.ARBOREAL_CACTUS_FRUIT, ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), LootItemCondition.DIRECT_CODEC.));
        add(ModBlock.POTTED_ARBOREAL_CACTUS, createPotFlowerItemTable(ModBlock.ARBOREAL_CACTUS_FRUIT.asItem()));
    }

    private void createWoodTypeLootTables(Block log, Block wood, Block strippedLog, Block strippedWood, Block planks, Block stairs, Block slab, Block button, Block pressurePlate, Block fence, Block fenceGate, Block door, Block trapdoor) {
        dropSelf(log);
        dropSelf(wood);
        dropSelf(strippedLog);
        dropSelf(strippedWood);
        dropSelf(planks);
        dropSelf(stairs);
        add(slab, createSlabItemTable(slab));
        dropSelf(button);
        dropSelf(pressurePlate);
        dropSelf(fence);
        dropSelf(fenceGate);
        add(door, createDoorTable(door));
        dropSelf(trapdoor);
    }
}
