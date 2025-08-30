package gay.beegirl.datagen;

import gay.beegirl.block.ModBlocks;
import gay.beegirl.util.ModTags;
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
                .add(ModBlocks.RAW_ALEXANDRITE_BLOCK, ModBlocks.ALEXANDRITE_BLOCK)
                .add(ModBlocks.STONE_ALEXANDRITE_ORE, ModBlocks.DEEPSLATE_ALEXANDRITE_ORE, ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE);

        valueLookupBuilder(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.GOLDENLEAF_LOGS)
                .addTag(ModTags.Blocks.SAKURA_LOGS)
                .addTag(ModTags.Blocks.FRIGID_LOGS)
                .addTag(ModTags.Blocks.ARBOREAL_CACTUS_STEMS);

        valueLookupBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_GOLDENLEAF_SAPLING)
                .add(ModBlocks.POTTED_SAKURA_SAPLING)
                .add(ModBlocks.POTTED_FRIGID_SAPLING)
                .add(ModBlocks.POTTED_ARBOREAL_CACTUS);

        valueLookupBuilder(BlockTags.PLANKS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.planks())
                .add(ModBlocks.SAKURA_PLANKS.planks())
                .add(ModBlocks.FRIGID_PLANKS.planks())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.planks());
        valueLookupBuilder(BlockTags.FENCES)
                .add(ModBlocks.GOLDENLEAF_PLANKS.fence())
                .add(ModBlocks.SAKURA_PLANKS.fence())
                .add(ModBlocks.FRIGID_PLANKS.fence())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.fence());
        valueLookupBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.GOLDENLEAF_PLANKS.fenceGate())
                .add(ModBlocks.SAKURA_PLANKS.fenceGate())
                .add(ModBlocks.FRIGID_PLANKS.fenceGate())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.fenceGate());
        valueLookupBuilder(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.standingSign())
                .add(ModBlocks.SAKURA_PLANKS.standingSign())
                .add(ModBlocks.FRIGID_PLANKS.standingSign())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.standingSign());
        valueLookupBuilder(BlockTags.WALL_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.wallSign())
                .add(ModBlocks.SAKURA_PLANKS.wallSign())
                .add(ModBlocks.FRIGID_PLANKS.wallSign())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.wallSign());
        valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.hangingSign())
                .add(ModBlocks.SAKURA_PLANKS.hangingSign())
                .add(ModBlocks.FRIGID_PLANKS.hangingSign())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingSign());
        valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.hangingWallSign())
                .add(ModBlocks.SAKURA_PLANKS.hangingWallSign())
                .add(ModBlocks.FRIGID_PLANKS.hangingWallSign())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.hangingWallSign());

        valueLookupBuilder(BlockTags.HAPPY_GHAST_AVOIDS)
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.log(), ModBlocks.ARBOREAL_CACTUS_PLANKS.wood())
                .add(ModBlocks.POINTED_CLOUDSHALE);

        valueLookupBuilder(ModTags.Blocks.CLOUDSHALE_ORE_REPLACEABLE)
                .add(ModBlocks.CLOUDSHALE.base(), ModBlocks.COBBLED_CLOUDSHALE.base(), ModBlocks.MOSSY_COBBLED_CLOUDSHALE.base(), ModBlocks.CHERRY_COBBLED_CLOUDSHALE.base());

        valueLookupBuilder(ModTags.Blocks.GOLDENLEAF_LOGS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.log(), ModBlocks.GOLDENLEAF_PLANKS.wood(), ModBlocks.GOLDENLEAF_PLANKS.strippedLog(), ModBlocks.GOLDENLEAF_PLANKS.strippedWood());
        valueLookupBuilder(ModTags.Blocks.SAKURA_LOGS)
                .add(ModBlocks.SAKURA_PLANKS.log(), ModBlocks.SAKURA_PLANKS.wood(), ModBlocks.SAKURA_PLANKS.strippedLog(), ModBlocks.SAKURA_PLANKS.strippedWood());
        valueLookupBuilder(ModTags.Blocks.FRIGID_LOGS)
                .add(ModBlocks.FRIGID_PLANKS.log(), ModBlocks.FRIGID_PLANKS.wood(), ModBlocks.FRIGID_PLANKS.strippedLog(), ModBlocks.FRIGID_PLANKS.strippedWood());
        valueLookupBuilder(ModTags.Blocks.ARBOREAL_CACTUS_STEMS)
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.log(), ModBlocks.ARBOREAL_CACTUS_PLANKS.wood(), ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedLog(), ModBlocks.ARBOREAL_CACTUS_PLANKS.strippedWood());
    }
}
