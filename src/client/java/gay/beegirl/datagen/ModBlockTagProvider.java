package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlock.RAW_ZONAITE_BLOCK, ModBlock.REFINED_ZONAITE_BLOCK)
                .add(ModBlock.STONE_ZONAITE_ORE, ModBlock.DEEPSLATE_ZONAITE_ORE, ModBlock.SKYSTONE_ZONAITE_ORE);
    }
}
