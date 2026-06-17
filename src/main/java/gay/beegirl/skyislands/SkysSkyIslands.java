package gay.beegirl.skyislands;

import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.entity.ModDataAttachments;
import gay.beegirl.skyislands.item.ModCreativeModeTabs;
import gay.beegirl.skyislands.item.ModDataComponents;
import gay.beegirl.skyislands.item.ModItems;
import gay.beegirl.skyislands.particle.ModParticleTypes;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(SkysSkyIslands.MOD_ID)
public class SkysSkyIslands {
    public static final String MOD_ID = "skyislands";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SkysSkyIslands(IEventBus modEventBus, ModContainer modContainer) {
        ModDataAttachments.registerDataAttachments(modEventBus);
        ModDataComponents.registerDataComponents(modEventBus);
        ModBlocks.registerBlocks(modEventBus);
        ModItems.registerItems(modEventBus);
        ModCreativeModeTabs.registerCreativeModeTabs(modEventBus);
        ModParticleTypes.registerParticleTypes(modEventBus);

        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation createId(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
}
