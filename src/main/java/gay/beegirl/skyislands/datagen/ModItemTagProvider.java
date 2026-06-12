package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.item.ModItems;
import gay.beegirl.skyislands.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.ALEXANDRITE.get());

        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModTags.Items.GOLDENLEAF_LOGS)
                .addTag(ModTags.Items.SAKURA_LOGS)
                .addTag(ModTags.Items.FRIGID_LOGS)
                .addTag(ModTags.Items.ARBOREAL_CACTUS_STEMS);

        tag(ItemTags.PLANKS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.planks().asItem())
                .add(ModBlocks.SAKURA_PLANKS.planks().asItem())
                .add(ModBlocks.FRIGID_PLANKS.planks().asItem());
                //.add(ModBlocks.ARBOREAL_CACTUS_PLANKS.planks().asItem());
        tag(ItemTags.SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.standingSign().asItem())
                .add(ModBlocks.SAKURA_PLANKS.standingSign().asItem())
                .add(ModBlocks.FRIGID_PLANKS.standingSign().asItem());
                //.add(ModBlocks.ARBOREAL_CACTUS_PLANKS.standingSign().asItem());
        tag(ItemTags.HANGING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.hangingSign().asItem())
                .add(ModBlocks.SAKURA_PLANKS.hangingSign().asItem())
                .add(ModBlocks.FRIGID_PLANKS.hangingSign().asItem());
                //.add(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingSign().asItem());

        //tag(ItemTags.BOATS)
        //        .add(ModItems.GOLDENLEAF_BOAT)
        //        .add(ModItems.SAKURA_BOAT)
        //        .add(ModItems.FRIGID_BOAT)
        //        .add(ModItems.ARBOREAL_CACTUS_BOAT);
        //tag(ItemTags.CHEST_BOATS)
        //        .add(ModItems.GOLDENLEAF_CHEST_BOAT)
        //        .add(ModItems.SAKURA_CHEST_BOAT)
        //        .add(ModItems.FRIGID_CHEST_BOAT)
        //        .add(ModItems.ARBOREAL_CACTUS_CHEST_BOAT);

        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ALEXANDRITE.get());

        tag(ModTags.Items.GOLDENLEAF_LOGS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.log().asItem(), ModBlocks.GOLDENLEAF_PLANKS.wood().asItem(), ModBlocks.GOLDENLEAF_PLANKS.strippedLog().asItem(), ModBlocks.GOLDENLEAF_PLANKS.strippedWood().asItem());
        tag(ModTags.Items.SAKURA_LOGS)
                .add(ModBlocks.SAKURA_PLANKS.log().asItem(), ModBlocks.SAKURA_PLANKS.wood().asItem(), ModBlocks.SAKURA_PLANKS.strippedLog().asItem(), ModBlocks.SAKURA_PLANKS.strippedWood().asItem());
        tag(ModTags.Items.FRIGID_LOGS)
                .add(ModBlocks.FRIGID_PLANKS.log().asItem(), ModBlocks.FRIGID_PLANKS.wood().asItem(), ModBlocks.FRIGID_PLANKS.strippedLog().asItem(), ModBlocks.FRIGID_PLANKS.strippedWood().asItem());
        //tag(ModTags.Items.ARBOREAL_CACTUS_STEMS)
        //        .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.log().asItem(), ModBlocks.ARBOREAL_CACTUS_PLANKS.wood().asItem(), ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedLog().asItem(), ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedWood().asItem());
    }
}
