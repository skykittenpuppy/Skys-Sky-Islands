package gay.beegirl.skyislands.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.entity.ModDataAttachments;
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

public class GliderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public static final ResourceLocation TEXTURE = SkysSkyIslands.createId("textures/entity/glider/frame.png");
    public static final ResourceLocation CLOTH_TEXTURE = SkysSkyIslands.createId("textures/entity/glider/default.png");
    private final ModelPart box;
    private final ModelPart clothRoot;

    public GliderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer, EntityModelSet modelSet) {
        super(renderer);
        ModelPart modelpartFrame = modelSet.bakeLayer(ModModelLayers.GLIDER);
        ModelPart modelpartCloth = modelSet.bakeLayer(ModModelLayers.GLIDER_CLOTH);
        this.box = modelpartFrame.getChild("box");
        this.clothRoot = modelpartCloth.getChild("cloth_root");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("box", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 3.0F, 16.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createClothLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        /*PartDefinition clothRootModel = */partdefinition.addOrReplaceChild("cloth_root", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -13.0F, -8.0F, 16.0F, 3.0F, 16.0F), PartPose.ZERO);
        //boxModel.addOrReplaceChild("box", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 3.0F, 16.0F), PartPose.ZERO);
        //boxModel.addOrReplaceChild("box", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -16.0F, -8.0F, 16.0F, 3.0F, 16.0F), PartPose.ZERO);
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer abstractClientPlayer, float v, float v1, float v2, float v3, float v4, float v5) {
        if (abstractClientPlayer.getData(ModDataAttachments.IS_GLIDING)) {
            VertexConsumer hvertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
            this.box.render(poseStack, hvertexconsumer, i, OverlayTexture.NO_OVERLAY);
            VertexConsumer cvertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(CLOTH_TEXTURE));
            this.clothRoot.render(poseStack, cvertexconsumer, i, OverlayTexture.NO_OVERLAY);
        }
    }
}
