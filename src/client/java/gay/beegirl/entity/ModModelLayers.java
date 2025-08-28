package gay.beegirl.entity;

import gay.beegirl.SkysSkyIslands;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation GOLDENLEAF_BOAT = register("boat/goldenleaf");
    public static final ModelLayerLocation GOLDENLEAF_CHEST_BOAT = register("chest_boat/goldenleaf");
    public static final ModelLayerLocation SAKURA_BOAT = register("boat/sakura");
    public static final ModelLayerLocation SAKURA_CHEST_BOAT = register("chest_boat/sakura");
    public static final ModelLayerLocation FRIGID_BOAT = register("boat/frigid");
    public static final ModelLayerLocation FRIGID_CHEST_BOAT = register("chest_boat/frigid");
    public static final ModelLayerLocation ARBOREAL_CACTUS_BOAT = register("boat/arboreal_cactus");
    public static final ModelLayerLocation ARBOREAL_CACTUS_CHEST_BOAT = register("chest_boat/arboreal_cactus");

    private static ModelLayerLocation register(String string) {
        return new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, string), "main");
    }

    public static void registerModModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GOLDENLEAF_BOAT, BoatModel::createBoatModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.GOLDENLEAF_CHEST_BOAT, BoatModel::createChestBoatModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SAKURA_BOAT, BoatModel::createBoatModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SAKURA_CHEST_BOAT, BoatModel::createChestBoatModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FRIGID_BOAT, BoatModel::createBoatModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FRIGID_CHEST_BOAT, BoatModel::createChestBoatModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ARBOREAL_CACTUS_BOAT, BoatModel::createBoatModel);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ARBOREAL_CACTUS_CHEST_BOAT, BoatModel::createChestBoatModel);
    }
}
