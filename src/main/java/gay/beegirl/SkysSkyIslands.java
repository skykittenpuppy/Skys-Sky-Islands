package gay.beegirl;

import gay.beegirl.block.ModBlocks;
import gay.beegirl.component.ModDataComponents;
import gay.beegirl.entity.ModEntityTypes;
import gay.beegirl.item.ModCreativeModeTabs;
import gay.beegirl.item.ModItems;
import gay.beegirl.recipe.ModRecipeSerializers;
import gay.beegirl.recipe.ModRecipeTypes;
import gay.beegirl.registry.ModRegistries;
import gay.beegirl.registry.ModRegistryResourceKeys;
import gay.beegirl.worldgen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkysSkyIslands implements ModInitializer {
	public static final String MOD_ID = "sky-islands";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModBlocks.registerBlocks();
        ModDataComponents.registerDataComponents();
        ModEntityTypes.registerEntityTypes();
        ModCreativeModeTabs.registerCreativeModeTabs();
        ModItems.registerItems();
        ModRecipeSerializers.registerRecipeSerializers();
        ModRecipeTypes.registerRecipeTypes();
        ModRegistries.registerRegistries();
        ModRegistryResourceKeys.registerRegistryResourceKeys();
        ModWorldGeneration.registerWorldGeneration();
    }
}