package gay.beegirl.item;

import gay.beegirl.SkysSkyIslands;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.function.Function;


public class ModItem {
    public static final Item ZONAITE = registerItem("zonaite", Item::new, new Item.Properties());
    public static final Item BLUE_ZONAITE = registerItem("blue_zonaite", Item::new, new Item.Properties());

    private static Item registerItem(String name, Function<Item.Properties, Item> function, Item.Properties properties) {
        ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        Item item = function.apply(properties.setId(resourceKey));
        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
    }

    public static  void registerModItems() {
        SkysSkyIslands.LOGGER.info("Registering Items for " + SkysSkyIslands.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(entries -> {
            entries.accept(ZONAITE);
            entries.accept(BLUE_ZONAITE);
        });
    }
}
