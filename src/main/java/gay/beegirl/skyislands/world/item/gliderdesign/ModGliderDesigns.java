package gay.beegirl.skyislands.world.item.gliderdesign;

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

public class ModGliderDesigns {
    public static final ResourceKey<GliderDesign> TESTING = registryKey("testing");

    public static void bootstrap(BootstrapContext<GliderDesign> context) {
        SkysSkyIslands.LOGGER.info("Trim Pattern Bootstrap for " + SkysSkyIslands.MOD_ID);

        register(context, ModItems.TESTING_GLIDER_DESIGN_SEWING_TEMPLATE.get(), TESTING);
    }

    private static ResourceKey<GliderDesign> registryKey(String name) {
        return ResourceKey.create(ModRegistries.GLIDER_DESIGN, SkysSkyIslands.createId(name));
    }

    public static Optional<Holder.Reference<GliderDesign>> getFromTemplate(HolderLookup.Provider registries, ItemStack template) {
        return registries.lookupOrThrow(ModRegistries.GLIDER_DESIGN).listElements().filter((reference) -> template.is(reference.value().templateItem)).findFirst();
    }

    public static void register(BootstrapContext<GliderDesign> context, Item templateItem, ResourceKey<GliderDesign> gliderDesignKey) {
        GliderDesign gliderDesign = new GliderDesign(
                BuiltInRegistries.ITEM.wrapAsHolder(templateItem),
                gliderDesignKey.location(),
                Component.translatable(Util.makeDescriptionId("glider_design", gliderDesignKey.location()))
        );
        context.register(gliderDesignKey, gliderDesign);
    }
}
