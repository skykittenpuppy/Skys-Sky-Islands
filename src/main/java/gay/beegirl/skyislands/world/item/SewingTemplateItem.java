package gay.beegirl.skyislands.world.item;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.gliderthing.ThingPattern;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class SewingTemplateItem extends Item {
    private static final ChatFormatting TITLE_FORMAT;
    private static final ChatFormatting DESCRIPTION_FORMAT;
    private static final Component APPLIES_TO_TITLE;
    //private static final Component SEWING_TEMPLATE_SUFFIX;
    private static final Component GLIDER_PATTERN_APPLIES_TO;
    private static final Component GLIDER_PATTERN_BASE_SLOT_DESCRIPTION;
    private static final ResourceLocation EMPTY_SLOT_GLIDER;
    private final Component upgradeDescription;

    public SewingTemplateItem(ResourceKey<ThingPattern> key, FeatureFlag... requiredFeatures) {
        super((new Item.Properties()).requiredFeatures(requiredFeatures));
        this.upgradeDescription = Component.translatable(Util.makeDescriptionId("glider_design", key.location())).withStyle(TITLE_FORMAT);
    }

    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(upgradeDescription);
        tooltipComponents.add(CommonComponents.EMPTY);
        tooltipComponents.add(APPLIES_TO_TITLE);
        tooltipComponents.add(CommonComponents.space().append(GLIDER_PATTERN_APPLIES_TO));
    }

    public Component getBaseSlotDescription() {
        return GLIDER_PATTERN_BASE_SLOT_DESCRIPTION;
    }

    public List<ResourceLocation> getBaseSlotEmptyIcons() {
        return List.of(EMPTY_SLOT_GLIDER);
    }

    static {
        TITLE_FORMAT = ChatFormatting.GRAY;
        DESCRIPTION_FORMAT = ChatFormatting.BLUE;
        //SEWING_TEMPLATE_SUFFIX = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template"))).withStyle(TITLE_FORMAT);
        APPLIES_TO_TITLE = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.applies_to"))).withStyle(TITLE_FORMAT);
        GLIDER_PATTERN_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.glider_pattern.applies_to"))).withStyle(DESCRIPTION_FORMAT);
        GLIDER_PATTERN_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.glider_pattern.base_slot_description")));
        EMPTY_SLOT_GLIDER = SkysSkyIslands.createId("container/slot/glider");
    }
}
