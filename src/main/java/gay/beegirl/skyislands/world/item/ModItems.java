package gay.beegirl.skyislands.world.item;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.gliderdesign.ModGliderDesigns;
import gay.beegirl.skyislands.world.item.armortrim.ModTrimPatterns;
import gay.beegirl.skyislands.world.level.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
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

    public static final DeferredItem<Item> GLIDER = ITEMS.registerItem("glider", properties -> new GliderItem(properties.rarity(Rarity.RARE).stacksTo(1)));

    public static final DeferredItem<Item> TESTING_ARMOR_TRIM_SMITHING_TEMPLATE = ITEMS.registerItem("testing_armor_trim_smithing_template", properties -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.TESTING));
    public static final DeferredItem<Item> TESTING_GLIDER_DESIGN_SEWING_TEMPLATE = ITEMS.registerItem("testing_glider_design_sewing_template", properties -> new SewingTemplateItem(ModGliderDesigns.TESTING));
    public static final DeferredItem<Item> TESTING2_GLIDER_DESIGN_SEWING_TEMPLATE = ITEMS.registerItem("testing2_glider_design_sewing_template", properties -> new SewingTemplateItem(ModGliderDesigns.TESTING2));

    public static final DeferredItem<Item> GOLDENLEAF_BOAT = ITEMS.registerItem("goldenleaf_boat", properties -> new Item(properties));//new BoatItem(false, ModEntityTypes.GOLDENLEAF_ENUM_PROXY.getValue(), properties.stacksTo(1)));
    public static final DeferredItem<Item> GOLDENLEAF_CHEST_BOAT = ITEMS.registerItem("goldenleaf_chest_boat", properties -> new Item(properties));//new BoatItem(true, ModEntityTypes.GOLDENLEAF_ENUM_PROXY.getValue(), properties.stacksTo(1)));

    public static final DeferredItem<Item> SAKURA_BOAT = ITEMS.registerItem("sakura_boat", properties -> new Item(properties));//new BoatItem(false, ModEntityTypes.SAKURA, properties.stacksTo(1)));
    public static final DeferredItem<Item> SAKURA_CHEST_BOAT = ITEMS.registerItem("sakura_chest_boat", properties -> new Item(properties));//new BoatItem(true, ModEntityTypes.SAKURA, properties.stacksTo(1)));

    public static final DeferredItem<Item> FRIGID_BOAT = ITEMS.registerItem("frigid_boat", properties -> new Item(properties));//new BoatItem(false, ModEntityTypes.FRIGID, properties.stacksTo(1)));
    public static final DeferredItem<Item> FRIGID_CHEST_BOAT = ITEMS.registerItem("frigid_chest_boat", properties -> new Item(properties));//new BoatItem(true, ModEntityTypes.FRIGID, properties.stacksTo(1)));

    public static final DeferredItem<Item> ARBOREAL_CACTUS_BOAT = ITEMS.registerItem("arboreal_cactus_boat", properties -> new Item(properties));//new BoatItem(false, ModEntityTypes.ARBOREAL_CACTUS, properties.stacksTo(1)));
    public static final DeferredItem<Item> ARBOREAL_CACTUS_CHEST_BOAT = ITEMS.registerItem("arboreal_cactus_chest_boat", properties -> new Item(properties));//new BoatItem(true, ModEntityTypes.ARBOREAL_CACTUS, properties.stacksTo(1)));
    public static final DeferredItem<Item> ARBOREAL_CACTUS_FRUIT = ITEMS.registerItem("arboreal_cactus_fruit", properties -> new BlockItem(ModBlocks.ARBOREAL_CACTUS_PLANT.get(), properties));

    public static void registerItems(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Items for " + SkysSkyIslands.MOD_ID);

        ITEMS.register(modEventBus);
    }
}
