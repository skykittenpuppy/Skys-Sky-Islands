package gay.beegirl.skyislands.registry;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.component.GliderDesign;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public class ModRegistries {
    public static void registerRegistries() {
        SkysSkyIslands.LOGGER.info("Registering Registries for " + SkysSkyIslands.MOD_ID);

        DynamicRegistries.registerSynced(ModRegistryResourceKeys.GLIDER_DESIGN, GliderDesign.DIRECT_CODEC);
    }
}
