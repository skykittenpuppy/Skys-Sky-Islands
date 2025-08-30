package gay.beegirl.recipe;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ModRecipeSerializers {
    public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerRecipeSerializer(String name, S recipeSerializer) {
        return Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ResourceKey.create(Registries.RECIPE_SERIALIZER, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name)), recipeSerializer);
    }
    public static void registerRecipeSerializers() {
        SkysSkyIslands.LOGGER.info("Registering Recipe Serializers for " + SkysSkyIslands.MOD_ID);
    }

    public static final RecipeSerializer<SewingDesignRecipe> SEWING_DESIGN_RECIPE_SERIALIZER = registerRecipeSerializer("sewing_design", new SewingDesignRecipe.Serializer());
}
