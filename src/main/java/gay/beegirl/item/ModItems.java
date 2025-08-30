package gay.beegirl.item;

import gay.beegirl.SkysSkyIslands;
import gay.beegirl.component.ModDataComponents;
import gay.beegirl.entity.ModEntityTypes;
import gay.beegirl.registry.armor_trim.ModTrimMaterials;
import gay.beegirl.util.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.function.Function;

public class ModItems {
    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        ResourceKey<Item> resourceKey = ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name));
        Item item = function.apply(new Item.Properties().setId(resourceKey));
        return Registry.register(BuiltInRegistries.ITEM, resourceKey, item);
    }

    public static  void registerItems() {
        SkysSkyIslands.LOGGER.info("Registering Items for " + SkysSkyIslands.MOD_ID);
    }

    public static final Item RAW_ALEXANDRITE = registerItem("raw_alexandrite", properties -> new Item(properties));

    public static final Item ALEXANDRITE = registerItem("alexandrite", properties -> new Item(properties.trimMaterial(ModTrimMaterials.ALEXANDRITE)));

    public static final Item GLIDER = registerItem("glider", properties -> new Item(properties.rarity(Rarity.RARE).component(ModDataComponents.HANG_GLIDER, Unit.INSTANCE).repairable(ModTags.Items.GLIDER_REPAIR_MATERIALS).stacksTo(1)));

    public static final Item TESTING_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("testing_armor_trim_smithing_template", properties -> SmithingTemplateItem.createArmorTrimTemplate(properties.trimMaterial(ModTrimMaterials.ALEXANDRITE)));
    public static final Item TESTING_GLIDER_PATTERN_SEWING_TEMPLATE = registerItem("testing_glider_pattern_sewing_template", properties -> new SewingTemplateItem(properties.rarity(Rarity.UNCOMMON)));

    public static final Item GOLDENLEAF_BOAT = registerItem("goldenleaf_boat", properties -> new BoatItem(ModEntityTypes.GOLDENLEAF_BOAT, properties.stacksTo(1)));
    public static final Item GOLDENLEAF_CHEST_BOAT = registerItem("goldenleaf_chest_boat", properties -> new BoatItem(ModEntityTypes.GOLDENLEAF_CHEST_BOAT, properties.stacksTo(1)));

    public static final Item SAKURA_BOAT = registerItem("sakura_boat", properties -> new BoatItem(ModEntityTypes.SAKURA_BOAT, properties.stacksTo(1)));
    public static final Item SAKURA_CHEST_BOAT = registerItem("sakura_chest_boat", properties -> new BoatItem(ModEntityTypes.SAKURA_CHEST_BOAT, properties.stacksTo(1)));

    public static final Item FRIGID_BOAT = registerItem("frigid_boat", properties -> new BoatItem(ModEntityTypes.FRIGID_BOAT, properties.stacksTo(1)));
    public static final Item FRIGID_CHEST_BOAT = registerItem("frigid_chest_boat", properties -> new BoatItem(ModEntityTypes.FRIGID_CHEST_BOAT, properties.stacksTo(1)));

    public static final Item ARBOREAL_CACTUS_BOAT = registerItem("arboreal_cactus_boat", properties -> new BoatItem(ModEntityTypes.ARBOREAL_CACTUS_BOAT, properties.stacksTo(1)));
    public static final Item ARBOREAL_CACTUS_CHEST_BOAT = registerItem("arboreal_cactus_chest_boat", properties -> new BoatItem(ModEntityTypes.ARBOREAL_CACTUS_CHEST_BOAT, properties.stacksTo(1)));
}
