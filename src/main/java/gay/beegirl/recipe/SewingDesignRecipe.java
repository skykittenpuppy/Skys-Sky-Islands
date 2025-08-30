package gay.beegirl.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.component.GliderDesign;
import gay.beegirl.component.GliderPattern;
import gay.beegirl.component.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SewingDesignRecipe implements SmithingRecipe {
    final Ingredient template;
    final Ingredient base;
    final Holder<GliderDesign> pattern;
    @Nullable
    private PlacementInfo placementInfo;

    public SewingDesignRecipe(Ingredient ingredient, Ingredient ingredient2, Holder<GliderDesign> holder) {
        this.template = ingredient;
        this.base = ingredient2;
        this.pattern = holder;
    }

    public ItemStack assemble(SmithingRecipeInput smithingRecipeInput, HolderLookup.Provider provider) {
        return applyDesign(provider, smithingRecipeInput.base(), this.pattern);
    }

    public static ItemStack applyDesign(HolderLookup.Provider provider, ItemStack itemStack, Holder<GliderDesign> holder) {
            GliderPattern gliderDesign = itemStack.get(ModDataComponents.GLIDER_PATTERN);
            GliderPattern gliderDesign2 = new GliderPattern(holder);
            if (Objects.equals(gliderDesign, gliderDesign2)) {
                return ItemStack.EMPTY;
            } else {
                ItemStack itemStack3 = itemStack.copyWithCount(1);
                itemStack3.set(ModDataComponents.GLIDER_PATTERN, gliderDesign2);
                return itemStack3;
            }
    }

    public @NotNull Optional<Ingredient> templateIngredient() {
        return Optional.of(this.template);
    }

    public @NotNull Ingredient baseIngredient() {
        return this.base;
    }

    public @NotNull Optional<Ingredient> additionIngredient() {
        return Optional.empty();
    }

    public @NotNull RecipeSerializer<SewingDesignRecipe> getSerializer() {
        return ModRecipeSerializers.SEWING_DESIGN_RECIPE_SERIALIZER;
    }

    public @NotNull PlacementInfo placementInfo() {
        if (this.placementInfo == null) {
            this.placementInfo = PlacementInfo.create(List.of(this.template, this.base));
        }

        return this.placementInfo;
    }

    public @NotNull List<RecipeDisplay> display() {
        return List.of();
        //SlotDisplay slotDisplay = this.base.display();
        //SlotDisplay slotDisplay3 = this.template.display();
        //return List.of(new SmithingRecipeDisplay(slotDisplay3, slotDisplay, new SlotDisplay.SewingdesignDemoSlotDisplay(slotDisplay, slotDisplay2, this.pattern), new SlotDisplay.ItemSlotDisplay(Items.SMITHING_TABLE)));
    }

    public static class Serializer implements RecipeSerializer<SewingDesignRecipe> {
        private static final MapCodec<SewingDesignRecipe> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Ingredient.CODEC.fieldOf("template").forGetter((sewingdesignRecipe) -> sewingdesignRecipe.template),
                Ingredient.CODEC.fieldOf("base").forGetter((sewingdesignRecipe) -> sewingdesignRecipe.base),
                GliderDesign.CODEC.fieldOf("pattern").forGetter((sewingdesignRecipe) -> sewingdesignRecipe.pattern)).apply(instance, SewingDesignRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, SewingDesignRecipe> STREAM_CODEC;

        public @NotNull MapCodec<SewingDesignRecipe> codec() {
            return CODEC;
        }

        public @NotNull StreamCodec<RegistryFriendlyByteBuf, SewingDesignRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        static {
            STREAM_CODEC = StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC, (sewingdesignRecipe) -> sewingdesignRecipe.template,
                    Ingredient.CONTENTS_STREAM_CODEC, (sewingdesignRecipe) -> sewingdesignRecipe.base,
                    GliderDesign.STREAM_CODEC, (sewingdesignRecipe) -> sewingdesignRecipe.pattern, SewingDesignRecipe::new);
        }
    }
}
