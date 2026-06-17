package gay.beegirl.skyislands.mixin.client;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.entity.ModDataAttachments;
import gay.beegirl.skyislands.util.HelperFunctions;
import gay.beegirl.skyislands.util.LivingEntityAccess;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

	@Unique
	private void islands$resetPose() {
		this.head.x =			0;
		this.head.y =			0;
		this.head.z =			0;

		this.body.x =			0;
		this.body.y =			0;
		this.body.z =			0;
		this.body.xRot =		0;
		this.body.yRot =		0;
		this.body.zRot =		0;

		this.leftArm.x =		5;
		this.leftArm.y =		2;
		this.leftArm.z =		0;
		this.leftArm.xRot =		0;
		this.leftArm.yRot =		0;
		this.leftArm.zRot =		0;

		this.rightArm.x =		-5;
		this.rightArm.y =		2;
		this.rightArm.z =		0;
		this.rightArm.xRot =	0;
		this.rightArm.yRot =	0;
		this.rightArm.zRot =	0;

		this.leftLeg.x =		2;
		this.leftLeg.y =		12;
		this.leftLeg.z =		0;
		this.leftLeg.xRot =		0;
		this.leftLeg.yRot =		0;
		this.leftLeg.zRot =		0;

		this.rightLeg.x =		-2;
		this.rightLeg.y =		12;
		this.rightLeg.z =		0;
		this.rightLeg.xRot =	0;
		this.rightLeg.yRot =	0;
		this.rightLeg.zRot =	0;
	}

	@Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At("TAIL"))
	private void islands$setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (entity.getData(ModDataAttachments.IS_DIVING)) { // Diving anim
			islands$resetPose();

			// Head
			this.head.xRot = (-(float)Math.PI / 4F);

			// Left Arm
			this.leftArm.xRot = 5F * ((float)Math.PI / 180F);
			this.leftArm.zRot = -5F * ((float)Math.PI / 180F);

			// Left Arm
			this.rightArm.xRot = 5F * ((float)Math.PI / 180F);
			this.rightArm.zRot = 5F * ((float)Math.PI / 180F);

			// Left Leg
			this.leftLeg.xRot = 5F * ((float)Math.PI / 180F);
			this.leftLeg.zRot = -5F * ((float)Math.PI / 180F);

			// Right Leg
			this.rightLeg.xRot = 5F * ((float)Math.PI / 180F);
			this.rightLeg.zRot = 5F * ((float)Math.PI / 180F);
		}
		else if (entity.getData(ModDataAttachments.IS_FREEFALLING)) { // Free fall anim
			islands$resetPose();

			// Head
			this.head.xRot = (-(float)Math.PI / 4F);

			// Left Arm
			this.leftArm.xRot = 175F * ((float)Math.PI / 180F);
			this.leftArm.zRot = 65F * ((float)Math.PI / 180F);

			// Right arm
			this.rightArm.xRot = 175F * ((float)Math.PI / 180F);
			this.rightArm.zRot = -65F * ((float)Math.PI / 180F);

			// Left Leg
			this.leftLeg.xRot = 5F * ((float)Math.PI / 180F);
			this.leftLeg.zRot = -5F * ((float)Math.PI / 180F);

			// Right Leg
			this.rightLeg.xRot = 5F * ((float)Math.PI / 180F);
			this.rightLeg.zRot = 5F * ((float)Math.PI / 180F);
		}
		else if (entity.getData(ModDataAttachments.IS_GLIDING)) { // Gliding anim
			islands$resetPose();

			int glideTicks = ((LivingEntityAccess)entity).islands$getGlideTicks();
			Vec3 localMovement = HelperFunctions.rotate3dY(entity.getDeltaMovement(), entity.getYRot() * ((float)Math.PI / 180F));

			float xRadians = (glideTicks < 21 ?
					(glideTicks < 6 ?
							Mth.clamp(-(glideTicks * 7), -45, 45) :
							Mth.clamp(Mth.lerp((float) (glideTicks - 5) / 15, -35.0F, (float)(localMovement.z * 100)), -45, 45)) :
					Mth.clamp((float)(localMovement.z * 100), -45, 45))
					* ((float) Math.PI / 180F);
			float zRadians = (glideTicks < 20 ?
					(glideTicks < 6 ?
					 0.0F :
							Mth.clamp(Mth.lerp((float) (glideTicks - 5) / 15, 0.0F, (float)(localMovement.x * 100)), -45, 45)) :
					Mth.clamp((float)localMovement.x * 100, -45, 45))
					* ((float) Math.PI / 180F);

			// Head
			this.head.yRot = 0F;

			// Body
			this.body.xRot = xRadians;
			this.body.yRot = 0.0F;
			this.body.zRot = zRadians;

			// Left Arm
			this.leftArm.xRot = ((float) Math.PI) ;//+ xRadians;
			this.leftArm.yRot = 0;
			this.leftArm.zRot = 10F * ((float)Math.PI / 180F);
			Vec3 leftArmOffset = new Vec3(5, 2, 0);
			leftArmOffset = HelperFunctions.rotate3dX(leftArmOffset, xRadians);
			leftArmOffset = HelperFunctions.rotate3dZ(leftArmOffset, zRadians);
			this.leftArm.x = (float) leftArmOffset.x;
			this.leftArm.y = (float) leftArmOffset.y;
			this.leftArm.z = (float) leftArmOffset.z;

			// Right Arm
			this.rightArm.xRot = ((float) Math.PI) ;//+ xRadians;
			this.rightArm.yRot = 0;
			this.rightArm.zRot = -10F * ((float)Math.PI / 180F);
			Vec3 rightArmOffset = new Vec3(-5, 2, 0);
			rightArmOffset = HelperFunctions.rotate3dX(rightArmOffset, xRadians);
			rightArmOffset = HelperFunctions.rotate3dZ(rightArmOffset, zRadians);
			this.rightArm.x = (float) rightArmOffset.x;
			this.rightArm.y = (float) rightArmOffset.y;
			this.rightArm.z = (float) rightArmOffset.z;

			// Left Leg
			this.leftLeg.xRot = xRadians * 2;
			this.leftLeg.zRot = zRadians * (zRadians < 0 ? 1.6f : 1.9f);
			Vec3 leftLegOffset = new Vec3(2, 12, 0);
			leftLegOffset = HelperFunctions.rotate3dX(leftLegOffset, xRadians);
			leftLegOffset = HelperFunctions.rotate3dZ(leftLegOffset, zRadians);
			this.leftLeg.x = (float) leftLegOffset.x;
			this.leftLeg.y = (float) leftLegOffset.y;
			this.leftLeg.z = (float) leftLegOffset.z;

			// Right Leg
			this.rightLeg.xRot = xRadians * 2;
			this.rightLeg.zRot = zRadians * (zRadians < 0 ? 1.6f : 1.9f);
			Vec3 rightLegOffset = new Vec3(-2, 12, 0);
			rightLegOffset = HelperFunctions.rotate3dX(rightLegOffset, xRadians);
			rightLegOffset = HelperFunctions.rotate3dZ(rightLegOffset, zRadians);
			this.rightLeg.x = (float) rightLegOffset.x;
			this.rightLeg.y = (float) rightLegOffset.y;
			this.rightLeg.z = (float) rightLegOffset.z;
		}

		// Sync Layers
		this.leftPants.copyFrom(this.leftLeg);
		this.rightPants.copyFrom(this.rightLeg);
		this.leftSleeve.copyFrom(this.leftArm);
		this.rightSleeve.copyFrom(this.rightArm);
		this.jacket.copyFrom(this.body);
	}
}
