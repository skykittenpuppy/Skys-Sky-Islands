package gay.beegirl.skyislands.world.item.gliderthing;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.skyislands.core.registries.ModRegistries;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;

public record ThingPattern(ResourceLocation assetId, Holder<Item> templateItem, Component description) {
    public static final Codec<ThingPattern> DIRECT_CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            ResourceLocation.CODEC.fieldOf("asset_id").forGetter(ThingPattern::assetId),
            RegistryFixedCodec.create(Registries.ITEM).fieldOf("template_item").forGetter(ThingPattern::templateItem),
            ComponentSerialization.CODEC.fieldOf("description").forGetter(ThingPattern::description)
    ).apply(instance, ThingPattern::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, ThingPattern> DIRECT_STREAM_CODEC;
    public static final Codec<Holder<ThingPattern>> CODEC;
    public static final StreamCodec<RegistryFriendlyByteBuf, Holder<ThingPattern>> STREAM_CODEC;

    public Component copyWithStyle() {
        return this.description.copy();
    }

    static {
        DIRECT_STREAM_CODEC = StreamCodec.composite(
                ResourceLocation.STREAM_CODEC, ThingPattern::assetId,
                ByteBufCodecs.holderRegistry(Registries.ITEM), ThingPattern::templateItem,
                ComponentSerialization.STREAM_CODEC, ThingPattern::description,
                ThingPattern::new
        );
        CODEC = RegistryFileCodec.create(ModRegistries.GLIDER_DESIGN, DIRECT_CODEC);
        STREAM_CODEC = ByteBufCodecs.holder(ModRegistries.GLIDER_DESIGN, DIRECT_STREAM_CODEC);
    }
}
