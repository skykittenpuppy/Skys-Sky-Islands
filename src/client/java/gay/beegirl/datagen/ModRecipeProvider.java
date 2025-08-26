package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import gay.beegirl.util.ModTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
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
                        ModItem.RAW_ALEXANDRITE,
                        ModBlock.STONE_ALEXANDRITE_ORE,
                        ModBlock.DEEPSLATE_ALEXANDRITE_ORE,
                        ModBlock.CLOUDSHALE_ALEXANDRITE_ORE
                );
                oreSmelting(ALEXANDRITE_SMELTING, RecipeCategory.MISC, ModItem.ALEXANDRITE, 0.25f, 200, "alexandrite");
                oreBlasting(ALEXANDRITE_SMELTING, RecipeCategory.MISC, ModItem.ALEXANDRITE, 0.25f, 100, "alexandrite");

                shaped(RecipeCategory.MISC, ModBlock.RAW_ALEXANDRITE_BLOCK)
                        .pattern("zzz")
                        .pattern("zzz")
                        .pattern("zzz")
                        .define('z', ModItem.RAW_ALEXANDRITE)
                        .unlockedBy(getHasName(ModItem.RAW_ALEXANDRITE), has(ModItem.RAW_ALEXANDRITE))
                        .save(recipeOutput);
                shaped(RecipeCategory.MISC, ModBlock.ALEXANDRITE_BLOCK)
                        .pattern("zzz")
                        .pattern("zzz")
                        .pattern("zzz")
                        .define('z', ModItem.ALEXANDRITE)
                        .unlockedBy(getHasName(ModItem.ALEXANDRITE), has(ModItem.ALEXANDRITE))
                        .save(recipeOutput);

                shapeless(RecipeCategory.MISC, ModItem.RAW_ALEXANDRITE, 9)
                        .requires(ModBlock.RAW_ALEXANDRITE_BLOCK)
                        .unlockedBy(getHasName(ModBlock.RAW_ALEXANDRITE_BLOCK), has(ModBlock.RAW_ALEXANDRITE_BLOCK))
                        .save(recipeOutput);
                shapeless(RecipeCategory.MISC, ModItem.ALEXANDRITE, 9)
                        .requires(ModBlock.ALEXANDRITE_BLOCK)
                        .unlockedBy(getHasName(ModBlock.ALEXANDRITE_BLOCK), has(ModBlock.ALEXANDRITE_BLOCK))
                        .save(recipeOutput);

                createWoodTypeRecipes(ModTag.Items.GOLDENLEAF_LOGS, ModBlock.GOLDENLEAF_LOG, ModBlock.GOLDENLEAF_WOOD, ModBlock.STRIPPED_GOLDENLEAF_LOG, ModBlock.STRIPPED_GOLDENLEAF_WOOD, ModBlock.GOLDENLEAF_PLANKS, ModBlock.GOLDENLEAF_STAIRS, ModBlock.GOLDENLEAF_SLAB, ModBlock.GOLDENLEAF_BUTTON, ModBlock.GOLDENLEAF_PRESSURE_PLATE, ModBlock.GOLDENLEAF_FENCE, ModBlock.GOLDENLEAF_FENCE_GATE, ModBlock.GOLDENLEAF_DOOR, ModBlock.GOLDENLEAF_TRAPDOOR, ModItem.GOLDENLEAF_BOAT, ModItem.GOLDENLEAF_CHEST_BOAT);

                createWoodTypeRecipes(ModTag.Items.SAKURA_LOGS, ModBlock.SAKURA_LOG, ModBlock.SAKURA_WOOD, ModBlock.STRIPPED_SAKURA_LOG, ModBlock.STRIPPED_SAKURA_WOOD, ModBlock.SAKURA_PLANKS, ModBlock.SAKURA_STAIRS, ModBlock.SAKURA_SLAB, ModBlock.SAKURA_BUTTON, ModBlock.SAKURA_PRESSURE_PLATE, ModBlock.SAKURA_FENCE, ModBlock.SAKURA_FENCE_GATE, ModBlock.SAKURA_DOOR, ModBlock.SAKURA_TRAPDOOR, ModItem.SAKURA_BOAT, ModItem.SAKURA_CHEST_BOAT);

                createWoodTypeRecipes(ModTag.Items.FRIGID_LOGS, ModBlock.FRIGID_LOG, ModBlock.FRIGID_WOOD, ModBlock.STRIPPED_FRIGID_LOG, ModBlock.STRIPPED_FRIGID_WOOD, ModBlock.FRIGID_PLANKS, ModBlock.FRIGID_STAIRS, ModBlock.FRIGID_SLAB, ModBlock.FRIGID_BUTTON, ModBlock.FRIGID_PRESSURE_PLATE, ModBlock.FRIGID_FENCE, ModBlock.FRIGID_FENCE_GATE, ModBlock.FRIGID_DOOR, ModBlock.FRIGID_TRAPDOOR, ModItem.FRIGID_BOAT, ModItem.FRIGID_CHEST_BOAT);

                createWoodTypeRecipes(ModTag.Items.ARBOREAL_CACTUS_STEMS, ModBlock.ARBOREAL_CACTUS_STEM, ModBlock.ARBOREAL_CACTUS_HYPHAE, ModBlock.STRIPPED_ARBOREAL_CACTUS_STEM, ModBlock.STRIPPED_ARBOREAL_CACTUS_HYPHAE, ModBlock.ARBOREAL_CACTUS_PLANKS, ModBlock.ARBOREAL_CACTUS_STAIRS, ModBlock.ARBOREAL_CACTUS_SLAB, ModBlock.ARBOREAL_CACTUS_BUTTON, ModBlock.ARBOREAL_CACTUS_PRESSURE_PLATE, ModBlock.ARBOREAL_CACTUS_FENCE, ModBlock.ARBOREAL_CACTUS_FENCE_GATE, ModBlock.ARBOREAL_CACTUS_DOOR, ModBlock.ARBOREAL_CACTUS_TRAPDOOR, ModItem.ARBOREAL_CACTUS_BOAT, ModItem.ARBOREAL_CACTUS_CHEST_BOAT);
            }

            private void createWoodTypeRecipes(TagKey<Item> logs, ItemLike log, ItemLike wood, ItemLike strippedLog, ItemLike strippedWood, ItemLike planks, ItemLike stairs, ItemLike slab, ItemLike button, ItemLike pressurePlate, ItemLike fence, ItemLike fenceGate, ItemLike door, ItemLike trapdoor, ItemLike boat, ItemLike chestBoat) {
                woodFromLogs(wood, log);
                woodFromLogs(strippedWood, strippedLog);
                planksFromLogs(planks, logs, 4);
                stairBuilder(stairs, Ingredient.of(planks))
                        .unlockedBy(getHasName(planks), has(planks))
                        .save(recipeOutput);
                slabBuilder(RecipeCategory.BUILDING_BLOCKS, slab, Ingredient.of(planks))
                        .unlockedBy(getHasName(planks), has(planks))
                        .save(recipeOutput);
                buttonBuilder(button, Ingredient.of(planks))
                        .unlockedBy(getHasName(planks), has(planks))
                        .save(recipeOutput);
                pressurePlateBuilder(RecipeCategory.REDSTONE, pressurePlate, Ingredient.of(planks))
                        .unlockedBy(getHasName(planks), has(planks))
                        .save(recipeOutput);
                fenceBuilder(fence, Ingredient.of(planks))
                        .unlockedBy(getHasName(planks), has(planks))
                        .save(recipeOutput);
                fenceGateBuilder(fenceGate, Ingredient.of(planks))
                        .unlockedBy(getHasName(planks), has(planks))
                        .save(recipeOutput);
                doorBuilder(door, Ingredient.of(planks))
                        .unlockedBy(getHasName(planks), has(planks))
                        .save(recipeOutput);
                trapdoorBuilder(trapdoor, Ingredient.of(planks))
                        .unlockedBy(getHasName(planks), has(planks))
                        .save(recipeOutput);
                woodenBoat(boat, planks);
                woodenBoat(chestBoat, boat);
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "Recipes";
    }

}
