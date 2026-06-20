package gay.beegirl.skyislands.world.item.gliderthing;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.core.registries.ModRegistries;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class ModThingPatterns {
    public static final ResourceKey<ThingPattern> TESTING = registryKey("testing");
    public static final ResourceKey<ThingPattern> TESTING2 = registryKey("testing2");

    public static void bootstrap(BootstrapContext<ThingPattern> context) {
        SkysSkyIslands.LOGGER.info("Trim Pattern Bootstrap for " + SkysSkyIslands.MOD_ID);

        register(context, TESTING, ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE.get());
        register(context, TESTING2, ModItems.TESTING2_GLIDER_PATTERN_SEWING_TEMPLATE.get());
    }

    private static ResourceKey<ThingPattern> registryKey(String name) {
        return ResourceKey.create(ModRegistries.GLIDER_DESIGN, SkysSkyIslands.createId(name));
    }

    public static Optional<Holder.Reference<ThingPattern>> getFromTemplate(HolderLookup.Provider registries, ItemStack template) {
        return registries.lookupOrThrow(ModRegistries.GLIDER_DESIGN).listElements().filter((reference) -> template.is((reference.value()).templateItem())).findFirst();
    }

    public static void register(BootstrapContext<ThingPattern> context, ResourceKey<ThingPattern> gliderDesignKey, Item templateItem) {
        ThingPattern gliderDesign = new ThingPattern(
                gliderDesignKey.location(),
                BuiltInRegistries.ITEM.wrapAsHolder(templateItem),
                Component.translatable(Util.makeDescriptionId("glider_design", gliderDesignKey.location()))
        );
        context.register(gliderDesignKey, gliderDesign);
    }
}
