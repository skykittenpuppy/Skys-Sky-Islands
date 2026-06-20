package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.tags.ModItemTags;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import gay.beegirl.skyislands.world.item.ModItems;
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
                .addTag(ModItemTags.GOLDENLEAF_LOGS)
                .addTag(ModItemTags.SAKURA_LOGS)
                .addTag(ModItemTags.FRIGID_LOGS)
                .addTag(ModItemTags.ARBOREAL_CACTUSES);

        tag(ItemTags.PLANKS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.base().asItem())
                .add(ModBlocks.SAKURA_PLANKS.base().asItem())
                .add(ModBlocks.FRIGID_PLANKS.base().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.base().asItem());
        tag(ItemTags.SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.standingSign().asItem())
                .add(ModBlocks.SAKURA_PLANKS.standingSign().asItem())
                .add(ModBlocks.FRIGID_PLANKS.standingSign().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.standingSign().asItem());
        tag(ItemTags.HANGING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.hangingSign().asItem())
                .add(ModBlocks.SAKURA_PLANKS.hangingSign().asItem())
                .add(ModBlocks.FRIGID_PLANKS.hangingSign().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingSign().asItem());

        tag(ItemTags.BOATS)
                .add(ModItems.GOLDENLEAF_BOAT.asItem())
                .add(ModItems.SAKURA_BOAT.asItem())
                .add(ModItems.FRIGID_BOAT.asItem())
                .add(ModItems.ARBOREAL_CACTUS_BOAT.asItem());
        tag(ItemTags.CHEST_BOATS)
                .add(ModItems.GOLDENLEAF_CHEST_BOAT.asItem())
                .add(ModItems.SAKURA_CHEST_BOAT.asItem())
                .add(ModItems.FRIGID_CHEST_BOAT.asItem())
                .add(ModItems.ARBOREAL_CACTUS_CHEST_BOAT.asItem());

        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ALEXANDRITE.get());
        tag(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get());

        tag(ModItemTags.GOLDENLEAF_LOGS)
                .add(ModBlocks.GOLDENLEAF_LOGS.log().asItem(), ModBlocks.GOLDENLEAF_LOGS.wood().asItem(), ModBlocks.GOLDENLEAF_LOGS.strippedLog().asItem(), ModBlocks.GOLDENLEAF_LOGS.strippedWood().asItem());
        tag(ModItemTags.SAKURA_LOGS)
                .add(ModBlocks.SAKURA_LOGS.log().asItem(), ModBlocks.SAKURA_LOGS.wood().asItem(), ModBlocks.SAKURA_LOGS.strippedLog().asItem(), ModBlocks.SAKURA_LOGS.strippedWood().asItem());
        tag(ModItemTags.FRIGID_LOGS)
                .add(ModBlocks.FRIGID_LOGS.log().asItem(), ModBlocks.FRIGID_LOGS.wood().asItem(), ModBlocks.FRIGID_LOGS.strippedLog().asItem(), ModBlocks.FRIGID_LOGS.strippedWood().asItem());
        tag(ModItemTags.ARBOREAL_CACTUSES)
                .add(ModBlocks.ARBOREAL_CACTUSES.cactus().asItem(), ModBlocks.ARBOREAL_CACTUSES.despinedCactus().asItem());
    }
}
