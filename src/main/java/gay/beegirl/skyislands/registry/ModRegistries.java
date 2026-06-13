package gay.beegirl.skyislands.registry;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.component.GliderDesign;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

public class ModRegistries {
    public static final ResourceKey<Registry<GliderDesign>> GLIDER_DESIGN = ResourceKey.createRegistryKey(SkysSkyIslands.createId("glider_design"));

    @SubscribeEvent
    public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(GLIDER_DESIGN, GliderDesign.DIRECT_CODEC, GliderDesign.DIRECT_CODEC);
    }
}
