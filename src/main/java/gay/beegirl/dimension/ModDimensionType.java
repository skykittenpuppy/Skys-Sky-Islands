package gay.beegirl.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public record ModDimensionType(OptionalLong fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultraWarm, boolean natural, double coordinateScale, boolean bedWorks, boolean respawnAnchorWorks, int minY, int height, int logicalHeight, TagKey<Block> infiniburn, ResourceLocation effectsLocation, float ambientLight, Optional<Integer> cloudHeight, Optional<List<CloudLayer>> cloudLayers, MonsterSettings monsterSettings) {
    public static final int BITS_FOR_Y;
    public static final int MIN_HEIGHT = 16;
    public static final int Y_SIZE;
    public static final int MAX_Y;
    public static final int MIN_Y;
    public static final int WAY_ABOVE_MAX_Y;
    public static final int WAY_BELOW_MIN_Y;
    public static final Codec<ModDimensionType> DIRECT_CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<ModDimensionType>> STREAM_CODEC;
    public static final int MOON_PHASES = 8;
    public static final float[] MOON_BRIGHTNESS_PER_PHASE;
    public static final Codec<Holder<ModDimensionType>> CODEC;

    public ModDimensionType {
        if (height < MIN_HEIGHT) {
            throw new IllegalStateException("height has to be at least 16");
        } else if (minY + height > MAX_Y + 1) {
            throw new IllegalStateException("min_y + height cannot be higher than: " + (MAX_Y + 1));
        } else if (logicalHeight > height) {
            throw new IllegalStateException("logical_height cannot be higher than height");
        } else if (height % 16 != 0) {
            throw new IllegalStateException("height has to be multiple of 16");
        } else if (minY % 16 != 0) {
            throw new IllegalStateException("min_y has to be a multiple of 16");
        }
    }

    public static double getTeleportationScale(net.minecraft.world.level.dimension.DimensionType dimensionType, net.minecraft.world.level.dimension.DimensionType dimensionType2) {
        double d = dimensionType.coordinateScale();
        double e = dimensionType2.coordinateScale();
        return d / e;
    }

    public static Path getStorageFolder(ResourceKey<Level> resourceKey, Path path) {
        if (resourceKey == Level.OVERWORLD) {
            return path;
        } else if (resourceKey == Level.END) {
            return path.resolve("DIM1");
        } else {
            return resourceKey == Level.NETHER ? path.resolve("DIM-1") : path.resolve("dimensions").resolve(resourceKey.location().getNamespace()).resolve(resourceKey.location().getPath());
        }
    }

    public boolean hasFixedTime() {
        return this.fixedTime.isPresent();
    }

    public float timeOfDay(long l) {
        double d = Mth.frac((double)this.fixedTime.orElse(l) / (double)24000.0F - (double)0.25F);
        double e = (double)0.5F - Math.cos(d * Math.PI) / (double)2.0F;
        return (float)(d * (double)2.0F + e) / 3.0F;
    }

    public int moonPhase(long l) {
        return (int)(l / 24000L % MOON_PHASES + MOON_PHASES) % MOON_PHASES;
    }

    public boolean piglinSafe() {
        return this.monsterSettings.piglinSafe();
    }

    public boolean hasRaids() {
        return this.monsterSettings.hasRaids();
    }

    public IntProvider monsterSpawnLightTest() {
        return this.monsterSettings.monsterSpawnLightTest();
    }

    public int monsterSpawnBlockLightLimit() {
        return this.monsterSettings.monsterSpawnBlockLightLimit();
    }

    static {
        BITS_FOR_Y = BlockPos.PACKED_Y_LENGTH;
        Y_SIZE = (1 << BITS_FOR_Y) - 32;
        MAX_Y = (Y_SIZE >> 1) - 1;
        MIN_Y = MAX_Y - Y_SIZE + 1;
        WAY_ABOVE_MAX_Y = MAX_Y << 4;
        WAY_BELOW_MIN_Y = MIN_Y << 4;
        DIRECT_CODEC = ExtraCodecs.catchDecoderException(
                RecordCodecBuilder.create((instance) -> instance.group(
                        ExtraCodecs.asOptionalLong(Codec.LONG.lenientOptionalFieldOf("fixed_time")).forGetter(ModDimensionType::fixedTime),
                        Codec.BOOL.fieldOf("has_skylight").forGetter(ModDimensionType::hasSkyLight),
                        Codec.BOOL.fieldOf("has_ceiling").forGetter(ModDimensionType::hasCeiling),
                        Codec.BOOL.fieldOf("ultrawarm").forGetter(ModDimensionType::ultraWarm),
                        Codec.BOOL.fieldOf("natural").forGetter(ModDimensionType::natural),
                        Codec.doubleRange((double)1.0E-5F, (double)3.0E7F).fieldOf("coordinate_scale").forGetter(ModDimensionType::coordinateScale),
                        Codec.BOOL.fieldOf("bed_works").forGetter(ModDimensionType::bedWorks),
                        Codec.BOOL.fieldOf("respawn_anchor_works").forGetter(ModDimensionType::respawnAnchorWorks),
                        Codec.intRange(MIN_Y, MAX_Y).fieldOf("min_y").forGetter(ModDimensionType::minY),
                        Codec.intRange(MIN_HEIGHT, Y_SIZE).fieldOf("height").forGetter(ModDimensionType::height),
                        Codec.intRange(0, Y_SIZE).fieldOf("logical_height").forGetter(ModDimensionType::logicalHeight),
                        TagKey.hashedCodec(Registries.BLOCK).fieldOf("infiniburn").forGetter(ModDimensionType::infiniburn),
                        ResourceLocation.CODEC.fieldOf("effects").orElse(BuiltinDimensionTypes.OVERWORLD_EFFECTS).forGetter(ModDimensionType::effectsLocation),
                        Codec.FLOAT.fieldOf("ambient_light").forGetter(ModDimensionType::ambientLight),
                        Codec.intRange(MIN_Y, MAX_Y).optionalFieldOf("cloud_height").forGetter(ModDimensionType::cloudHeight),
                        Codec.list(CloudLayer.CODEC).optionalFieldOf("cloud_layers").forGetter(ModDimensionType::cloudLayers),
                        MonsterSettings.CODEC.forGetter(ModDimensionType::monsterSettings)
                ).apply(instance, ModDimensionType::new)));
        STREAM_CODEC = ByteBufCodecs.holderRegistry(Registries.DIMENSION_TYPE);
        MOON_BRIGHTNESS_PER_PHASE = new float[]{1.0F, 0.75F, 0.5F, 0.25F, 0.0F, 0.25F, 0.5F, 0.75F};
        CODEC = RegistryFileCodec.create(Registries.DIMENSION_TYPE, DIRECT_CODEC);
    }

    public record MonsterSettings(boolean piglinSafe, boolean hasRaids, IntProvider monsterSpawnLightTest, int monsterSpawnBlockLightLimit) {
        public static final MapCodec<MonsterSettings> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Codec.BOOL.fieldOf("piglin_safe").forGetter(MonsterSettings::piglinSafe),
                Codec.BOOL.fieldOf("has_raids").forGetter(MonsterSettings::hasRaids),
                IntProvider.codec(0, 15).fieldOf("monster_spawn_light_level").forGetter(MonsterSettings::monsterSpawnLightTest),
                Codec.intRange(0, 15).fieldOf("monster_spawn_block_light_limit").forGetter(MonsterSettings::monsterSpawnBlockLightLimit)
        ).apply(instance, MonsterSettings::new));
    }
    public record CloudLayer(int height, Optional<Float> xOffset, Optional<Float> yOffset) {
        public static final MapCodec<CloudLayer> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                Codec.intRange(MIN_Y, MAX_Y).fieldOf("height").forGetter(CloudLayer::height),
                Codec.FLOAT.optionalFieldOf("x_offset").forGetter(CloudLayer::xOffset),
                Codec.FLOAT.optionalFieldOf("y_offset").forGetter(CloudLayer::yOffset)
        ).apply(instance, CloudLayer::new));
    }
}
