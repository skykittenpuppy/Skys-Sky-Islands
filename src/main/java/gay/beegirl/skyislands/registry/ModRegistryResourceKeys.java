package gay.beegirl.skyislands.registry;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.component.GliderDesign;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ModRegistryResourceKeys {
    private static <T> ResourceKey<Registry<T>> registerRegistryKey(String name) {
        return ResourceKey.createRegistryKey(SkysSkyIslands.createId(name));
    }

    public static void registerRegistryResourceKeys() {
        SkysSkyIslands.LOGGER.info("Registering Registry Resource Keys for " + SkysSkyIslands.MOD_ID);
    }

    public static final ResourceKey<Registry<GliderDesign>> GLIDER_DESIGN = registerRegistryKey("glider_design");
}
