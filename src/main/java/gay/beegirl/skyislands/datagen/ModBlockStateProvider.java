package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.RenderTypeGroup;
import net.neoforged.neoforge.client.RenderTypeHelper;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

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

        tintedGrassLikeBlock(ModBlocks.CLOUDSHALE_GRASS.get(), ModBlocks.CLOUDSHALE.get());
        untintedGrassLikeBlock(ModBlocks.CLOUDSHALE_CHERRY_GRASS.get(), ModBlocks.CLOUDSHALE.get());
        logBlock((RotatedPillarBlock) ModBlocks.CLOUDSHALE.get());
        uncheckedBlockItem(ModBlocks.CLOUDSHALE.get());
        //createPointedBlock(blockModelGenerators, ModBlocks.POINTED_CLOUDSHALE);

        createStoneSetBlockStates(ModBlocks.COBBLED_CLOUDSHALE);
        createStoneSetBlockStates(ModBlocks.MOSSY_COBBLED_CLOUDSHALE);
        createStoneSetBlockStates(ModBlocks.CHERRY_COBBLED_CLOUDSHALE);

        createLogSetBlockStates(ModBlocks.GOLDENLEAF_LOGS);
        createWoodSetBlockStates(ModBlocks.GOLDENLEAF_PLANKS);
        simpleBlockWithItem(ModBlocks.GOLDENLEAF_LEAVES.get());
        plantAndPot(ModBlocks.GOLDENLEAF_SAPLING.get(), ModBlocks.POTTED_GOLDENLEAF_SAPLING.get());

        createLogSetBlockStates(ModBlocks.SAKURA_LOGS);
        createWoodSetBlockStates(ModBlocks.SAKURA_PLANKS);
        simpleBlockWithItem(ModBlocks.SAKURA_LEAVES.get());
        plantAndPot(ModBlocks.SAKURA_SAPLING.get(), ModBlocks.POTTED_SAKURA_SAPLING.get());

        createLogSetBlockStates(ModBlocks.FRIGID_LOGS);
        createWoodSetBlockStates(ModBlocks.FRIGID_PLANKS);
        simpleBlockWithItem(ModBlocks.FRIGID_LEAVES.get());
        plantAndPot(ModBlocks.FRIGID_SAPLING.get(), ModBlocks.POTTED_FRIGID_SAPLING.get());

        createCactusSetBlockStates(ModBlocks.ARBOREAL_CACTUSES);
        createWoodSetBlockStates(ModBlocks.ARBOREAL_CACTUS_PLANKS);
        itemModels().basicItem(ModBlocks.ARBOREAL_CACTUS_FRUIT.get().asItem());
    }

    private void simpleBlockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }

    public final void untintedGrassLikeBlock(Block block, Block baseBlock) {
        ResourceLocation bottomTex = blockTexture(baseBlock);
        if (baseBlock instanceof RotatedPillarBlock)
            bottomTex.withSuffix("_top");
        simpleBlockWithItem(block, models().cubeBottomTop(name(block),
                blockTexture(block).withSuffix("_side"),
                bottomTex,
                blockTexture(block).withSuffix("_top")));
    }
    public final void tintedGrassLikeBlock(Block block, Block baseBlock) {
        ResourceLocation bottomTex = blockTexture(baseBlock);
        if (baseBlock instanceof RotatedPillarBlock)
            bottomTex.withSuffix("_top");
        simpleBlockWithItem(block, models().getBuilder(name(block))
                .parent(new ModelFile.UncheckedModelFile("block/block"))
                .texture("particle", blockTexture(baseBlock))
                .texture("bottom", bottomTex)
                .texture("top", blockTexture(block).withSuffix("_top"))
                .texture("side", blockTexture(block).withSuffix("_side"))
                .texture("overlay", blockTexture(block).withSuffix("_side_overlay"))
                .element()
                    .face(Direction.UP)
                        .cullface(Direction.UP)
                        .tintindex(0)
                        .texture("#top")
                    .end()
                    .face(Direction.DOWN)
                        .cullface(Direction.DOWN)
                        .texture("#bottom")
                    .end()
                    .allFacesExcept(
                            (direction, faceBuilder) ->
                                    faceBuilder
                                            .cullface(direction)
                                            .texture("#side"),
                            Set.of(Direction.UP, Direction.DOWN))
                    .end()
                .element()
                    .allFacesExcept(
                        (direction, faceBuilder) ->
                                faceBuilder
                                        .cullface(direction)
                                        .tintindex(0)
                                        .texture("#overlay"),
                        Set.of(Direction.UP, Direction.DOWN))
                    .end()
                .renderType("minecraft:cutout")
        );
    }

    public final void plantAndPot(Block plant, Block pottedPlant) {
        simpleBlock(plant, models().cross(name(plant), blockTexture(plant)));
        flatBlockItem(plant);
        simpleBlock(pottedPlant, models().singleTexture(name(pottedPlant), mcLoc("block/flower_pot_cross"), "plant", blockTexture(plant)));
    }

    public final void flatBlockItem(Block block) {
        itemModels().getBuilder(name(block.asItem()))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", blockTexture(block));
    }
    public final void uncheckedBlockItem(Block block) {
        itemModels().getBuilder(name(block.asItem()))
                .parent(new ModelFile.UncheckedModelFile(key(block).withPrefix("block/")));
    }
    public final void uncheckedBlockItem(Block block, String suffix) {
        itemModels().getBuilder(name(block.asItem()))
                .parent(new ModelFile.UncheckedModelFile(key(block).withPrefix("block/").withSuffix(suffix)));
    }

    public final void createStoneSetBlockStates(ModBlocks.StoneBlockSet stoneSet){
        simpleBlockWithItem(stoneSet.base().get());

        buttonBlock((ButtonBlock) stoneSet.button().get(), blockTexture(stoneSet.base().get()));
        uncheckedBlockItem(stoneSet.button().get(), "_inventory");

        wallBlock((WallBlock) stoneSet.wall().get(), blockTexture(stoneSet.base().get()));
        uncheckedBlockItem(stoneSet.wall().get(), "_inventory");

        slabBlock((SlabBlock) stoneSet.slab().get(), blockTexture(stoneSet.base().get()), blockTexture(stoneSet.base().get()));
        uncheckedBlockItem(stoneSet.slab().get());

        stairsBlock((StairBlock) stoneSet.stairs().get(), blockTexture(stoneSet.base().get()));
        uncheckedBlockItem(stoneSet.stairs().get());

        pressurePlateBlock((PressurePlateBlock) stoneSet.pressurePlate().get(), blockTexture(stoneSet.base().get()));
        uncheckedBlockItem(stoneSet.pressurePlate().get());
    }
    public final void createLogSetBlockStates(ModBlocks.LogBlockSet logSet) {
        logBlock((RotatedPillarBlock) logSet.log().get());
        uncheckedBlockItem(logSet.log().get());

        axisBlock((RotatedPillarBlock) logSet.wood().get(), blockTexture(logSet.log().get()), blockTexture(logSet.log().get()));
        uncheckedBlockItem(logSet.wood().get());

        logBlock((RotatedPillarBlock) logSet.strippedLog().get());
        uncheckedBlockItem(logSet.strippedLog().get());

        axisBlock((RotatedPillarBlock) logSet.strippedWood().get(), blockTexture(logSet.strippedLog().get()), blockTexture(logSet.strippedLog().get()));
        uncheckedBlockItem(logSet.strippedWood().get());
    }
    public final void createCactusSetBlockStates(ModBlocks.CactusBlockSet cactusSet) {
        // TODO:
        simpleBlock(cactusSet.cactus().get(), models().cubeBottomTop(name(cactusSet.cactus().get()),
                blockTexture(cactusSet.cactus().get()).withSuffix("_side"),
                blockTexture(cactusSet.cactus().get()).withSuffix("_bottom"),
                blockTexture(cactusSet.cactus().get()).withSuffix("_top")));
        uncheckedBlockItem(cactusSet.cactus().get());

        // TODO:
        simpleBlock(cactusSet.despinedCactus().get(), models().cubeBottomTop(name(cactusSet.despinedCactus().get()),
                blockTexture(cactusSet.despinedCactus().get()).withSuffix("_side"),
                blockTexture(cactusSet.despinedCactus().get()).withSuffix("_bottom"),
                blockTexture(cactusSet.despinedCactus().get()).withSuffix("_top")));
        uncheckedBlockItem(cactusSet.despinedCactus().get());
    }
    public final void createWoodSetBlockStates(ModBlocks.WoodBlockSet woodSet) {
        simpleBlockWithItem(woodSet.base().get());

        buttonBlock((ButtonBlock) woodSet.button().get(), blockTexture(woodSet.base().get()));
        uncheckedBlockItem(woodSet.button().get(), "_inventory");

        doorBlock((DoorBlock) woodSet.door().get(), blockTexture(woodSet.door().get()).withSuffix("_bottom"), blockTexture(woodSet.door().get()).withSuffix("_top"));
        itemModels().basicItem(woodSet.door().asItem());

        fenceBlock((FenceBlock) woodSet.fence().get(), blockTexture(woodSet.base().get()));
        uncheckedBlockItem(woodSet.fence().get(), "_inventory");

        fenceGateBlock((FenceGateBlock) woodSet.fenceGate().get(), blockTexture(woodSet.base().get()));
        uncheckedBlockItem(woodSet.fenceGate().get());

        signBlock((StandingSignBlock) woodSet.standingSign().get(), (WallSignBlock) woodSet.wallSign().get(), blockTexture(woodSet.base().get()));
        itemModels().basicItem(woodSet.standingSign().asItem());

        hangingSignBlock((CeilingHangingSignBlock) woodSet.hangingSign().get(), (WallHangingSignBlock) woodSet.hangingWallSign().get(), blockTexture(woodSet.base().get()));
        itemModels().basicItem(woodSet.hangingSign().asItem());

        slabBlock((SlabBlock) woodSet.slab().get(), blockTexture(woodSet.base().get()), blockTexture(woodSet.base().get()));
        uncheckedBlockItem(woodSet.slab().get());

        stairsBlock((StairBlock) woodSet.stairs().get(), blockTexture(woodSet.base().get()));
        uncheckedBlockItem(woodSet.stairs().get());

        pressurePlateBlock((PressurePlateBlock) woodSet.pressurePlate().get(), blockTexture(woodSet.base().get()));
        uncheckedBlockItem(woodSet.pressurePlate().get());

        trapdoorBlock((TrapDoorBlock) woodSet.trapdoor().get(), blockTexture(woodSet.trapdoor().get()), true);
        uncheckedBlockItem(woodSet.trapdoor().get(), "_bottom");
    }

    // Copied from BlockStateProvider
    public @NotNull ResourceLocation blockTexture(@NotNull Block block) {
        ResourceLocation name = key(block);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "block/" + name.getPath());
    }
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }
    private ResourceLocation key(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }
    private String name(Block block) {
        return key(block).getPath();
    }
    private String name(Item item) {
        return key(item).getPath();
    }
}
