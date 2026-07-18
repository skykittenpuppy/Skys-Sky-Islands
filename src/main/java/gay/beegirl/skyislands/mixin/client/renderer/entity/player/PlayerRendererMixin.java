package gay.beegirl.skyislands.mixin.client.renderer.entity.player;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.zigythebird.playeranim.animation.PlayerAnimationController;
import com.zigythebird.playeranim.animation.PlayerRawAnimationBuilder;
import com.zigythebird.playeranim.api.PlayerAnimationAccess;
import com.zigythebird.playeranimcore.animation.Animation;
import com.zigythebird.playeranimcore.animation.ExtraAnimationData;
import com.zigythebird.playeranimcore.animation.layered.PlayerAnimationFrame;
import com.zigythebird.playeranimcore.animation.layered.modifier.AbstractFadeModifier;
import com.zigythebird.playeranimcore.animation.layered.modifier.AbstractModifier;
import com.zigythebird.playeranimcore.easing.EasingType;
import com.zigythebird.playeranimcore.enums.FadeType;
import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.client.model.ModAnimationLayers;
import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import gay.beegirl.skyislands.util.LivingEntityAccess;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Vector;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    @Shadow
    protected abstract void renderHand(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, AbstractClientPlayer player, ModelPart rendererArm, ModelPart rendererArmwear);

    public PlayerRendererMixin(EntityRendererProvider.Context context, PlayerModel<AbstractClientPlayer> model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    /*@Inject(method = "<init>", at = @At("TAIL"))
    private void islands$init(EntityRendererProvider.Context context, boolean useSlimModel, CallbackInfo ci) {
        this.addLayer(new GliderLayer(this, context.getModelSet()));
    }

    @Inject(method ="renderRightHand", at = @At("HEAD"), cancellable = true)
    private void islands$renderFirstPersonHandWhileGliding(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, AbstractClientPlayer player, CallbackInfo ci) {
        if (player.getData(ModDataAttachments.IS_GLIDING)) {
            this.renderHand(poseStack, buffer, combinedLight, player, ((PlayerModel)this.model).leftArm, ((PlayerModel)this.model).leftSleeve);
            ci.cancel();
        }
    }*/

    @WrapOperation(method = "setupRotations(Lnet/minecraft/client/player/AbstractClientPlayer;Lcom/mojang/blaze3d/vertex/PoseStack;FFFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;setupRotations(Lnet/minecraft/world/entity/LivingEntity;Lcom/mojang/blaze3d/vertex/PoseStack;FFFF)V", ordinal = 2))
    private void islands$setupRotations(PlayerRenderer instance, LivingEntity entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale, Operation<Void> original) {
        if (entity instanceof AbstractClientPlayer player) {
            PoseStack.Pose last = poseStack.last();
			Vector3f lastTranslation = last.pose().getTranslation(new Vector3f());
            Vector3f newTranslation = lastTranslation;

            // TODO: Stuff like this should interpolate between states
            if (player.getData(ModDataAttachments.IS_FREEFALLING)) {
                original.call(instance, player, poseStack, bob, yBodyRot, partialTick, scale);
                float f2 = (float)((LivingEntityAccess)entity).islands$getFreeFallTicks() + partialTick;
                float f3 = Mth.clamp(f2 * f2 / 100.0F, 0.0F, 1.0F);
                float f4 = Mth.clamp(f2 * f2 / 100.0F, 0.0F, 1.0F);
                if (!player.isAutoSpinAttack()) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(f3 * -90.0F));
                    newTranslation.add(0f, -f4, 0f);
                    //poseStack.translate(0F, -f4, 0F); // TODO: Move player up a tad while free falling
                }

                Vec3 vec3 = player.getViewVector(partialTick);
                Vec3 vec31 = player.getDeltaMovementLerped(partialTick);
                double d0 = vec31.horizontalDistanceSqr();
                double d1 = vec3.horizontalDistanceSqr();
                if (d0 > (double)0.0F && d1 > (double)0.0F) {
                    double d2 = (vec31.x * vec3.x + vec31.z * vec3.z) / Math.sqrt(d0 * d1);
                    double d3 = vec31.x * vec3.z - vec31.z * vec3.x;
                    poseStack.mulPose(Axis.YP.rotation((float)(Math.signum(d3) * Math.acos(d2))));
                }
            } else if (player.getData(ModDataAttachments.IS_DIVING)) {
                original.call(instance, player, poseStack, bob, yBodyRot, partialTick, scale);
                float f2 = (float)((LivingEntityAccess)entity).islands$getDiveTicks() + partialTick;
                float f3 = Mth.clamp(f2 * f2 / 100.0F, 0.0F, 1.0F);
                float f4 = Mth.clamp(f2 * f2 / 100.0F, 0.0F, 1.0F);
                float f5 = Mth.clamp(f2 * f2 / 25.0F, 0.0F, 0.25F);
                if (!player.isAutoSpinAttack()) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(f3 * -180.0F));
                    newTranslation.add(0f, -f4, f5);
                    //poseStack.translate(0F, -f4, f5);
                }

                Vec3 vec3 = player.getViewVector(partialTick);
                Vec3 vec31 = player.getDeltaMovementLerped(partialTick);
                double d0 = vec31.horizontalDistanceSqr();
                double d1 = vec3.horizontalDistanceSqr();
                if (d0 > (double)0.0F && d1 > (double)0.0F) {
                    double d2 = (vec31.x * vec3.x + vec31.z * vec3.z) / Math.sqrt(d0 * d1);
                    double d3 = vec31.x * vec3.z - vec31.z * vec3.x;
                    poseStack.mulPose(Axis.YP.rotation((float)(Math.signum(d3) * Math.acos(d2))));
                }
            }
            else original.call(instance, entity, poseStack, bob, yBodyRot, partialTick, scale);
            last.pose().setTranslation(lastTranslation.add(newTranslation).mul(0.5f));
        } else original.call(instance, entity, poseStack, bob, yBodyRot, partialTick, scale);
    }
}
