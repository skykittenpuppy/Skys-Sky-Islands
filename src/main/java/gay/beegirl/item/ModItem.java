package gay.beegirl.item;

import gay.beegirl.SkysSkyIslands;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Function;


public class ModItem {
    public static final Item RAW_ZONAITE = registerItem("raw_zonaite", properties -> new Item(properties));
    public static final Item REFINED_ZONAITE = registerItem("refined_zonaite", properties -> new Item(properties));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name);
        ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, resourceLocation);
        Item item = function.apply(new Item.Properties().setId(resourceKey));
        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
    }

    public static  void registerModItems() {
        SkysSkyIslands.LOGGER.info("Registering Items for " + SkysSkyIslands.MOD_ID);
    }
}
