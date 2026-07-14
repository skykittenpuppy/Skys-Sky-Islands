package gay.beegirl.skyislands.world.item;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.gliderdesign.HangGliderDesign;
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
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SewingTemplateItem extends Item {
    private static final ChatFormatting TITLE_FORMAT;
    private static final ChatFormatting DESCRIPTION_FORMAT;
    private static final Component INGREDIENTS_TITLE;
    private static final Component APPLIES_TO_TITLE;
    private static final Component DESIGN_APPLIES_TO;
    private static final Component DESIGN_INGREDIENTS;
    private static final Component BASE_SLOT_DESCRIPTION;
    private static final Component ADDITIONS_SLOT_DESCRIPTION;
    private static final ResourceLocation EMPTY_SLOT_GLIDER;
    private static final ResourceLocation EMPTY_SLOT_STRING;
    private final Component designDescription;

    public SewingTemplateItem(ResourceKey<HangGliderDesign> key, FeatureFlag... requiredFeatures) {
        super((new Item.Properties()).requiredFeatures(requiredFeatures));
        this.designDescription = Component.translatable(Util.makeDescriptionId("hang_glider_design", key.location())).withStyle(TITLE_FORMAT);
    }

    public void appendHoverText(@NotNull ItemStack stack, Item.@NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(designDescription);
        tooltipComponents.add(CommonComponents.EMPTY);
        tooltipComponents.add(APPLIES_TO_TITLE);
        tooltipComponents.add(CommonComponents.space().append(DESIGN_APPLIES_TO));
        tooltipComponents.add(INGREDIENTS_TITLE);
        tooltipComponents.add(CommonComponents.space().append(DESIGN_INGREDIENTS));
    }

    public Component getBaseSlotDescription() {
        return BASE_SLOT_DESCRIPTION;
    }

    public Component getAdditionSlotDescription() {
        return ADDITIONS_SLOT_DESCRIPTION;
    }

    public List<ResourceLocation> getBaseSlotEmptyIcons() {
        return List.of(EMPTY_SLOT_GLIDER);
    }

    public List<ResourceLocation> getAdditionalSlotEmptyIcons() {
        return List.of(EMPTY_SLOT_STRING);
    }

    static {
        TITLE_FORMAT = ChatFormatting.GRAY;
        DESCRIPTION_FORMAT = ChatFormatting.BLUE;
        INGREDIENTS_TITLE = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.ingredients"))).withStyle(TITLE_FORMAT);
        APPLIES_TO_TITLE = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.applies_to"))).withStyle(TITLE_FORMAT);
        DESIGN_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.hang_glider_design.applies_to"))).withStyle(DESCRIPTION_FORMAT);
        DESIGN_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.hang_glider_design.ingredients"))).withStyle(DESCRIPTION_FORMAT);
        BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.hang_glider_design.base_slot_description")));
        ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", SkysSkyIslands.createId("sewing_template.hang_glider_design.additions_slot_description")));
        EMPTY_SLOT_GLIDER = SkysSkyIslands.createId("item/empty_slot_glider");
        EMPTY_SLOT_STRING = SkysSkyIslands.createId("item/empty_slot_string");
    }
}
