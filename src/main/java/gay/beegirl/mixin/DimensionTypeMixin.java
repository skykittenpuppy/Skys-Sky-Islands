package gay.beegirl.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.dimension.HasCloudLayers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Mixin(DimensionType.class)
public abstract class DimensionTypeMixin implements HasCloudLayers {
    @Mutable
    @Shadow
    public static @Final Codec<DimensionType> DIRECT_CODEC;
    @Unique
    private Optional<List<CloudLayer>> cloudLayers = Optional.empty();

    @Unique
    private DimensionType cloudLayerDimensionType(OptionalLong fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultraWarm, boolean natural, double coordinateScale,
                                                  boolean bedWorks, boolean respawnAnchorWorks, int minY, int height, int logicalHeight, TagKey<Block> infiniburn,
                                                  ResourceLocation effectsLocation, float ambientLight, Optional<Integer> cloudHeight, Optional<List<CloudLayer>> cloudLayers,
                                                  DimensionType.MonsterSettings monsterSettings) {
        this.cloudLayers = cloudLayers;
        return new DimensionType(fixedTime, hasSkyLight, hasCeiling, ultraWarm, natural, coordinateScale, bedWorks, respawnAnchorWorks, minY, height, logicalHeight, infiniburn, effectsLocation, ambientLight, cloudHeight, monsterSettings);
    }

    @Override
    public Optional<List<CloudLayer>> getCloudLayers() {
        return cloudLayers;
    }

    /*@Inject(method = "<clinit>", at = @At("TAIL"))
    private static void clinit(CallbackInfo ci) {
        DIRECT_CODEC = ExtraCodecs.catchDecoderException(RecordCodecBuilder.create((instance) -> instance.group(
                ExtraCodecs.asOptionalLong(Codec.LONG.lenientOptionalFieldOf("fixed_time")).forGetter(DimensionType::fixedTime),
                Codec.BOOL.fieldOf("has_skylight").forGetter(DimensionType::hasSkyLight),
                Codec.BOOL.fieldOf("has_ceiling").forGetter(DimensionType::hasCeiling),
                Codec.BOOL.fieldOf("ultrawarm").forGetter(DimensionType::ultraWarm),
                Codec.BOOL.fieldOf("natural").forGetter(DimensionType::natural),
                Codec.doubleRange((double)1.0E-5F, (double)3.0E7F).fieldOf("coordinate_scale").forGetter(DimensionType::coordinateScale),
                Codec.BOOL.fieldOf("bed_works").forGetter(DimensionType::bedWorks),
                Codec.BOOL.fieldOf("respawn_anchor_works").forGetter(DimensionType::respawnAnchorWorks),
                Codec.intRange(DimensionType.MIN_Y, DimensionType.MAX_Y).fieldOf("min_y").forGetter(DimensionType::minY),
                Codec.intRange(DimensionType.MIN_HEIGHT, DimensionType.Y_SIZE).fieldOf("height").forGetter(DimensionType::height),
                Codec.intRange(0, DimensionType.Y_SIZE).fieldOf("logical_height").forGetter(DimensionType::logicalHeight),
                TagKey.hashedCodec(Registries.BLOCK).fieldOf("infiniburn").forGetter(DimensionType::infiniburn),
                ResourceLocation.CODEC.fieldOf("effects").orElse(BuiltinDimensionTypes.OVERWORLD_EFFECTS).forGetter(DimensionType::effectsLocation),
                Codec.FLOAT.fieldOf("ambient_light").forGetter(DimensionType::ambientLight),
                Codec.intRange(DimensionType.MIN_Y, DimensionType.MAX_Y).optionalFieldOf("cloud_height").forGetter(DimensionType::cloudHeight),
                Codec.unit(Optional.empty()),
                DimensionType.MonsterSettings.CODEC.forGetter(DimensionType::monsterSettings)
        ).apply(instance, DimensionTypeMixin::cloudLayerDimensionType)));
    }*/
}
