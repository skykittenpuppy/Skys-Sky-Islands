package gay.beegirl.skyislands.client;

import com.google.common.collect.Sets;
import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.Set;

@EventBusSubscriber(modid = SkysSkyIslands.MOD_ID)
public class ModModelLayers {
    private static final String DEFAULT_LAYER = "main";
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();
    public static final ModelLayerLocation GLIDER = register("gider");

    private static ModelLayerLocation register(String path) {
        return register(path, "main");
    }

    private static ModelLayerLocation register(String path, String model) {
        ModelLayerLocation modellayerlocation = createLocation(path, model);
        if (!ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + String.valueOf(modellayerlocation));
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(SkysSkyIslands.createId(path), model);
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Model Layers for " + SkysSkyIslands.MOD_ID);
        modEventBus.registerLayerDefinition(GLIDER, GliderLayer::createLayer);
    }
}
