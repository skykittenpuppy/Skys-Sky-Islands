package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.block.ModBlocks;
import net.minecraft.client.renderer.block.model.MultiVariant;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.data.models.model.TexturedModel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //WHY ARE YOU NOT BLOCKMODELGENERATORS GRAHHH
        BlockModelProvider aaaa  = models();
        blockWithItem(ModBlocks.RAW_ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.ALEXANDRITE_BLOCK);
        blockWithItem(ModBlocks.STONE_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
        blockWithItem(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE);
        createCloudshaleGrassBlock(blockModelGenerators, ModBlocks.CLOUDSHALE_GRASS);
        createCloudshaleGrassBlock(blockModelGenerators, ModBlocks.CLOUDSHALE_CHERRY_GRASS);
        createPointedBlock(blockModelGenerators, ModBlocks.POINTED_CLOUDSHALE);
        //blockModelGenerators.family(ModBlocks.CLOUDSHALE.base()).generateFor(ModBlocks.CLOUDSHALE_FAMILY);
        //blockModelGenerators.family(ModBlocks.COBBLED_CLOUDSHALE.base()).generateFor(ModBlocks.COBBLED_CLOUDSHALE_FAMILY);
        //blockModelGenerators.family(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.base()).generateFor(ModBlocks.MOSSY_COBBLED_CLOUDSHALE_FAMILY);
        //blockModelGenerators.family(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.base()).generateFor(ModBlocks.CHERRY_COBBLED_CLOUDSHALE_FAMILY);

        //createWoodTypeModels(blockModelGenerators, ModBlocks.GOLDENLEAF_PLANKS, ModBlocks.GOLDENLEAF_PLANKS_FAMILY);
        blockModelGenerators.createTintedLeaves(ModBlocks.GOLDENLEAF_LEAVES, TexturedModel.LEAVES, 0);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.GOLDENLEAF_SAPLING, ModBlocks.POTTED_GOLDENLEAF_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);

        //createWoodTypeModels(blockModelGenerators, ModBlocks.SAKURA_PLANKS, ModBlocks.SAKURA_PLANKS_FAMILY);
        blockModelGenerators.createTrivialBlock(ModBlocks.SAKURA_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.SAKURA_SAPLING, ModBlocks.POTTED_SAKURA_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);

        //createWoodTypeModels(blockModelGenerators, ModBlocks.FRIGID_PLANKS, ModBlocks.FRIGID_PLANKS_FAMILY);
        blockModelGenerators.createTintedLeaves(ModBlocks.FRIGID_LEAVES, TexturedModel.LEAVES, 0);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.FRIGID_SAPLING, ModBlocks.POTTED_FRIGID_SAPLING, BlockModelGenerators.TintState.NOT_TINTED);

        //createWoodTypeModels(blockModelGenerators, ModBlocks.ARBOREAL_CACTUS_PLANKS, ModBlocks.ARBOREAL_CACTUS_PLANKS_FAMILY);
        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.POTTED_ARBOREAL_CACTUS);
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    public final void createCloudshaleGrassBlock(BlockModelGenerators blockModelGenerators, Block block) {
        TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlocks.CLOUDSHALE.base().get())).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block)).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, blockModelGenerators.modelOutput))));
    }

    private void createPointedBlock(BlockModelGenerators blockModelGenerators, Block block) {
        PropertyDispatch.C2<MultiVariant, Direction, DripstoneThickness> c2 = PropertyDispatch.initial(BlockStateProperties.VERTICAL_DIRECTION, BlockStateProperties.DRIPSTONE_THICKNESS);

        for(DripstoneThickness dripstoneThickness : DripstoneThickness.values()) {
            c2.select(Direction.UP, dripstoneThickness, createPointedBlockVariant(blockModelGenerators, block, Direction.UP, dripstoneThickness));
        }

        for(DripstoneThickness dripstoneThickness : DripstoneThickness.values()) {
            c2.select(Direction.DOWN, dripstoneThickness, createPointedBlockVariant(blockModelGenerators, block, Direction.DOWN, dripstoneThickness));
        }

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(c2));
    }
    private void createPointedDripstone() {
        BlockModelGenerators.skipAutoItemBlock(Blocks.POINTED_DRIPSTONE);
        PropertyDispatch.C2<Direction, DripstoneThickness> c2 = PropertyDispatch.properties(
                BlockStateProperties.VERTICAL_DIRECTION, BlockStateProperties.DRIPSTONE_THICKNESS
        );

        for (DripstoneThickness dripstonethickness : DripstoneThickness.values()) {
            c2.select(Direction.UP, dripstonethickness, this.createPointedDripstoneVariant(Direction.UP, dripstonethickness));
        }

        for (DripstoneThickness dripstonethickness1 : DripstoneThickness.values()) {
            c2.select(Direction.DOWN, dripstonethickness1, this.createPointedDripstoneVariant(Direction.DOWN, dripstonethickness1));
        }

        BlockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(Blocks.POINTED_DRIPSTONE).with(c2));
    }
    public final MultiVariant createPointedBlockVariant(BlockModelGenerators blockModelGenerators, Block block, Direction direction, DripstoneThickness dripstoneThickness) {
        String var10000 = direction.getSerializedName();
        String string = "_" + var10000 + "_" + dripstoneThickness.getSerializedName();
        TextureMapping textureMapping = TextureMapping.cross(TextureMapping.getBlockTexture(block, string));
        return BlockModelGenerators.plainVariant(ModelTemplates.POINTED_DRIPSTONE.createWithSuffix(block, string, textureMapping, blockModelGenerators.modelOutput));
    }
    private Variant createPointedDripstoneVariant(Direction direction, DripstoneThickness dripstoneThickness) {
        String s = "_" + direction.getSerializedName() + "_" + dripstoneThickness.getSerializedName();
        TextureMapping texturemapping = TextureMapping.cross(TextureMapping.getBlockTexture(Blocks.POINTED_DRIPSTONE, s));
        return Variant.variant()
                .with(VariantProperties.MODEL, ModelTemplates.POINTED_DRIPSTONE.createWithSuffix(Blocks.POINTED_DRIPSTONE, s, texturemapping, BlockModelGenerators.modelOutput));
    }

    private void createWoodTypeModels(BlockModelGenerators blockModelGenerators, ModBlocks.WoodSetBlocks woodSetBlocks, BlockFamily family) {
        blockModelGenerators.woodProvider(woodSetBlocks.log()).log(woodSetBlocks.log()).wood(woodSetBlocks.wood());
        blockModelGenerators.woodProvider(woodSetBlocks.strippedLog()).log(woodSetBlocks.strippedLog()).wood(woodSetBlocks.strippedWood());
        blockModelGenerators.createHangingSign(woodSetBlocks.strippedLog(), woodSetBlocks.hangingSign(), woodSetBlocks.hangingWallSign());
        blockModelGenerators.family(woodSetBlocks.planks()).generateFor(family);
    }
}
