package gay.beegirl.skyislands.world.item;

import gay.beegirl.skyislands.core.component.ModDataComponents;
import gay.beegirl.skyislands.tags.ModItemTags;
import gay.beegirl.skyislands.world.item.gliderdesign.HangGliderDesign;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class HangGliderItem extends Item {
    public HangGliderItem(Properties properties) {
        super(properties);
    }

    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ModItemTags.HANG_GLIDER_REPAIR_MATERIALS) || super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        HangGliderDesign pattern = stack.get(ModDataComponents.SEWING_PATTERN.get());
        if (pattern != null) {
            pattern.addToTooltip(context, tooltipComponents::add, tooltipFlag);
        }
    }
}
