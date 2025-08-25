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
                .add(ModBlock.RAW_ALEXANDRITE_BLOCK, ModBlock.ALEXANDRITE_BLOCK)
                .add(ModBlock.STONE_ALEXANDRITE_ORE, ModBlock.DEEPSLATE_ALEXANDRITE_ORE, ModBlock.CLOUDSHALE_ALEXANDRITE_ORE);

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .add(ModBlock.GOLDENLEAF_LOG)
                .add(ModBlock.SAKURA_LOG)
                .add(ModBlock.FRIGID_LOG);

        valueLookupBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlock.POTTED_ARBOREAL_CACTUS);
    }
}
