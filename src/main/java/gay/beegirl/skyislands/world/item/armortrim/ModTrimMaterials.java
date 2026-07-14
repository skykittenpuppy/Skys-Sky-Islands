package gay.beegirl.skyislands.world.item.armortrim;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;

public class ModTrimMaterials {
    public static final ResourceKey<TrimMaterial> ZEPHYRUM = registryKey("zephyrum");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        SkysSkyIslands.LOGGER.info("Trim Material Bootstrap for " + SkysSkyIslands.MOD_ID);

        TrimMaterials.register(context, ZEPHYRUM, ModItems.ZEPHYRUM.get(), Style.EMPTY.withColor(0), 0.713F);
    }

    private static ResourceKey<TrimMaterial> registryKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, SkysSkyIslands.createId(name));
    }
}
