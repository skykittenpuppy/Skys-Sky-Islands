package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAW_ALEXANDRITE_BLOCK.get(), ModBlocks.ALEXANDRITE_BLOCK.get())
                .add(ModBlocks.STONE_ALEXANDRITE_ORE.get(), ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.GOLDENLEAF_LOGS)
                .addTag(ModTags.Blocks.SAKURA_LOGS)
                .addTag(ModTags.Blocks.FRIGID_LOGS)
                .addTag(ModTags.Blocks.ARBOREAL_CACTUSES);

        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_GOLDENLEAF_SAPLING.get())
                .add(ModBlocks.POTTED_SAKURA_SAPLING.get())
                .add(ModBlocks.POTTED_FRIGID_SAPLING.get())
                .add(ModBlocks.POTTED_ARBOREAL_CACTUS.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.base().get())
                .add(ModBlocks.SAKURA_PLANKS.base().get())
                .add(ModBlocks.FRIGID_PLANKS.base().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.base().get());
        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.GOLDENLEAF_PLANKS.fence().get())
                .add(ModBlocks.SAKURA_PLANKS.fence().get())
                .add(ModBlocks.FRIGID_PLANKS.fence().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.fence().get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.GOLDENLEAF_PLANKS.fenceGate().get())
                .add(ModBlocks.SAKURA_PLANKS.fenceGate().get())
                .add(ModBlocks.FRIGID_PLANKS.fenceGate().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.fenceGate().get());
        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.standingSign().get())
                .add(ModBlocks.SAKURA_PLANKS.standingSign().get())
                .add(ModBlocks.FRIGID_PLANKS.standingSign().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.standingSign().get());
        tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.wallSign().get())
                .add(ModBlocks.SAKURA_PLANKS.wallSign().get())
                .add(ModBlocks.FRIGID_PLANKS.wallSign().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.wallSign().get());
        tag(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.hangingSign().get())
                .add(ModBlocks.SAKURA_PLANKS.hangingSign().get())
                .add(ModBlocks.FRIGID_PLANKS.hangingSign().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingSign().get());
        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.hangingWallSign().get())
                .add(ModBlocks.SAKURA_PLANKS.hangingWallSign().get())
                .add(ModBlocks.FRIGID_PLANKS.hangingWallSign().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingWallSign().get());

        tag(ModTags.Blocks.CLOUDSHALE_ORE_REPLACEABLE)
                .add(ModBlocks.CLOUDSHALE.base().get(), ModBlocks.COBBLED_CLOUDSHALE.base().get(), ModBlocks.MOSSY_COBBLED_CLOUDSHALE.base().get(), ModBlocks.CHERRY_COBBLED_CLOUDSHALE.base().get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.CLOUDSHALE.wall().get())
                .add(ModBlocks.COBBLED_CLOUDSHALE.wall().get())
                .add(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.wall().get())
                .add(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.wall().get());

        tag(ModTags.Blocks.GOLDENLEAF_LOGS)
                .add(ModBlocks.GOLDENLEAF_LOGS.log().get(), ModBlocks.GOLDENLEAF_LOGS.wood().get(), ModBlocks.GOLDENLEAF_LOGS.strippedLog().get(), ModBlocks.GOLDENLEAF_LOGS.strippedWood().get());
        tag(ModTags.Blocks.SAKURA_LOGS)
                .add(ModBlocks.SAKURA_LOGS.log().get(), ModBlocks.SAKURA_LOGS.wood().get(), ModBlocks.SAKURA_LOGS.strippedLog().get(), ModBlocks.SAKURA_LOGS.strippedWood().get());
        tag(ModTags.Blocks.FRIGID_LOGS)
                .add(ModBlocks.FRIGID_LOGS.log().get(), ModBlocks.FRIGID_LOGS.wood().get(), ModBlocks.FRIGID_LOGS.strippedLog().get(), ModBlocks.FRIGID_LOGS.strippedWood().get());
        tag(ModTags.Blocks.ARBOREAL_CACTUSES)
                .add(ModBlocks.ARBOREAL_CACTUSES.cactus().get(), ModBlocks.ARBOREAL_CACTUSES.despinedCactus().get());
    }
}
