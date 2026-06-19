package gay.beegirl.skyislands.tags;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
	public static final TagKey<Item> GLIDER_REPAIR_MATERIALS = bind("glider_repair_materials");
	public static final TagKey<Item> GOLDENLEAF_LOGS = bind("goldenleaf_logs");
	public static final TagKey<Item> SAKURA_LOGS = bind("sakura_logs");
	public static final TagKey<Item> FRIGID_LOGS = bind("frigid_logs");
	public static final TagKey<Item> ARBOREAL_CACTUSES = bind("arboreal_cactuses");

	private static TagKey<Item> bind(String name) {
		return TagKey.create(Registries.ITEM, SkysSkyIslands.createId(name));
	}
}
