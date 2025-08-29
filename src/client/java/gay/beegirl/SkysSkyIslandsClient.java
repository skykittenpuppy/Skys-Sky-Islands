package gay.beegirl;

import gay.beegirl.block.ModBlock;
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

        BlockRenderLayerMap.putBlock(ModBlock.POINTED_CLOUDSHALE, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.GOLDENLEAF_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.GOLDENLEAF_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.POTTED_GOLDENLEAF_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.SAKURA_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.SAKURA_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.POTTED_SAKURA_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.FRIGID_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.FRIGID_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlock.POTTED_FRIGID_SAPLING, ChunkSectionLayer.CUTOUT);
	}
}