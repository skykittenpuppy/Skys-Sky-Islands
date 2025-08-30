package gay.beegirl.entity;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;

public class ModEntityTypes {
    private static <T extends Entity> EntityType<T> registerEntityType(String name, EntityType.Builder<T> builder) {
        ResourceKey<EntityType<?>> resourceKey = ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(resourceKey));
    }

    public static void registerEntityTypes() {
        SkysSkyIslands.LOGGER.info("Registering Entity Types for " + SkysSkyIslands.MOD_ID);
    }

    public static final EntityType<Boat> GOLDENLEAF_BOAT  = registerEntityType("goldenleaf_boat", EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.GOLDENLEAF_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> GOLDENLEAF_CHEST_BOAT = registerEntityType("goldenleaf_chest_boat", EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.GOLDENLEAF_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<Boat> SAKURA_BOAT  = registerEntityType("sakura_boat", EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.SAKURA_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> SAKURA_CHEST_BOAT = registerEntityType("sakura_chest_boat", EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.SAKURA_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<Boat> FRIGID_BOAT  = registerEntityType("frigid_boat", EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.FRIGID_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> FRIGID_CHEST_BOAT = registerEntityType("frigid_chest_boat", EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.FRIGID_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<Boat> ARBOREAL_CACTUS_BOAT  = registerEntityType("arboreal_cactus_boat", EntityType.Builder.of(EntityType.boatFactory(() -> ModItems.ARBOREAL_CACTUS_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> ARBOREAL_CACTUS_CHEST_BOAT = registerEntityType("arboreal_cactus_chest_boat", EntityType.Builder.of(EntityType.chestBoatFactory(() -> ModItems.ARBOREAL_CACTUS_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
}
