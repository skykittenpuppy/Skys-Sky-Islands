package gay.beegirl.skyislands.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import gay.beegirl.skyislands.entity.ModDataAttachments;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @WrapOperation(method = "updatePlayerPose", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;setPose(Lnet/minecraft/world/entity/Pose;)V", ordinal = 1))
    private void islands$setPose(Player instance, Pose pose, Operation<Void> original) {
        Pose pose1;
        if (instance.getData(ModDataAttachments.IS_FREEFALLING)) pose1 = Pose.SPIN_ATTACK;
        else if (instance.getData(ModDataAttachments.IS_DIVING)) pose1 = Pose.FALL_FLYING;
        else if (instance.getData(ModDataAttachments.IS_GLIDING)) pose1 = Pose.STANDING;
        else pose1 = pose;
        original.call(instance, pose1);
    }
}
