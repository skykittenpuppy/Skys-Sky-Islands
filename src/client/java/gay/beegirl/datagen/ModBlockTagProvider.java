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

        valueLookupBuilder(BlockTags.FLOWER_POTS)
                .add(ModBlock.POTTED_GOLDENLEAF_SAPLING)
                .add(ModBlock.POTTED_SAKURA_SAPLING)
                .add(ModBlock.POTTED_FRIGID_SAPLING)
                .add(ModBlock.POTTED_ARBOREAL_CACTUS);

        valueLookupBuilder(BlockTags.PLANKS)
                .add(ModBlock.GOLDENLEAF_PLANKS.planks())
                .add(ModBlock.SAKURA_PLANKS.planks())
                .add(ModBlock.FRIGID_PLANKS.planks())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.planks());
        valueLookupBuilder(BlockTags.FENCES)
                .add(ModBlock.GOLDENLEAF_PLANKS.fence())
                .add(ModBlock.SAKURA_PLANKS.fence())
                .add(ModBlock.FRIGID_PLANKS.fence())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.fence());
        valueLookupBuilder(BlockTags.FENCE_GATES)
                .add(ModBlock.GOLDENLEAF_PLANKS.fenceGate())
                .add(ModBlock.SAKURA_PLANKS.fenceGate())
                .add(ModBlock.FRIGID_PLANKS.fenceGate())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.fenceGate());
        valueLookupBuilder(BlockTags.STANDING_SIGNS)
                .add(ModBlock.GOLDENLEAF_PLANKS.standingSign())
                .add(ModBlock.SAKURA_PLANKS.standingSign())
                .add(ModBlock.FRIGID_PLANKS.standingSign())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.standingSign());
        valueLookupBuilder(BlockTags.WALL_SIGNS)
                .add(ModBlock.GOLDENLEAF_PLANKS.wallSign())
                .add(ModBlock.SAKURA_PLANKS.wallSign())
                .add(ModBlock.FRIGID_PLANKS.wallSign())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.wallSign());
        valueLookupBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlock.GOLDENLEAF_PLANKS.hangingSign())
                .add(ModBlock.SAKURA_PLANKS.hangingSign())
                .add(ModBlock.FRIGID_PLANKS.hangingSign())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.hangingSign());
        valueLookupBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlock.GOLDENLEAF_PLANKS.hangingWallSign())
                .add(ModBlock.SAKURA_PLANKS.hangingWallSign())
                .add(ModBlock.FRIGID_PLANKS.hangingWallSign())
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.hangingWallSign());

        valueLookupBuilder(BlockTags.HAPPY_GHAST_AVOIDS)
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.log(), ModBlock.ARBOREAL_CACTUS_PLANKS.wood())
                .add(ModBlock.POINTED_CLOUDSHALE);

        valueLookupBuilder(ModTag.Blocks.CLOUDSHALE_ORE_REPLACEABLE)
                .add(ModBlock.CLOUDSHALE.base(), ModBlock.COBBLED_CLOUDSHALE.base(), ModBlock.MOSSY_COBBLED_CLOUDSHALE.base(), ModBlock.CHERRY_COBBLED_CLOUDSHALE.base());

        valueLookupBuilder(ModTag.Blocks.GOLDENLEAF_LOGS)
                .add(ModBlock.GOLDENLEAF_PLANKS.log(), ModBlock.GOLDENLEAF_PLANKS.wood(), ModBlock.GOLDENLEAF_PLANKS.strippedLog(), ModBlock.GOLDENLEAF_PLANKS.strippedWood());
        valueLookupBuilder(ModTag.Blocks.SAKURA_LOGS)
                .add(ModBlock.SAKURA_PLANKS.log(), ModBlock.SAKURA_PLANKS.wood(), ModBlock.SAKURA_PLANKS.strippedLog(), ModBlock.SAKURA_PLANKS.strippedWood());
        valueLookupBuilder(ModTag.Blocks.FRIGID_LOGS)
                .add(ModBlock.FRIGID_PLANKS.log(), ModBlock.FRIGID_PLANKS.wood(), ModBlock.FRIGID_PLANKS.strippedLog(), ModBlock.FRIGID_PLANKS.strippedWood());
        valueLookupBuilder(ModTag.Blocks.ARBOREAL_CACTUS_STEMS)
                .add(ModBlock.ARBOREAL_CACTUS_PLANKS.log(), ModBlock.ARBOREAL_CACTUS_PLANKS.wood(), ModBlock.ARBOREAL_CACTUS_PLANKS.strippedLog(), ModBlock.ARBOREAL_CACTUS_PLANKS.strippedWood());
    }
}
