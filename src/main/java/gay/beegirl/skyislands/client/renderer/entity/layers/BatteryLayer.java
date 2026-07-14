package gay.beegirl.skyislands.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.client.model.geom.ModModelLayers;
import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BatteryLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public static final ResourceLocation TEXTURE = SkysSkyIslands.createId("textures/entity/player/battery.png");
    private final ModelPart BATTERY;

    public BatteryLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer, EntityModelSet modelSet) {
        super(renderer);
        ModelPart modelPartFrame = modelSet.bakeLayer(ModModelLayers.BATTERY);

        this.BATTERY = modelPartFrame.getChild("battery");
    }

    public static LayerDefinition createLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild("battery", CubeListBuilder.create()
                .texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, CubeDeformation.NONE),
                PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, 0.0F, -0.04363323130F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, AbstractClientPlayer entity, float v, float v1, float v2, float v3, float v4, float v5) {
        if (entity.getData(ModDataAttachments.BATTERY_COUNT) > 0) {
            VertexConsumer frameVertexconsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
			this.BATTERY.render(poseStack, frameVertexconsumer, i, OverlayTexture.NO_OVERLAY);
        }
    }
}
