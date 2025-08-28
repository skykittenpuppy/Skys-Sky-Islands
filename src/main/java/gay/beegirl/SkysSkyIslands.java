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

        StrippableBlockRegistry.register(ModBlock.GOLDENLEAF_PLANKS.log(), ModBlock.GOLDENLEAF_PLANKS.strippedLog());
        StrippableBlockRegistry.register(ModBlock.GOLDENLEAF_PLANKS.wood(), ModBlock.GOLDENLEAF_PLANKS.strippedWood());
        StrippableBlockRegistry.register(ModBlock.SAKURA_PLANKS.log(), ModBlock.SAKURA_PLANKS.strippedLog());
        StrippableBlockRegistry.register(ModBlock.SAKURA_PLANKS.wood(), ModBlock.SAKURA_PLANKS.strippedWood());
        StrippableBlockRegistry.register(ModBlock.FRIGID_PLANKS.log(), ModBlock.FRIGID_PLANKS.strippedLog());
        StrippableBlockRegistry.register(ModBlock.FRIGID_PLANKS.wood(), ModBlock.FRIGID_PLANKS.strippedWood());
        StrippableBlockRegistry.register(ModBlock.ARBOREAL_CACTUS_PLANKS.log(), ModBlock.ARBOREAL_CACTUS_PLANKS.strippedLog());
        StrippableBlockRegistry.register(ModBlock.ARBOREAL_CACTUS_PLANKS.wood(), ModBlock.ARBOREAL_CACTUS_PLANKS.strippedWood());
	}
}