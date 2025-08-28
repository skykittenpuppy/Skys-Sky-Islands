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
                .add(ModBlock.GOLDENLEAF_PLANKS.planks().asItem())
                .add(ModBlock.SAKURA_PLANKS.planks().asItem())
                .add(ModBlock.FRIGID_PLANKS.planks().asItem())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.planks().asItem());
        valueLookupBuilder(ItemTags.SIGNS)
                .add(ModBlock.GOLDENLEAF_PLANKS.standingSign().asItem())
                .add(ModBlock.SAKURA_PLANKS.standingSign().asItem())
                .add(ModBlock.FRIGID_PLANKS.standingSign().asItem())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.standingSign().asItem());
        valueLookupBuilder(ItemTags.HANGING_SIGNS)
                .add(ModBlock.GOLDENLEAF_PLANKS.hangingSign().asItem())
                .add(ModBlock.SAKURA_PLANKS.hangingSign().asItem())
                .add(ModBlock.FRIGID_PLANKS.hangingSign().asItem())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.hangingSign().asItem());

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
                .add(ModBlock.GOLDENLEAF_PLANKS.log().asItem(), ModBlock.GOLDENLEAF_PLANKS.wood().asItem(), ModBlock.GOLDENLEAF_PLANKS.strippedLog().asItem(), ModBlock.GOLDENLEAF_PLANKS.strippedWood().asItem());
        valueLookupBuilder(ModTag.Items.SAKURA_LOGS)
                .add(ModBlock.SAKURA_PLANKS.log().asItem(), ModBlock.SAKURA_PLANKS.wood().asItem(), ModBlock.SAKURA_PLANKS.strippedLog().asItem(), ModBlock.SAKURA_PLANKS.strippedWood().asItem());
        valueLookupBuilder(ModTag.Items.FRIGID_LOGS)
                .add(ModBlock.FRIGID_PLANKS.log().asItem(), ModBlock.FRIGID_PLANKS.wood().asItem(), ModBlock.FRIGID_PLANKS.strippedLog().asItem(), ModBlock.FRIGID_PLANKS.strippedWood().asItem());
        valueLookupBuilder(ModTag.Items.ARBOREAL_CACTUS_STEMS)
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.log().asItem(), ModBlock.ARBOREAL_CACTUS_PLANKS.wood().asItem(), ModBlock.ARBOREAL_CACTUS_PLANKS.strippedLog().asItem(), ModBlock.ARBOREAL_CACTUS_PLANKS.strippedWood().asItem());
    }
}
