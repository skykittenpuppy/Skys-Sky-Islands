package gay.beegirl.skyislands.mixin.client;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.entity.ModDataAttachments;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
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
        this.body.zRot = 0;
        this.leftLeg.x = 2;
        this.rightLeg.x = -2;

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

            Vec3 world = entity.getDeltaMovement();
            //float facing = -entity.getYRot();
            //float sin = Mth.sin(facing * ((float)Math.PI / 180F));
            //float cos = Mth.cos(facing * ((float)Math.PI / 180F));
            //double newX = world.x * cos - world.z * sin;
            //double newZ = world.x * sin + world.z * cos;
            //Vec3 local = new Vec3(newX, world.y, newZ);
            //Minecraft.getInstance().player.displayClientMessage(Component.literal(world + " : " + local + " : " + facing), true);


            //float zValue = (float) (local.z * 100.0F);
            //float zValue = SkysSkyIslands.rotate2d(new Vec2((float) world.x, (float) world.z), -entity.getYRot() * ((float)Math.PI / 180F)).y * 100;
            Vec3 localMovement = SkysSkyIslands.rotate3dY(world, entity.getYRot() * ((float)Math.PI / 180F));
            //float xValue = entity.getXRot() / 2; // Value between -90 and 90
            float xRadians = (float) (Mth.clamp(localMovement.z * 100, -45, 45) * (float)Math.PI / 180F);
            float zRadians = (float) (Mth.clamp(localMovement.x * 100, -45, 45) * (float)Math.PI / 180F);

            this.body.xRot = xRadians;
            this.body.zRot = zRadians;

            //this.leftArm.xRot += (float) Mth.clamp(((zValue * 2) * ((float)Math.PI / 180F)) * 0.167, -90, 90);
            //this.leftArm.y = Mth.sin(((90-zValue) * ((float)Math.PI / 180F))) * 2;
            //this.leftArm.z = Mth.cos(((90-zValue) * ((float)Math.PI / 180F))) * 2;

            //Both
            this.leftLeg.xRot = xRadians * 2;
            this.leftLeg.zRot = zRadians * (zRadians < 0 ? 1.6f : 1.9f);
            Vec3 leftLegOffset = new Vec3(2, 12, 0);
            leftLegOffset = SkysSkyIslands.rotate3dX(leftLegOffset, xRadians);
            leftLegOffset = SkysSkyIslands.rotate3dZ(leftLegOffset, zRadians);
            this.leftLeg.x = (float) leftLegOffset.x;
            this.leftLeg.y = (float) leftLegOffset.y;
            this.leftLeg.z = (float) leftLegOffset.z;

            //Both
            this.rightLeg.xRot = xRadians * 2;
            this.rightLeg.zRot = zRadians * (zRadians < 0 ? 1.6f : 1.9f);
            Vec3 rightLegOffset = new Vec3(-2, 12, 0);
            rightLegOffset = SkysSkyIslands.rotate3dX(rightLegOffset, xRadians);
            rightLegOffset = SkysSkyIslands.rotate3dZ(rightLegOffset, zRadians);
            this.rightLeg.x = (float) rightLegOffset.x;
            this.rightLeg.y = (float) rightLegOffset.y;
            this.rightLeg.z = (float) rightLegOffset.z;

            this.leftPants.copyFrom(this.leftLeg);
            this.rightPants.copyFrom(this.rightLeg);
            this.leftSleeve.copyFrom(this.leftArm);
            this.rightSleeve.copyFrom(this.rightArm);
            this.jacket.copyFrom(this.body);
        }
    }
}
