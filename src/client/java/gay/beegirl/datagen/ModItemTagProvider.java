package gay.beegirl.datagen;

import gay.beegirl.block.ModBlocks;
import gay.beegirl.item.ModItems;
import gay.beegirl.util.ModTags;
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
                .add(ModItems.RAW_ALEXANDRITE, ModItems.ALEXANDRITE);

        valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.GOLDENLEAF_LOGS)
                .addTag(ModTags.Items.SAKURA_LOGS)
                .addTag(ModTags.Items.FRIGID_LOGS)
                .addTag(ModTags.Items.ARBOREAL_CACTUS_STEMS);

        valueLookupBuilder(ItemTags.PLANKS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.planks().asItem())
                .add(ModBlocks.SAKURA_PLANKS.planks().asItem())
                .add(ModBlocks.FRIGID_PLANKS.planks().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.planks().asItem());
        valueLookupBuilder(ItemTags.SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.standingSign().asItem())
                .add(ModBlocks.SAKURA_PLANKS.standingSign().asItem())
                .add(ModBlocks.FRIGID_PLANKS.standingSign().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.standingSign().asItem());
        valueLookupBuilder(ItemTags.HANGING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.hangingSign().asItem())
                .add(ModBlocks.SAKURA_PLANKS.hangingSign().asItem())
                .add(ModBlocks.FRIGID_PLANKS.hangingSign().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingSign().asItem());

        valueLookupBuilder(ItemTags.BOATS)
                .add(ModItems.GOLDENLEAF_BOAT)
                .add(ModItems.SAKURA_BOAT)
                .add(ModItems.FRIGID_BOAT)
                .add(ModItems.ARBOREAL_CACTUS_BOAT);
        valueLookupBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.GOLDENLEAF_CHEST_BOAT)
                .add(ModItems.SAKURA_CHEST_BOAT)
                .add(ModItems.FRIGID_CHEST_BOAT)
                .add(ModItems.ARBOREAL_CACTUS_CHEST_BOAT);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ALEXANDRITE);

        valueLookupBuilder(ModTags.Items.GOLDENLEAF_LOGS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.log().asItem(), ModBlocks.GOLDENLEAF_PLANKS.wood().asItem(), ModBlocks.GOLDENLEAF_PLANKS.strippedLog().asItem(), ModBlocks.GOLDENLEAF_PLANKS.strippedWood().asItem());
        valueLookupBuilder(ModTags.Items.SAKURA_LOGS)
                .add(ModBlocks.SAKURA_PLANKS.log().asItem(), ModBlocks.SAKURA_PLANKS.wood().asItem(), ModBlocks.SAKURA_PLANKS.strippedLog().asItem(), ModBlocks.SAKURA_PLANKS.strippedWood().asItem());
        valueLookupBuilder(ModTags.Items.FRIGID_LOGS)
                .add(ModBlocks.FRIGID_PLANKS.log().asItem(), ModBlocks.FRIGID_PLANKS.wood().asItem(), ModBlocks.FRIGID_PLANKS.strippedLog().asItem(), ModBlocks.FRIGID_PLANKS.strippedWood().asItem());
        valueLookupBuilder(ModTags.Items.ARBOREAL_CACTUS_STEMS)
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.log().asItem(), ModBlocks.ARBOREAL_CACTUS_PLANKS.wood().asItem(), ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedLog().asItem(), ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedWood().asItem());
    }
}
