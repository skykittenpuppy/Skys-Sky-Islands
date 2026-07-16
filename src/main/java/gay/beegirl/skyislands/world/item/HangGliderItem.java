package gay.beegirl.skyislands.world.item;

import gay.beegirl.skyislands.core.component.ModDataComponents;
import gay.beegirl.skyislands.tags.ModItemTags;
import gay.beegirl.skyislands.world.item.gliderdesign.HangGliderDesign;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HangGliderItem extends Item {
    public HangGliderItem(Properties properties) {
        super(properties.component(ModDataComponents.HANG_GLIDER.get(), Unit.INSTANCE).rarity(Rarity.RARE).stacksTo(1));
    }

    public boolean isValidRepairItem(@NotNull ItemStack toRepair, ItemStack repair) {
        return repair.is(ModItemTags.HANG_GLIDER_REPAIR_MATERIALS) || super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public void appendHoverText(ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        HangGliderDesign pattern = stack.get(ModDataComponents.SEWING_PATTERN.get());
        if (pattern != null) {
            pattern.addToTooltip(context, tooltipComponents::add, tooltipFlag);
        }
    }
}
