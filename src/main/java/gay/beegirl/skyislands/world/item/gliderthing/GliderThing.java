package gay.beegirl.skyislands.world.item.gliderthing;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public class GliderThing implements TooltipProvider {
    public static final Codec<GliderThing> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    ThingPattern.CODEC.fieldOf("pattern").forGetter(GliderThing::design),
                    Codec.BOOL.optionalFieldOf("show_in_tooltip", true).forGetter(gliderThing -> gliderThing.showInTooltip)
            ).apply(instance, GliderThing::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, GliderThing> STREAM_CODEC = StreamCodec.composite(
            ThingPattern.STREAM_CODEC,
            GliderThing::design,
            ByteBufCodecs.BOOL,
            gliderThing -> gliderThing.showInTooltip,
            GliderThing::new
    );
    private static final Component THING_TITLE = Component.translatable(
                    Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.thing"))
            )
            .withStyle(ChatFormatting.GRAY);
    private final Holder<ThingPattern> design;
    private final boolean showInTooltip;

    public GliderThing(Holder<ThingPattern> pattern, boolean showInTooltip) {
        this.design = pattern;
        this.showInTooltip = showInTooltip;
    }

    public GliderThing(Holder<ThingPattern> pattern) {
        this(pattern, true);
    }

    public boolean hasDesign(Holder<ThingPattern> pattern) {
        return pattern.equals(this.design);
    }

    public Holder<ThingPattern> design() {
        return this.design;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof GliderThing gliderPattern && this.showInTooltip == gliderPattern.showInTooltip && this.design.equals(gliderPattern.design);
    }

    @Override
    public int hashCode() {
        int i = this.design.hashCode();
        return 31 * i + (this.showInTooltip ? 1 : 0);
    }

    @Override
    public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
        if (this.showInTooltip) {
            tooltipAdder.accept(THING_TITLE);
            tooltipAdder.accept(CommonComponents.space().append(this.design.value().copyWithStyle()));
        }
    }

    public GliderThing withTooltip(boolean showInTooltip) {
        return new GliderThing(this.design, showInTooltip);
    }
}
