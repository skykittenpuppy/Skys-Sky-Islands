package gay.beegirl.skyislands.world.item.armortrim;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.armortrim.TrimPatterns;

public class ModTrimPatterns {
    public static final ResourceKey<TrimPattern> TESTING = registryKey("testing");

    public static void bootstrap(BootstrapContext<TrimPattern> context) {
        SkysSkyIslands.LOGGER.info("Trim Pattern Bootstrap for " + SkysSkyIslands.MOD_ID);

        TrimPatterns.register(context, ModItems.TESTING_ARMOR_TRIM_SMITHING_TEMPLATE.get(), TESTING);
    }

    private static ResourceKey<TrimPattern> registryKey(String name) {
        return ResourceKey.create(Registries.TRIM_PATTERN, SkysSkyIslands.createId(name));
    }
}
