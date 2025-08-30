package gay.beegirl.datagen;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.block.ModBlocks;
import gay.beegirl.component.GliderDesign;
import gay.beegirl.item.ModItems;
import gay.beegirl.recipe.SewingDesignRecipeBuilder;
import gay.beegirl.registry.ModRegistryResourceKeys;
import gay.beegirl.registry.armor_trim.ModTrimPatterns;
import gay.beegirl.registry.glider_pattern.GliderDesigns;
import gay.beegirl.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTrimRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }
    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                List<ItemLike> ALEXANDRITE_SMELTING = List.of(
                        ModItems.RAW_ALEXANDRITE,
                        ModBlocks.STONE_ALEXANDRITE_ORE,
                        ModBlocks.DEEPSLATE_ALEXANDRITE_ORE,
                        ModBlocks.CLOUDSHALE_ALEXANDRITE_ORE
                );
                oreSmelting(ALEXANDRITE_SMELTING, RecipeCategory.MISC, ModItems.ALEXANDRITE, 0.25f, 200, "alexandrite");
                oreBlasting(ALEXANDRITE_SMELTING, RecipeCategory.MISC, ModItems.ALEXANDRITE, 0.25f, 100, "alexandrite");

                shaped(RecipeCategory.MISC, ModBlocks.RAW_ALEXANDRITE_BLOCK)
                        .pattern("zzz")
                        .pattern("zzz")
                        .pattern("zzz")
                        .define('z', ModItems.RAW_ALEXANDRITE)
                        .unlockedBy(getHasName(ModItems.RAW_ALEXANDRITE), has(ModItems.RAW_ALEXANDRITE))
                        .save(recipeOutput);
                shaped(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK)
                        .pattern("zzz")
                        .pattern("zzz")
                        .pattern("zzz")
                        .define('z', ModItems.ALEXANDRITE)
                        .unlockedBy(getHasName(ModItems.ALEXANDRITE), has(ModItems.ALEXANDRITE))
                        .save(recipeOutput);

                shapeless(RecipeCategory.MISC, ModItems.RAW_ALEXANDRITE, 9)
                        .requires(ModBlocks.RAW_ALEXANDRITE_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.RAW_ALEXANDRITE_BLOCK), has(ModBlocks.RAW_ALEXANDRITE_BLOCK))
                        .save(recipeOutput);
                shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE, 9)
                        .requires(ModBlocks.ALEXANDRITE_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK), has(ModBlocks.ALEXANDRITE_BLOCK))
                        .save(recipeOutput);

                copySmithingTemplate(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE, ModBlocks.CLOUDSHALE.base());
                smithingTrim(ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE, ModTrimPatterns.TESTING);
                sewingTrim(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE, GliderDesigns.TESTING);

                twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLOUDSHALE.base(), ModBlocks.POINTED_CLOUDSHALE);
                generateRecipes(ModBlocks.CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(ModBlocks.COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(ModBlocks.MOSSY_COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(ModBlocks.CHERRY_COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                createWoodTypeRecipes(ModTags.Items.GOLDENLEAF_LOGS, ModItems.GOLDENLEAF_BOAT, ModItems.GOLDENLEAF_CHEST_BOAT, ModBlocks.GOLDENLEAF_PLANKS, ModBlocks.GOLDENLEAF_PLANKS_FAMILY);

                createWoodTypeRecipes(ModTags.Items.SAKURA_LOGS, ModItems.SAKURA_BOAT, ModItems.SAKURA_CHEST_BOAT, ModBlocks.SAKURA_PLANKS, ModBlocks.SAKURA_PLANKS_FAMILY);

                createWoodTypeRecipes(ModTags.Items.FRIGID_LOGS, ModItems.FRIGID_BOAT, ModItems.FRIGID_CHEST_BOAT, ModBlocks.FRIGID_PLANKS, ModBlocks.FRIGID_PLANKS_FAMILY);

                createWoodTypeRecipes(ModTags.Items.ARBOREAL_CACTUS_STEMS, ModItems.ARBOREAL_CACTUS_BOAT, ModItems.ARBOREAL_CACTUS_CHEST_BOAT, ModBlocks.ARBOREAL_CACTUS_PLANKS, ModBlocks.ARBOREAL_CACTUS_PLANKS_FAMILY);
            }

            private void createWoodTypeRecipes(TagKey<Item> logs, ItemLike boat, ItemLike chestBoat, ModBlocks.WoodSetBlocks woodSetBlocks, BlockFamily family) {
                woodFromLogs(woodSetBlocks.wood(), woodSetBlocks.log());
                woodFromLogs(woodSetBlocks.strippedWood(), woodSetBlocks.strippedLog());
                planksFromLogs(woodSetBlocks.planks(), logs, 4);
                generateRecipes(family, FeatureFlagSet.of(FeatureFlags.VANILLA));
                hangingSign(woodSetBlocks.hangingSign(), woodSetBlocks.strippedLog());
                woodenBoat(boat, woodSetBlocks.planks());
                chestBoat(chestBoat, boat);
            }

            public void smithingTrim(ItemLike item, ResourceKey<TrimPattern> resourceKey) {
                SmithingTrimRecipeBuilder.smithingTrim(
                        Ingredient.of(item),
                        this.tag(ItemTags.TRIMMABLE_ARMOR),
                        this.tag(ItemTags.TRIM_MATERIALS),
                        this.registries.lookupOrThrow(Registries.TRIM_PATTERN).getOrThrow(resourceKey),
                        RecipeCategory.MISC)
                        .unlocks("has_smithing_trim_template", this.has(item))
                        .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, getItemName(item) + "_smithing_trim")));
            }

            public void sewingTrim(ItemLike item, ResourceKey<GliderDesign> resourceKey) {
                SewingDesignRecipeBuilder.sewingDesign(
                        Ingredient.of(item),
                        Ingredient.of(ModItems.GLIDER),
                        this.registries.lookupOrThrow(ModRegistryResourceKeys.GLIDER_DESIGN).getOrThrow(resourceKey),
                        RecipeCategory.MISC)
                        .unlocks("has_sewing_design_template", this.has(item))
                        .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, getItemName(item) + "_sewing_design")));
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "Recipes";
    }

}
