package gay.beegirl.skyislands.world.item;

import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BatteryItem extends Item {
    public BatteryItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int batteryCount = player.getData(ModDataAttachments.BATTERY_COUNT);
        if (batteryCount >= 8) {
            player.displayClientMessage(Component.translatable("message.islands.maxBatteries"), true);
            return InteractionResultHolder.fail(stack);
        }
        player.setData(ModDataAttachments.BATTERY_COUNT, batteryCount+1);
        return InteractionResultHolder.consume(stack);
    }
}
