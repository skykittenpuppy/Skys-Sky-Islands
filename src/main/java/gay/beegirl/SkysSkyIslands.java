package gay.beegirl;

import gay.beegirl.block.ModBlock;
import gay.beegirl.item.ModCreativeModeTab;
import gay.beegirl.item.ModItem;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
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

        StrippableBlockRegistry.register(ModBlock.GOLDENLEAF_LOG, ModBlock.STRIPPED_GOLDENLEAF_LOG);
        StrippableBlockRegistry.register(ModBlock.GOLDENLEAF_WOOD, ModBlock.STRIPPED_GOLDENLEAF_WOOD);
        StrippableBlockRegistry.register(ModBlock.SAKURA_LOG, ModBlock.STRIPPED_SAKURA_LOG);
        StrippableBlockRegistry.register(ModBlock.SAKURA_WOOD, ModBlock.STRIPPED_SAKURA_WOOD);
        StrippableBlockRegistry.register(ModBlock.FRIGID_LOG, ModBlock.STRIPPED_FRIGID_LOG);
        StrippableBlockRegistry.register(ModBlock.FRIGID_WOOD, ModBlock.STRIPPED_FRIGID_WOOD);
        StrippableBlockRegistry.register(ModBlock.ARBOREAL_CACTUS_STEM, ModBlock.STRIPPED_ARBOREAL_CACTUS_STEM);
        StrippableBlockRegistry.register(ModBlock.ARBOREAL_CACTUS_HYPHAE, ModBlock.STRIPPED_ARBOREAL_CACTUS_HYPHAE);
	}
}