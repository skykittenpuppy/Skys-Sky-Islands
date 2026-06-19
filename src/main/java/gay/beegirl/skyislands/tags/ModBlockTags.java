package gay.beegirl.skyislands.tags;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
	public static final TagKey<Block> CLOUDSHALE_ORE_REPLACEABLE = create("cloudshale_ore_replaceable");
	public static final TagKey<Block> GOLDENLEAF_LOGS = create("goldenleaf_logs");
	public static final TagKey<Block> SAKURA_LOGS = create("sakura_logs");
	public static final TagKey<Block> FRIGID_LOGS = create("frigid_logs");
	public static final TagKey<Block> ARBOREAL_CACTUSES = create("arboreal_cactuses");

	public static TagKey<Block> create(String name) {
		return TagKey.create(Registries.BLOCK, SkysSkyIslands.createId(name));
	}
}
