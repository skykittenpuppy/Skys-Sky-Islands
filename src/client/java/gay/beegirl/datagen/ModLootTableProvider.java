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
        dropSelf(ModBlock.RAW_ZONAITE_BLOCK);
        dropSelf(ModBlock.REFINED_ZONAITE_BLOCK);
        add(ModBlock.STONE_ZONAITE_ORE, createOreDrop(ModBlock.STONE_ZONAITE_ORE, ModItem.RAW_ZONAITE));
        add(ModBlock.DEEPSLATE_ZONAITE_ORE, createOreDrop(ModBlock.DEEPSLATE_ZONAITE_ORE, ModItem.RAW_ZONAITE));
        add(ModBlock.SKYSTONE_ZONAITE_ORE, createOreDrop(ModBlock.SKYSTONE_ZONAITE_ORE, ModItem.RAW_ZONAITE));
    }
}
