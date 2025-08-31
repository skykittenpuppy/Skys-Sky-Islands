package gay.beegirl.mixin;

import gay.beegirl.component.ModDataComponents;
import net.minecraft.Util;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {
    @Shadow
    private void travelInAir(Vec3 vec3) {}
    @Shadow
    protected double getEffectiveGravity() { return 0; }
    @Shadow
    private SoundEvent getFallDamageSound(int i) { return null; }

    @Unique
    private static final int FLAG_HANG_GLIDING = 0; //Protected
    @Unique
    private static final int FLAG_DIVING = 1; //Protected
    @Unique
    private static final int FLAG_FREEFALLING = 2; //Protected
    @Unique
    protected int hangGlideTicks;

    //TODO: travel()

    @Unique
    private void travelHangGliding(Vec3 vec3) {
        LivingEntity thisObject = (LivingEntity)(Object)this;

        if (thisObject.onClimbable()) {
            travelInAir(vec3);
            stopHangGliding();
        } else {
            Vec3 vec32 = thisObject.getDeltaMovement();
            double d = vec32.horizontalDistance();
            thisObject.setDeltaMovement(updateHangGlidingMovement(vec32));
            thisObject.move(MoverType.SELF, thisObject.getDeltaMovement());
            if (!thisObject.level().isClientSide) {
                double e = thisObject.getDeltaMovement().horizontalDistance();
                handleHangGlidingCollisions(d, e);
            }

        }
    }

    @Unique
    public void stopHangGliding() {
        setSkyIslandFlag(FLAG_HANG_GLIDING, true);
        setSkyIslandFlag(FLAG_HANG_GLIDING, false);
    }

    @Unique
    private Vec3 updateHangGlidingMovement(Vec3 vec3) {
        LivingEntity thisObject = (LivingEntity)(Object)this;

        Vec3 vec32 = thisObject.getLookAngle();
        float f = thisObject.getXRot() * ((float)Math.PI / 180F);
        double d = Math.sqrt(vec32.x * vec32.x + vec32.z * vec32.z);
        double e = vec3.horizontalDistance();
        double g = getEffectiveGravity();
        double h = Mth.square(Math.cos((double)f));
        vec3 = vec3.add((double)0.0F, g * ((double)-1.0F + h * (double)0.75F), (double)0.0F);
        if (vec3.y < (double)0.0F && d > (double)0.0F) {
            double i = vec3.y * -0.1 * h;
            vec3 = vec3.add(vec32.x * i / d, i, vec32.z * i / d);
        }

        if (f < 0.0F && d > (double)0.0F) {
            double i = e * (double)(-Mth.sin(f)) * 0.04;
            vec3 = vec3.add(-vec32.x * i / d, i * 3.2, -vec32.z * i / d);
        }

        if (d > (double)0.0F) {
            vec3 = vec3.add((vec32.x / d * e - vec3.x) * 0.1, (double)0.0F, (vec32.z / d * e - vec3.z) * 0.1);
        }

        return vec3.multiply((double)0.99F, (double)0.98F, (double)0.99F);
    }

    @Unique
    private void handleHangGlidingCollisions(double d, double e) {
        LivingEntity thisObject = (LivingEntity)(Object)this;

        if (thisObject.horizontalCollision) {
            double f = d - e;
            float g = (float)(f * (double)10.0F - (double)3.0F);
            if (g > 0.0F) {
                thisObject.playSound(getFallDamageSound((int)g), 1.0F, 1.0F);
                thisObject.hurt(thisObject.damageSources().flyIntoWall(), g);
            }
        }

    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSleeping()Z", shift =  At.Shift.BEFORE))
    protected void tick(CallbackInfo ci) {
        if (isHangGliding()) {
            ++hangGlideTicks;
        } else {
            hangGlideTicks = 0;
        }
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getBoundingBox()Lnet/minecraft/world/phys/AABB;", shift = At.Shift.BEFORE))
    protected void aiStep(CallbackInfo ci) {
        if (isHangGliding()) {
            updateHangGliding();
        }
    }

    @Unique
    protected void updateHangGliding() {
        LivingEntity thisObject = (LivingEntity)(Object)this;

        thisObject.checkFallDistanceAccumulation();
        if (!thisObject.level().isClientSide) {
            if (!canHangGlide()) {
                setSkyIslandFlag(FLAG_HANG_GLIDING, false);
                return;
            }

            int i = hangGlideTicks + 1;
            if (i % 10 == 0) {
                int j = i / 10;
                if (j % 2 == 0) {
                    List<EquipmentSlot> list = EquipmentSlot.VALUES.stream().filter((equipmentSlotx) -> canHangGlideUsing(thisObject.getItemBySlot(equipmentSlotx), equipmentSlotx)).toList();
                    EquipmentSlot equipmentSlot = Util.getRandom(list, random);
                    thisObject.getItemBySlot(equipmentSlot).hurtAndBreak(1, thisObject, equipmentSlot);
                }

                //thisObject.gameEvent(GameEvent.ELYTRA_GLIDE);
            }
        }
    }

    @Unique
    protected boolean canHangGlide() {
        LivingEntity thisObject = (LivingEntity)(Object)this;

        if (!thisObject.onGround() && !thisObject.isPassenger() && !thisObject.hasEffect(MobEffects.LEVITATION)) {
            for(EquipmentSlot equipmentSlot : EquipmentSlot.VALUES) {
                if (canHangGlideUsing(thisObject.getItemBySlot(equipmentSlot), equipmentSlot)) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    @Unique
    public boolean isHangGliding() {
        return getSkyIslandFlag(FLAG_HANG_GLIDING);
    }

    @Unique
    public int getHangGlidingTicks() {
        return hangGlideTicks;
    }

    @Unique
    private static boolean canHangGlideUsing(ItemStack itemStack, EquipmentSlot equipmentSlot) {
        if (!itemStack.has(ModDataComponents.HANG_GLIDER)) {
            return false;
        } else {
            Equippable equippable = itemStack.get(DataComponents.EQUIPPABLE);
            return equippable != null && equipmentSlot == equippable.slot() && !itemStack.nextDamageWillBreak();
        }
    }
}
