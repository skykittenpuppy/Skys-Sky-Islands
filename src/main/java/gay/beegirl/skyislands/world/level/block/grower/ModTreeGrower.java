package gay.beegirl.skyislands.world.level.block.grower;

import gay.beegirl.skyislands.data.worldgen.features.ModTreeFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public final class ModTreeGrower {
	//public static final TreeGrower GOLDENLEAF = new TreeGrower(
	//		"goldenleaf",
	//		Optional.empty(),
	//		Optional.of(ModTreeFeatures.GOLDENLEAF),
	//		Optional.of(ModTreeFeatures.GOLDENLEAF_BEES_005));
	public static final TreeGrower GOLDENLEAF = new TreeGrower(
			"goldenleaf",
			Optional.of(ModTreeFeatures.GOLDENLEAF),
			Optional.empty(),
			Optional.empty());
	public static final TreeGrower SAKURA = new TreeGrower(
			"sakura",
			Optional.empty(),
			Optional.of(ModTreeFeatures.SAKURA),
			Optional.of(ModTreeFeatures.SAKURA_BEES_005));
	public static final TreeGrower FRIGID = new TreeGrower(
			"frigid",
			0.5f,
			Optional.empty(),
			Optional.empty(),
			Optional.of(ModTreeFeatures.FRIGID_PINE),
			Optional.of(ModTreeFeatures.FRIGID_SPRUCE),
			Optional.of(ModTreeFeatures.FRIGID_PINE_BEES_005),
			Optional.of(ModTreeFeatures.FRIGID_SPRUCE_BEES_005));
	public static final TreeGrower ARBOREAL_CACTUS = new TreeGrower(
			"arboreal_cactus",
			Optional.empty(),
			Optional.of(ModTreeFeatures.ARBOREAL_CACTUS),
			Optional.empty());
}
