package gay.beegirl.skyislands.mixin.world.entity;

import gay.beegirl.skyislands.core.component.ModDataComponents;
import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.util.LivingEntityAccess;
import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, ILivingEntityExtension, LivingEntityAccess {
    @Unique private static final float FALL_DISTANCE_TILL_CAN_GLIDE = 2.5F; // TODO: Replace with gamerule/config/attribute
    @Unique private static final float FALL_DISTANCE_TILL_FREE_FALL = 8.0F; // TODO: Replace with gamerule/config/attribute

    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot equipmentSlot);
    @Shadow public abstract boolean hasEffect(Holder<MobEffect> effect);

    @Unique
    private boolean islands$canGlideUsing(ItemStack itemStack) {
        return  itemStack.has(ModDataComponents.HANG_GLIDER) &&
                !(itemStack.isDamageableItem() && itemStack.getDamageValue() >= itemStack.getMaxDamage() - 1);
    }

    @Unique
    private boolean islands$canHangGlide() {
        if (!this.isShiftKeyDown() && !this.onGround() && !this.isPassenger() && !this.hasEffect(MobEffects.LEVITATION)) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (islands$canGlideUsing(this.getItemBySlot(slot)))
                    return true;
            }
        }
        return false;
    }

    @Unique
    private boolean islands$canFreeFall() {
		return !this.onGround() && !this.isPassenger() && !this.hasEffect(MobEffects.LEVITATION) && !this.hasEffect(MobEffects.SLOW_FALLING);
	}

    @Unique
    private boolean islands$isFreeFalling;

    @Unique
    private boolean islands$isDiving;

    @Unique
    private boolean islands$isGliding;

    @Unique
    private int islands$glideTicks;

    @Unique
    private int islands$freeFallTicks;

    @Unique
    private int islands$diveTicks;

    @Unique
    public int islands$getGlideTicks() {
        return this.islands$glideTicks;
    }

    @Unique
    public int islands$getFreeFallTicks() {
        return this.islands$freeFallTicks;
    }

    @Unique
    public int islands$getDiveTicks() {
        return this.islands$diveTicks;
    }

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyArgs(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V", ordinal = 3))
    private void islands$modifyMovement(Args args, Vec3 travelVector) {
        if (this.islands$isDiving) {
            double xVel = args.get(0);
            double yVel = args.get(1);
            double zVel = args.get(2);
            this.move(MoverType.SELF, new Vec3(-(0.85F * xVel), yVel, -(0.85F * zVel)));
        } else if (this.islands$isFreeFalling) {
            double xVel = args.get(0);
            double yVel = args.get(1);
            double zVel = args.get(2);
            this.move(MoverType.SELF, new Vec3(0.15F * xVel, -(0.05 * yVel), 0.15F * zVel));
        } else if (this.islands$isGliding) {
            double xVel = args.get(0);
            double yVel = args.get(1);
            double zVel = args.get(2);
            double newYVel = Mth.lerp(Math.clamp(this.islands$getGlideTicks(), 0, 20)/20.0F, yVel, -this.getGravity() * 1.5F);
            this.move(MoverType.SELF, new Vec3(0.5F * xVel, -0.0000001F, 0.5F * zVel));
            args.set(1, newYVel);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void islands$setModStates(CallbackInfo ci) {
        this.setData(ModDataAttachments.IS_GLIDING,
                (this.fallDistance > FALL_DISTANCE_TILL_CAN_GLIDE || this.islands$isGliding) && this.islands$canHangGlide());
        this.islands$isGliding = this.getData(ModDataAttachments.IS_GLIDING);

        if (this.islands$isGliding) {
            this.fallDistance = 0.0F;
            this.islands$glideTicks++;
        } else {
            this.islands$glideTicks = 0;
        }

        this.setData(ModDataAttachments.IS_FREEFALLING,
                this.fallDistance > FALL_DISTANCE_TILL_FREE_FALL && this.islands$canFreeFall() && !this.isShiftKeyDown());
        this.islands$isFreeFalling = this.getData(ModDataAttachments.IS_FREEFALLING);

        if (this.islands$isFreeFalling) {
            this.makeBoundingBox().setMaxY(1.0F);
            this.islands$freeFallTicks++;
        } else {
            this.islands$freeFallTicks = 0;
        }

        this.setData(ModDataAttachments.IS_DIVING,
                this.fallDistance > FALL_DISTANCE_TILL_FREE_FALL && this.islands$canFreeFall() && this.isShiftKeyDown());
        this.islands$isDiving = this.getData(ModDataAttachments.IS_DIVING);

        if (this.islands$isDiving) {
            this.islands$diveTicks++;
        } else {
            this.islands$diveTicks = 0;
        }
    }
}
