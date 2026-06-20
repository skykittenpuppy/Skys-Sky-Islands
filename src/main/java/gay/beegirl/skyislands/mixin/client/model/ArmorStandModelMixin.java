package gay.beegirl.skyislands.mixin.client.model;

import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import net.minecraft.client.model.ArmorStandArmorModel;
import net.minecraft.client.model.ArmorStandModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorStandModel.class)
public class ArmorStandModelMixin extends ArmorStandArmorModel {

	public ArmorStandModelMixin(ModelPart root) {
		super(root);
	}

	@Inject(method = "setupAnim(Lnet/minecraft/world/entity/decoration/ArmorStand;FFFFF)V", at = @At("TAIL"))
	private void islands$setupAnim(ArmorStand entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
		if (entity.getData(ModDataAttachments.IS_GLIDING)) { // Gliding anim
			// Left Arm
			this.leftArm.xRot = ((float) Math.PI);
			this.leftArm.yRot = 0;
			this.leftArm.zRot = 10F * ((float)Math.PI / 180F);

			// Right Arm
			this.rightArm.xRot = ((float) Math.PI);
			this.rightArm.yRot = 0;
			this.rightArm.zRot = -10F * ((float)Math.PI / 180F);
		}
	}
}
