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
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.item.equipment.trim.TrimPatterns;

import java.util.Optional;

public class ModTrimPatterns {
    public static final ResourceKey<TrimPattern> TESTING = registerKey("testing");

    public static void bootstrap(BootstrapContext<TrimPattern> bootstrapContext) {
        register(bootstrapContext, TESTING);
    }

    public static ResourceKey<TrimPattern> registerKey(String name) {
        return ResourceKey.create(Registries.TRIM_PATTERN, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
    }
    public static void register(BootstrapContext<TrimPattern> bootstrapContext, ResourceKey<TrimPattern> resourceKey) {
        TrimPattern trimPattern = new TrimPattern(defaultAssetId(resourceKey), Component.translatable(Util.makeDescriptionId("trim_pattern", resourceKey.location())), false);
        bootstrapContext.register(resourceKey, trimPattern);
    }

    public static ResourceLocation defaultAssetId(ResourceKey<TrimPattern> resourceKey) {
        return resourceKey.location();
    }
}
