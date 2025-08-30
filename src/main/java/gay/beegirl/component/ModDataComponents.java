package gay.beegirl.component;

import gay.beegirl.SkysSkyIslands;
import net.fabricmc.fabric.api.item.v1.ComponentTooltipAppenderRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

public class ModDataComponents {
    private static <T> DataComponentType<T> registerDataComponent(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE,
                ResourceKey.create(Registries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name)),
                builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void registerDataComponents() {
        SkysSkyIslands.LOGGER.info("Registering Data Components for " + SkysSkyIslands.MOD_ID);
        ComponentTooltipAppenderRegistry.addAfter(DataComponents.TRIM, GLIDER_PATTERN);
    }
    public static final DataComponentType<Unit> HANG_GLIDER = registerDataComponent("hang_glider", unitBuilder -> unitBuilder.persistent(Unit.CODEC).networkSynchronized(Unit.STREAM_CODEC));
    public static final DataComponentType<GliderPattern> GLIDER_PATTERN = registerDataComponent("glider_pattern", unitBuilder -> unitBuilder.persistent(GliderPattern.CODEC).networkSynchronized(GliderPattern.STREAM_CODEC).cacheEncoding());
}