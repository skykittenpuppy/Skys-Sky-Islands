package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.world.level.block.Block;

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
        blockModelGenerators.createTrivialCube(ModBlock.CLOUDSHALE);
        blockModelGenerators.createTrivialCube(ModBlock.COBBLED_CLOUDSHALE);
        blockModelGenerators.createTrivialCube(ModBlock.MOSSY_COBBLED_CLOUDSHALE);
        blockModelGenerators.createTrivialCube(ModBlock.CHERRY_COBBLED_CLOUDSHALE);

        blockModelGenerators.woodProvider(ModBlock.GOLDENLEAF_LOG).log(ModBlock.GOLDENLEAF_LOG);
        blockModelGenerators.woodProvider(ModBlock.SAKURA_LOG).log(ModBlock.SAKURA_LOG);
        blockModelGenerators.woodProvider(ModBlock.FRIGID_LOG).log(ModBlock.FRIGID_LOG);
        blockModelGenerators.woodProvider(ModBlock.ARBOREAL_CACTUS_STEM).log(ModBlock.ARBOREAL_CACTUS_STEM);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItem.RAW_ALEXANDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.ALEXANDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.declareCustomModelItem(ModItem.GLIDER);
        itemModelGenerators.generateFlatItem(ModBlock.ARBOREAL_CACTUS_FRUIT.asItem(), ModelTemplates.FLAT_ITEM);
    }


    public final void createCloudshaleGrassBlock(BlockModelGenerators blockModelGenerators, Block block) {
        TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlock.CLOUDSHALE)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block)).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, blockModelGenerators.modelOutput))));
    }
}
