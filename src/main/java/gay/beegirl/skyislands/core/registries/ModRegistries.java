package gay.beegirl.skyislands.core.registries;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.gliderdesign.HangGliderDesign;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class ModRegistries {
    public static final ResourceKey<Registry<HangGliderDesign>> HANG_GLIDER_DESIGN = ResourceKey.createRegistryKey(SkysSkyIslands.createId("hang_glider_design"));
}
