package gay.beegirl.datagen;

import gay.beegirl.block.ModBlocks;
import gay.beegirl.item.ModItems;
import gay.beegirl.registry.armor_trim.ModTrimMaterials;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.RAW_ALEXANDRITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.ALEXANDRITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.STONE_ALEXANDRITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.DEEPSLATE_ALEXANDRITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE);
        createCloudshaleGrassBlock(blockModelGenerators, ModBlocks.CLOUDSHALE_GRASS);
        createCloudshaleGrassBlock(blockModelGenerators, ModBlocks.CLOUDSHALE_CHERRY_GRASS);
        createPointedBlock(blockModelGenerators, ModBlocks.POINTED_CLOUDSHALE);
        blockModelGenerators.family(ModBlocks.CLOUDSHALE.base()).generateFor(ModBlocks.CLOUDSHALE_FAMILY);
        blockModelGenerators.family(ModBlocks.COBBLED_CLOUDSHALE.base()).generateFor(ModBlocks.COBBLED_CLOUDSHALE_FAMILY);
        blockModelGenerators.family(ModBlocks.MOSSY_COBBLED_CLOUDSHALE.base()).generateFor(ModBlocks.MOSSY_COBBLED_CLOUDSHALE_FAMILY);
        blockModelGenerators.family(ModBlocks.CHERRY_COBBLED_CLOUDSHALE.base()).generateFor(ModBlocks.CHERRY_COBBLED_CLOUDSHALE_FAMILY);

        createWoodTypeModels(blockModelGenerators, ModBlocks.GOLDENLEAF_PLANKS, ModBlocks.GOLDENLEAF_PLANKS_FAMILY);
        blockModelGenerators.createTintedLeaves(ModBlocks.GOLDENLEAF_LEAVES, TexturedModel.LEAVES, 0);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.GOLDENLEAF_SAPLING, ModBlocks.POTTED_GOLDENLEAF_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlocks.SAKURA_PLANKS, ModBlocks.SAKURA_PLANKS_FAMILY);
        blockModelGenerators.createTrivialBlock(ModBlocks.SAKURA_LEAVES, TexturedModel.LEAVES);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.SAKURA_SAPLING, ModBlocks.POTTED_SAKURA_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlocks.FRIGID_PLANKS, ModBlocks.FRIGID_PLANKS_FAMILY);
        blockModelGenerators.createTintedLeaves(ModBlocks.FRIGID_LEAVES, TexturedModel.LEAVES, 0);
        blockModelGenerators.createPlantWithDefaultItem(ModBlocks.FRIGID_SAPLING, ModBlocks.POTTED_FRIGID_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);

        createWoodTypeModels(blockModelGenerators, ModBlocks.ARBOREAL_CACTUS_PLANKS, ModBlocks.ARBOREAL_CACTUS_PLANKS_FAMILY);
        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.POTTED_ARBOREAL_CACTUS);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.RAW_ALEXANDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ALEXANDRITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.declareCustomModelItem(ModItems.GLIDER);

        itemModelGenerators.generateFlatItem(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.declareCustomModelItem(ModBlocks.POINTED_CLOUDSHALE.asItem());
        itemModelGenerators.generateFlatItem(ModItems.GOLDENLEAF_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.GOLDENLEAF_CHEST_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SAKURA_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.SAKURA_CHEST_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FRIGID_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.FRIGID_CHEST_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ARBOREAL_CACTUS_BOAT.asItem(), ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.ARBOREAL_CACTUS_CHEST_BOAT.asItem(), ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(ModBlocks.ARBOREAL_CACTUS_FRUIT.asItem(), ModelTemplates.FLAT_ITEM);

        //Overwrites
        itemModelGenerators.generateTrimmableItem(Items.TURTLE_HELMET, EquipmentAssets.TURTLE_SCUTE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(Items.LEATHER_HELMET, EquipmentAssets.LEATHER, ItemModelGenerators.TRIM_PREFIX_HELMET, true);
        itemModelGenerators.generateTrimmableItem(Items.LEATHER_CHESTPLATE, EquipmentAssets.LEATHER, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, true);
        itemModelGenerators.generateTrimmableItem(Items.LEATHER_LEGGINGS, EquipmentAssets.LEATHER, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, true);
        itemModelGenerators.generateTrimmableItem(Items.LEATHER_BOOTS, EquipmentAssets.LEATHER, ItemModelGenerators.TRIM_PREFIX_BOOTS, true);
        itemModelGenerators.generateTrimmableItem(Items.CHAINMAIL_HELMET, EquipmentAssets.CHAINMAIL, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(Items.CHAINMAIL_CHESTPLATE, EquipmentAssets.CHAINMAIL, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(Items.CHAINMAIL_LEGGINGS, EquipmentAssets.CHAINMAIL, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(Items.CHAINMAIL_BOOTS, EquipmentAssets.CHAINMAIL, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerators.generateTrimmableItem(Items.IRON_HELMET, EquipmentAssets.IRON, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(Items.IRON_CHESTPLATE, EquipmentAssets.IRON, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(Items.IRON_LEGGINGS, EquipmentAssets.IRON, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(Items.IRON_BOOTS, EquipmentAssets.IRON, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerators.generateTrimmableItem(Items.DIAMOND_HELMET, EquipmentAssets.DIAMOND, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(Items.DIAMOND_CHESTPLATE, EquipmentAssets.DIAMOND, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(Items.DIAMOND_LEGGINGS, EquipmentAssets.DIAMOND, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(Items.DIAMOND_BOOTS, EquipmentAssets.DIAMOND, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerators.generateTrimmableItem(Items.GOLDEN_HELMET, EquipmentAssets.GOLD, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(Items.GOLDEN_CHESTPLATE, EquipmentAssets.GOLD, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(Items.GOLDEN_LEGGINGS, EquipmentAssets.GOLD, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(Items.GOLDEN_BOOTS, EquipmentAssets.GOLD, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
        itemModelGenerators.generateTrimmableItem(Items.NETHERITE_HELMET, EquipmentAssets.NETHERITE, ItemModelGenerators.TRIM_PREFIX_HELMET, false);
        itemModelGenerators.generateTrimmableItem(Items.NETHERITE_CHESTPLATE, EquipmentAssets.NETHERITE, ItemModelGenerators.TRIM_PREFIX_CHESTPLATE, false);
        itemModelGenerators.generateTrimmableItem(Items.NETHERITE_LEGGINGS, EquipmentAssets.NETHERITE, ItemModelGenerators.TRIM_PREFIX_LEGGINGS, false);
        itemModelGenerators.generateTrimmableItem(Items.NETHERITE_BOOTS, EquipmentAssets.NETHERITE, ItemModelGenerators.TRIM_PREFIX_BOOTS, false);
    }


    public final void createCloudshaleGrassBlock(BlockModelGenerators blockModelGenerators, Block block) {
        TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlocks.CLOUDSHALE.base())).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block)).put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side"));
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

    private void createWoodTypeModels(BlockModelGenerators blockModelGenerators, ModBlocks.WoodSetBlocks woodSetBlocks, BlockFamily family) {
        blockModelGenerators.woodProvider(woodSetBlocks.log()).log(woodSetBlocks.log()).wood(woodSetBlocks.wood());
        blockModelGenerators.woodProvider(woodSetBlocks.strippedLog()).log(woodSetBlocks.strippedLog()).wood(woodSetBlocks.strippedWood());
        blockModelGenerators.createHangingSign(woodSetBlocks.strippedLog(), woodSetBlocks.hangingSign(), woodSetBlocks.hangingWallSign());
        blockModelGenerators.family(woodSetBlocks.planks()).generateFor(family);
    }
}