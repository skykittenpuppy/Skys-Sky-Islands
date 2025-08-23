package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlock.RAW_ZONAITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlock.REFINED_ZONAITE_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlock.STONE_ZONAITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlock.DEEPSLATE_ZONAITE_ORE);
        blockModelGenerators.createTrivialCube(ModBlock.SKYSTONE_ZONAITE_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItem.RAW_ZONAITE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItem.REFINED_ZONAITE, ModelTemplates.FLAT_ITEM);
    }
}
