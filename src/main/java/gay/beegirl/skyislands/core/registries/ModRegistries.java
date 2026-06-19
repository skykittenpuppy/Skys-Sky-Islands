package gay.beegirl.skyislands.core.registries;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.gliderthing.ThingPattern;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ModRegistries {
    public static final ResourceKey<Registry<ThingPattern>> GLIDER_DESIGN = ResourceKey.createRegistryKey(SkysSkyIslands.createId("glider_design"));
}
