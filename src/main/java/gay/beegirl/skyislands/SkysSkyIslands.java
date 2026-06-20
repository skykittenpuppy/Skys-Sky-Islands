package gay.beegirl.skyislands;

import gay.beegirl.skyislands.client.particle.SakuraParticle;
import gay.beegirl.skyislands.client.renderer.entity.layers.GliderLayer;
import gay.beegirl.skyislands.client.model.geom.ModModelLayers;
import gay.beegirl.skyislands.client.renderer.item.ModItemProperties;
import gay.beegirl.skyislands.world.item.gliderthing.GliderThing;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import gay.beegirl.skyislands.world.item.gliderthing.ThingPattern;
import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import gay.beegirl.skyislands.world.item.ModCreativeModeTabs;
import gay.beegirl.skyislands.core.component.ModDataComponents;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.core.particles.ModParticleTypes;
import gay.beegirl.skyislands.world.item.crafting.ModRecipeSerializer;
import gay.beegirl.skyislands.core.registries.ModRegistries;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
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
        ModRecipeSerializer.registerRecipeSerializers(modEventBus);

        //modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public static ResourceLocation createId(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    @EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
    public static class ModEvents {
        @SubscribeEvent
        public static void addRegistries(DataPackRegistryEvent.NewRegistry event) {
            event.dataPackRegistry(ModRegistries.GLIDER_DESIGN, ThingPattern.DIRECT_CODEC, ThingPattern.DIRECT_CODEC);
        }

        @SubscribeEvent
        static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> ItemProperties.register(ModItems.GLIDER.get(), ModItemProperties.THING_TYPE, (stack, level, entity, seed) -> {
				GliderThing gliderThing = stack.get(ModDataComponents.SEWING_PATTERN);
				return gliderThing != null ? gliderThing.design().value().assetId().hashCode() : Float.NEGATIVE_INFINITY;
			}));
        }

        @SubscribeEvent
        public static void registerParticleProviders(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(
                    ModParticleTypes.SAKURA_PETALS.get(),
                    spriteSet ->
                            (type, level, x, y, z, xSpeed, ySpeed, zSpeed) ->
                                    new SakuraParticle(level, x, y, z, spriteSet));
        }

        @SubscribeEvent
        public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModModelLayers.GLIDER, GliderLayer::createLayer);
            event.registerLayerDefinition(ModModelLayers.GLIDER_CLOTH, GliderLayer::createClothLayer);
        }

        @SubscribeEvent
        public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
            event.register((state, level, pos, tintIndex) ->
                            level != null && pos != null ? BiomeColors.getAverageGrassColor(level, pos) : GrassColor.getDefaultColor(),
                    ModBlocks.CLOUDSHALE_GRASS.get());
        }
    }
}
