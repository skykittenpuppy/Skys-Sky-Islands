package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.client.renderer.item.ModItemProperties;
import gay.beegirl.skyislands.core.registries.ModRegistries;
import gay.beegirl.skyislands.world.item.armortrim.ModTrimMaterials;
import gay.beegirl.skyislands.world.item.gliderthing.ModThingPatterns;
import gay.beegirl.skyislands.world.item.gliderthing.ThingPattern;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import gay.beegirl.skyislands.world.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.level.block.*;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.ModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.UnknownNullability;

import java.util.List;
import java.util.Objects;

public class ModItemModelProvider extends ItemModelProvider {
    private static final List<ResourceKey<TrimMaterial>> MOD_TRIM_MATERIALS = List.of(
            ModTrimMaterials.ALEXANDRITE
    );
    private static final List<ResourceKey<ThingPattern>> MOD_THING_PATTERNS = List.of(
            ModThingPatterns.TESTING, ModThingPatterns.TESTING2
    );

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SkysSkyIslands.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_ALEXANDRITE.get());
        basicItem(ModItems.ALEXANDRITE.get());

        gliderItem();
        for (ResourceKey<TrimMaterial> trimMaterial : MOD_TRIM_MATERIALS) {
            existingFileHelper.trackGenerated(ResourceLocation.withDefaultNamespace(trimMaterial.location().withPrefix("trims/items/helmet_trim_").getPath()), ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(ResourceLocation.withDefaultNamespace(trimMaterial.location().withPrefix("trims/items/chestplate_trim_").getPath()), ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(ResourceLocation.withDefaultNamespace(trimMaterial.location().withPrefix("trims/items/leggings_trim_").getPath()), ModelProvider.TEXTURE);
            existingFileHelper.trackGenerated(ResourceLocation.withDefaultNamespace(trimMaterial.location().withPrefix("trims/items/boots_trim_").getPath()), ModelProvider.TEXTURE);
        }
        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof ArmorItem armorItem && armorItem.getType().hasTrims()) {
                for (ResourceKey<TrimMaterial> trimMaterial : MOD_TRIM_MATERIALS) {
                    generateTrimmedArmorModel(armorItem, trimMaterial);
                }
            }
        }

        basicItem(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        basicItem(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE.get());

        createStoneSetItemModels(ModBlocks.CLOUDSHALE);
        createStoneSetItemModels(ModBlocks.COBBLED_CLOUDSHALE);
        createStoneSetItemModels(ModBlocks.MOSSY_COBBLED_CLOUDSHALE);
        createStoneSetItemModels(ModBlocks.CHERRY_COBBLED_CLOUDSHALE);

        createLogSetItemModels(ModBlocks.GOLDENLEAF_LOGS);
        createWoodSetItemModels(ModBlocks.GOLDENLEAF_PLANKS);
        uncheckedBlockItem(ModBlocks.GOLDENLEAF_LEAVES.get());
        flatBlockItem(ModBlocks.GOLDENLEAF_SAPLING.get());
        basicItem(ModItems.GOLDENLEAF_BOAT.get());
        basicItem(ModItems.GOLDENLEAF_CHEST_BOAT.get());

        createLogSetItemModels(ModBlocks.SAKURA_LOGS);
        createWoodSetItemModels(ModBlocks.SAKURA_PLANKS);
        uncheckedBlockItem(ModBlocks.SAKURA_LEAVES.get());
        flatBlockItem(ModBlocks.SAKURA_SAPLING.get());
        basicItem(ModItems.SAKURA_BOAT.get());
        basicItem(ModItems.SAKURA_CHEST_BOAT.get());

        createLogSetItemModels(ModBlocks.FRIGID_LOGS);
        createWoodSetItemModels(ModBlocks.FRIGID_PLANKS);
        uncheckedBlockItem(ModBlocks.FRIGID_LEAVES.get());
        flatBlockItem(ModBlocks.FRIGID_SAPLING.get());
        basicItem(ModItems.FRIGID_BOAT.get());
        basicItem(ModItems.FRIGID_CHEST_BOAT.get());

        createCactusSetItemModels(ModBlocks.ARBOREAL_CACTUSES);
        createWoodSetItemModels(ModBlocks.ARBOREAL_CACTUS_PLANKS);
        basicItem(ModBlocks.ARBOREAL_CACTUS_FRUIT.get().asItem());
        basicItem(ModItems.ARBOREAL_CACTUS_BOAT.get());
        basicItem(ModItems.ARBOREAL_CACTUS_CHEST_BOAT.get());
    }

    public static final List<ThingPattern> GENERATED_TRIM_MODELS = List.of(
            new ThingPattern(ModThingPatterns.TESTING.location(), BuiltInRegistries.ITEM.wrapAsHolder(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE.get()), Component.empty()),
            new ThingPattern(ModThingPatterns.TESTING2.location(), BuiltInRegistries.ITEM.wrapAsHolder(ModItems.TESTING2_GLIDER_PATTERN_SEWING_TEMPLATE.get()), Component.empty())
    );

    public ItemModelBuilder gliderItem() {
        ResourceLocation itemLoc = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(ModItems.GLIDER.get()));
        ResourceLocation patternLoc = SkysSkyIslands.createId("default");
        ItemModelBuilder modelBuilder = getBuilder(itemLoc.getPath())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(itemLoc.getNamespace(), "item/" + itemLoc.getPath()))
                .texture("layer1", ResourceLocation.fromNamespaceAndPath(itemLoc.getNamespace(), "patterns/items/" + patternLoc.getPath()));

        for(ResourceKey<ThingPattern> thingPattern : MOD_THING_PATTERNS) {
            patternLoc = thingPattern.location();
            ItemModelBuilder thingModelBuilder = getBuilder(patternLoc.withPrefix(itemLoc.getPath() + "_").getPath())
                    .parent(new ModelFile.UncheckedModelFile("item/generated"))
                    .texture("layer0", ResourceLocation.fromNamespaceAndPath(itemLoc.getNamespace(), "item/" + itemLoc.getPath()))
                    .texture("layer1", ResourceLocation.fromNamespaceAndPath(itemLoc.getNamespace(), "patterns/items/" + patternLoc.getPath()));

            modelBuilder.override()
                    .model(thingModelBuilder)
                    .predicate(ModItemProperties.THING_TYPE, patternLoc.hashCode());
        }
        return modelBuilder;
    }

    public ItemModelBuilder generateTrimmedArmorModel(ArmorItem armorItem, ResourceKey<TrimMaterial> trimMaterial) {
        ResourceLocation itemLoc = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(armorItem));
        return getBuilder(itemLoc.withSuffix("_" + trimMaterial.location().getPath()).getPath() + "_trim")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(itemLoc.getNamespace(), "item/" + itemLoc.getPath()))
                .texture("layer1", ResourceLocation.withDefaultNamespace("trims/items/" + armorItem.getType().getName() + "_trim_" + trimMaterial.location().getPath()));
    }

    public ItemModelBuilder flatBlockItem(Block block) {
        ResourceLocation itemLoc = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(block.asItem()));
        return getBuilder(itemLoc.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(itemLoc.getNamespace(), "block/" + itemLoc.getPath()));
    }

    public ItemModelBuilder uncheckedBlockItem(Block block) {
        ResourceLocation blockLoc = Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block));
        return getBuilder(blockLoc.toString())
                .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(blockLoc.getNamespace(), "block/" + blockLoc.getPath())));
    }

    public ItemModelBuilder uncheckedBlockItem(Block block, String suffix) {
        ResourceLocation blockLoc = Objects.requireNonNull(BuiltInRegistries.BLOCK.getKey(block));
        return getBuilder(blockLoc.toString())
                .parent(new ModelFile.UncheckedModelFile(ResourceLocation.fromNamespaceAndPath(blockLoc.getNamespace(), "block/" + blockLoc.getPath() + suffix)));
    }

    public final void createStoneSetItemModels(ModBlocks.StoneBlockSet stoneSet){
        uncheckedBlockItem(stoneSet.base().get());
        uncheckedBlockItem(stoneSet.button().get(), "_inventory");
        uncheckedBlockItem(stoneSet.wall().get(), "_inventory");
        uncheckedBlockItem(stoneSet.slab().get());
        uncheckedBlockItem(stoneSet.stairs().get());
        uncheckedBlockItem(stoneSet.pressurePlate().get());
    }
    public final void createLogSetItemModels(ModBlocks.LogBlockSet logSet) {
        uncheckedBlockItem(logSet.log().get());
        uncheckedBlockItem(logSet.wood().get());
        uncheckedBlockItem(logSet.strippedLog().get());
        uncheckedBlockItem(logSet.strippedWood().get());
    }
    public final void createCactusSetItemModels(ModBlocks.CactusBlockSet cactusSet) {
        uncheckedBlockItem(cactusSet.cactus().get());
        uncheckedBlockItem(cactusSet.despinedCactus().get());
    }
    public final void createWoodSetItemModels(ModBlocks.WoodBlockSet woodSet) {
        uncheckedBlockItem(woodSet.base().get());
        uncheckedBlockItem(woodSet.button().get(), "_inventory");
        basicItem(woodSet.door().get().asItem());
        uncheckedBlockItem(woodSet.fence().get(), "_inventory");
        uncheckedBlockItem(woodSet.fenceGate().get());
        basicItem(woodSet.standingSign().get().asItem());
        basicItem(woodSet.hangingSign().get().asItem());
        uncheckedBlockItem(woodSet.slab().get());
        uncheckedBlockItem(woodSet.stairs().get());
        uncheckedBlockItem(woodSet.pressurePlate().get());
        uncheckedBlockItem(woodSet.trapdoor().get(), "_bottom");
    }
}
