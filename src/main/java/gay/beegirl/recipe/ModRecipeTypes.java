package gay.beegirl.recipe;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {
    public static <S extends RecipeType<T>, T extends Recipe<?>> S registerRecipeType(String name, S recipeType) {
        return Registry.register(BuiltInRegistries.RECIPE_TYPE, ResourceKey.create(Registries.RECIPE_TYPE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name)), recipeType);
    }

    public static void registerRecipeTypes() {
        SkysSkyIslands.LOGGER.info("Registering Recipe Types for " + SkysSkyIslands.MOD_ID);
    }

    public static final RecipeType<SewingDesignRecipe> SEWING_DESIGN_RECIPE_TYPE = registerRecipeType("sewing_design", new RecipeType<SewingDesignRecipe>() {
        @Override
        public int hashCode() {
            return super.hashCode();
        }
    });
}
