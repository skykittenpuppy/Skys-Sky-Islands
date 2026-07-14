package gay.beegirl.skyislands.mixin.client.screen;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.world.item.SewingTemplateItem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.CyclingSlotBackground;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.client.gui.screens.inventory.SmithingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
@Mixin(SmithingScreen.class)
public abstract class SmithingScreenMixin extends ItemCombinerScreen<SmithingMenu> {
    @Unique private static final ResourceLocation EMPTY_SLOT_SEWING_TEMPLATE = SkysSkyIslands.createId(
            "item/empty_slot_sewing_template_glider_design"
    );

    @Shadow @Final @Mutable
    private static List<ResourceLocation> EMPTY_SLOT_SMITHING_TEMPLATES;
    @Shadow @Final private CyclingSlotBackground baseIcon;
    @Shadow @Final private CyclingSlotBackground additionalIcon;
    @Shadow private ArmorStand armorStandPreview;

    public SmithingScreenMixin(SmithingMenu menu, Inventory playerInventory, Component title, ResourceLocation menuResource) {
        super(menu, playerInventory, title, menuResource);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void islands$init(CallbackInfo ci) {
        EMPTY_SLOT_SMITHING_TEMPLATES = new ArrayList<>(EMPTY_SLOT_SMITHING_TEMPLATES);
        EMPTY_SLOT_SMITHING_TEMPLATES.add(EMPTY_SLOT_SEWING_TEMPLATE);
    }

    @Unique
    private Optional<SewingTemplateItem> islands$getTemplateItem() {
        ItemStack itemstack = this.menu.getSlot(0).getItem();
        return !itemstack.isEmpty() && itemstack.getItem() instanceof SewingTemplateItem templateItem
                ? Optional.of(templateItem)
                : Optional.empty();
    }

    @Inject(method = "containerTick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/inventory/CyclingSlotBackground;tick(Ljava/util/List;)V", shift = At.Shift.AFTER), cancellable = true)
    private void islands$containerTick(CallbackInfo ci, @Local Optional<SmithingTemplateItem> smithingTemplate) {
        if (smithingTemplate.isEmpty()) {
            Optional<SewingTemplateItem> sewingTemplate = islands$getTemplateItem();
            baseIcon.tick(sewingTemplate.map(SewingTemplateItem::getBaseSlotEmptyIcons).orElse(List.of()));
            additionalIcon.tick(sewingTemplate.map(SewingTemplateItem::getAdditionalSlotEmptyIcons).orElse(List.of()));
            ci.cancel();
        }
    }

    @Inject(method = "slotChanged", at = @At("HEAD"))
    private void islands$slotChanged(AbstractContainerMenu containerToSend, int slotInd, ItemStack stack, CallbackInfo ci) {
        armorStandPreview.setData(ModDataAttachments.IS_GLIDING, false);
        if (slotInd == 3 && stack.is(ModItems.HANG_GLIDER)) {
            armorStandPreview.setData(ModDataAttachments.IS_GLIDING, true);
        }
    }

    @Inject(method = "renderOnboardingTooltips(Lnet/minecraft/client/gui/GuiGraphics;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;getItem()Lnet/minecraft/world/item/Item;", shift = At.Shift.AFTER))
    private void islands$renderOnboardingTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, CallbackInfo ci, @Local LocalRef<Optional<Component>> optional, @Local(ordinal = 0) ItemStack templateItemStack, @Local(ordinal = 1) ItemStack hoveredItemStack) {
        if (templateItemStack.getItem() instanceof SewingTemplateItem sewingTemplateItem) {
            if (hoveredItemStack.isEmpty()) {
                if (this.hoveredSlot.index == 1) {
                    optional.set(Optional.of(sewingTemplateItem.getBaseSlotDescription()));
                } else if (this.hoveredSlot.index == 2) {
                    optional.set(Optional.of(sewingTemplateItem.getAdditionSlotDescription()));
                }
            }
        }
    }
}
