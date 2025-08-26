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
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

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

        createWoodTypeModels(blockModelGenerators, ModBlock.GOLDENLEAF_LOG, ModBlock.GOLDENLEAF_WOOD, ModBlock.STRIPPED_GOLDENLEAF_LOG, ModBlock.STRIPPED_GOLDENLEAF_WOOD, ModBlock.GOLDENLEAF_PLANKS, ModBlock.GOLDENLEAF_STAIRS, ModBlock.GOLDENLEAF_SLAB, ModBlock.GOLDENLEAF_BUTTON, ModBlock.GOLDENLEAF_PRESSURE_PLATE, ModBlock.GOLDENLEAF_FENCE, ModBlock.GOLDENLEAF_FENCE_GATE, ModBlock.GOLDENLEAF_DOOR, ModBlock.GOLDENLEAF_TRAPDOOR);
        blockModelGenerators.createTintedLeaves(ModBlock.GOLDENLEAF_LEAVES, TexturedModel.LEAVES, 0);
        blockModelGenerators.createPlantWithDefaultItem(ModBlock.GOLDENLEAF_SAPLING, ModBlock.POTTED_GOLDENLEAF_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlock.SAKURA_LOG, ModBlock.SAKURA_WOOD, ModBlock.STRIPPED_SAKURA_LOG, ModBlock.STRIPPED_SAKURA_WOOD, ModBlock.SAKURA_PLANKS, ModBlock.SAKURA_STAIRS, ModBlock.SAKURA_SLAB, ModBlock.SAKURA_BUTTON, ModBlock.SAKURA_PRESSURE_PLATE, ModBlock.SAKURA_FENCE, ModBlock.SAKURA_FENCE_GATE, ModBlock.SAKURA_DOOR, ModBlock.SAKURA_TRAPDOOR);
        blockModelGenerators.createTrivialBlock(ModBlock.SAKURA_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlock.SAKURA_SAPLING, ModBlock.POTTED_SAKURA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlock.FRIGID_LOG, ModBlock.FRIGID_WOOD, ModBlock.STRIPPED_FRIGID_LOG, ModBlock.STRIPPED_FRIGID_WOOD, ModBlock.FRIGID_PLANKS, ModBlock.FRIGID_STAIRS, ModBlock.FRIGID_SLAB, ModBlock.FRIGID_BUTTON, ModBlock.FRIGID_PRESSURE_PLATE, ModBlock.FRIGID_FENCE, ModBlock.FRIGID_FENCE_GATE, ModBlock.FRIGID_DOOR, ModBlock.FRIGID_TRAPDOOR);
        blockModelGenerators.createTintedLeaves(ModBlock.FRIGID_LEAVES, TexturedModel.LEAVES, 0);
        blockModelGenerators.createPlantWithDefaultItem(ModBlock.FRIGID_SAPLING, ModBlock.POTTED_FRIGID_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlock.ARBOREAL_CACTUS_STEM, ModBlock.ARBOREAL_CACTUS_HYPHAE, ModBlock.STRIPPED_ARBOREAL_CACTUS_STEM, ModBlock.STRIPPED_ARBOREAL_CACTUS_HYPHAE, ModBlock.ARBOREAL_CACTUS_PLANKS, ModBlock.ARBOREAL_CACTUS_STAIRS, ModBlock.ARBOREAL_CACTUS_SLAB, ModBlock.ARBOREAL_CACTUS_BUTTON, ModBlock.ARBOREAL_CACTUS_PRESSURE_PLATE, ModBlock.ARBOREAL_CACTUS_FENCE, ModBlock.ARBOREAL_CACTUS_FENCE_GATE, ModBlock.ARBOREAL_CACTUS_DOOR, ModBlock.ARBOREAL_CACTUS_TRAPDOOR);
        blockModelGenerators.createNonTemplateModelBlock(ModBlock.POTTED_ARBOREAL_CACTUS);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItem.RAW_ALEXANDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.ALEXANDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.declareCustomModelItem(ModItem.GLIDER);

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
        TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlock.CLOUDSHALE)).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block)).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, BlockModelGenerators.plainVariant(ModelTemplates.CUBE_BOTTOM_TOP.create(block, textureMapping, blockModelGenerators.modelOutput))));
    }

    private void createWoodTypeModels(BlockModelGenerators blockModelGenerators, Block log, Block wood, Block strippedLog, Block strippedWood, Block planks, Block stairs, Block slab, Block button, Block pressurePlate, Block fence, Block fenceGate, Block door, Block trapdoor) {
        blockModelGenerators.woodProvider(log).log(log).wood(wood);
        blockModelGenerators.woodProvider(strippedLog).log(strippedLog).wood(strippedWood);
        BlockModelGenerators.BlockFamilyProvider plankPool = blockModelGenerators.family(planks);
        plankPool.stairs(stairs);
        plankPool.slab(slab);
        plankPool.button(button);
        plankPool.pressurePlate(pressurePlate);
        plankPool.fence(fence);
        plankPool.fenceGate(fenceGate);
        blockModelGenerators.createDoor(door);
        blockModelGenerators.createTrapdoor(trapdoor);
    }
}