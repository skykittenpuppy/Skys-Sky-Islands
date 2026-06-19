package gay.beegirl.skyislands.mixin.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import gay.beegirl.skyislands.client.renderer.entity.layers.GliderLayer;
import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    @Shadow public abstract @Final boolean addLayer(RenderLayer<T, M> layer);

    protected LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void islands$init(EntityRendererProvider.Context context, EntityModel model, float shadowRadius, CallbackInfo ci) {
        if (model instanceof HumanoidModel<?> humanoidModel) this.addLayer((RenderLayer<T, M>) new GliderLayer((RenderLayerParent<LivingEntity, HumanoidModel<LivingEntity>>) this, context.getModelSet()));
    }

    @ModifyArgs(method = "setupRotations", at = @At(value = "INVOKE", target = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;", ordinal = 0))
    private void islands$stopBodyJitter(Args args, T entity, PoseStack poseStack, float bob, float yBodyRot, float partialTick, float scale) {
        if (entity.getData(ModDataAttachments.IS_GLIDING)) {
            args.set(0, 180.0F - entity.getYHeadRot());
        }
    }
}
