package gay.beegirl.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.SkysSkyIslands;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.ArmorTrim;

import java.util.function.Consumer;

public record GliderPattern(Holder<GliderDesign> design) implements TooltipProvider {
    public static final Codec<GliderPattern> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
            GliderDesign.CODEC.fieldOf("design").forGetter(GliderPattern::design)).apply(instance, GliderPattern::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, GliderPattern> STREAM_CODEC;
    private static final Component PATTERN_TITLE;

    public void addToTooltip(Item.TooltipContext tooltipContext, Consumer<Component> consumer, TooltipFlag tooltipFlag, DataComponentGetter dataComponentGetter) {
        consumer.accept(PATTERN_TITLE);
        consumer.accept(CommonComponents.space().append(this.design.value().description()));
    }

    public ResourceLocation layerAssetId(String string, ResourceKey<EquipmentAsset> resourceKey) {
        return this.design().value().assetId().withPath((string2) -> string + "/" + string2);
    }

    static {
        STREAM_CODEC = StreamCodec.composite(GliderDesign.STREAM_CODEC, GliderPattern::design, GliderPattern::new);
        PATTERN_TITLE = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sewing_template.pattern"))).withStyle(ChatFormatting.GRAY);
    }
}