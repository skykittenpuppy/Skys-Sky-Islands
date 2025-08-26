package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.util.ModTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

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
                .addTag(ModTag.Blocks.GOLDENLEAF_LOGS)
                .addTag(ModTag.Blocks.SAKURA_LOGS)
                .addTag(ModTag.Blocks.FRIGID_LOGS)
                .addTag(ModTag.Blocks.ARBOREAL_CACTUS_STEMS);

        valueLookupBuilder(BlockTags.PLANKS)
                .add(ModBlock.GOLDENLEAF_PLANKS)
                .add(ModBlock.SAKURA_PLANKS)
                .add(ModBlock.FRIGID_PLANKS)
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS);

        valueLookupBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlock.POTTED_GOLDENLEAF_SAPLING)
                .add(ModBlock.POTTED_SAKURA_SAPLING)
                .add(ModBlock.POTTED_FRIGID_SAPLING)
                .add(ModBlock.POTTED_ARBOREAL_CACTUS);

        valueLookupBuilder(BlockTags.FENCES)
                .add(ModBlock.GOLDENLEAF_FENCE)
                .add(ModBlock.SAKURA_FENCE)
                .add(ModBlock.FRIGID_FENCE)
                .add(ModBlock.ARBOREAL_CACTUS_FENCE);

        valueLookupBuilder(BlockTags.FENCE_GATES)
                .add(ModBlock.GOLDENLEAF_FENCE_GATE)
                .add(ModBlock.SAKURA_FENCE_GATE)
                .add(ModBlock.FRIGID_FENCE_GATE)
                .add(ModBlock.ARBOREAL_CACTUS_FENCE_GATE);

        valueLookupBuilder(ModTag.Blocks.GOLDENLEAF_LOGS)
                .add(ModBlock.GOLDENLEAF_LOG, ModBlock.GOLDENLEAF_WOOD, ModBlock.STRIPPED_GOLDENLEAF_LOG, ModBlock.STRIPPED_GOLDENLEAF_WOOD);
        valueLookupBuilder(ModTag.Blocks.SAKURA_LOGS)
                .add(ModBlock.SAKURA_LOG, ModBlock.SAKURA_WOOD, ModBlock.STRIPPED_SAKURA_LOG, ModBlock.STRIPPED_SAKURA_WOOD);
        valueLookupBuilder(ModTag.Blocks.FRIGID_LOGS)
                .add(ModBlock.FRIGID_LOG, ModBlock.FRIGID_WOOD, ModBlock.STRIPPED_FRIGID_LOG, ModBlock.STRIPPED_FRIGID_WOOD);
        valueLookupBuilder(ModTag.Blocks.ARBOREAL_CACTUS_STEMS)
                .add(ModBlock.ARBOREAL_CACTUS_STEM, ModBlock.ARBOREAL_CACTUS_HYPHAE, ModBlock.STRIPPED_ARBOREAL_CACTUS_STEM, ModBlock.STRIPPED_ARBOREAL_CACTUS_HYPHAE);
    }
}
