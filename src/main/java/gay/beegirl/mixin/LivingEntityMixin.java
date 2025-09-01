package gay.beegirl.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import gay.beegirl.SkysSkyIslands;
import gay.beegirl.component.ModDataComponents;
import gay.beegirl.entity.ModAttributes;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin {
    @Shadow
    private void travelFallFlying(Vec3 vec3) {}
    @Shadow
    public AttributeInstance getAttribute(Holder<Attribute> holder) { return null; }

    @Shadow
    protected abstract void travelInAir(Vec3 vec3);

    @Unique
    protected boolean canRegenStamina = true;
    @Unique
    protected boolean isHangGliding = false;
    @Unique
    protected boolean isDiving = false;
    @Unique
    protected boolean isFreefalling = false;
    @Unique
    protected double minFallDistanceForFreefall = 5;
    @Unique
    private static final EntityDataAccessor<Integer> DATA_STAMINA_TICKS_ID;
    @Unique
    private static final int baseMaxStaminaTicks = 400;
    @Unique
    protected int hangGlideTicks;

    @ModifyExpressionValue(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isFallFlying()Z"))
    private boolean travel$isFallFlyingOrHangGlidingOrDivingOrFreefalling(boolean original) {
        return original || isHangGliding || isDiving || isFreefalling;
    }
    @Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;travelFallFlying(Lnet/minecraft/world/phys/Vec3;)V"))
    private void travel$travelFallFlyingOrHangGliding(LivingEntity thisObject, Vec3 vec3){
        if (thisObject.isFallFlying()) travelFallFlying(vec3);
        else if (isHangGliding) {
            travelInAir(vec3);
            Vec3 deltaMovement = thisObject.getDeltaMovement();
            if (deltaMovement.y < -0.25) deltaMovement = deltaMovement.lerp(new Vec3(deltaMovement.x, -0.25, deltaMovement.z), 0.5);
            if (deltaMovement.y > -0.33) thisObject.fallDistance = Math.min(thisObject.fallDistance, 0.1f);
            thisObject.setDeltaMovement(deltaMovement);
        }
        else if (isDiving)  {
            travelInAir(vec3);
            Vec3 deltaMovement = thisObject.getDeltaMovement();
            deltaMovement = deltaMovement.lerp(new Vec3(deltaMovement.x, -2.0, deltaMovement.z), 0.5);
            thisObject.setDeltaMovement(deltaMovement);
        }
        else if (isFreefalling)  {
            travelInAir(vec3);
            Vec3 deltaMovement = thisObject.getDeltaMovement();
            if (deltaMovement.y < -0.75) deltaMovement = deltaMovement.lerp(new Vec3(deltaMovement.x, -0.75, deltaMovement.z), 0.5);
            thisObject.setDeltaMovement(deltaMovement);
        }
    }

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isSleeping()Z", shift =  At.Shift.BEFORE))
    protected void tick(CallbackInfo ci) {
        int curStaminaTicks = entityData.get(DATA_STAMINA_TICKS_ID);
        if (canRegenStamina) {
            AttributeInstance attributeInstance = this.getAttribute(ModAttributes.STAMINA_BONUS);
            int bonus = attributeInstance != null ? (int)attributeInstance.getValue() : 0;
            curStaminaTicks = Math.min(++curStaminaTicks, baseMaxStaminaTicks + bonus);
        }

        if (isHangGliding) {
            curStaminaTicks--;
            if (curStaminaTicks <= 0) curStaminaTicks = -20;
            hangGlideTicks++;
        }
        else hangGlideTicks = 0;
        entityData.set(DATA_STAMINA_TICKS_ID, curStaminaTicks);
    }

    @Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getBoundingBox()Lnet/minecraft/world/phys/AABB;", shift = At.Shift.BEFORE))
    protected void aiStep(CallbackInfo ci) {
        canHangGlideOrDiveOrFreefall();
        if (isHangGliding) updateHangGliding();
    }

    @Unique
    protected void updateHangGliding() {
        LivingEntity thisObject = (LivingEntity)(Object)this;
        if (!thisObject.level().isClientSide) {
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
    protected void canHangGlideOrDiveOrFreefall() {
        LivingEntity thisObject = (LivingEntity)(Object)this;

        canRegenStamina = thisObject.onGround() || thisObject.onClimbable() || thisObject.isPassenger() || thisObject.hasEffect(MobEffects.LEVITATION) || thisObject.hasEffect(MobEffects.SLOW_FALLING);

        if (!canRegenStamina && (isHangGliding || isDiving || isFreefalling || thisObject.fallDistance > minFallDistanceForFreefall)) {
            isHangGliding = false;
            isDiving = false;
            isFreefalling = false;
            for (EquipmentSlot equipmentSlot : EquipmentSlot.VALUES) {
                if (canHangGlideUsing(thisObject.getItemBySlot(equipmentSlot), equipmentSlot) && entityData.get(DATA_STAMINA_TICKS_ID) > 0) {
                    isHangGliding = true;
                    return;
                }
            }
            if (thisObject.isShiftKeyDown()) {
                isDiving = true;
                return;
            }
            isFreefalling = true;
            return;
        }
        isHangGliding = false;
        isDiving = false;
        isFreefalling = false;
    }

    @Unique
    private static boolean canHangGlideUsing(ItemStack itemStack, EquipmentSlot equipmentSlot) {
        if (itemStack.has(ModDataComponents.HANG_GLIDER)) {
            Equippable equippable = itemStack.get(DataComponents.EQUIPPABLE);
            return equippable != null && equipmentSlot == equippable.slot();
        }
        return false;
    }

    @ModifyReturnValue(method = "createLivingAttributes", at = @At("RETURN"))
    private static AttributeSupplier.Builder createLivingAttributes(AttributeSupplier.Builder original){
        return original.add(ModAttributes.STAMINA_BONUS);
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private static void defineSynchedData(CallbackInfo ci, @Local SynchedEntityData.Builder builder){
        builder.define(DATA_STAMINA_TICKS_ID, 400);
    }

    static {
        DATA_STAMINA_TICKS_ID = SynchedEntityData.defineId(LivingEntity.class, EntityDataSerializers.INT);
    }
}
