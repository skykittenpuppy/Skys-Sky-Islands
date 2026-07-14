package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.tags.ModBlockTags;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        tag(BlockTags.PLANKS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.base().get())
                .add(ModBlocks.SAKURA_PLANKS.base().get())
                .add(ModBlocks.FRIGID_PLANKS.base().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.base().get());
        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.button().get())
                .add(ModBlocks.SAKURA_PLANKS.button().get())
                .add(ModBlocks.FRIGID_PLANKS.button().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.button().get());
        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.door().get())
                .add(ModBlocks.SAKURA_PLANKS.door().get())
                .add(ModBlocks.FRIGID_PLANKS.door().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.door().get());
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
        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.slab().get())
                .add(ModBlocks.SAKURA_PLANKS.slab().get())
                .add(ModBlocks.FRIGID_PLANKS.slab().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.slab().get());
        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.stairs().get())
                .add(ModBlocks.SAKURA_PLANKS.stairs().get())
                .add(ModBlocks.FRIGID_PLANKS.stairs().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.stairs().get());
        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.GOLDENLEAF_PLANKS.pressurePlate().get())
                .add(ModBlocks.SAKURA_PLANKS.pressurePlate().get())
                .add(ModBlocks.FRIGID_PLANKS.pressurePlate().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.pressurePlate().get());
        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.GOLDENLEAF_PLANKS.trapdoor().get())
                .add(ModBlocks.SAKURA_PLANKS.trapdoor().get())
                .add(ModBlocks.FRIGID_PLANKS.trapdoor().get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANKS.trapdoor().get());

        tag(ModBlockTags.GOLDENLEAF_LOGS)
                .add(ModBlocks.GOLDENLEAF_LOGS.log().get(), ModBlocks.GOLDENLEAF_LOGS.wood().get())
                .add(ModBlocks.GOLDENLEAF_LOGS.strippedLog().get(), ModBlocks.GOLDENLEAF_LOGS.strippedWood().get());
        tag(ModBlockTags.SAKURA_LOGS)
                .add(ModBlocks.SAKURA_LOGS.log().get(), ModBlocks.SAKURA_LOGS.wood().get())
                .add(ModBlocks.SAKURA_LOGS.strippedLog().get(), ModBlocks.SAKURA_LOGS.strippedWood().get());
        tag(ModBlockTags.FRIGID_LOGS)
                .add(ModBlocks.FRIGID_LOGS.log().get(), ModBlocks.FRIGID_LOGS.wood().get())
                .add(ModBlocks.FRIGID_LOGS.strippedLog().get(), ModBlocks.FRIGID_LOGS.strippedWood().get());
        tag(ModBlockTags.ARBOREAL_CACTUSES)
                .add(ModBlocks.ARBOREAL_CACTUSES.cactus().get(), ModBlocks.ARBOREAL_CACTUSES.despinedCactus().get());

        tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModBlockTags.GOLDENLEAF_LOGS)
                .addTag(ModBlockTags.SAKURA_LOGS)
                .addTag(ModBlockTags.FRIGID_LOGS);
        tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.GOLDENLEAF_LOGS.log().get())
                .add(ModBlocks.SAKURA_LOGS.log().get())
                .add(ModBlocks.FRIGID_LOGS.log().get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.GOLDENLEAF_LEAVES.get())
                .add(ModBlocks.SAKURA_LEAVES.get())
                .add(ModBlocks.FRIGID_LEAVES.get());
        tag(BlockTags.SAPLINGS)
                .add(ModBlocks.GOLDENLEAF_SAPLING.get())
                .add(ModBlocks.SAKURA_SAPLING.get())
                .add(ModBlocks.FRIGID_SAPLING.get())
                .add(ModBlocks.ARBOREAL_CACTUS_PLANT.get());
        tag(BlockTags.FLOWER_POTS)
                .add(ModBlocks.POTTED_GOLDENLEAF_SAPLING.get())
                .add(ModBlocks.POTTED_SAKURA_SAPLING.get())
                .add(ModBlocks.POTTED_FRIGID_SAPLING.get())
                .add(ModBlocks.POTTED_ARBOREAL_CACTUS.get());

        tag(BlockTags.WALLS)
                .add(ModBlocks.COBBLED_CLOUDSHALE.wall().get())
                .add(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.wall().get())
                .add(ModBlocks.CLOUDSHALE_BRICK.wall().get());
        tag(BlockTags.SLABS)
                .add(ModBlocks.COBBLED_CLOUDSHALE.slab().get())
                .add(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.slab().get())
                .add(ModBlocks.CLOUDSHALE_BRICK.slab().get());
        tag(BlockTags.STAIRS)
                .add(ModBlocks.COBBLED_CLOUDSHALE.stairs().get())
                .add(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.stairs().get())
                .add(ModBlocks.CLOUDSHALE_BRICK.stairs().get());


        tag(BlockTags.DIRT)
                .add(ModBlocks.CLOUDSHALE_GRASS.get());
        tag(BlockTags.FLOWERS)
                .add(ModBlocks.WHITE_PETALS.get());
        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.ZEPHYRUM_BLOCK.get());
        tag(ModBlockTags.ZEPHYRUM_ORES)
                .add(ModBlocks.STONE_ZEPHYRUM_ORE.get(), ModBlocks.DEEPSLATE_ZEPHYRUM_ORE.get(), ModBlocks.CLOUDSHALE_ZEPHYRUM_ORE.get());
        tag(BlockTags.BASE_STONE_OVERWORLD)
                .add(ModBlocks.CLOUDSHALE.get());
        tag(ModBlockTags.CLOUDSHALE_ORE_REPLACEABLE)
                .add(ModBlocks.CLOUDSHALE.get());
        tag(BlockTags.CRYSTAL_SOUND_BLOCKS)
                .add(ModBlocks.RAW_ZEPHYRUM_BLOCK.get(), ModBlocks.ZEPHYRUM_BLOCK.get());
        tag(BlockTags.INSIDE_STEP_SOUND_BLOCKS)
                .add(ModBlocks.WHITE_PETALS.get());
        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.WHITE_PETALS.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.RAW_ZEPHYRUM_BLOCK.get(), ModBlocks.ZEPHYRUM_BLOCK.get())
                .add(ModBlocks.STONE_ZEPHYRUM_ORE.get(), ModBlocks.DEEPSLATE_ZEPHYRUM_ORE.get(), ModBlocks.CLOUDSHALE_ZEPHYRUM_ORE.get());
        tag(BlockTags.SWORD_EFFICIENT)
                .add(ModBlocks.WHITE_PETALS.get());
        tag(BlockTags.ANIMALS_SPAWNABLE_ON)
                .add(ModBlocks.CLOUDSHALE_GRASS.get());
    }
}
