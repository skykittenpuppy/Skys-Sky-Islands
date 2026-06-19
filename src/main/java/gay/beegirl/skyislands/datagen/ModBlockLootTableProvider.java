package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.world.level.block.ModBlocks;
import gay.beegirl.skyislands.world.item.ModItems;
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
        createStoneSetLootTables(ModBlocks.COBBLED_CLOUDSHALE);
        createStoneSetLootTables(ModBlocks.MOSSY_COBBLED_CLOUDSHALE);
        createStoneSetLootTables(ModBlocks.CHERRY_COBBLED_CLOUDSHALE);

        createLogSetLootTables(ModBlocks.GOLDENLEAF_LOGS);
        createWoodSetLootTables(ModBlocks.GOLDENLEAF_PLANKS);
        add(ModBlocks.GOLDENLEAF_LEAVES.get(), createLeavesDrops(ModBlocks.GOLDENLEAF_LEAVES.get(), ModBlocks.GOLDENLEAF_SAPLING.get(), 0.0625f));
        dropSelf(ModBlocks.GOLDENLEAF_SAPLING.get());
        add(ModBlocks.POTTED_GOLDENLEAF_SAPLING.get(), createPotFlowerItemTable(ModBlocks.GOLDENLEAF_SAPLING.get()));

        createLogSetLootTables(ModBlocks.SAKURA_LOGS);
        createWoodSetLootTables(ModBlocks.SAKURA_PLANKS);
        add(ModBlocks.SAKURA_LEAVES.get(), createLeavesDrops(ModBlocks.SAKURA_LEAVES.get(), ModBlocks.SAKURA_SAPLING.get(), 0.0625f));
        dropSelf(ModBlocks.SAKURA_SAPLING.get());
        add(ModBlocks.POTTED_SAKURA_SAPLING.get(), createPotFlowerItemTable(ModBlocks.SAKURA_SAPLING.get()));

        createLogSetLootTables(ModBlocks.FRIGID_LOGS);
        createWoodSetLootTables(ModBlocks.FRIGID_PLANKS);
        add(ModBlocks.FRIGID_LEAVES.get(), createLeavesDrops(ModBlocks.FRIGID_LEAVES.get(), ModBlocks.FRIGID_SAPLING.get(), 0.0625f));
        dropSelf(ModBlocks.FRIGID_SAPLING.get());
        add(ModBlocks.POTTED_FRIGID_SAPLING.get(), createPotFlowerItemTable(ModBlocks.FRIGID_SAPLING.get()));

        createCactusSetLootTables(ModBlocks.ARBOREAL_CACTUSES);
        createWoodSetLootTables(ModBlocks.ARBOREAL_CACTUS_PLANKS);
        dropSelf(ModBlocks.ARBOREAL_CACTUS_FRUIT.get());
        add(ModBlocks.POTTED_ARBOREAL_CACTUS.get(), createPotFlowerItemTable(ModBlocks.ARBOREAL_CACTUS_FRUIT.get()));
    }

    private void createStoneSetLootTables(ModBlocks.StoneBlockSet stoneSetBlocks) {
        dropSelf(stoneSetBlocks.base().get());
        dropSelf(stoneSetBlocks.button().get());
        dropSelf(stoneSetBlocks.wall().get());
        dropSelf(stoneSetBlocks.slab().get());
        dropSelf(stoneSetBlocks.stairs().get());
        dropSelf(stoneSetBlocks.pressurePlate().get());
    }
    private void createLogSetLootTables(ModBlocks.LogBlockSet logSetBlocks) {
        dropSelf(logSetBlocks.log().get());
        dropSelf(logSetBlocks.wood().get());
        dropSelf(logSetBlocks.strippedLog().get());
        dropSelf(logSetBlocks.strippedWood().get());
    }
    private void createCactusSetLootTables(ModBlocks.CactusBlockSet cactusSetBlocks) {
        dropSelf(cactusSetBlocks.cactus().get());
        dropSelf(cactusSetBlocks.despinedCactus().get());
    }
    private void createWoodSetLootTables(ModBlocks.WoodBlockSet woodSetBlocks) {
        dropSelf(woodSetBlocks.base().get());
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
