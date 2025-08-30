package gay.beegirl;

import gay.beegirl.block.ModBlocks;
import gay.beegirl.entity.ModModelLayers;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;

public class SkysSkyIslandsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
        ModModelLayers.registerModModelLayers();

        //TODO: LevelRenderer line 470
        //TODO: DimensionType line 111

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
	}
}