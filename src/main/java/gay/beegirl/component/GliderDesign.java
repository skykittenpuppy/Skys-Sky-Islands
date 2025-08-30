package gay.beegirl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.registry.ModRegistryResourceKeys;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.equipment.trim.TrimPattern;
import net.minecraft.world.item.equipment.trim.TrimPatterns;

public record GliderDesign(ResourceLocation assetId, Component description) {
    public static final Codec<GliderDesign> DIRECT_CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(GliderDesign::assetId),
            ComponentSerialization.CODEC.fieldOf("description").forGetter(GliderDesign::description)).apply(instance, GliderDesign::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, GliderDesign> DIRECT_STREAM_CODEC;
    public static final Codec<Holder<GliderDesign>> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<GliderDesign>> STREAM_CODEC;

    static {
        DIRECT_STREAM_CODEC = StreamCodec.composite(ResourceLocation.STREAM_CODEC, GliderDesign::assetId,
                ComponentSerialization.STREAM_CODEC, GliderDesign::description, GliderDesign::new);
        CODEC = RegistryFileCodec.create(ModRegistryResourceKeys.GLIDER_DESIGN, DIRECT_CODEC);
        STREAM_CODEC = ByteBufCodecs.holder(ModRegistryResourceKeys.GLIDER_DESIGN, DIRECT_STREAM_CODEC);
    }
}