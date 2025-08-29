package gay.beegirl;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModCreativeModeTab;
import gay.beegirl.item.ModItem;
import gay.beegirl.worldgen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkysSkyIslands implements ModInitializer {
	public static final String MOD_ID = "sky-islands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModBlock.registerBlocks();
        ModCreativeModeTab.registerCreativeModeTabs();
        ModItem.registerItems();
        ModWorldGeneration.registerWorldGeneration();

        FlammableBlockRegistry.getDefaultInstance().add(ModBlock.GOLDENLEAF_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlock.SAKURA_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlock.FRIGID_LEAVES, 30, 60);
    }
}