package gay.beegirl.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public class ModBlockSetType {
    public static final BlockSetType GOLDENLEAF = BlockSetType.register(new BlockSetType("goldenleaf"));
    public static final BlockSetType SAKURA = BlockSetType.register(new BlockSetType("sakura", true, true, true,BlockSetType.PressurePlateSensitivity.EVERYTHING, SoundType.CHERRY_WOOD, SoundEvents.CHERRY_WOOD_DOOR_CLOSE, SoundEvents.CHERRY_WOOD_DOOR_OPEN, SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON));
    public static final BlockSetType FRIGID = BlockSetType.register(new BlockSetType("frigid"));
    public static final BlockSetType ARBOREAL_CACTUS = BlockSetType.register(new BlockSetType("arboreal_cactus"));
}
