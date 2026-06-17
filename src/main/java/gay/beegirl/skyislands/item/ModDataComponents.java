package gay.beegirl.skyislands.item;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, SkysSkyIslands.MOD_ID);
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ResourceLocation>> SEWING_PATTERN = DATA_COMPONENTS.registerComponentType("sewing_pattern", builder -> builder.persistent(ResourceLocation.CODEC).networkSynchronized(ResourceLocation.STREAM_CODEC));

    public static void registerDataComponents(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Data Components for " + SkysSkyIslands.MOD_ID);

        DATA_COMPONENTS.register(modEventBus);
    }
}
