package gay.beegirl.mixin;

import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntityMixin{
    @Shadow
    private @Final Abilities abilities;

    @Unique
    private static final int FLAG_HANG_GLIDING = 0; //Protected
    @Unique
    private static final int FLAG_DIVING = 1; //Protected
    @Unique
    private static final int FLAG_FREEFALLING = 2; //Protected

    @Inject(method = "getDesiredPose", at = @At("HEAD"), cancellable = true)
    private void getDesiredPose(CallbackInfoReturnable<Pose> cir) {
        if (isHangGliding()) {
            cir.setReturnValue(Pose.FALL_FLYING); //TODO: Poses?
        }
    } //TODO: this feels janky

    protected boolean canHangGlide() {
        return !this.abilities.flying && super.canHangGlide();
    }

    @Unique
    public boolean tryToStartHangGliding() {
        Player thisObject = (Player)(Object)this;

        if (!isHangGliding() && canHangGlide() && !thisObject.isInWater()) {
            startHangGliding();
            return true;
        } else {
            return false;
        }
    }

    @Unique
    public void startHangGliding() {
        this.setSkyIslandFlag(FLAG_HANG_GLIDING, true);
    }

    @Inject(method = "getBlockSpeedFactor", at = @At("HEAD"), cancellable = true)
    protected void getBlockSpeedFactor(CallbackInfoReturnable<Float> cir) {
        if (isHangGliding()) {
            cir.setReturnValue(1.0F);
        }
    } //TODO: this feels janky

    //TODO: getRopeHoldPosition()
}
