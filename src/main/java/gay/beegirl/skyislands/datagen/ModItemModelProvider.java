package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.client.renderer.item.ModItemProperties;
import gay.beegirl.skyislands.world.item.armortrim.ModTrimMaterials;
import gay.beegirl.skyislands.world.item.gliderdesign.HangGliderDesign;
import gay.beegirl.skyislands.world.item.gliderdesign.ModHangGliderDesigns;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModItemModelProvider extends ItemModelProvider {
    private static final List<ResourceKey<TrimMaterial>> MOD_TRIM_MATERIALS = List.of(
            ModTrimMaterials.ZEPHYRUM
    );
    private static final List<ResourceKey<HangGliderDesign>> MOD_GLIDER_DESIGNS = List.of(
            ModHangGliderDesigns.TESTING
    );

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_ZEPHYRUM.get());
        basicItem(ModItems.ZEPHYRUM.get());

        basicItem(ModItems.BATTERY.get());

        parentedItem(ModBlocks.POINTED_CLOUDSHALE.asItem(), Items.POINTED_DRIPSTONE);
        basicItem(ModBlocks.WHITE_PETALS.asItem());

        generateArmorModelsForMaterials(MOD_TRIM_MATERIALS);
        generateGliderModelsForPatterns(MOD_GLIDER_DESIGNS);

        basicItem(ModItems.TESTING_POTTERY_SHERD.get());
        basicItem(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        basicItem(ModItems.TESTING_HANG_GLIDER_DESIGN_SEWING_TEMPLATE.get());

        basicItem(ModItems.GOLDENLEAF_BOAT.get());
        basicItem(ModItems.GOLDENLEAF_CHEST_BOAT.get());

        basicItem(ModItems.SAKURA_BOAT.get());
        basicItem(ModItems.SAKURA_CHEST_BOAT.get());

        basicItem(ModItems.FRIGID_BOAT.get());
        basicItem(ModItems.FRIGID_CHEST_BOAT.get());

        basicItem(ModItems.ARBOREAL_CACTUS_BOAT.get());
        basicItem(ModItems.ARBOREAL_CACTUS_CHEST_BOAT.get());
        basicItem(ModItems.ARBOREAL_CACTUS_FRUIT.get());
    }

    public ItemModelBuilder parentedItem(Item item, Item parent){
        return getBuilder(name(item))
                .parent(new ModelFile.UncheckedModelFile(itemTexture(parent)))
                .texture("layer0", itemTexture(item));
    }

    public void generateGliderModelsForPatterns(List<ResourceKey<HangGliderDesign>> gliderDesigns) {
        Item glider = ModItems.HANG_GLIDER.get();
        ItemModelBuilder modelBuilder = getBuilder(name(glider))
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", itemTexture(glider))
                .texture("layer1", SkysSkyIslands.createId("designs/items/default"));
        for(ResourceKey<HangGliderDesign> gliderDesign : gliderDesigns) {
            ItemModelBuilder patternedModel = getBuilder(name(glider) + "_" + gliderDesign.location().getPath() + "_design")
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", itemTexture(glider))
                    .texture("layer1", designTexture(gliderDesign));
            modelBuilder.override()
                    .model(patternedModel)
                    .predicate(ModItemProperties.HANG_GLIDER_DESIGN_PREDICATE, gliderDesign.location().hashCode());
        }
    }

    public void generateArmorModelsForMaterials(List<ResourceKey<TrimMaterial>> trimMaterials) {
        for (ResourceKey<TrimMaterial> trimMaterial : trimMaterials) {
            ResourceLocation patternLoc = trimMaterial.location();
            existingFileHelper.trackGenerated(ResourceLocation.withDefaultNamespace(patternLoc.withPrefix("trims/items/helmet_trim_").getPath()), ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(ResourceLocation.withDefaultNamespace(patternLoc.withPrefix("trims/items/chestplate_trim_").getPath()), ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(ResourceLocation.withDefaultNamespace(patternLoc.withPrefix("trims/items/leggings_trim_").getPath()), ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(ResourceLocation.withDefaultNamespace(patternLoc.withPrefix("trims/items/boots_trim_").getPath()), ModelProvider.TEXTURE);
        }
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof ArmorItem armorItem && armorItem.getType().hasTrims()) {
                for (ResourceKey<TrimMaterial> trimMaterial : trimMaterials) {
                    getBuilder(name(armorItem) + "_" + trimMaterial.location().getPath() + "_trim")
                            .parent(new ModelFile.UncheckedModelFile("item/generated"))
                            .texture("layer0", itemTexture(armorItem))
                            .texture("layer1", trimTexture(armorItem, trimMaterial));
                }
            }
        }
    }

    public @NotNull ResourceLocation designTexture(ResourceKey<HangGliderDesign> gliderDesign) {
        return ResourceLocation.fromNamespaceAndPath(gliderDesign.location().getNamespace(), "designs/items/" + gliderDesign.location().getPath());
    }
    public @NotNull ResourceLocation trimTexture(@NotNull ArmorItem item, ResourceKey<TrimMaterial> trimMaterial) {
        return ResourceLocation.withDefaultNamespace("trims/items/" + item.getType().getName() + "_trim_" + trimMaterial.location().getPath());
    }
    // Copied from BlockStateProvider
    public @NotNull ResourceLocation itemTexture(@NotNull Item item) {
        ResourceLocation name = key(item);
        return ResourceLocation.fromNamespaceAndPath(name.getNamespace(), "item/" + name.getPath());
    }
    private ResourceLocation key(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }
    private String name(Item item) {
        return key(item).getPath();
    }
}
