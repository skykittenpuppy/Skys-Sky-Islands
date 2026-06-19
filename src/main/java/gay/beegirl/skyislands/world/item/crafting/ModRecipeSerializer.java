package gay.beegirl.skyislands.world.item.crafting;


import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipeSerializer {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, SkysSkyIslands.MOD_ID);

	public static final DeferredHolder<RecipeSerializer<?>, SewingDesignRecipe.Serializer> SEWING_DESIGN = RECIPE_SERIALIZERS.register("sewing_design", SewingDesignRecipe.Serializer::new);

	public static void registerRecipeSerializers(IEventBus modEventBus) {
		SkysSkyIslands.LOGGER.info("Registering Recipe Serializers for " + SkysSkyIslands.MOD_ID);

		RECIPE_SERIALIZERS.register(modEventBus);
	}
}
