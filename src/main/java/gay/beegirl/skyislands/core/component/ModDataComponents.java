package gay.beegirl.skyislands.core.component;

import com.mojang.serialization.Codec;
import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.gliderdesign.HangGliderDesign;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, SkysSkyIslands.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<HangGliderDesign>> SEWING_PATTERN = DATA_COMPONENTS.registerComponentType("sewing_pattern", builder -> builder
            .persistent(HangGliderDesign.CODEC)
            .networkSynchronized(HangGliderDesign.STREAM_CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Unit>> HANG_GLIDER = DATA_COMPONENTS.registerComponentType("hang_glider", builder -> builder
            .persistent(Codec.unit(Unit.INSTANCE))
            .networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    public static void registerDataComponents(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Data Components for " + SkysSkyIslands.MOD_ID);

        DATA_COMPONENTS.register(modEventBus);
    }
}
