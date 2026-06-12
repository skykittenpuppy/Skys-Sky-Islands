package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_ALEXANDRITE.get());
        basicItem(ModItems.ALEXANDRITE.get());

        //basicItem(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        basicItem(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE.get());

        //basicItem(ModItems.GOLDENLEAF_BOAT.get());
        //basicItem(ModItems.GOLDENLEAF_CHEST_BOAT.get());
        //basicItem(ModItems.SAKURA_BOAT.get());
        //basicItem(ModItems.SAKURA_CHEST_BOAT.get());
        //basicItem(ModItems.FRIGID_BOAT.get());
        //basicItem(ModItems.FRIGID_CHEST_BOAT.get());
        //basicItem(ModItems.ARBOREAL_CACTUS_BOAT.get());
        //basicItem(ModItems.ARBOREAL_CACTUS_CHEST_BOAT.get());

        //basicItem(ModItems.ARBOREAL_CACTUS_FRUIT.get());
    }
}
