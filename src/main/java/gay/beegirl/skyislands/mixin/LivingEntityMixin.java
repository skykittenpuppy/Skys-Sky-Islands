package gay.beegirl.skyislands.mixin;

import gay.beegirl.skyislands.entity.ModDataAttachments;
import gay.beegirl.skyislands.item.ModItems;
import gay.beegirl.skyislands.util.LivingEntityAccess;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Attackable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, ILivingEntityExtension, LivingEntityAccess {
    @Unique private static final float FALL_DISTANCE_TILL_FREE_FALL = 8.0F; // TODO: Replace with gamerule/config/attribute
    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand hand);

    @Unique
    private boolean islands$isFreeFalling;

    @Unique
    private boolean islands$isDiving;

    @Unique
    private boolean islands$isGliding;

    @Unique
    private int islands$freeFallTicks;

    @Unique
    public boolean islands$isFreeFalling() {
        return this.islands$isFreeFalling;
    }

    @Unique
    public int islands$getFreeFallTicks() {
        return this.islands$freeFallTicks;
    }

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void islands$testFreeFalling(CallbackInfo ci) {
        this.setData(ModDataAttachments.IS_GLIDING, (this.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.GLIDER) || this.getItemInHand(InteractionHand.OFF_HAND).is(ModItems.GLIDER)) && !this.onGround()); // TODO: Replace with proper glide trigger
        //this.setData(ModDataAttachments.IS_FREEFALLING, this.fallDistance > FALL_DISTANCE_TILL_FREE_FALL); not slowfalling, not levitation, is player, isnt gliding, etc
        this.setData(ModDataAttachments.IS_FREEFALLING, this.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE));
        this.setData(ModDataAttachments.IS_DIVING, this.islands$isFreeFalling() && this.isShiftKeyDown());
        this.islands$isFreeFalling = this.getData(ModDataAttachments.IS_FREEFALLING);
        this.islands$isDiving = this.getData(ModDataAttachments.IS_DIVING);
        this.islands$isGliding = this.getData(ModDataAttachments.IS_GLIDING);
        if (this.islands$isGliding) this.fallDistance = 0.0F;
        if (this.islands$isFreeFalling()) {
            this.makeBoundingBox().setMaxY(1.0F); // TODO: Look into resizing to 2 blocks wide
            ++this.islands$freeFallTicks;
        } else {
            this.islands$freeFallTicks = 0;
        }
    }
}
