package gay.beegirl;

import gay.beegirl.block.ModBlocks;
import gay.beegirl.entity.ModModelLayers;
import gay.beegirl.particle.ModParticleTypes;
import gay.beegirl.particle.SakuraPetalParticle;
import gay.beegirl.render.PlayerStaminaHUD;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.dimension.DimensionType;

public class SkysSkyIslandsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ModModelLayers.registerModModelLayers();

        //TODO: LevelRenderer line 470 (Bookmarked)

        BlockRenderLayerMap.putBlock(ModBlocks.POINTED_CLOUDSHALE, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLDENLEAF_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.GOLDENLEAF_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_GOLDENLEAF_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SAKURA_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SAKURA_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_SAKURA_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.FRIGID_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.FRIGID_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.POTTED_FRIGID_SAPLING, ChunkSectionLayer.CUTOUT);

        HudElementRegistry.addLast(PlayerStaminaHUD.STAMINA_LAYER, PlayerStaminaHUD::render);

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.SAKURA_PETALS, SakuraPetalParticle.SakuraPetalProvider::new);
	}
}