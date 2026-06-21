package gay.beegirl.skyislands.world.item;

import gay.beegirl.skyislands.core.component.ModDataComponents;
import gay.beegirl.skyislands.tags.ModItemTags;
import gay.beegirl.skyislands.world.item.gliderdesign.GliderDesign;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class GliderItem extends Item {
    public GliderItem(Properties properties) {
        super(properties);
    }

    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ModItemTags.GLIDER_REPAIR_MATERIALS) || super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        GliderDesign pattern = stack.get(ModDataComponents.SEWING_PATTERN.get());
        if (pattern != null) {
            pattern.addToTooltip(context, tooltipComponents::add, tooltipFlag);
        }
    }
}
