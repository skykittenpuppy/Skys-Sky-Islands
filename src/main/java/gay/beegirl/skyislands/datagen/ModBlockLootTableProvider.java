package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
        dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
        add(ModBlocks.STONE_ALEXANDRITE_ORE.get(), createOreDrop(ModBlocks.STONE_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));
        add(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), createOreDrop(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));
        add(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE.get(), createOreDrop(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));

        add(ModBlocks.CLOUDSHALE_GRASS.get(), block -> createSingleItemTableWithSilkTouch(block, ModBlocks.COBBLED_CLOUDSHALE.base().get()));
        add(ModBlocks.CLOUDSHALE_CHERRY_GRASS.get(), block -> createSingleItemTableWithSilkTouch(block, ModBlocks.COBBLED_CLOUDSHALE.base().get()));
        dropSelf(ModBlocks.POINTED_CLOUDSHALE.get());
        add(ModBlocks.CLOUDSHALE.base().get(), block -> createSingleItemTableWithSilkTouch(block, ModBlocks.COBBLED_CLOUDSHALE.base().get()));
        dropSelf(ModBlocks.CLOUDSHALE.button().get());
        dropSelf(ModBlocks.CLOUDSHALE.wall().get());
        dropSelf(ModBlocks.CLOUDSHALE.slab().get());
        dropSelf(ModBlocks.CLOUDSHALE.stairs().get());
        dropSelf(ModBlocks.CLOUDSHALE.pressurePlate().get());
        createStoneTypeLootTables(ModBlocks.COBBLED_CLOUDSHALE);
        createStoneTypeLootTables(ModBlocks.MOSSY_COBBLED_CLOUDSHALE);
        createStoneTypeLootTables(ModBlocks.CHERRY_COBBLED_CLOUDSHALE);

        createWoodTypeLootTables(ModBlocks.GOLDENLEAF_PLANKS);
        add(ModBlocks.GOLDENLEAF_LEAVES.get(), createLeavesDrops(ModBlocks.GOLDENLEAF_LEAVES.get(), ModBlocks.GOLDENLEAF_SAPLING.get(), 0.0625f));
        dropSelf(ModBlocks.GOLDENLEAF_SAPLING.get());
        add(ModBlocks.POTTED_GOLDENLEAF_SAPLING.get(), createPotFlowerItemTable(ModBlocks.GOLDENLEAF_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlocks.SAKURA_PLANKS);
        add(ModBlocks.SAKURA_LEAVES.get(), createLeavesDrops(ModBlocks.SAKURA_LEAVES.get(), ModBlocks.SAKURA_SAPLING.get(), 0.0625f));
        dropSelf(ModBlocks.SAKURA_SAPLING.get());
        add(ModBlocks.POTTED_SAKURA_SAPLING.get(), createPotFlowerItemTable(ModBlocks.SAKURA_SAPLING.asItem()));

        createWoodTypeLootTables(ModBlocks.FRIGID_PLANKS);
        add(ModBlocks.FRIGID_LEAVES.get(), createLeavesDrops(ModBlocks.FRIGID_LEAVES.get(), ModBlocks.FRIGID_SAPLING.get(), 0.0625f));
        dropSelf(ModBlocks.FRIGID_SAPLING.get());
        add(ModBlocks.POTTED_FRIGID_SAPLING.get(), createPotFlowerItemTable(ModBlocks.FRIGID_SAPLING.asItem()));

        //createWoodTypeLootTables(ModBlocks.ARBOREAL_CACTUS_PLANKS);
        //add(ModBlock.ARBOREAL_CACTUS_FRUIT, createCropDrops(ModBlock.ARBOREAL_CACTUS_FRUIT, ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), LootItemCondition.DIRECT_CODEC.));
        //add(ModBlocks.POTTED_ARBOREAL_CACTUS, createPotFlowerItemTable(ModBlocks.ARBOREAL_CACTUS_FRUIT.asItem()));
    }

    private void createStoneTypeLootTables(ModBlocks.StoneSetBlocks stoneSetBlocks) {
        dropSelf(stoneSetBlocks.base().get());
        dropSelf(stoneSetBlocks.button().get());
        dropSelf(stoneSetBlocks.wall().get());
        dropSelf(stoneSetBlocks.slab().get());
        dropSelf(stoneSetBlocks.stairs().get());
        dropSelf(stoneSetBlocks.pressurePlate().get());
    }

    private void createWoodTypeLootTables(ModBlocks.WoodSetBlocks woodSetBlocks) {
        dropSelf(woodSetBlocks.log().get());
        dropSelf(woodSetBlocks.wood().get());
        dropSelf(woodSetBlocks.strippedLog().get());
        dropSelf(woodSetBlocks.strippedWood().get());
        dropSelf(woodSetBlocks.planks().get());
        dropSelf(woodSetBlocks.button().get());
        add(woodSetBlocks.door().get(), createDoorTable(woodSetBlocks.door().get()));
        dropSelf(woodSetBlocks.fence().get());
        dropSelf(woodSetBlocks.fenceGate().get());
        dropSelf(woodSetBlocks.standingSign().get());
        dropSelf(woodSetBlocks.hangingSign().get());
        add(woodSetBlocks.slab().get(), createSlabItemTable(woodSetBlocks.slab().get()));
        dropSelf(woodSetBlocks.stairs().get());
        dropSelf(woodSetBlocks.pressurePlate().get());
        dropSelf(woodSetBlocks.trapdoor().get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
