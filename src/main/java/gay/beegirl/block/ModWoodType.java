package gay.beegirl.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static final WoodType GOLDENLEAF = WoodType.register(new WoodType("goldenleaf", ModBlockSetType.GOLDENLEAF));
    public static final WoodType SAKURA = WoodType.register(new WoodType("sakura", ModBlockSetType.SAKURA, SoundType.CHERRY_WOOD, SoundType.CHERRY_WOOD_HANGING_SIGN, SoundEvents.CHERRY_WOOD_FENCE_GATE_CLOSE, SoundEvents.CHERRY_WOOD_FENCE_GATE_OPEN));
    public static final WoodType FRIGID = WoodType.register(new WoodType("frigid", ModBlockSetType.FRIGID));
    public static final WoodType ARBOREAL_CACTUS = WoodType.register(new WoodType("arboreal_cactus", ModBlockSetType.ARBOREAL_CACTUS));
}
