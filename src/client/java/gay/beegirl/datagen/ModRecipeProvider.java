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
                List<ItemLike> ZONAITE_SMELTING = List.of(
                        ModItem.RAW_ZONAITE,
                        ModBlock.STONE_ZONAITE_ORE,
                        ModBlock.DEEPSLATE_ZONAITE_ORE,
                        ModBlock.SKYSTONE_ZONAITE_ORE
                );
                oreSmelting(ZONAITE_SMELTING, RecipeCategory.MISC, ModItem.REFINED_ZONAITE, 0.25f, 200, "zonaite");
                oreBlasting(ZONAITE_SMELTING, RecipeCategory.MISC, ModItem.REFINED_ZONAITE, 0.25f, 100, "zonaite");

                shaped(RecipeCategory.MISC, ModBlock.RAW_ZONAITE_BLOCK)
                        .pattern("zzz")
                        .pattern("zzz")
                        .pattern("zzz")
                        .define('z', ModItem.RAW_ZONAITE)
                        .unlockedBy(getHasName(ModItem.RAW_ZONAITE), has(ModItem.RAW_ZONAITE))
                        .save(recipeOutput);
                shaped(RecipeCategory.MISC, ModBlock.REFINED_ZONAITE_BLOCK)
                        .pattern("zzz")
                        .pattern("zzz")
                        .pattern("zzz")
                        .define('z', ModItem.REFINED_ZONAITE)
                        .unlockedBy(getHasName(ModItem.REFINED_ZONAITE), has(ModItem.REFINED_ZONAITE))
                        .save(recipeOutput);

                shapeless(RecipeCategory.MISC, ModItem.RAW_ZONAITE, 9)
                        .requires(ModBlock.RAW_ZONAITE_BLOCK)
                        .unlockedBy(getHasName(ModBlock.RAW_ZONAITE_BLOCK), has(ModBlock.RAW_ZONAITE_BLOCK))
                        .save(recipeOutput);
                shapeless(RecipeCategory.MISC, ModItem.REFINED_ZONAITE, 9)
                        .requires(ModBlock.REFINED_ZONAITE_BLOCK)
                        .unlockedBy(getHasName(ModBlock.REFINED_ZONAITE_BLOCK), has(ModBlock.REFINED_ZONAITE_BLOCK))
                        .save(recipeOutput);
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "Recipes";
    }
}
