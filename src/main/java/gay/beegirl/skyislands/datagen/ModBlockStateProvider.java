package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.level.block.IslandsCactusFruitBlock;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import gay.beegirl.skyislands.world.level.block.PointedCloudshaleBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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
        naturalRotatedPillarBlock((RotatedPillarBlock) ModBlocks.CLOUDSHALE.get());
        uncheckedBlockItem(ModBlocks.CLOUDSHALE.get());
        createPointedBlock(ModBlocks.POINTED_CLOUDSHALE.get());

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
        createFlowerBed(ModBlocks.WHITE_PETALS.get());

        createLogSetBlockStates(ModBlocks.FRIGID_LOGS);
        createWoodSetBlockStates(ModBlocks.FRIGID_PLANKS);
        simpleBlockWithItem(ModBlocks.FRIGID_LEAVES.get());
        plantAndPot(ModBlocks.FRIGID_SAPLING.get(), ModBlocks.POTTED_FRIGID_SAPLING.get());

        createCactusSetBlockStates(ModBlocks.ARBOREAL_CACTUSES);
        createWoodSetBlockStates(ModBlocks.ARBOREAL_CACTUS_PLANKS);
        getVariantBuilder(ModBlocks.ARBOREAL_CACTUS_FRUIT.get())
                .forAllStates(state -> ConfiguredModel.builder()
                        .modelFile(new ModelFile.UncheckedModelFile(key(ModBlocks.ARBOREAL_CACTUS_FRUIT.get())
                                .withPrefix("block/")
                                .withSuffix(state.getValue(IslandsCactusFruitBlock.AGE).toString())))
                        .rotationY((int)state.getValue(IslandsCactusFruitBlock.FACING).toYRot())
                        .build());
        statelessBlockWithCustomModel(ModBlocks.ARBOREAL_CACTUS_PLANT.get());
        flatBlockItem(ModBlocks.ARBOREAL_CACTUS_PLANT.get());
        statelessBlockWithCustomModel(ModBlocks.POTTED_ARBOREAL_CACTUS.get());
    }

    private void statelessBlockWithCustomModel(Block block) {
        simpleBlock(block, new ModelFile.UncheckedModelFile(key(block).withPrefix("block/")));
    }
    private void simpleBlockWithItem(Block block) {
        simpleBlockWithItem(block, cubeAll(block));
    }
    private void simpleBlockWithItem(Block block, ConfiguredModel... models) {
        simpleBlock(block, models);
        uncheckedBlockItem(block);
    }

    public final void naturalRotatedPillarBlock(RotatedPillarBlock block) {
        ModelFile model = models().cubeColumn(name(block),
                blockTexture(block),
                blockTexture(block).withSuffix("_top"));

        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).setModels(ConfiguredModel.allYRotations(model, 0, false))
                // INFO: really want 4 rotations for horizontal, but it seems impossible with pre 1.21.11 blockstate format
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).setModels(List.of(
                        new ConfiguredModel(model, 90, 0, false, 1),
                        new ConfiguredModel(model, 270, 0, false, 1)).toArray(ConfiguredModel[]::new))
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).setModels(List.of(
                        new ConfiguredModel(model, 90, 90, false, 1),
                        new ConfiguredModel(model, 270, 90, false, 1)).toArray(ConfiguredModel[]::new));
        uncheckedBlockItem(block);
    }
    public final void untintedGrassLikeBlock(Block block, Block baseBlock) {
        ResourceLocation bottomTex = blockTexture(baseBlock);
        if (baseBlock instanceof RotatedPillarBlock)
            bottomTex = blockTexture(baseBlock).withSuffix("_top");

        ModelFile model = models().cubeBottomTop(name(block),
                blockTexture(block).withSuffix("_side"),
                bottomTex,
                blockTexture(block).withSuffix("_top")
        ).texture("particle", blockTexture(baseBlock));

        simpleBlockWithItem(block, ConfiguredModel.allYRotations(model, 0, false));
    }
    public final void tintedGrassLikeBlock(Block block, Block baseBlock) {
        ResourceLocation bottomTex = blockTexture(baseBlock);
        if (baseBlock instanceof RotatedPillarBlock)
            bottomTex = bottomTex.withSuffix("_top");

        ModelFile model = models().getBuilder(name(block))
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
                    .renderType("minecraft:cutout");

        simpleBlockWithItem(block, ConfiguredModel.allYRotations(model, 0, false));
    }
    public final void plantAndPot(Block plant, Block pottedPlant) {
        simpleBlock(plant, models().cross(name(plant), blockTexture(plant)).renderType("minecraft:cutout"));
        flatBlockItem(plant);
        simpleBlock(pottedPlant, models().singleTexture(name(pottedPlant), mcLoc("block/flower_pot_cross"), "plant", blockTexture(plant)).renderType("minecraft:cutout"));
    }

    private void createFlowerBed(Block flowerBedBlock) {
        ModelFile mdl1 = models().withExistingParent(name(flowerBedBlock)+"_1", mcLoc("block/pink_petals_1"))
                .texture("flowerbed", blockTexture(flowerBedBlock))
                .texture("stem", blockTexture(flowerBedBlock).withSuffix("_stem"))
                .renderType("minecraft:cutout");
        ModelFile mdl2 = models().withExistingParent(name(flowerBedBlock)+"_2", mcLoc("block/pink_petals_2"))
                .texture("flowerbed", blockTexture(flowerBedBlock))
                .texture("stem", blockTexture(flowerBedBlock).withSuffix("_stem"))
                .renderType("minecraft:cutout");
        ModelFile mdl3 = models().withExistingParent(name(flowerBedBlock)+"_3", mcLoc("block/pink_petals_3"))
                .texture("flowerbed", blockTexture(flowerBedBlock))
                .texture("stem", blockTexture(flowerBedBlock).withSuffix("_stem"))
                .renderType("minecraft:cutout");
        ModelFile mdl4 = models().withExistingParent(name(flowerBedBlock)+"_4", mcLoc("block/pink_petals_4"))
                .texture("flowerbed", blockTexture(flowerBedBlock))
                .texture("stem", blockTexture(flowerBedBlock).withSuffix("_stem"))
                .renderType("minecraft:cutout");

        getMultipartBuilder(flowerBedBlock).part()
                .modelFile(mdl1)
                .rotationY(0)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.NORTH).end().part()
                .modelFile(mdl1)
                .rotationY(90)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.EAST).end().part()
                .modelFile(mdl1)
                .rotationY(180)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.SOUTH).end().part()
                .modelFile(mdl1)
                .rotationY(270)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 1, 2, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.WEST).end().part()
                .modelFile(mdl2)
                .rotationY(0)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.NORTH).end().part()
                .modelFile(mdl2)
                .rotationY(90)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.EAST).end().part()
                .modelFile(mdl2)
                .rotationY(180)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.SOUTH).end().part()
                .modelFile(mdl2)
                .rotationY(270)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 2, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.WEST).end().part()
                .modelFile(mdl3)
                .rotationY(0)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.NORTH).end().part()
                .modelFile(mdl3)
                .rotationY(90)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.EAST).end().part()
                .modelFile(mdl3)
                .rotationY(180)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.SOUTH).end().part()
                .modelFile(mdl3)
                .rotationY(270)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 3, 4)
                .condition(BlockStateProperties.FACING, Direction.WEST).end().part()
                .modelFile(mdl4)
                .rotationY(0)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 4)
                .condition(BlockStateProperties.FACING, Direction.NORTH).end().part()
                .modelFile(mdl4)
                .rotationY(90)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 4)
                .condition(BlockStateProperties.FACING, Direction.EAST).end().part()
                .modelFile(mdl4)
                .rotationY(180)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 4)
                .condition(BlockStateProperties.FACING, Direction.SOUTH).end().part()
                .modelFile(mdl4)
                .rotationY(270)
                .addModel()
                .condition(BlockStateProperties.FLOWER_AMOUNT, 4)
                .condition(BlockStateProperties.FACING, Direction.WEST).end();
    }
    private void createPointedBlock(Block pointedBlock){
        getVariantBuilder(pointedBlock).forAllStates(state -> {
                    Direction direction = state.getValue(PointedCloudshaleBlock.TIP_DIRECTION);
                    DripstoneThickness dripstoneThickness = state.getValue(PointedCloudshaleBlock.THICKNESS);
                    return ConfiguredModel.builder()
                            .modelFile(models()
                                    .withExistingParent(
                                            name(pointedBlock) + "_" + direction.getSerializedName() + "_" + dripstoneThickness.getSerializedName(),
                                            key(Blocks.POINTED_DRIPSTONE))
                                    .texture("cross",
                                            blockTexture(pointedBlock) + "_" + direction.getSerializedName() + "_" + dripstoneThickness.getSerializedName())
                                    .renderType("minecraft:cutout"))
                            .build();
                });
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

        wallBlock((WallBlock) stoneSet.wall().get(), blockTexture(stoneSet.base().get()));
        ModelFile wallInventoryModel = models().getBuilder(name(stoneSet.wall().get()) + "_inventory")
                .parent(new ModelFile.UncheckedModelFile("block/wall_inventory"))
                .texture("wall", blockTexture(stoneSet.base().get()));
        simpleBlockItem(stoneSet.wall().get(), wallInventoryModel);

        slabBlock((SlabBlock) stoneSet.slab().get(), blockTexture(stoneSet.base().get()), blockTexture(stoneSet.base().get()));
        uncheckedBlockItem(stoneSet.slab().get());

        stairsBlock((StairBlock) stoneSet.stairs().get(), blockTexture(stoneSet.base().get()));
        uncheckedBlockItem(stoneSet.stairs().get());
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
        // TODO: Cactus blockstates
        simpleBlock(cactusSet.cactus().get(), new ModelFile.UncheckedModelFile(key(cactusSet.cactus().get()).withPrefix("block/")));
        uncheckedBlockItem(cactusSet.cactus().get());

        getVariantBuilder(cactusSet.despinedCactus().get())
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y).modelForState().modelFile(new ModelFile.UncheckedModelFile(key(cactusSet.despinedCactus().get()).withPrefix("block/"))).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z).modelForState().modelFile(new ModelFile.UncheckedModelFile(key(cactusSet.despinedCactus().get()).withPrefix("block/"))).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X).modelForState().modelFile(new ModelFile.UncheckedModelFile(key(cactusSet.despinedCactus().get()).withPrefix("block/"))).rotationX(90).rotationY(90).addModel();
        uncheckedBlockItem(cactusSet.despinedCactus().get());
    }
    public final void createWoodSetBlockStates(ModBlocks.WoodBlockSet woodSet) {
        simpleBlockWithItem(woodSet.base().get());

        buttonBlock((ButtonBlock) woodSet.button().get(), blockTexture(woodSet.base().get()));
        ModelFile buttonInventoryModel = models().getBuilder(name(woodSet.button().get()) + "_inventory")
                .parent(new ModelFile.UncheckedModelFile("block/button_inventory"))
                .texture("texture", blockTexture(woodSet.base().get()));
        simpleBlockItem(woodSet.button().get(), buttonInventoryModel);

        doorBlockWithRenderType((DoorBlock) woodSet.door().get(), blockTexture(woodSet.door().get()).withSuffix("_bottom"), blockTexture(woodSet.door().get()).withSuffix("_top"), "minecraft:cutout");
        itemModels().basicItem(woodSet.door().asItem());

        fenceBlock((FenceBlock) woodSet.fence().get(), blockTexture(woodSet.base().get()));
        ModelFile fenceInventoryModel = models().getBuilder(name(woodSet.fence().get()) + "_inventory")
                .parent(new ModelFile.UncheckedModelFile("block/fence_inventory"))
                .texture("texture", blockTexture(woodSet.base().get()));
        simpleBlockItem(woodSet.fence().get(), fenceInventoryModel);

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

        trapdoorBlockWithRenderType((TrapDoorBlock) woodSet.trapdoor().get(), blockTexture(woodSet.trapdoor().get()), true, "minecraft:cutout");
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
