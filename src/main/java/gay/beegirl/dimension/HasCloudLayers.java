package gay.beegirl.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.dimension.DimensionType;

import java.util.List;
import java.util.Optional;

public interface HasCloudLayers {
    Optional<List<CloudLayer>> getCloudLayers();

    record CloudLayer(int height, Optional<Float> xOffset, Optional<Float> yOffset) {
        public static final MapCodec<CloudLayer> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Codec.INT.fieldOf("height").forGetter(CloudLayer::height),
                Codec.floatRange(DimensionType.MIN_Y, DimensionType.MAX_Y).optionalFieldOf("x_offset").forGetter(CloudLayer::xOffset),
                Codec.floatRange(DimensionType.MIN_Y, DimensionType.MAX_Y).optionalFieldOf("y_offset").forGetter(CloudLayer::yOffset)
        ).apply(instance, CloudLayer::new));
    }
}
