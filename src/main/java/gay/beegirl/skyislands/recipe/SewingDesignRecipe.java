package gay.beegirl.skyislands.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.skyislands.component.GliderDesign;
import gay.beegirl.skyislands.component.GliderPattern;
import gay.beegirl.skyislands.component.ModDataComponents;
import gay.beegirl.skyislands.item.ModItems;
import gay.beegirl.skyislands.registry.ModRegistries;
import gay.beegirl.skyislands.registry.glider_pattern.ModGliderPatternDesigns;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;

import java.util.Optional;
import java.util.stream.Stream;

public class SewingDesignRecipe implements SmithingRecipe {
	final Ingredient template;
	final Ingredient base;

	public SewingDesignRecipe(Ingredient template, Ingredient base) {
		this.template = template;
		this.base = base;
	}

	public boolean matches(SmithingRecipeInput input, Level level) {
		return this.template.test(input.template()) && this.base.test(input.base());
	}

	public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
		ItemStack itemstack = input.base();
		if (this.base.test(itemstack)) {
			Optional<Holder.Reference<GliderDesign>> designHolder = ModGliderPatternDesigns.getFromTemplate(registries, input.template());
			if (designHolder.isPresent()) {
				GliderPattern pattern = itemstack.get(ModDataComponents.PATTERN);
				if (pattern != null && pattern.hasDesign(designHolder.get())) {
					return ItemStack.EMPTY;
				}

				ItemStack itemstack1 = itemstack.copyWithCount(1);
				itemstack1.set(ModDataComponents.PATTERN, new GliderPattern(designHolder.get()));
				return itemstack1;
			}
		}

		return ItemStack.EMPTY;
	}

	public ItemStack getResultItem(HolderLookup.Provider registries) {
		ItemStack itemstack = new ItemStack(ModItems.GLIDER.get());
		Optional<Holder.Reference<GliderDesign>> designHolder = registries.lookupOrThrow(ModRegistries.GLIDER_DESIGN).listElements().findFirst();
		if (designHolder.isPresent()) {
			itemstack.set(ModDataComponents.PATTERN, new GliderPattern(designHolder.get()));
		}

		return itemstack;
	}

	public boolean isTemplateIngredient(ItemStack stack) {
		return this.template.test(stack);
	}

	public boolean isBaseIngredient(ItemStack stack) {
		return this.base.test(stack);
	}

	public boolean isAdditionIngredient(ItemStack stack) {
		return false;
	}

	public RecipeSerializer<?> getSerializer() {
		return ModRecipeSerializer.SEWING_DESIGN.get();
	}

	public boolean isIncomplete() {
		return Stream.of(this.template, this.base).anyMatch(Ingredient::hasNoItems);
	}

	public static class Serializer implements RecipeSerializer<SewingDesignRecipe> {
		private static final MapCodec<SewingDesignRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Ingredient.CODEC.fieldOf("template").forGetter((recipe) -> recipe.template), Ingredient.CODEC.fieldOf("base").forGetter((recipe) -> recipe.base)).apply(instance, SewingDesignRecipe::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, SewingDesignRecipe> STREAM_CODEC = StreamCodec.of(SewingDesignRecipe.Serializer::toNetwork, SewingDesignRecipe.Serializer::fromNetwork);

		public Serializer() {
		}

		public MapCodec<SewingDesignRecipe> codec() {
			return CODEC;
		}

		public StreamCodec<RegistryFriendlyByteBuf, SewingDesignRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static SewingDesignRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			Ingredient template = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			Ingredient base = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			return new SewingDesignRecipe(template, base);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, SewingDesignRecipe recipe) {
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
		}
	}
}
