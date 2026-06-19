package gay.beegirl.skyislands.core.component;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.gliderthing.GliderThing;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, SkysSkyIslands.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<GliderThing>> SEWING_PATTERN = DATA_COMPONENTS.registerComponentType("sewing_pattern", builder -> builder.persistent(GliderThing.CODEC).networkSynchronized(GliderThing.STREAM_CODEC));

    public static void registerDataComponents(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Data Components for " + SkysSkyIslands.MOD_ID);

        DATA_COMPONENTS.register(modEventBus);
    }
}
