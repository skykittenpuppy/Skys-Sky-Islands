package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
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
        List<ItemLike> ALEXANDRITE_SMELTING = List.of(
                ModItems.RAW_ALEXANDRITE,
                ModBlocks.STONE_ALEXANDRITE_ORE,
                ModBlocks.DEEPSLATE_ALEXANDRITE_ORE,
                ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE
        );
        oreSmelting(recipeOutput, ALEXANDRITE_SMELTING, RecipeCategory.MISC, ModItems.ALEXANDRITE, 0.25f, 200, "alexandrite");
        oreBlasting(recipeOutput, ALEXANDRITE_SMELTING, RecipeCategory.MISC, ModItems.ALEXANDRITE, 0.25f, 100, "alexandrite");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_BLOCK)
                .pattern("zzz")
                .pattern("zzz")
                .pattern("zzz")
                .define('z', ModItems.RAW_ALEXANDRITE)
                .unlockedBy(getHasName(ModItems.RAW_ALEXANDRITE), has(ModItems.RAW_ALEXANDRITE))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK)
                .pattern("zzz")
                .pattern("zzz")
                .pattern("zzz")
                .define('z', ModItems.ALEXANDRITE)
                .unlockedBy(getHasName(ModItems.ALEXANDRITE), has(ModItems.ALEXANDRITE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE, 9)
                .requires(ModBlocks.RAW_ALEXANDRITE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.RAW_ALEXANDRITE_BLOCK), has(ModBlocks.RAW_ALEXANDRITE_BLOCK))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE, 9)
                .requires(ModBlocks.ALEXANDRITE_BLOCK)
                .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK), has(ModBlocks.ALEXANDRITE_BLOCK))
                .save(recipeOutput);

        //SmithingTrimRecipeBuilder.copySmithingTemplate(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE, ModBlocks.CLOUDSHALE.base());
        //SmithingTrimRecipeBuilder.smithingTrim(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE, ModTrimPatterns.TESTING);
        //sewingTrim(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE, ModGliderDesigns.TESTING);

        twoByTwoPacker(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLOUDSHALE.base(), ModBlocks.POINTED_CLOUDSHALE);
        //generateRecipes(recipeOutput, ModBlocks.CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
        //generateRecipes(recipeOutput, ModBlocks.COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
        //generateRecipes(recipeOutput, ModBlocks.MOSSY_COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
        //generateRecipes(recipeOutput, ModBlocks.CHERRY_COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

        //createWoodTypeRecipes(ModTags.Items.GOLDENLEAF_LOGS, ModItems.GOLDENLEAF_BOAT, ModItems.GOLDENLEAF_CHEST_BOAT, ModBlocks.GOLDENLEAF_PLANKS, ModBlocks.GOLDENLEAF_PLANKS_FAMILY);

        //createWoodTypeRecipes(ModTags.Items.SAKURA_LOGS, ModItems.SAKURA_BOAT, ModItems.SAKURA_CHEST_BOAT, ModBlocks.SAKURA_PLANKS, ModBlocks.SAKURA_PLANKS_FAMILY);

        //createWoodTypeRecipes(ModTags.Items.FRIGID_LOGS, ModItems.FRIGID_BOAT, ModItems.FRIGID_CHEST_BOAT, ModBlocks.FRIGID_PLANKS, ModBlocks.FRIGID_PLANKS_FAMILY);

        //createWoodTypeRecipes(ModTags.Items.ARBOREAL_CACTUS_STEMS, ModItems.ARBOREAL_CACTUS_BOAT, ModItems.ARBOREAL_CACTUS_CHEST_BOAT, ModBlocks.ARBOREAL_CACTUS_PLANKS, ModBlocks.ARBOREAL_CACTUS_PLANKS_FAMILY);
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String suffix) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, SkysSkyIslands.MOD_ID + ":" + getItemName(result) + suffix + "_" + getItemName(itemlike));
        }

    }

    private void createWoodTypeRecipes(RecipeOutput recipeOutput, TagKey<Item> logs, ItemLike boat, ItemLike chestBoat, ModBlocks.WoodSetBlocks woodSetBlocks, BlockFamily family) {
        woodFromLogs(recipeOutput, woodSetBlocks.wood(), woodSetBlocks.log());
        woodFromLogs(recipeOutput, woodSetBlocks.strippedWood(), woodSetBlocks.strippedLog());
        planksFromLogs(recipeOutput, woodSetBlocks.planks(), logs, 4);
        generateRecipes(recipeOutput, family, FeatureFlagSet.of(FeatureFlags.VANILLA));
        hangingSign(recipeOutput, woodSetBlocks.hangingSign(), woodSetBlocks.strippedLog());
        woodenBoat(recipeOutput, boat, woodSetBlocks.planks());
        chestBoat(recipeOutput, chestBoat, boat);
    }

    /*public void sewingTrim(ItemLike item, ResourceKey<GliderDesign> resourceKey) {
        SewingDesignRecipeBuilder.sewingDesign(
                        Ingredient.of(item),
                        Ingredient.of(ModItems.GLIDER),
                        this.registries.lookupOrThrow(ModRegistryResourceKeys.GLIDER_DESIGN).getOrThrow(resourceKey),
                        RecipeCategory.MISC)
                .unlocks("has_sewing_design_template", this.has(item))
                .save(this.output, ResourceKey.create(Registries.RECIPE, SkysSkyIslands.createId(getItemName(item) + "_sewing_design")));
    }*/
}
