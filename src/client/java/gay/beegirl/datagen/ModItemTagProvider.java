package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import gay.beegirl.util.ModTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
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
                .addTag(ModTag.Items.GOLDENLEAF_LOGS)
                .addTag(ModTag.Items.SAKURA_LOGS)
                .addTag(ModTag.Items.FRIGID_LOGS)
                .addTag(ModTag.Items.ARBOREAL_CACTUS_STEMS);

        valueLookupBuilder(ItemTags.PLANKS)
                .add(ModBlock.GOLDENLEAF_PLANKS.asItem())
                .add(ModBlock.SAKURA_PLANKS.asItem())
                .add(ModBlock.FRIGID_PLANKS.asItem())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.asItem());

        valueLookupBuilder(ItemTags.BOATS)
                .add(ModItem.GOLDENLEAF_BOAT)
                .add(ModItem.SAKURA_BOAT)
                .add(ModItem.FRIGID_BOAT)
                .add(ModItem.ARBOREAL_CACTUS_BOAT);

        valueLookupBuilder(ItemTags.CHEST_BOATS)
                .add(ModItem.GOLDENLEAF_CHEST_BOAT)
                .add(ModItem.SAKURA_CHEST_BOAT)
                .add(ModItem.FRIGID_CHEST_BOAT)
                .add(ModItem.ARBOREAL_CACTUS_CHEST_BOAT);

        valueLookupBuilder(ModTag.Items.GOLDENLEAF_LOGS)
                .add(ModBlock.GOLDENLEAF_LOG.asItem(), ModBlock.GOLDENLEAF_WOOD.asItem(), ModBlock.STRIPPED_GOLDENLEAF_LOG.asItem(), ModBlock.STRIPPED_GOLDENLEAF_WOOD.asItem());
        valueLookupBuilder(ModTag.Items.SAKURA_LOGS)
                .add(ModBlock.SAKURA_LOG.asItem(), ModBlock.SAKURA_WOOD.asItem(), ModBlock.STRIPPED_SAKURA_LOG.asItem(), ModBlock.STRIPPED_SAKURA_WOOD.asItem());
        valueLookupBuilder(ModTag.Items.FRIGID_LOGS)
                .add(ModBlock.FRIGID_LOG.asItem(), ModBlock.FRIGID_WOOD.asItem(), ModBlock.STRIPPED_FRIGID_LOG.asItem(), ModBlock.STRIPPED_FRIGID_WOOD.asItem());
        valueLookupBuilder(ModTag.Items.ARBOREAL_CACTUS_STEMS)
                .add(ModBlock.ARBOREAL_CACTUS_STEM.asItem(), ModBlock.ARBOREAL_CACTUS_HYPHAE.asItem(), ModBlock.STRIPPED_ARBOREAL_CACTUS_STEM.asItem(), ModBlock.STRIPPED_ARBOREAL_CACTUS_HYPHAE.asItem());
    }
}
