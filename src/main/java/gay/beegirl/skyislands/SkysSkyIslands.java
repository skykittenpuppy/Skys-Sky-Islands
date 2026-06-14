package gay.beegirl.skyislands;

import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.item.ModCreativeModeTabs;
import gay.beegirl.skyislands.item.ModItems;
import gay.beegirl.skyislands.particle.ModParticleTypes;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SkysSkyIslands.MOD_ID)
public class SkysSkyIslands {
    public static final String MOD_ID = "skyislands";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SkysSkyIslands(IEventBus modEventBus, ModContainer modContainer) {
        //modEventBus.addListener(this::commonSetup);

        ModBlocks.registerBlocks(modEventBus);
        ModItems.registerItems(modEventBus);
        ModCreativeModeTabs.registerCreativeModeTabs(modEventBus);
        ModParticleTypes.registerParticleTypes(modEventBus);

        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    public static ResourceLocation createId(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
}
