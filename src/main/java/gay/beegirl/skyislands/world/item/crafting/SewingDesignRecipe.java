package gay.beegirl.skyislands.world.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.skyislands.world.item.gliderdesign.GliderDesign;
import gay.beegirl.skyislands.world.item.gliderdesign.ModGliderDesigns;
import gay.beegirl.skyislands.core.component.ModDataComponents;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.core.registries.ModRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public class SewingDesignRecipe implements SmithingRecipe {
	final Ingredient template;
	final Ingredient base;
	final Ingredient addition;

	public SewingDesignRecipe(Ingredient template, Ingredient base, Ingredient addition) {
		this.template = template;
		this.base = base;
		this.addition = addition;
	}

	public boolean matches(SmithingRecipeInput input, @NotNull Level level) {
		return this.template.test(input.template()) && this.base.test(input.base()) && this.addition.test(input.addition());
	}

	public @NotNull ItemStack assemble(SmithingRecipeInput input, HolderLookup.@NotNull Provider registries) {
		ItemStack inputStack = input.base();
		if (this.base.test(inputStack)) {
			Optional<Holder.Reference<GliderDesign>> designHolder = ModGliderDesigns.getFromTemplate(registries, input.template());
			if (designHolder.isPresent()) {
				GliderDesign pattern = inputStack.get(ModDataComponents.SEWING_PATTERN.get());
				if (pattern != null && pattern.equals(designHolder.get().value())) {
					return ItemStack.EMPTY;
				}

				ItemStack outputStack = inputStack.copyWithCount(1);
				outputStack.set(ModDataComponents.SEWING_PATTERN.get(), new GliderDesign(designHolder.get().value()));
				return outputStack;
			}
		}

		return ItemStack.EMPTY;
	}

	public @NotNull ItemStack getResultItem(HolderLookup.Provider registries) {
		ItemStack itemstack = new ItemStack(ModItems.GLIDER.get());
		Optional<Holder.Reference<GliderDesign>> designHolder = registries.lookupOrThrow(ModRegistries.GLIDER_DESIGN).listElements().findFirst();
		designHolder.ifPresent(
				gliderDesignReference -> itemstack.set(ModDataComponents.SEWING_PATTERN.get(), new GliderDesign(gliderDesignReference.value())));
		return itemstack;
	}

	public boolean isTemplateIngredient(@NotNull ItemStack stack) {
		return this.template.test(stack);
	}

	public boolean isBaseIngredient(@NotNull ItemStack stack) {
		return this.base.test(stack);
	}

	public boolean isAdditionIngredient(@NotNull ItemStack stack) {
		return this.addition.test(stack);
	}

	public @NotNull RecipeSerializer<?> getSerializer() {
		return ModRecipeSerializer.SEWING_DESIGN.get();
	}

	public boolean isIncomplete() {
		return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::hasNoItems);
	}

	public static class Serializer implements RecipeSerializer<SewingDesignRecipe> {
		private static final MapCodec<SewingDesignRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
				Ingredient.CODEC.fieldOf("template").forGetter((recipe) -> recipe.template),
				Ingredient.CODEC.fieldOf("base").forGetter((recipe) -> recipe.base),
				Ingredient.CODEC.fieldOf("addition").forGetter((recipe) -> recipe.addition)).apply(instance, SewingDesignRecipe::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, SewingDesignRecipe> STREAM_CODEC = StreamCodec.of(SewingDesignRecipe.Serializer::toNetwork, SewingDesignRecipe.Serializer::fromNetwork);

		public Serializer() {
		}

		public @NotNull MapCodec<SewingDesignRecipe> codec() {
			return CODEC;
		}

		public @NotNull StreamCodec<RegistryFriendlyByteBuf, SewingDesignRecipe> streamCodec() {
			return STREAM_CODEC;
		}

		private static SewingDesignRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
			Ingredient template = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			Ingredient base = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			Ingredient addition = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
			return new SewingDesignRecipe(template, base, addition);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, SewingDesignRecipe recipe) {
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.template);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.base);
			Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.addition);
		}
	}
}
