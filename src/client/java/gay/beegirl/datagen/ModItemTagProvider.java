package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItem.RAW_ALEXANDRITE, ModItem.ALEXANDRITE);
        valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlock.GOLDENLEAF_LOG.asItem())
                .add(ModBlock.SAKURA_LOG.asItem())
                .add(ModBlock.FRIGID_LOG.asItem())
                .add(ModBlock.ARBOREAL_CACTUS_STEM.asItem());
    }
}
