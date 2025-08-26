package gay.beegirl.entity;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.item.ModItem;
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
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ModEntityType {
    public static final EntityType<Boat> GOLDENLEAF_BOAT  = registerEntityType("goldenleaf_boat", EntityType.Builder.of(boatFactory(() -> ModItem.GOLDENLEAF_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> GOLDENLEAF_CHEST_BOAT = registerEntityType("goldenleaf_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> ModItem.GOLDENLEAF_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<Boat> SAKURA_BOAT  = registerEntityType("sakura_boat", EntityType.Builder.of(boatFactory(() -> ModItem.SAKURA_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> SAKURA_CHEST_BOAT = registerEntityType("sakura_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> ModItem.SAKURA_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<Boat> FRIGID_BOAT  = registerEntityType("frigid_boat", EntityType.Builder.of(boatFactory(() -> ModItem.FRIGID_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> FRIGID_CHEST_BOAT = registerEntityType("frigid_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> ModItem.FRIGID_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<Boat> ARBOREAL_CACTUS_BOAT  = registerEntityType("arboreal_cactus_boat", EntityType.Builder.of(boatFactory(() -> ModItem.ARBOREAL_CACTUS_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));
    public static final EntityType<ChestBoat> ARBOREAL_CACTUS_CHEST_BOAT = registerEntityType("arboreal_cactus_chest_boat", EntityType.Builder.of(chestBoatFactory(() -> ModItem.ARBOREAL_CACTUS_CHEST_BOAT), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10));

    private static <T extends Entity> EntityType<T> registerEntityType(String name, EntityType.Builder<T> builder) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name);
        ResourceKey<EntityType<?>> resourceKey = ResourceKey.create(Registries.ENTITY_TYPE, resourceLocation);
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, resourceKey, builder.build(resourceKey));
    }

    private static EntityType.EntityFactory<Boat> boatFactory(Supplier<Item> supplier) {
        return (entityType, level) -> new Boat(entityType, level, supplier);
    }

    private static EntityType.EntityFactory<ChestBoat> chestBoatFactory(Supplier<Item> supplier) {
        return (entityType, level) -> new ChestBoat(entityType, level, supplier);
    }
}
