package gay.beegirl.skyislands.tags;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
	public static final TagKey<Item> ALEXANDRITE_ORES = create("alexandrite_ores");
	public static final TagKey<Item> GLIDER_REPAIR_MATERIALS = create("glider_repair_materials");
	public static final TagKey<Item> GOLDENLEAF_LOGS = create("goldenleaf_logs");
	public static final TagKey<Item> SAKURA_LOGS = create("sakura_logs");
	public static final TagKey<Item> FRIGID_LOGS = create("frigid_logs");
	public static final TagKey<Item> ARBOREAL_CACTUSES = create("arboreal_cactuses");

	public static TagKey<Item> create(String name) {
		return TagKey.create(Registries.ITEM, SkysSkyIslands.createId(name));
	}
}
