package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
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

        dropSelf(ModBlock.GOLDENLEAF_LOG);
        dropSelf(ModBlock.SAKURA_LOG);
        dropSelf(ModBlock.FRIGID_LOG);
        dropSelf(ModBlock.ARBOREAL_CACTUS_STEM);
        //add(ModBlock.ARBOREAL_CACTUS_FRUIT, createCropDrops(ModBlock.ARBOREAL_CACTUS_FRUIT, ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), LootItemCondition.DIRECT_CODEC.));
        add(ModBlock.POTTED_ARBOREAL_CACTUS, createPotFlowerItemTable(ModBlock.ARBOREAL_CACTUS_FRUIT.asItem()));
    }
}
