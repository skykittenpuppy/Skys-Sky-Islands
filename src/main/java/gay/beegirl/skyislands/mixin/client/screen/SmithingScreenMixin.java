package gay.beegirl.skyislands.mixin.client.screen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import gay.beegirl.skyislands.world.item.ModItems;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.gui.screens.inventory.SmithingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@OnlyIn(Dist.CLIENT)
@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin extends ItemCombinerScreen<SmithingMenu> {
    @Shadow private ArmorStand armorStandPreview;

    public SmithingScreenMixin(SmithingMenu menu, Inventory playerInventory, Component title, ResourceLocation menuResource) {
        super(menu, playerInventory, title, menuResource);
    }

    @Inject(method = "slotChanged", at = @At("HEAD"), cancellable = true)
    private void islands$slotChanged(AbstractContainerMenu containerToSend, int slotInd, ItemStack stack, CallbackInfo ci) {
        armorStandPreview.setData(ModDataAttachments.IS_GLIDING, false); // ?
        if (slotInd == 3 && stack.is(ModItems.GLIDER)) {
            ci.cancel();
            armorStandPreview.setData(ModDataAttachments.IS_GLIDING, true);
        }
    }
}
