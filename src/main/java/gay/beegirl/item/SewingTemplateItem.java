package gay.beegirl.item;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;
import java.util.function.Consumer;

public class SewingTemplateItem extends Item {
    private static final ChatFormatting TITLE_FORMAT;
    private static final ChatFormatting DESCRIPTION_FORMAT;
    private static final Component APPLIES_TO_TITLE;
    private static final Component SEWING_TEMPLATE_SUFFIX;
    private static final Component GLIDER_PATTERN_APPLIES_TO;
    private static final Component GLIDER_PATTERN_BASE_SLOT_DESCRIPTION;
    private static final ResourceLocation EMPTY_SLOT_GLIDER;
    private final Component appliesTo;
    private final Component baseSlotDescription;
    private final List<ResourceLocation> baseSlotEmptyIcons;

    public SewingTemplateItem(Item.Properties properties) {
        super(properties);
        this.appliesTo = GLIDER_PATTERN_APPLIES_TO;
        this.baseSlotDescription = GLIDER_PATTERN_BASE_SLOT_DESCRIPTION;
        this.baseSlotEmptyIcons = List.of(EMPTY_SLOT_GLIDER);
    }

    public void appendHoverText(ItemStack itemStack, Item.TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {
        consumer.accept(SEWING_TEMPLATE_SUFFIX);
        consumer.accept(CommonComponents.EMPTY);
        consumer.accept(APPLIES_TO_TITLE);
        consumer.accept(CommonComponents.space().append(this.appliesTo));
    }

    public Component getBaseSlotDescription() {
        return this.baseSlotDescription;
    }

    public List<ResourceLocation> getBaseSlotEmptyIcons() {
        return this.baseSlotEmptyIcons;
    }

    static {
        TITLE_FORMAT = ChatFormatting.GRAY;
        DESCRIPTION_FORMAT = ChatFormatting.BLUE;
        SEWING_TEMPLATE_SUFFIX = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sewing_template"))).withStyle(TITLE_FORMAT);
        APPLIES_TO_TITLE = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sewing_template.applies_to"))).withStyle(TITLE_FORMAT);
        GLIDER_PATTERN_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sewing_template.glider_pattern.applies_to"))).withStyle(DESCRIPTION_FORMAT);
        GLIDER_PATTERN_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "sewing_template.glider_pattern.base_slot_description")));
        EMPTY_SLOT_GLIDER = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, "container/slot/glider");
    }
}
