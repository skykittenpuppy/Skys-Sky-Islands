package gay.beegirl.datagen;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModItem;
import gay.beegirl.util.ModTag;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
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

                twoByTwoPacker(RecipeCategory.BUILDING_BLOCKS, ModBlock.CLOUDSHALE.base(), ModBlock.POINTED_CLOUDSHALE);
                generateRecipes(ModBlock.CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(ModBlock.COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(ModBlock.MOSSY_COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));
                generateRecipes(ModBlock.CHERRY_COBBLED_CLOUDSHALE_FAMILY, FeatureFlagSet.of(FeatureFlags.VANILLA));

                createWoodTypeRecipes(ModTag.Items.GOLDENLEAF_LOGS, ModItem.GOLDENLEAF_BOAT, ModItem.GOLDENLEAF_CHEST_BOAT, ModBlock.GOLDENLEAF_PLANKS, ModBlock.GOLDENLEAF_PLANKS_FAMILY);

                createWoodTypeRecipes(ModTag.Items.SAKURA_LOGS, ModItem.SAKURA_BOAT, ModItem.SAKURA_CHEST_BOAT, ModBlock.SAKURA_PLANKS, ModBlock.SAKURA_PLANKS_FAMILY);

                createWoodTypeRecipes(ModTag.Items.FRIGID_LOGS, ModItem.FRIGID_BOAT, ModItem.FRIGID_CHEST_BOAT, ModBlock.FRIGID_PLANKS, ModBlock.FRIGID_PLANKS_FAMILY);

                createWoodTypeRecipes(ModTag.Items.ARBOREAL_CACTUS_STEMS, ModItem.ARBOREAL_CACTUS_BOAT, ModItem.ARBOREAL_CACTUS_CHEST_BOAT, ModBlock.ARBOREAL_CACTUS_PLANKS, ModBlock.ARBOREAL_CACTUS_PLANKS_FAMILY);
            }

            private void createWoodTypeRecipes(TagKey<Item> logs, ItemLike boat, ItemLike chestBoat, ModBlock.WoodSetBlocks woodSetBlocks, BlockFamily family) {
                woodFromLogs(woodSetBlocks.wood(), woodSetBlocks.log());
                woodFromLogs(woodSetBlocks.strippedWood(), woodSetBlocks.strippedLog());
                planksFromLogs(woodSetBlocks.planks(), logs, 4);
                generateRecipes(family, FeatureFlagSet.of(FeatureFlags.VANILLA));
                hangingSign(woodSetBlocks.hangingSign(), woodSetBlocks.strippedLog());
                woodenBoat(boat, woodSetBlocks.planks());
                chestBoat(chestBoat, boat);
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "Recipes";
    }

}
