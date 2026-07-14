package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.tags.ModItemTags;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.data.recipes.SewingDesignRecipeBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        List<ItemLike> ZEPHYRUM_SMELTING = List.of(
                ModItems.RAW_ZEPHYRUM,
                ModBlocks.STONE_ZEPHYRUM_ORE,
                ModBlocks.DEEPSLATE_ZEPHYRUM_ORE,
                ModBlocks.CLOUDSHALE_ZEPHYRUM_ORE
        );
        oreSmelting(recipeOutput, ZEPHYRUM_SMELTING, RecipeCategory.MISC, ModItems.ZEPHYRUM, 0.25f, 200, "zephyrum");
        oreBlasting(recipeOutput, ZEPHYRUM_SMELTING, RecipeCategory.MISC, ModItems.ZEPHYRUM, 0.25f, 100, "zephyrum");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ZEPHYRUM_BLOCK)
                .pattern("zzz")
                .pattern("zzz")
                .pattern("zzz")
                .define('z', ModItems.RAW_ZEPHYRUM)
                .unlockedBy(getHasName(ModItems.RAW_ZEPHYRUM), has(ModItems.RAW_ZEPHYRUM))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ZEPHYRUM_BLOCK)
                .pattern("zzz")
                .pattern("zzz")
                .pattern("zzz")
                .define('z', ModItems.ZEPHYRUM)
                .unlockedBy(getHasName(ModItems.ZEPHYRUM), has(ModItems.ZEPHYRUM))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ZEPHYRUM, 9)
                .requires(ModBlocks.RAW_ZEPHYRUM_BLOCK)
                .unlockedBy(getHasName(ModBlocks.RAW_ZEPHYRUM_BLOCK), has(ModBlocks.RAW_ZEPHYRUM_BLOCK))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ZEPHYRUM, 9)
                .requires(ModBlocks.ZEPHYRUM_BLOCK)
                .unlockedBy(getHasName(ModBlocks.ZEPHYRUM_BLOCK), has(ModBlocks.ZEPHYRUM_BLOCK))
                .save(recipeOutput);


        copySmithingTemplate(recipeOutput, ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get(), ModBlocks.COBBLED_CLOUDSHALE.base().asItem());
        trimSmithing(recipeOutput, ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get());
        copySewingTemplate(recipeOutput, ModItems.TESTING_GLIDER_DESIGN_SEWING_TEMPLATE.get());
        designSewing(recipeOutput, ModItems.TESTING_GLIDER_DESIGN_SEWING_TEMPLATE.get());

        smeltingResultFromBase(recipeOutput, ModBlocks.CLOUDSHALE, ModBlocks.COBBLED_CLOUDSHALE.base());
        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLOUDSHALE.get(), ModBlocks.POINTED_CLOUDSHALE);

        createStoneSetRecipes(recipeOutput, ModBlocks.COBBLED_CLOUDSHALE);
        createStoneSetRecipes(recipeOutput, ModBlocks.MOSSY_COBBLED_CLOUDSHALE);
        createStoneSetRecipes(recipeOutput, ModBlocks.CLOUDSHALE_BRICK);

        createWoodSetRecipes(recipeOutput, ModBlocks.GOLDENLEAF_LOGS, ModItemTags.GOLDENLEAF_LOGS, ModBlocks.GOLDENLEAF_PLANKS, ModItems.GOLDENLEAF_BOAT, ModItems.GOLDENLEAF_CHEST_BOAT);
        createWoodSetRecipes(recipeOutput, ModBlocks.SAKURA_LOGS, ModItemTags.SAKURA_LOGS, ModBlocks.SAKURA_PLANKS, ModItems.SAKURA_BOAT, ModItems.SAKURA_CHEST_BOAT);
        createWoodSetRecipes(recipeOutput, ModBlocks.FRIGID_LOGS, ModItemTags.FRIGID_LOGS, ModBlocks.FRIGID_PLANKS, ModItems.FRIGID_BOAT, ModItems.FRIGID_CHEST_BOAT);
        createWoodSetRecipes(recipeOutput, ModBlocks.ARBOREAL_CACTUSES, ModItemTags.ARBOREAL_CACTUSES, ModBlocks.ARBOREAL_CACTUS_PLANKS, ModItems.ARBOREAL_CACTUS_BOAT, ModItems.ARBOREAL_CACTUS_CHEST_BOAT);

        oneToOneConversionRecipe(recipeOutput, Items.WHITE_DYE, ModBlocks.WHITE_PETALS.asItem(), "white_dye", 1);
    }

    protected static void twoByTwoPacker(RecipeOutput recipeOutput, RecipeCategory category, ItemLike packed, ItemLike unpacked) {
        ShapedRecipeBuilder.shaped(category, packed, 1)
                .define('#', unpacked)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(unpacked), has(unpacked))
                .save(recipeOutput, BuiltInRegistries.ITEM.getKey(unpacked.asItem()).withSuffix("_packing"));
    }

    private static void createStoneSetRecipes(RecipeOutput recipeOutput, ModBlocks.StoneBlockSet stoneBlockSet) {

    }
    private static void createWoodSetRecipes(RecipeOutput recipeOutput, ModBlocks.LogBlockSet logBlockSet, TagKey<Item> logs, ModBlocks.WoodBlockSet woodBlockSet, ItemLike boat, ItemLike chestBoat) {
        woodFromLogs(recipeOutput, logBlockSet.wood(), logBlockSet.log());
        woodFromLogs(recipeOutput, logBlockSet.strippedWood(), logBlockSet.strippedLog());
        planksFromLogs(recipeOutput, woodBlockSet.base(), logs, 4);
        hangingSign(recipeOutput, woodBlockSet.hangingSign(), logBlockSet.strippedLog());
        woodenBoat(recipeOutput, boat, woodBlockSet.base());
        chestBoat(recipeOutput, chestBoat, boat);
    }
    private static void createWoodSetRecipes(RecipeOutput recipeOutput, ModBlocks.CactusBlockSet cactusBlockSet, TagKey<Item> cactuses, ModBlocks.WoodBlockSet woodBlockSet, ItemLike boat, ItemLike chestBoat) {
        planksFromLogs(recipeOutput, woodBlockSet.base(), cactuses, 4);
        hangingSign(recipeOutput, woodBlockSet.hangingSign(), cactusBlockSet.despinedCactus());
        woodenBoat(recipeOutput, boat, woodBlockSet.base());
        chestBoat(recipeOutput, chestBoat, boat);
    }

    private static void trimSmithing(RecipeOutput recipeOutput, Item ingredientItem) {
        SmithingTrimRecipeBuilder.smithingTrim(
                        Ingredient.of(ingredientItem),
                        Ingredient.of(ItemTags.TRIMMABLE_ARMOR),
                        Ingredient.of(ItemTags.TRIM_MATERIALS),
                        RecipeCategory.MISC)
                .unlocks("has_smithing_trim_template",
                        has(ingredientItem))
                .save(recipeOutput,
                        SkysSkyIslands.createId(BuiltInRegistries.ITEM.getKey(ingredientItem).getPath() + "_smithing_trim"));
    }
    private static void copySewingTemplate(RecipeOutput recipeOutput, ItemLike template) {
        copySmithingTemplate(recipeOutput, template, ItemTags.WOOL);
    }
    private static void designSewing(RecipeOutput recipeOutput, Item ingredientItem) {
        SewingDesignRecipeBuilder.sewingDesign(
                        Ingredient.of(ingredientItem),
                        Ingredient.of(ModItems.GLIDER),
                        Ingredient.of(Items.STRING),
                        RecipeCategory.MISC)
                .unlocks("has_sewing_design_template",
                        has(ingredientItem))
                .save(recipeOutput,
                        SkysSkyIslands.createId(BuiltInRegistries.ITEM.getKey(ingredientItem).getPath() + "_sewing"));
    }

    // Copied* from RecipeProvider
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String suffix) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, SkysSkyIslands.createId(getItemName(result) + suffix + "_" + getItemName(itemlike)));
        }

    }
}
