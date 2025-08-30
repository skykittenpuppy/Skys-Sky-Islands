package gay.beegirl.mixin;

import gay.beegirl.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public class PlayerMixin extends EntityMixin{
    @Shadow
    @Final
    private Abilities abilities;

    @Unique
    private static final EntityDataAccessor<Byte> DATA_SKY_ISLAND_FLAGS_ID;
    @Unique
    private static final int FLAG_GLIDING = 0;
    @Unique
    private static final int FLAG_DIVING = 1;
    @Unique
    private static final int FLAG_FREEFALLING = 2;
    @Unique
    private static final float glideMinimumDistance = 0.0f;
    @Unique
    private static final float diveMinimumDistance = 5.0f;
    @Unique
    private static final float freefallMinimumDistance = 5.0f;

    @Unique
    public boolean isGliding() {
        return getSkyIslandFlag(FLAG_GLIDING);
    }
    @Unique
    public boolean isDiving() {
        return getSkyIslandFlag(FLAG_DIVING);
    }
    @Unique
    public boolean isFreefalling() {
        return getSkyIslandFlag(FLAG_FREEFALLING);
    }

    @Unique
    public void updateGliding() {
        Player thisObject = (Player)(Object)this;
        setSkyIslandFlag(FLAG_GLIDING,
                    !abilities.flying &&
                        !thisObject.isSpectator() &&
                        !thisObject.isPassenger() &&
                        !thisObject.isSwimming() &&
                        !thisObject.onGround() &&
                        thisObject.isHolding(ModItems.GLIDER) &&
                        thisObject.fallDistance > glideMinimumDistance
        );
        if (getSkyIslandFlag(FLAG_GLIDING)) {
            thisObject.fallDistance = Math.min(thisObject.fallDistance, 0.01f);
        }
    }
    @Unique
    public void updateDiving() {
        Player thisObject = (Player)(Object)this;
        setSkyIslandFlag(FLAG_DIVING,
                !abilities.flying &&
                        !thisObject.isSpectator() &&
                        !thisObject.isPassenger() &&
                        !thisObject.isSwimming() &&
                        !thisObject.onGround() &&
                        !this.isGliding() &&
                        thisObject.isShiftKeyDown() &&
                        thisObject.fallDistance > diveMinimumDistance
        );
    }
    @Unique
    public void updateFreefalling() {
        Player thisObject = (Player)(Object)this;
        setSkyIslandFlag(FLAG_FREEFALLING,
                !abilities.flying &&
                        !thisObject.isSpectator() &&
                        !thisObject.isPassenger() &&
                        !thisObject.isSwimming() &&
                        !thisObject.onGround() &&
                        !this.isGliding() &&
                        !this.isDiving() &&
                        thisObject.fallDistance > freefallMinimumDistance
        );
    }

    @Unique
    protected boolean getSkyIslandFlag(int i) {
        return (entityData.get(DATA_SKY_ISLAND_FLAGS_ID) & 1 << i) != 0;
    }
    @Unique
    protected void setSkyIslandFlag(int i, boolean bl) {
        byte b = entityData.get(DATA_SKY_ISLAND_FLAGS_ID);
        if (bl) {
            entityData.set(DATA_SKY_ISLAND_FLAGS_ID, (byte)(b | 1 << i));
        } else {
            entityData.set(DATA_SKY_ISLAND_FLAGS_ID, (byte)(b & ~(1 << i)));
        }
    }

    @Inject(method = "getDesiredPose", at = @At("HEAD"), cancellable = true)
    private void getDesiredPoseMixin(CallbackInfoReturnable<Pose> cir) {
        if (this.isGliding()) {
            cir.setReturnValue(Pose.CROUCHING);
        } else if (this.isDiving()) {
            cir.setReturnValue(Pose.SPIN_ATTACK);
        } else if (this.isFreefalling()) {
            cir.setReturnValue(Pose.SITTING);
        }
    }

    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    protected void defineSynchedDataMixin(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_SKY_ISLAND_FLAGS_ID, (byte) 0);
    }

    @Override
    protected void baseTickMixin(CallbackInfo cl) {
        this.updateGliding();
        this.updateDiving();
        this.updateFreefalling();
    }

    static {
        DATA_SKY_ISLAND_FLAGS_ID = SynchedEntityData.defineId(Player.class, EntityDataSerializers.BYTE);
    }
}
