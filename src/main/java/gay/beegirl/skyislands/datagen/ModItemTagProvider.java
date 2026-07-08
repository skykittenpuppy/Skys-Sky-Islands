package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.tags.ModBlockTags;
import gay.beegirl.skyislands.tags.ModItemTags;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import gay.beegirl.skyislands.world.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ItemTags.PLANKS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.base().asItem())
                .add(ModBlocks.SAKURA_PLANKS.base().asItem())
                .add(ModBlocks.FRIGID_PLANKS.base().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.base().asItem());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.button().asItem())
                .add(ModBlocks.SAKURA_PLANKS.button().asItem())
                .add(ModBlocks.FRIGID_PLANKS.button().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.button().asItem());
        tag(ItemTags.WOODEN_DOORS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.door().asItem())
                .add(ModBlocks.SAKURA_PLANKS.door().asItem())
                .add(ModBlocks.FRIGID_PLANKS.door().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.door().asItem());
        tag(ItemTags.WOODEN_FENCES)
                .add(ModBlocks.GOLDENLEAF_PLANKS.fence().asItem())
                .add(ModBlocks.SAKURA_PLANKS.fence().asItem())
                .add(ModBlocks.FRIGID_PLANKS.fence().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.fence().asItem());
        tag(ItemTags.FENCE_GATES)
                .add(ModBlocks.GOLDENLEAF_PLANKS.fenceGate().asItem())
                .add(ModBlocks.SAKURA_PLANKS.fenceGate().asItem())
                .add(ModBlocks.FRIGID_PLANKS.fenceGate().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.fenceGate().asItem());
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
        tag(ItemTags.WOODEN_SLABS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.slab().asItem())
                .add(ModBlocks.SAKURA_PLANKS.slab().asItem())
                .add(ModBlocks.FRIGID_PLANKS.slab().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.slab().asItem());
        tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.stairs().asItem())
                .add(ModBlocks.SAKURA_PLANKS.stairs().asItem())
                .add(ModBlocks.FRIGID_PLANKS.stairs().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.stairs().asItem());
        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.GOLDENLEAF_PLANKS.pressurePlate().asItem())
                .add(ModBlocks.SAKURA_PLANKS.pressurePlate().asItem())
                .add(ModBlocks.FRIGID_PLANKS.pressurePlate().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.pressurePlate().asItem());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.trapdoor().asItem())
                .add(ModBlocks.SAKURA_PLANKS.trapdoor().asItem())
                .add(ModBlocks.FRIGID_PLANKS.trapdoor().asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.trapdoor().asItem());

        copy(ModBlockTags.GOLDENLEAF_LOGS, ModItemTags.GOLDENLEAF_LOGS);
        copy(ModBlockTags.SAKURA_LOGS, ModItemTags.SAKURA_LOGS);
        copy(ModBlockTags.FRIGID_LOGS, ModItemTags.FRIGID_LOGS);
        copy(ModBlockTags.ARBOREAL_CACTUSES, ModItemTags.ARBOREAL_CACTUSES);

        tag(ItemTags.LOGS_THAT_BURN)
                .addTag(ModItemTags.GOLDENLEAF_LOGS)
                .addTag(ModItemTags.SAKURA_LOGS)
                .addTag(ModItemTags.FRIGID_LOGS);

        tag(ItemTags.LEAVES)
                .add(ModBlocks.GOLDENLEAF_LEAVES.asItem())
                .add(ModBlocks.SAKURA_LEAVES.asItem())
                .add(ModBlocks.FRIGID_LEAVES.asItem());
        tag(ItemTags.SAPLINGS)
                .add(ModBlocks.GOLDENLEAF_SAPLING.asItem())
                .add(ModBlocks.SAKURA_SAPLING.asItem())
                .add(ModBlocks.FRIGID_SAPLING.asItem())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANT.asItem());

        tag(ItemTags.WALLS)
                .add(ModBlocks.COBBLED_CLOUDSHALE.wall().asItem())
                .add(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.wall().asItem())
                .add(ModBlocks.CLOUDSHALE_BRICK.wall().asItem());
        tag(ItemTags.SLABS)
                .add(ModBlocks.COBBLED_CLOUDSHALE.slab().asItem())
                .add(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.slab().asItem())
                .add(ModBlocks.CLOUDSHALE_BRICK.slab().asItem());
        tag(ItemTags.STAIRS)
                .add(ModBlocks.COBBLED_CLOUDSHALE.stairs().asItem())
                .add(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.stairs().asItem())
                .add(ModBlocks.CLOUDSHALE_BRICK.stairs().asItem());

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
        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.ALEXANDRITE.get());
        tag(ModItemTags.ALEXANDRITE_ORES)
                .add(ModBlocks.STONE_ALEXANDRITE_ORE.asItem(), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.asItem(), ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE.asItem());
        tag(ItemTags.STONE_TOOL_MATERIALS)
                .add(ModBlocks.COBBLED_CLOUDSHALE.base().asItem());
        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(ModBlocks.COBBLED_CLOUDSHALE.base().asItem());
        tag(ItemTags.DECORATED_POT_SHERDS)
                .add(ModItems.TESTING_POTTERY_SHERD.get());
        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.ALEXANDRITE.get());
        tag(ItemTags.TRIM_TEMPLATES)
                .add(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get());

    }
}
