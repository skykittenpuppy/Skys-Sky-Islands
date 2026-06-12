package gay.beegirl.skyislands.item;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlot;
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

    public static final DeferredItem<Item> GLIDER = ITEMS.registerItem("glider", properties -> new Item(
            properties
                    .rarity(Rarity.RARE)
                    //.component(ModDataComponents.HANG_GLIDER, Unit.INSTANCE)
                    //.component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.MAINHAND).setDamageOnHurt(false).build())
                    //.repairable(ModTags.Items.GLIDER_REPAIR_MATERIALS)
                    .stacksTo(1)
    ));

    //public static final DeferredItem<Item> TESTING_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.registerItem("testing_armor_trim_smithing_template", properties -> SmithingTemplateItem.createArmorTrimTemplate(new Item.Properties()));
    public static final DeferredItem<Item> TESTING_GLIDER_PATTERN_SEWING_TEMPLATE = ITEMS.registerItem("testing_glider_pattern_sewing_template", properties -> new SewingTemplateItem(properties.rarity(Rarity.UNCOMMON)));

    //public static final Item GOLDENLEAF_BOAT = ITEMS.registerItem("goldenleaf_boat", properties -> new BoatItem(ModEntityTypes.GOLDENLEAF_BOAT, properties.stacksTo(1)));
    //public static final Item GOLDENLEAF_CHEST_BOAT = ITEMS.registerItem("goldenleaf_chest_boat", properties -> new BoatItem(ModEntityTypes.GOLDENLEAF_CHEST_BOAT, properties.stacksTo(1)));

    //public static final Item SAKURA_BOAT = ITEMS.registerItem("sakura_boat", properties -> new BoatItem(ModEntityTypes.SAKURA_BOAT, properties.stacksTo(1)));
    //public static final Item SAKURA_CHEST_BOAT = ITEMS.registerItem("sakura_chest_boat", properties -> new BoatItem(ModEntityTypes.SAKURA_CHEST_BOAT, properties.stacksTo(1)));

    //public static final Item FRIGID_BOAT = ITEMS.registerItem("frigid_boat", properties -> new BoatItem(ModEntityTypes.FRIGID_BOAT, properties.stacksTo(1)));
    //public static final Item FRIGID_CHEST_BOAT = ITEMS.registerItem("frigid_chest_boat", properties -> new BoatItem(ModEntityTypes.FRIGID_CHEST_BOAT, properties.stacksTo(1)));

    //public static final Item ARBOREAL_CACTUS_BOAT = ITEMS.registerItem("arboreal_cactus_boat", properties -> new BoatItem(ModEntityTypes.ARBOREAL_CACTUS_BOAT, properties.stacksTo(1)));
    //public static final Item ARBOREAL_CACTUS_CHEST_BOAT = ITEMS.registerItem("arboreal_cactus_chest_boat", properties -> new BoatItem(ModEntityTypes.ARBOREAL_CACTUS_CHEST_BOAT, properties.stacksTo(1)));

    public static void registerItems(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Items for " + SkysSkyIslands.MOD_ID);

        ITEMS.register(modEventBus);
    }
}
