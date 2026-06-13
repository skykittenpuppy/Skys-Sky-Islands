package gay.beegirl.skyislands.component;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    public static final DataComponentType<GliderPattern> PATTERN = register("pattern", (p_341838_) ->
            p_341838_.persistent(GliderPattern.CODEC).networkSynchronized(GliderPattern.STREAM_CODEC).cacheEncoding());

    private static <T> DataComponentType<T> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, name, (builder.apply(DataComponentType.builder())).build());
    }
}