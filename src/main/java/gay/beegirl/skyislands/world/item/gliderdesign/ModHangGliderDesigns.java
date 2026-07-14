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

public class ModHangGliderDesigns {
    public static final ResourceKey<HangGliderDesign> TESTING = registryKey("testing");

    public static void bootstrap(BootstrapContext<HangGliderDesign> context) {
        SkysSkyIslands.LOGGER.info("Hang Glider Design Bootstrap for " + SkysSkyIslands.MOD_ID);

        register(context, ModItems.TESTING_HANG_GLIDER_DESIGN_SEWING_TEMPLATE.get(), TESTING);
    }

    private static ResourceKey<HangGliderDesign> registryKey(String name) {
        return ResourceKey.create(ModRegistries.HANG_GLIDER_DESIGN, SkysSkyIslands.createId(name));
    }

    public static Optional<Holder.Reference<HangGliderDesign>> getFromTemplate(HolderLookup.Provider registries, ItemStack template) {
        return registries.lookupOrThrow(ModRegistries.HANG_GLIDER_DESIGN).listElements().filter((reference) -> template.is(reference.value().templateItem)).findFirst();
    }

    public static void register(BootstrapContext<HangGliderDesign> context, Item templateItem, ResourceKey<HangGliderDesign> designKey) {
        HangGliderDesign design = new HangGliderDesign(
                BuiltInRegistries.ITEM.wrapAsHolder(templateItem),
                designKey.location(),
                Component.translatable(Util.makeDescriptionId("hang_glider_design", designKey.location()))
        );
        context.register(designKey, design);
    }
}
