package gay.beegirl.registry.glider_pattern;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.component.GliderDesign;
import gay.beegirl.registry.ModRegistryResourceKeys;
import net.minecraft.Util;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class ModGliderDesigns {
    public static final ResourceKey<GliderDesign> TESTING = registerKey("testing");

    public static void registerGliderDesigns() {
        SkysSkyIslands.LOGGER.info("Registering Glider Designs for " + SkysSkyIslands.MOD_ID);
    }

    public static void bootstrap(BootstrapContext<GliderDesign> bootstrapContext) {
        SkysSkyIslands.LOGGER.info("Glider Design Bootstrap for " + SkysSkyIslands.MOD_ID);

        register(bootstrapContext, TESTING);
    }

    public static ResourceKey<GliderDesign> registerKey(String name) {
        return ResourceKey.create(ModRegistryResourceKeys.GLIDER_DESIGN, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
    }
    public static void register(BootstrapContext<GliderDesign> bootstrapContext, ResourceKey<GliderDesign> resourceKey) {
        GliderDesign gliderDesign = new GliderDesign(defaultAssetId(resourceKey), Component.translatable(Util.makeDescriptionId("glider_design", resourceKey.location())));
        bootstrapContext.register(resourceKey, gliderDesign);
    }

    public static ResourceLocation defaultAssetId(ResourceKey<GliderDesign> resourceKey) {
        return resourceKey.location();
    }
}