package gay.beegirl.skyislands.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.entity.ModDataAttachments;
import gay.beegirl.skyislands.item.ModDataComponents;
import gay.beegirl.skyislands.item.ModItems;
import gay.beegirl.skyislands.util.HelperFunctions;
import gay.beegirl.skyislands.util.LivingEntityAccess;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class GliderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public static final ResourceLocation TEXTURE = SkysSkyIslands.createId("textures/entity/glider/frame.png");
    public static final ResourceLocation CLOTH_TEXTURE = SkysSkyIslands.createId("textures/entity/glider/default.png");
    private final ModelPart frameRoot;
    private final ModelPart clothRoot;

    public GliderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer, EntityModelSet modelSet) {
        super(renderer);
        ModelPart modelpartFrame = modelSet.bakeLayer(ModModelLayers.GLIDER);
        ModelPart modelpartCloth = modelSet.bakeLayer(ModModelLayers.GLIDER_CLOTH);
        this.frameRoot = modelpartFrame.getChild("frame_root");
        this.clothRoot = modelpartCloth.getChild("cloth_root");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition franeRoot = partdefinition.addOrReplaceChild("frame_root", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 3.0F, 16.0F), PartPose.ZERO);
        franeRoot.addOrReplaceChild("left_handle", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F), PartPose.ZERO);
        franeRoot.addOrReplaceChild("right_handle", CubeListBuilder.create().texOffs(0, 0).addBox(5.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createClothLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition clothRootModel = partdefinition.addOrReplaceChild("cloth_root", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 3.0F, 16.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer entity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (entity.getData(ModDataAttachments.IS_GLIDING)) {
            int glideTicks = ((LivingEntityAccess)entity).islands$getGlideTicks();
            Vec3 localMovement = HelperFunctions.rotate3dY(entity.getDeltaMovement(), entity.getYRot() * ((float)Math.PI / 180F));

            float zRadians = (glideTicks < 20 ?
                    (glideTicks < 6 ?
                     0.0F :
                            Mth.clamp(Mth.lerp((float) (glideTicks - 5) / 15, 0.0F, (float)(localMovement.x * 100)), -45, 45)) :
                    Mth.clamp((float)localMovement.x * 100, -45, 45))
                    * ((float) Math.PI / 180F);

            VertexConsumer hvertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
            this.frameRoot.zRot = 0.8F * zRadians;
            this.frameRoot.x = -6 * zRadians;
			this.frameRoot.render(poseStack, hvertexconsumer, i, OverlayTexture.NO_OVERLAY);

            ResourceLocation clothTexture = entity.getMainHandItem().is(ModItems.GLIDER) ? entity.getMainHandItem().get(ModDataComponents.SEWING_PATTERN) : entity.getOffhandItem().get(ModDataComponents.SEWING_PATTERN);
			VertexConsumer cvertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(clothTexture != null ? ResourceLocation.fromNamespaceAndPath(clothTexture.getNamespace(), "textures/entity/glider/"+clothTexture.getPath()+".png") : CLOTH_TEXTURE));
            this.clothRoot.zRot = 0.8F * zRadians;
            this.clothRoot.x = -6 * zRadians;
			this.clothRoot.render(poseStack, cvertexconsumer, i, OverlayTexture.NO_OVERLAY);
        }
    }
}
