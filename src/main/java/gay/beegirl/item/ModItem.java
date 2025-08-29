package gay.beegirl.item;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.entity.ModEntityType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;

import java.util.function.Function;


public class ModItem {
    public static final Item RAW_ALEXANDRITE = registerItem("raw_alexandrite", properties -> new Item(properties));

    public static final Item ALEXANDRITE = registerItem("alexandrite", properties -> new Item(properties)); //TODO: trimMaterial()

    public static final Item GLIDER = registerItem("glider", properties -> new ModGliderItem(properties.stacksTo(1)));

    public static final Item GOLDENLEAF_BOAT = registerItem("goldenleaf_boat", properties -> new BoatItem(ModEntityType.GOLDENLEAF_BOAT, properties.stacksTo(1)));
    public static final Item GOLDENLEAF_CHEST_BOAT = registerItem("goldenleaf_chest_boat", properties -> new BoatItem(ModEntityType.GOLDENLEAF_CHEST_BOAT, properties.stacksTo(1)));

    public static final Item SAKURA_BOAT = registerItem("sakura_boat", properties -> new BoatItem(ModEntityType.SAKURA_BOAT, properties.stacksTo(1)));
    public static final Item SAKURA_CHEST_BOAT = registerItem("sakura_chest_boat", properties -> new BoatItem(ModEntityType.SAKURA_CHEST_BOAT, properties.stacksTo(1)));

    public static final Item FRIGID_BOAT = registerItem("frigid_boat", properties -> new BoatItem(ModEntityType.FRIGID_BOAT, properties.stacksTo(1)));
    public static final Item FRIGID_CHEST_BOAT = registerItem("frigid_chest_boat", properties -> new BoatItem(ModEntityType.FRIGID_CHEST_BOAT, properties.stacksTo(1)));

    public static final Item ARBOREAL_CACTUS_BOAT = registerItem("arboreal_cactus_boat", properties -> new BoatItem(ModEntityType.ARBOREAL_CACTUS_BOAT, properties.stacksTo(1)));
    public static final Item ARBOREAL_CACTUS_CHEST_BOAT = registerItem("arboreal_cactus_chest_boat", properties -> new BoatItem(ModEntityType.ARBOREAL_CACTUS_CHEST_BOAT, properties.stacksTo(1)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name);
        ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, resourceLocation);
        Item item = function.apply(new Item.Properties().setId(resourceKey));
        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
    }

    public static  void registerItems() {
        SkysSkyIslands.LOGGER.info("Registering Items for " + SkysSkyIslands.MOD_ID);
    }
}
