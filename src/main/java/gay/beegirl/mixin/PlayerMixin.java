package gay.beegirl.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import gay.beegirl.SkysSkyIslands;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntityMixin{
    @Shadow
    private @Final Abilities abilities;

    protected void canHangGlideOrDiveOrFreefall() {
        super.canHangGlideOrDiveOrFreefall();
        if (this.abilities.flying) {
            this.isHangGliding = false;
            this.isDiving = false;
            this.isFreefalling = false;
        }
    }

    @ModifyExpressionValue(method = "getDesiredPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isFallFlying()Z"))
    private boolean getDesiredPose$isFallFlyingOrHangGlidingOrDivingOrFreefalling(boolean original) {
        return original || this.isHangGliding || this.isDiving || this.isFreefalling;
    }
    @ModifyReturnValue(method = "getDesiredPose", at = @At("RETURN"))
    private Pose getDesiredPose$fallFlyingOrHangGlidingPose(Pose original){
        Player thisObject = (Player)(Object)this;
        if (thisObject.isFallFlying()) return original;
        else if (this.isHangGliding) return Pose.STANDING; //TODO: Custom poses?
        else if (this.isDiving) return Pose.SPIN_ATTACK;
        else if (this.isFreefalling) return Pose.SWIMMING;
        else return original;
    }

    @ModifyExpressionValue(method = "getBlockSpeedFactor", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isFallFlying()Z"))
    private boolean getBlockSpeedFactor$isFallFlyingOrHangGlidingOrDivingOrFreefalling(boolean original) {
        return original || this.isHangGliding || this.isDiving || this.isFreefalling;
    } //NOTE: ORs due to the entire value being negated and ANDed

    @ModifyExpressionValue(method = "getRopeHoldPosition", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;isFallFlying()Z"))
    private boolean getRopeHoldPosition$isFallFlyingOrHangGlidingOrDivingOrFreefalling(boolean original) {
        return original || this.isHangGliding || this.isDiving || this.isFreefalling;
    } //NOTE: ORs due to the entire value being negated and ANDed
}