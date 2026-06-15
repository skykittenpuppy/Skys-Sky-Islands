package gay.beegirl.skyislands.mixin.client;

import gay.beegirl.skyislands.entity.ModDataAttachments;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerModel.class)
public abstract class PlayerModelMixin<T extends LivingEntity> extends HumanoidModel<T> {
    @Shadow public @Final ModelPart leftSleeve;
    @Shadow public @Final ModelPart rightSleeve;
    @Shadow public @Final ModelPart leftPants;
    @Shadow public @Final ModelPart rightPants;
    @Shadow public @Final ModelPart jacket;
    public PlayerModelMixin(ModelPart root) {
        super(root);
    }

    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
    private void islands$setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (entity.getData(ModDataAttachments.IS_DIVING)) { // Diving anim
            this.head.xRot = (-(float)Math.PI / 4F);

            //this.leftArm.zRot = -105F * ((float)Math.PI / 180F);
            //this.leftArm.yRot = -180F * ((float)Math.PI / 180F);

            this.leftLeg.xRot = 5F * ((float)Math.PI / 180F);
            this.leftLeg.zRot = -5F * ((float)Math.PI / 180F);
            this.rightLeg.xRot = 5F * ((float)Math.PI / 180F);
            this.rightLeg.zRot = 5F * ((float)Math.PI / 180F);

            this.leftPants.copyFrom(this.leftLeg);
            this.rightPants.copyFrom(this.rightLeg);
            this.leftSleeve.copyFrom(this.leftArm);
            this.rightSleeve.copyFrom(this.rightArm);
        } else if (entity.getData(ModDataAttachments.IS_FREEFALLING)) { // Free fall anim
            this.head.xRot = (-(float)Math.PI / 4F);

            this.leftArm.xRot = 175F * ((float)Math.PI / 180F);
            this.leftArm.zRot = 65F * ((float)Math.PI / 180F);
            //this.leftArm.yRot = -180F * ((float)Math.PI / 180F);
            this.rightArm.xRot = 175F * ((float)Math.PI / 180F);
            this.rightArm.zRot = -65F * ((float)Math.PI / 180F);

            this.leftLeg.xRot = 5F * ((float)Math.PI / 180F);
            this.leftLeg.zRot = -5F * ((float)Math.PI / 180F);
            this.rightLeg.xRot = 5F * ((float)Math.PI / 180F);
            this.rightLeg.zRot = 5F * ((float)Math.PI / 180F);

            this.leftPants.copyFrom(this.leftLeg);
            this.rightPants.copyFrom(this.rightLeg);
            this.leftSleeve.copyFrom(this.leftArm);
            this.rightSleeve.copyFrom(this.rightArm);
        } else if (entity.getData(ModDataAttachments.IS_GLIDING)) { // Gliding anim
            //this.head.xRot = (-(float)Math.PI / 4F);

            this.leftArm.xRot = 180F * ((float)Math.PI / 180F);
            this.rightArm.xRot = 180F * ((float)Math.PI / 180F);
            this.leftArm.yRot = 0F * ((float)Math.PI / 180F);
            this.rightArm.yRot = 0F * ((float)Math.PI / 180F);
            this.leftArm.zRot = 0F * ((float)Math.PI / 180F);
            this.rightArm.zRot = 0F * ((float)Math.PI / 180F);
            //this.leftArm.yRot = -180F * ((float)Math.PI / 180F);

            this.body.xRot = entity.getSpeed();

            //this.leftLeg.xRot = 5F * ((float)Math.PI / 180F);
            //this.leftLeg.zRot = -5F * ((float)Math.PI / 180F);
            //this.rightLeg.xRot = 5F * ((float)Math.PI / 180F);
            //this.rightLeg.zRot = 5F * ((float)Math.PI / 180F);

            this.leftPants.copyFrom(this.leftLeg);
            this.rightPants.copyFrom(this.rightLeg);
            this.leftSleeve.copyFrom(this.leftArm);
            this.rightSleeve.copyFrom(this.rightArm);
            this.jacket.copyFrom(this.body);
        }
    }
}
