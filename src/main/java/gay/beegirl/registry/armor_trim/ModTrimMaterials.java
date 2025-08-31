package gay.beegirl.registry.armor_trim;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ProvidesTrimMaterial;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

import java.util.Optional;

public class ModTrimMaterials {
    public static final MaterialAssetGroup ALEXANDRITE_GROUP = MaterialAssetGroup.create("alexandrite");
    public static final ResourceKey<TrimMaterial> ALEXANDRITE = registerKey("alexandrite");

    public static void registerTrimMaterials() {
        SkysSkyIslands.LOGGER.info("Registering Trim Materials for " + SkysSkyIslands.MOD_ID);
    }

    public static void bootstrap(BootstrapContext<TrimMaterial> bootstrapContext) {
        SkysSkyIslands.LOGGER.info("Trim Material Bootstrap for " + SkysSkyIslands.MOD_ID);
        register(bootstrapContext, ALEXANDRITE, Style.EMPTY.withColor(0), ALEXANDRITE_GROUP); //TODO: Alexandrite Colour
    }

    public static ResourceKey<TrimMaterial> registerKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
    }
    private static void register(BootstrapContext<TrimMaterial> bootstrapContext, ResourceKey<TrimMaterial> resourceKey, Style style, MaterialAssetGroup materialAssetGroup) {
        Component component = Component.translatable(Util.makeDescriptionId("trim_material", resourceKey.location())).withStyle(style);
        bootstrapContext.register(resourceKey, new TrimMaterial(materialAssetGroup, component));
    }

    public static Optional<Holder<TrimMaterial>> getFromIngredient(HolderLookup.Provider provider, ItemStack itemStack) {
        ProvidesTrimMaterial providesTrimMaterial = itemStack.get(DataComponents.PROVIDES_TRIM_MATERIAL);
        return providesTrimMaterial != null ? providesTrimMaterial.unwrap(provider) : Optional.empty();
    }
}
