package gay.beegirl.skyislands.registry.armor_trim;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.armortrim.TrimPattern;

public class ModTrimPatterns {
    public static final ResourceKey<TrimPattern> TESTING = registerKey("testing");

    public static void registerTrimPatterns() {
        SkysSkyIslands.LOGGER.info("Registering Trim Patterns for " + SkysSkyIslands.MOD_ID);
    }

    public static void bootstrap(BootstrapContext<TrimPattern> bootstrapContext) {
        SkysSkyIslands.LOGGER.info("Trim Pattern Bootstrap for " + SkysSkyIslands.MOD_ID);
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
