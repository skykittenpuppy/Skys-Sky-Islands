package gay.beegirl.skyislands;

import gay.beegirl.skyislands.block.ModBlocks;
import gay.beegirl.skyislands.entity.ModDataAttachments;
import gay.beegirl.skyislands.item.ModCreativeModeTabs;
import gay.beegirl.skyislands.item.ModItems;
import gay.beegirl.skyislands.particle.ModParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
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
        ModBlocks.registerBlocks(modEventBus);
        ModItems.registerItems(modEventBus);
        ModCreativeModeTabs.registerCreativeModeTabs(modEventBus);
        ModParticleTypes.registerParticleTypes(modEventBus);

        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation createId(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public static Vec3 rotate3dX(Vec3 original, float radians) {
        float sin = Mth.sin(radians);
        float cos = Mth.cos(radians);
        double newY = original.y * cos - original.z * sin;
        double newZ = original.y * sin + original.z * cos;
        return new Vec3(original.x, newY, newZ);
    }
    public static Vec3 rotate3dY(Vec3 original, float radians) {
        float sin = Mth.sin(radians);
        float cos = Mth.cos(radians);
        double newX = original.x * cos + original.z * sin;
        double newZ = -original.x * sin + original.z * cos;
        return new Vec3(newX, original.y, newZ);
    }
    public static Vec3 rotate3dZ(Vec3 original, float radians) {
        float sin = Mth.sin(radians);
        float cos = Mth.cos(radians);
        double newX = original.x * cos - original.y * sin;
        double newY = original.x * sin + original.y * cos;
        return new Vec3(newX, newY, original.z);
    }
}
