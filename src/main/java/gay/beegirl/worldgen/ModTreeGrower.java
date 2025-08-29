package gay.beegirl.worldgen;

import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower GOLDEANLEAF = new TreeGrower("goldenleaf", Optional.empty(), Optional.of(ModConfiguredFeatures.SAKURA), Optional.of(ModConfiguredFeatures.SAKURA_BEES_005));
    public static final TreeGrower SAKURA = new TreeGrower("sakura", Optional.empty(), Optional.of(ModConfiguredFeatures.SAKURA), Optional.of(ModConfiguredFeatures.SAKURA_BEES_005));
    public static final TreeGrower FRIGID = new TreeGrower("frigid", Optional.empty(), Optional.of(ModConfiguredFeatures.SAKURA), Optional.of(ModConfiguredFeatures.SAKURA_BEES_005));
    public static final TreeGrower ARBOREAL_CACTUS = new TreeGrower("arboreal_cactus", Optional.empty(), Optional.of(ModConfiguredFeatures.ARBOREAL_CACTUS), Optional.empty());
}
