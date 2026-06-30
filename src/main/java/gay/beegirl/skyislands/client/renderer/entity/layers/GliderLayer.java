package gay.beegirl.skyislands.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.client.model.geom.ModModelLayers;
import gay.beegirl.skyislands.world.item.gliderdesign.GliderDesign;
import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import gay.beegirl.skyislands.core.component.ModDataComponents;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.util.HelperFunctions;
import gay.beegirl.skyislands.util.LivingEntityAccess;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class GliderLayer extends RenderLayer<LivingEntity, HumanoidModel<LivingEntity>> {
    public static final ResourceLocation FRAME_TEXTURE = SkysSkyIslands.createId("textures/entity/glider/frame.png");
    public static final ResourceLocation DEFAULT_CLOTH_TEXTURE = SkysSkyIslands.createId("textures/designs/entity/glider/default.png");
    //private final ModelPart frameRoot;
    //private final ModelPart clothRoot;
    private final ModelPart LEFT_FRAME;
    private final ModelPart RIGHT_FRAME;
    private final ModelPart LEFT_CLOTH;
    private final ModelPart RIGHT_CLOTH;
    private final ModelPart LEFT_CLOTH_STREAMER;
    private final ModelPart RIGHT_CLOTH_STREAMER;

    public GliderLayer(RenderLayerParent<LivingEntity, HumanoidModel<LivingEntity>> renderer, EntityModelSet modelSet) {
        super(renderer);
        ModelPart modelPartFrame = modelSet.bakeLayer(ModModelLayers.GLIDER);
        ModelPart modelPartCloth = modelSet.bakeLayer(ModModelLayers.GLIDER_CLOTH);
        //this.frameRoot = modelPartFrame.getChild("frame_root");
        //this.clothRoot = modelPartCloth.getChild("cloth_root");

        this.LEFT_FRAME = modelPartFrame.getChild("LEFT_FRAME");
        this.RIGHT_FRAME = modelPartFrame.getChild("RIGHT_FRAME");
        this.LEFT_CLOTH = modelPartCloth.getChild("LEFT_CLOTH");
        this.RIGHT_CLOTH = modelPartCloth.getChild("RIGHT_CLOTH");
        this.LEFT_CLOTH_STREAMER = modelPartCloth.getChild("LEFT_CLOTH_STREAMER");
        this.RIGHT_CLOTH_STREAMER = modelPartCloth.getChild("RIGHT_CLOTH_STREAMER");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        //PartDefinition franeRoot = partdefinition.addOrReplaceChild("frame_root", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 3.0F, 16.0F), PartPose.ZERO);
        //franeRoot.addOrReplaceChild("left_handle", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F), PartPose.ZERO);
        //franeRoot.addOrReplaceChild("right_handle", CubeListBuilder.create().texOffs(0, 0).addBox(5.5F, -8.0F, -4.0F, 2.0F, 2.0F, 8.0F), PartPose.ZERO);

        PartDefinition LEFT_FRAME = partdefinition.addOrReplaceChild("LEFT_FRAME", CubeListBuilder.create()
                .texOffs(0, 0).addBox(0.0F, 0.0F, -11.0F, 16.0F, 4.0F, 4.0F, CubeDeformation.NONE)
                .texOffs(0, 16).addBox(7.0F, 1.0F, -12.0F, 2.0F, 2.0F, 12.0F, CubeDeformation.NONE)
                .texOffs(32, 16).addBox(7.0F, 1.0F, 0.0F, 2.0F, 2.0F, 12.0F, CubeDeformation.NONE)
                .texOffs(0, 8).addBox(0.0F, 0.0F, 7.0F, 12.0F, 4.0F, 4.0F, CubeDeformation.NONE)
                .texOffs(34, 8).addBox(7.0F, 5.0F, -3.0F, 2.0F, 2.0F, 6.0F, CubeDeformation.NONE)
                .texOffs(32, 8).addBox(7.0F, 3.0F, -5.0F, 2.0F, 4.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(44, 8).addBox(7.0F, 3.0F, 3.0F, 2.0F, 4.0F, 2.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition RIGHT_FRAME = partdefinition.addOrReplaceChild("RIGHT_FRAME", CubeListBuilder.create()
                .texOffs(0, 32).addBox(-16.0F, 0.0F, -11.0F, 16.0F, 4.0F, 4.0F, CubeDeformation.NONE)
                .texOffs(0, 48).addBox(-9.0F, 1.0F, -12.0F, 2.0F, 2.0F, 12.0F, CubeDeformation.NONE)
                .texOffs(32, 48).addBox(-9.0F, 1.0F, 0.0F, 2.0F, 2.0F, 12.0F, CubeDeformation.NONE)
                .texOffs(0, 40).addBox(-12.0F, 0.0F, 7.0F, 12.0F, 4.0F, 4.0F, CubeDeformation.NONE)
                .texOffs(34, 40).addBox(-9.0F, 5.0F, -3.0F, 2.0F, 2.0F, 6.0F, CubeDeformation.NONE)
                .texOffs(32, 40).addBox(-9.0F, 3.0F, -5.0F, 2.0F, 4.0F, 2.0F, CubeDeformation.NONE)
                .texOffs(44, 40).addBox(-9.0F, 3.0F, 3.0F, 2.0F, 4.0F, 2.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createClothLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        //PartDefinition clothRootModel = partdefinition.addOrReplaceChild("cloth_root", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 3.0F, 16.0F), PartPose.ZERO);

        PartDefinition LEFT_CLOTH = partdefinition.addOrReplaceChild("LEFT_CLOTH", CubeListBuilder.create()
                .texOffs(-22, 0).addBox(0.0F, 0.0F, -11.0F, 16.0F, 0.0F, 22.0F, CubeDeformation.NONE)
                .texOffs(0, 48).addBox(1.0F, 0.0F, -11.0F, 14.0F, 2.0F, 0.0F, CubeDeformation.NONE)
                .texOffs(0, 50).addBox(1.0F, 0.0F, 11.0F, 10.0F, 2.0F, 0.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.1745F));

        PartDefinition LEFT_CLOTH_STREAMER = partdefinition.addOrReplaceChild("LEFT_CLOTH_STREAMER", CubeListBuilder.create()
                .texOffs(-16, 22).addBox(1.0F, 0.0F, 0.0F, 10.0F, 0.0F, 16.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition RIGHT_CLOTH = partdefinition.addOrReplaceChild("RIGHT_CLOTH", CubeListBuilder.create()
                .texOffs(10, 0).addBox(-16.0F, 0.0F, -11.0F, 16.0F, 0.0F, 22.0F, CubeDeformation.NONE)
                .texOffs(32, 48).addBox(-15.0F, 0.0F, -11.0F, 14.0F, 2.0F, 0.0F, CubeDeformation.NONE)
                .texOffs(32, 50).addBox(-11.0F, 0.0F, 11.0F, 10.0F, 2.0F, 0.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, 0.0F, -0.1745F));

        PartDefinition RIGHT_CLOTH_STREAMER = partdefinition.addOrReplaceChild("RIGHT_CLOTH_STREAMER", CubeListBuilder.create()
                .texOffs(16, 22).addBox(-11.0F, 0.0F, 0.0F, 10.0F, 0.0F, 16.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 0.0F, 11.0F, -0.1745F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, LivingEntity entity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (entity.getData(ModDataAttachments.IS_GLIDING)) {
            int glideTicks = ((LivingEntityAccess)entity).islands$getGlideTicks();
            Vec3 localMovement = HelperFunctions.rotate3dY(entity.getDeltaMovement(), entity.getYRot() * ((float)Math.PI / 180F));

            float zRadians = (glideTicks < 20 ?
                    (glideTicks < 6 ?
                     0.0F :
                            Mth.clamp(Mth.lerp((float) (glideTicks - 5) / 15, 0.0F, (float)(localMovement.x * 100)), -45, 45)) :
                    Mth.clamp((float)localMovement.x * 100, -45, 45))
                    * ((float) Math.PI / 180F);

            GliderDesign pattern = entity.getMainHandItem().is(ModItems.GLIDER) ? entity.getMainHandItem().get(ModDataComponents.SEWING_PATTERN) : entity.getOffhandItem().get(ModDataComponents.SEWING_PATTERN);
            ResourceLocation clothTexture = DEFAULT_CLOTH_TEXTURE;
            if (pattern != null) {
                ResourceLocation patternResourceLocation = pattern.assetId;
                clothTexture = ResourceLocation.fromNamespaceAndPath(patternResourceLocation.getNamespace(), "textures/designs/entity/glider/"+patternResourceLocation.getPath()+".png");
            }

            VertexConsumer frameVertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(FRAME_TEXTURE));
            VertexConsumer clothVertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(clothTexture));

            this.LEFT_FRAME.zRot = 0.8F * zRadians;
            this.LEFT_FRAME.x = -6 * zRadians;
            this.LEFT_FRAME.y = -32;
            this.RIGHT_FRAME.copyFrom(this.LEFT_FRAME);
            this.LEFT_CLOTH.copyFrom(this.LEFT_FRAME);
            this.RIGHT_CLOTH.copyFrom(this.LEFT_FRAME);
            this.LEFT_CLOTH_STREAMER.copyFrom(this.LEFT_FRAME);
            this.RIGHT_CLOTH_STREAMER.copyFrom(this.LEFT_FRAME);

            this.LEFT_FRAME.zRot += 0.1F;
            this.RIGHT_FRAME.zRot -= 0.1F;
            this.LEFT_CLOTH.zRot += 0.1F;
            this.RIGHT_CLOTH.zRot -= 0.1F;
            this.LEFT_CLOTH_STREAMER.zRot += 0.1F;
            this.LEFT_CLOTH_STREAMER.xRot = 100;
            this.RIGHT_CLOTH_STREAMER.zRot -= 0.1F;
            this.RIGHT_CLOTH_STREAMER.xRot = 100;

			this.LEFT_FRAME.render(poseStack, frameVertexconsumer, i, OverlayTexture.NO_OVERLAY);
            this.RIGHT_FRAME.render(poseStack, frameVertexconsumer, i, OverlayTexture.NO_OVERLAY);

            this.LEFT_CLOTH.render(poseStack, clothVertexconsumer, i, OverlayTexture.NO_OVERLAY);
            this.RIGHT_CLOTH.render(poseStack, clothVertexconsumer, i, OverlayTexture.NO_OVERLAY);
            this.LEFT_CLOTH_STREAMER.render(poseStack, clothVertexconsumer, i, OverlayTexture.NO_OVERLAY);
            this.RIGHT_CLOTH_STREAMER.render(poseStack, clothVertexconsumer, i, OverlayTexture.NO_OVERLAY);
        }
    }
}
