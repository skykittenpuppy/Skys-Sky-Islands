package gay.beegirl.registry;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.component.GliderDesign;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class ModRegistryResourceKeys {
    private static <T> ResourceKey<Registry<T>> registerRegistryKey(String name) {
        return ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
    }

    public static void registerRegistryResourceKeys() {
        SkysSkyIslands.LOGGER.info("Registering Registry Resource Keys for " + SkysSkyIslands.MOD_ID);
    }

    public static final ResourceKey<Registry<GliderDesign>> GLIDER_DESIGN = registerRegistryKey("glider_design");
}