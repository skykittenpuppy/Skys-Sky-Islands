package gay.beegirl.skyislands.data.recipes;

import gay.beegirl.skyislands.world.item.crafting.SewingDesignRecipe;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SewingDesignRecipeBuilder {
	private final RecipeCategory category;
	private final Ingredient template;
	private final Ingredient base;
	private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

	public SewingDesignRecipeBuilder(RecipeCategory category, Ingredient template, Ingredient base) {
		this.category = category;
		this.template = template;
		this.base = base;
	}

	public static SewingDesignRecipeBuilder sewingDesign(Ingredient template, Ingredient base, RecipeCategory category) {
		return new SewingDesignRecipeBuilder(category, template, base);
	}

	public SewingDesignRecipeBuilder unlocks(String key, Criterion<?> criterion) {
		this.criteria.put(key, criterion);
		return this;
	}

	public void save(RecipeOutput recipeOutput, ResourceLocation recipeId) {
		this.ensureValid(recipeId);
		Advancement.Builder advancement$builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipeId)).rewards(AdvancementRewards.Builder.recipe(recipeId)).requirements(AdvancementRequirements.Strategy.OR);
		Objects.requireNonNull(advancement$builder);
		this.criteria.forEach(advancement$builder::addCriterion);
		SewingDesignRecipe sewingDesignRecipe = new SewingDesignRecipe(this.template, this.base);
		recipeOutput.accept(recipeId, sewingDesignRecipe, advancement$builder.build(recipeId.withPrefix("recipes/" + this.category.getFolderName() + "/")));
	}

	private void ensureValid(ResourceLocation location) {
		if (this.criteria.isEmpty()) {
			throw new IllegalStateException("No way of obtaining recipe " + location);
		}
	}
}
