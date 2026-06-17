package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
        simpleBlockWithItem(ModBlocks.ALEXANDRITE_BLOCK.get());
        simpleBlockWithItem(ModBlocks.STONE_ALEXANDRITE_ORE.get());
        simpleBlockWithItem(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get());
        simpleBlockWithItem(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE.get());

        grassLikeBlock(ModBlocks.CLOUDSHALE_GRASS.get(), ModBlocks.CLOUDSHALE.base().get());
        grassLikeBlock(ModBlocks.CLOUDSHALE_CHERRY_GRASS.get(), ModBlocks.CLOUDSHALE.base().get());
        //createPointedBlock(blockModelGenerators, ModBlocks.POINTED_CLOUDSHALE);
        stoneSet(ModBlocks.CLOUDSHALE);
        stoneSet(ModBlocks.COBBLED_CLOUDSHALE);
        stoneSet(ModBlocks.MOSSY_COBBLED_CLOUDSHALE);
        stoneSet(ModBlocks.CHERRY_COBBLED_CLOUDSHALE);

        logSet(ModBlocks.GOLDENLEAF_LOGS);
        woodSet(ModBlocks.GOLDENLEAF_PLANKS);
        simpleBlockWithItem(ModBlocks.GOLDENLEAF_LEAVES.get());
        plantAndPot(ModBlocks.GOLDENLEAF_SAPLING.get(), ModBlocks.POTTED_GOLDENLEAF_SAPLING.get());

        logSet(ModBlocks.SAKURA_LOGS);
        woodSet(ModBlocks.SAKURA_PLANKS);
        simpleBlockWithItem(ModBlocks.SAKURA_LEAVES.get());
        plantAndPot(ModBlocks.SAKURA_SAPLING.get(), ModBlocks.POTTED_SAKURA_SAPLING.get());

        logSet(ModBlocks.FRIGID_LOGS);
        woodSet(ModBlocks.FRIGID_PLANKS);
        simpleBlockWithItem(ModBlocks.FRIGID_LEAVES.get());
        plantAndPot(ModBlocks.FRIGID_SAPLING.get(), ModBlocks.POTTED_FRIGID_SAPLING.get());

        woodSet(ModBlocks.ARBOREAL_CACTUS_PLANKS);
    }

    private void simpleBlockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    public final void grassLikeBlock(Block block, Block baseBlock) {
        simpleBlockWithItem(block, models().cubeBottomTop(name(block),
                extend(blockTexture(block), "_side"),
                blockTexture(baseBlock),
                blockTexture(block)));
    }

    public final void plantAndPot(Block plant, Block pottedPlant) {
        simpleBlock(plant, models().cross(name(plant), blockTexture(plant)));
        simpleBlock(pottedPlant, models().singleTexture(name(pottedPlant), mcLoc("block/flower_pot_cross"), "plant", blockTexture(plant)));
    }

    public final void stoneSet(ModBlocks.StoneBlockSet stoneSet){
        simpleBlock(stoneSet.base().get());
        buttonBlock((ButtonBlock) stoneSet.button().get(), blockTexture(stoneSet.base().get()));
        wallBlock((WallBlock) stoneSet.wall().get(), blockTexture(stoneSet.base().get()));
        slabBlock((SlabBlock) stoneSet.slab().get(), blockTexture(stoneSet.base().get()), blockTexture(stoneSet.base().get()));
        stairsBlock((StairBlock) stoneSet.stairs().get(), blockTexture(stoneSet.base().get()));
        pressurePlateBlock((PressurePlateBlock) stoneSet.pressurePlate().get(), blockTexture(stoneSet.base().get()));
    }
    public final void logSet(ModBlocks.LogBlockSet logSet) {
        logBlock((RotatedPillarBlock) logSet.log().get());
        axisBlock((RotatedPillarBlock) logSet.wood().get(), blockTexture(logSet.log().get()), blockTexture(logSet.log().get()));
        logBlock((RotatedPillarBlock) logSet.strippedLog().get());
        axisBlock((RotatedPillarBlock) logSet.strippedWood().get(), blockTexture(logSet.strippedLog().get()), blockTexture(logSet.strippedLog().get()));
    }
    public final void woodSet(ModBlocks.WoodBlockSet woodSet) {
        simpleBlock(woodSet.base().get());
        buttonBlock((ButtonBlock) woodSet.button().get(), blockTexture(woodSet.base().get()));
        doorBlock((DoorBlock) woodSet.door().get(), extend(blockTexture(woodSet.door().get()), "_bottom"), extend(blockTexture(woodSet.door().get()), "_top"));
        fenceBlock((FenceBlock) woodSet.fence().get(), blockTexture(woodSet.base().get()));
        fenceGateBlock((FenceGateBlock) woodSet.fenceGate().get(), blockTexture(woodSet.base().get()));
        //signBlock((StandingSignBlock) woodSet.standingSign().get(), (WallSignBlock) woodSet.wallSign().get(), blockTexture(woodSet.base().get()));
        //hangingSignBlock((CeilingHangingSignBlock) woodSet.hangingSign().get(), (WallHangingSignBlock) woodSet.hangingWallSign().get(), blockTexture(woodSet.base().get()));
        slabBlock((SlabBlock) woodSet.slab().get(), blockTexture(woodSet.base().get()), blockTexture(woodSet.base().get()));
        stairsBlock((StairBlock) woodSet.stairs().get(), blockTexture(woodSet.base().get()));
        pressurePlateBlock((PressurePlateBlock) woodSet.pressurePlate().get(), blockTexture(woodSet.base().get()));
        trapdoorBlock((TrapDoorBlock) woodSet.trapdoor().get(), blockTexture(woodSet.trapdoor().get()), true);
    }

    // Copied from BlockStateProvider
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    private String name(Block block) {
        return this.key(block).getPath();
    }
    private ResourceLocation extend(ResourceLocation rl, String suffix) {
        String namespace = rl.getNamespace();
        String path = rl.getPath();
        return ResourceLocation.fromNamespaceAndPath(namespace, path + suffix);
    }
}
