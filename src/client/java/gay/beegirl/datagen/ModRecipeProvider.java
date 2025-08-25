package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
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
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "Recipes";
    }
}
