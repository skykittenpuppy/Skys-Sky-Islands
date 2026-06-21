package gay.beegirl.skyislands.world.item.gliderdesign;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.RegistryFixedCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public class GliderDesign implements TooltipProvider {
	public static final Codec<GliderDesign> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					RegistryFixedCodec.create(Registries.ITEM).fieldOf("template_item").forGetter(gliderDesign -> gliderDesign.templateItem),
					ResourceLocation.CODEC.fieldOf("asset_id").forGetter(gliderDesign -> gliderDesign.assetId),
					ComponentSerialization.CODEC.fieldOf("description").forGetter(gliderDesign -> gliderDesign.description),
					Codec.BOOL.optionalFieldOf("show_in_tooltip", true).forGetter(gliderDesign -> gliderDesign.showInTooltip)
			).apply(instance, GliderDesign::new)
	);
	public static final StreamCodec<RegistryFriendlyByteBuf, GliderDesign> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.holderRegistry(Registries.ITEM), gliderDesign -> gliderDesign.templateItem,
			ResourceLocation.STREAM_CODEC, gliderDesign -> gliderDesign.assetId,
			ComponentSerialization.STREAM_CODEC, gliderDesign -> gliderDesign.description,
			ByteBufCodecs.BOOL, gliderDesign -> gliderDesign.showInTooltip,
			GliderDesign::new
	);

	private static final Component DESIGN_TITLE = Component.translatable(
					Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.design"))
			)
			.withStyle(ChatFormatting.GRAY);

	public final Holder<Item> templateItem;
	public final ResourceLocation assetId;
	public final Component description;
	private final boolean showInTooltip;

	public GliderDesign(GliderDesign other){
		this.templateItem = other.templateItem;
		this.assetId = other.assetId;
		this.description = other.description;
		this.showInTooltip = other.showInTooltip;
	}
	public GliderDesign(Holder<Item> templateItem, ResourceLocation assetId, Component description) {
		this.templateItem = templateItem;
		this.assetId = assetId;
		this.description = description;
		this.showInTooltip = true;
	}
	public GliderDesign(Holder<Item> templateItem, ResourceLocation assetId, Component description, boolean showInTooltip) {
		this.templateItem = templateItem;
		this.assetId = assetId;
		this.description = description;
		this.showInTooltip = showInTooltip;
	}

	@Override
	public void addToTooltip(Item.TooltipContext context, Consumer<Component> tooltipAdder, TooltipFlag tooltipFlag) {
		if (this.showInTooltip) {
			tooltipAdder.accept(DESIGN_TITLE);
			tooltipAdder.accept(CommonComponents.space().append(description));
		}
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof GliderDesign gliderPattern &&
				this.templateItem == gliderPattern.templateItem &&
				this.assetId == gliderPattern.assetId &&
				this.description == gliderPattern.description &&
				this.showInTooltip == gliderPattern.showInTooltip;
	}

	@Override
	public int hashCode() {
		int i = 1;//this.hashCode();
		return 31 * i + (this.showInTooltip ? 1 : 0);
	}

	public GliderDesign withTooltip(boolean showInTooltip) {
		return new GliderDesign(templateItem, assetId, description, showInTooltip);
	}
}
