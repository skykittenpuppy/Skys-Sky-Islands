package gay.beegirl.skyislands.item;

import gay.beegirl.skyislands.tags.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class GliderItem extends Item {
    public GliderItem(Properties properties) {
        super(properties);
    }

    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ModTags.Items.GLIDER_REPAIR_MATERIALS) || super.isValidRepairItem(toRepair, repair);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ResourceLocation pattern = stack.get(ModDataComponents.SEWING_PATTERN);
        if (pattern != null) {
            tooltipComponents.add(Component.translatable("item.skyislands.sewing_template.pattern").withStyle(ChatFormatting.GRAY));
            tooltipComponents.add(Component.literal(" ").append(Component.translatable("glider_design."+pattern.getNamespace()+"."+pattern.getPath())));
        }
    }
}
