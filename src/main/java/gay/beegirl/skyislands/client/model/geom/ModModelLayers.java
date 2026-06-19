package gay.beegirl.skyislands.client.model.geom;

import com.google.common.collect.Sets;
import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.client.model.geom.ModelLayerLocation;

import java.util.Set;

public class ModModelLayers {
    private static final String DEFAULT_LAYER = "main";
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();
    public static final ModelLayerLocation GLIDER = register("glider");
    public static final ModelLayerLocation GLIDER_CLOTH = register("glider", "cloth");

    private static ModelLayerLocation register(String path) {
        return register(path, DEFAULT_LAYER);
    }

    private static ModelLayerLocation register(String path, String model) {
        ModelLayerLocation modellayerlocation = createLocation(path, model);
        if (!ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }

    private static ModelLayerLocation createLocation(String path, String model) {
        return new ModelLayerLocation(SkysSkyIslands.createId(path), model);
    }
}
