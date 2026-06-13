package gay.beegirl.skyislands.registry.armor_trim;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;

public class ModArmorTrimMaterials {
    public static final ResourceKey<TrimMaterial> ALEXANDRITE = registryKey("alexandrite");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        SkysSkyIslands.LOGGER.info("Trim Material Bootstrap for " + SkysSkyIslands.MOD_ID);

        TrimMaterials.register(context, ALEXANDRITE, ModItems.ALEXANDRITE.get(), Style.EMPTY.withColor(0), 0.13F);
        //TrimMaterials.register(context, IRON, Items.IRON_INGOT, Style.EMPTY.withColor(15527148), 0.2F, Map.of(ArmorMaterials.IRON, "iron_darker"));
    }

    private static ResourceKey<TrimMaterial> registryKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, SkysSkyIslands.createId(name));
    }
}
