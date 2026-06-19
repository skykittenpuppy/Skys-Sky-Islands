package gay.beegirl.skyislands.tags;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;

public class ModBiomeTags {
	public static final TagKey<Biome> SKY_ISLANDS = create("sky_islands");

	private static TagKey<Biome> create(String name) {
		return TagKey.create(Registries.BIOME, SkysSkyIslands.createId(name));
	}
}
