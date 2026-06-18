package gay.beegirl.skyislands.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimPattern;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class GliderPattern implements TooltipProvider {
    public static final Codec<GliderPattern> CODEC = RecordCodecBuilder.create(
            p_337943_ -> p_337943_.group(
                            GliderDesign.CODEC.fieldOf("pattern").forGetter(GliderPattern::design),
                            Codec.BOOL.optionalFieldOf("show_in_tooltip", Boolean.valueOf(true)).forGetter(p_330108_ -> p_330108_.showInTooltip)
                    )
                    .apply(p_337943_, GliderPattern::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, GliderPattern> STREAM_CODEC = StreamCodec.composite(
            GliderDesign.STREAM_CODEC,
            GliderPattern::design,
            ByteBufCodecs.BOOL,
            p_330107_ -> p_330107_.showInTooltip,
            GliderPattern::new
    );
    private static final Component UPGRADE_TITLE = Component.translatable(
                    Util.makeDescriptionId("item", ResourceLocation.withDefaultNamespace("smithing_template.upgrade"))
            )
            .withStyle(ChatFormatting.GRAY);
    private final Holder<GliderDesign> design;
    private final boolean showInTooltip;

    private GliderPattern(
            Holder<GliderDesign> design,
            boolean showInTooltip,
            Function<Holder<ArmorMaterial>, ResourceLocation> innerTexture,
            Function<Holder<ArmorMaterial>, ResourceLocation> outerTexture
    ) {
        this.design = design;
        this.showInTooltip = showInTooltip;
    }

    public GliderPattern(Holder<GliderDesign> pattern, boolean showInTooltip) {
        this.design = pattern;
        this.showInTooltip = showInTooltip;
    }

    public GliderPattern(Holder<GliderDesign> pattern) {
        this(pattern, true);
    }

    public boolean hasDesign(Holder<GliderDesign> pattern) {
        return pattern.equals(this.design);
    }

    public Holder<GliderDesign> design() {
        return this.design;
    }

    @Override
    public boolean equals(Object other) {
        return !(other instanceof GliderPattern gliderPattern)
                ? false
                : this.showInTooltip == gliderPattern.showInTooltip && this.design.equals(gliderPattern.design);
    }

    @Override
    public int hashCode() {
        int i = this.design.hashCode();
        return 31 * i + (this.showInTooltip ? 1 : 0);
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
        if (this.showInTooltip) {
            tooltipAdder.accept(UPGRADE_TITLE);
            tooltipAdder.accept(CommonComponents.space().append(this.design.value().copyWithStyle()));
        }
    }

    public GliderPattern withTooltip(boolean showInTooltip) {
        return new GliderPattern(this.design, showInTooltip);
    }
}
