package gay.beegirl;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModCreativeModeTab;
import gay.beegirl.item.ModItem;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkysSkyIslands implements ModInitializer {
	public static final String MOD_ID = "sky-islands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModBlock.registerModBlocks();
        ModCreativeModeTab.registerCreativeModeTabs();
        ModItem.registerModItems();
	}
}