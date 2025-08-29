package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.impl.renderer.VanillaBlockModelPartEncoder;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.core.Direction;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlock.RAW_ALEXANDRITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlock.ALEXANDRITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlock.STONE_ALEXANDRITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlock.DEEPSLATE_ALEXANDRITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlock.CLOUDSHALE_ALEXANDRITE_ORE);
        createCloudshaleGrassBlock(blockModelGenerators, ModBlock.CLOUDSHALE_GRASS);
        createCloudshaleGrassBlock(blockModelGenerators, ModBlock.CLOUDSHALE_CHERRY_GRASS);
        createPointedBlock(blockModelGenerators, ModBlock.POINTED_CLOUDSHALE);
        blockModelGenerators.family(ModBlock.CLOUDSHALE.base()).generateFor(ModBlock.CLOUDSHALE_FAMILY);
        blockModelGenerators.family(ModBlock.COBBLED_CLOUDSHALE.base()).generateFor(ModBlock.COBBLED_CLOUDSHALE_FAMILY);
        blockModelGenerators.family(ModBlock.MOSSY_COBBLED_CLOUDSHALE.base()).generateFor(ModBlock.MOSSY_COBBLED_CLOUDSHALE_FAMILY);
        blockModelGenerators.family(ModBlock.CHERRY_COBBLED_CLOUDSHALE.base()).generateFor(ModBlock.CHERRY_COBBLED_CLOUDSHALE_FAMILY);

        createWoodTypeModels(blockModelGenerators, ModBlock.GOLDENLEAF_PLANKS, ModBlock.GOLDENLEAF_PLANKS_FAMILY);
        blockModelGenerators.createTintedLeaves(ModBlock.GOLDENLEAF_LEAVES, TexturedModel.LEAVES, 0);
        blockModelGenerators.createPlantWithDefaultItem(ModBlock.GOLDENLEAF_SAPLING, ModBlock.POTTED_GOLDENLEAF_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlock.SAKURA_PLANKS, ModBlock.SAKURA_PLANKS_FAMILY);
        blockModelGenerators.createTrivialBlock(ModBlock.SAKURA_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlock.SAKURA_SAPLING, ModBlock.POTTED_SAKURA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlock.FRIGID_PLANKS, ModBlock.FRIGID_PLANKS_FAMILY);
        blockModelGenerators.createTintedLeaves(ModBlock.FRIGID_LEAVES, TexturedModel.LEAVES, 0);
        blockModelGenerators.createPlantWithDefaultItem(ModBlock.FRIGID_SAPLING, ModBlock.POTTED_FRIGID_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlock.ARBOREAL_CACTUS_PLANKS, ModBlock.ARBOREAL_CACTUS_PLANKS_FAMILY);
        blockModelGenerators.createNonTemplateModelBlock(ModBlock.POTTED_ARBOREAL_CACTUS);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItem.RAW_ALEXANDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.ALEXANDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.declareCustomModelItem(ModItem.GLIDER);

        itemModelGenerators.declareCustomModelItem(ModBlock.POINTED_CLOUDSHALE.asItem());
        itemModelGenerators.generateFlatItem(ModItem.GOLDENLEAF_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.GOLDENLEAF_CHEST_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.SAKURA_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.SAKURA_CHEST_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.FRIGID_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.FRIGID_CHEST_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.ARBOREAL_CACTUS_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.ARBOREAL_CACTUS_CHEST_BOAT.asItem(), ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), ModelTemplates.FLAT_ITEM);
    }


    public final void createCloudshaleGrassBlock(BlockModelGenerators blockModelGenerators, Block block) {
        TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlock.CLOUDSHALE.base())).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block)).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
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
    public final MultiVariant createPointedBlockVariant(BlockModelGenerators blockModelGenerators, Block block, Direction direction, DripstoneThickness dripstoneThickness) {
        String var10000 = direction.getSerializedName();
        String string = "_" + var10000 + "_" + dripstoneThickness.getSerializedName();
        TextureMapping textureMapping = TextureMapping.cross(TextureMapping.getBlockTexture(block, string));
        return BlockModelGenerators.plainVariant(ModelTemplates.POINTED_DRIPSTONE.createWithSuffix(block, string, textureMapping, blockModelGenerators.modelOutput));
    }

    private void createWoodTypeModels(BlockModelGenerators blockModelGenerators, ModBlock.WoodSetBlocks woodSetBlocks, BlockFamily family) {
        blockModelGenerators.woodProvider(woodSetBlocks.log()).log(woodSetBlocks.log()).wood(woodSetBlocks.wood());
        blockModelGenerators.woodProvider(woodSetBlocks.strippedLog()).log(woodSetBlocks.strippedLog()).wood(woodSetBlocks.strippedWood());
        blockModelGenerators.createHangingSign(woodSetBlocks.strippedLog(), woodSetBlocks.hangingSign(), woodSetBlocks.hangingWallSign());
        blockModelGenerators.family(woodSetBlocks.planks()).generateFor(family);
    }
}