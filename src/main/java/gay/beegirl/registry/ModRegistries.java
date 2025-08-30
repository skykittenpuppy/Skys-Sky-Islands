package gay.beegirl.registry;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.component.GliderDesign;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;

public class ModRegistries {
    public static void registerRegistries() {
        SkysSkyIslands.LOGGER.info("Registering Registries for " + SkysSkyIslands.MOD_ID);

        DynamicRegistries.registerSynced(ModRegistryResourceKeys.GLIDER_DESIGN, GliderDesign.DIRECT_CODEC);
    }
}
