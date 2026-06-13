package gay.beegirl.skyislands.item;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.entity.ModEntityTypes;
import gay.beegirl.skyislands.registry.armor_trim.ModArmorTrimPatterns;
import gay.beegirl.skyislands.registry.glider_pattern.ModGliderPatternDesigns;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SmithingTemplateItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SkysSkyIslands.MOD_ID);

    public static final DeferredItem<Item> RAW_ALEXANDRITE = ITEMS.registerSimpleItem("raw_alexandrite", new Item.Properties());
    public static final DeferredItem<Item> ALEXANDRITE = ITEMS.registerSimpleItem("alexandrite", new Item.Properties());

    public static final DeferredItem<Item> GLIDER = ITEMS.registerItem("glider", properties -> new GliderItem(
            properties
                    .rarity(Rarity.RARE)
                    //.component(ModDataComponents.HANG_GLIDER, Unit.INSTANCE)
                    //.component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.MAINHAND).setDamageOnHurt(false).build())
                    .stacksTo(1)
    ));

    public static final DeferredItem<Item> TESTING_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.registerItem("testing_armor_trim_smithing_template", properties -> SmithingTemplateItem.createArmorTrimTemplate(ModArmorTrimPatterns.TESTING));
    public static final DeferredItem<Item> TESTING_GLIDER_PATTERN_SEWING_TEMPLATE = ITEMS.registerItem("testing_glider_pattern_sewing_template", properties -> new SewingTemplateItem(ModGliderPatternDesigns.TESTING));

    public static final DeferredItem<Item> GOLDENLEAF_BOAT = ITEMS.registerItem("goldenleaf_boat", properties -> new Item(properties));//new BoatItem(false, ModEntityTypes.GOLDENLEAF_ENUM_PROXY.getValue(), properties.stacksTo(1)));
    public static final DeferredItem<Item> GOLDENLEAF_CHEST_BOAT = ITEMS.registerItem("goldenleaf_chest_boat", properties -> new Item(properties));//new BoatItem(true, ModEntityTypes.GOLDENLEAF_ENUM_PROXY.getValue(), properties.stacksTo(1)));

    public static final DeferredItem<Item> SAKURA_BOAT = ITEMS.registerItem("sakura_boat", properties -> new Item(properties));//new BoatItem(false, ModEntityTypes.SAKURA, properties.stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_CHEST_BOAT = ITEMS.registerItem("sakura_chest_boat", properties -> new Item(properties));//new BoatItem(true, ModEntityTypes.SAKURA, properties.stacksTo(1)));

    public static final DeferredItem<Item> FRIGID_BOAT = ITEMS.registerItem("frigid_boat", properties -> new Item(properties));//new BoatItem(false, ModEntityTypes.FRIGID, properties.stacksTo(1)));
    public static final DeferredItem<Item> FRIGID_CHEST_BOAT = ITEMS.registerItem("frigid_chest_boat", properties -> new Item(properties));//new BoatItem(true, ModEntityTypes.FRIGID, properties.stacksTo(1)));

    public static final DeferredItem<Item> ARBOREAL_CACTUS_BOAT = ITEMS.registerItem("arboreal_cactus_boat", properties -> new Item(properties));//new BoatItem(false, ModEntityTypes.ARBOREAL_CACTUS, properties.stacksTo(1)));
    public static final DeferredItem<Item> ARBOREAL_CACTUS_CHEST_BOAT = ITEMS.registerItem("arboreal_cactus_chest_boat", properties -> new Item(properties));//new BoatItem(true, ModEntityTypes.ARBOREAL_CACTUS, properties.stacksTo(1)));

    public static void registerItems(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Items for " + SkysSkyIslands.MOD_ID);

        ITEMS.register(modEventBus);
    }
}
