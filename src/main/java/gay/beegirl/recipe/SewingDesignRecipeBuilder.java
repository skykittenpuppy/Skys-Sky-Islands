package gay.beegirl.recipe;

import gay.beegirl.component.GliderDesign;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.Holder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SewingDesignRecipeBuilder {
    private final RecipeCategory category;
    private final Ingredient template;
    private final Ingredient base;
    private final Holder<GliderDesign> design;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public SewingDesignRecipeBuilder(RecipeCategory recipeCategory, Ingredient ingredient, Ingredient ingredient2, Holder<GliderDesign> holder) {
        this.category = recipeCategory;
        this.template = ingredient;
        this.base = ingredient2;
        this.design = holder;
    }

    public static SewingDesignRecipeBuilder sewingDesign(Ingredient ingredient, Ingredient ingredient2, Holder<GliderDesign> holder, RecipeCategory recipeCategory) {
        return new SewingDesignRecipeBuilder(recipeCategory, ingredient, ingredient2, holder);
    }

    public SewingDesignRecipeBuilder unlocks(String string, Criterion<?> criterion) {
        this.criteria.put(string, criterion);
        return this;
    }

    public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey) {
        this.ensureValid(resourceKey);
        Advancement.Builder builder = recipeOutput.advancement().addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(resourceKey)).rewards(AdvancementRewards.Builder.recipe(resourceKey)).requirements(AdvancementRequirements.Strategy.OR);
        Objects.requireNonNull(builder);
        this.criteria.forEach(builder::addCriterion);
        SewingDesignRecipe sewingDesignRecipe = new SewingDesignRecipe(this.template, this.base, this.design);
        recipeOutput.accept(resourceKey, sewingDesignRecipe, builder.build(resourceKey.location().withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    private void ensureValid(ResourceKey<Recipe<?>> resourceKey) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + resourceKey.location());
        }
    }
}
