package gay.beegirl.skyislands.item;

import gay.beegirl.skyislands.util.ModTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GliderItem extends Item {
    public GliderItem(Properties properties) {
        super(properties);
    }

    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(ModTags.Items.GLIDER_REPAIR_MATERIALS) || super.isValidRepairItem(toRepair, repair);
    }
}
