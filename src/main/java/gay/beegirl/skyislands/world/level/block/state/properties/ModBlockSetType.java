package gay.beegirl.skyislands.world.level.block.state.properties;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public final class ModBlockSetType {
	public static final BlockSetType CLOUDSHALE = BlockSetType.register(new BlockSetType(
			"cloudshale",
			true,
			true,
			false,
			BlockSetType.PressurePlateSensitivity.MOBS,
			SoundType.STONE,
			SoundEvents.IRON_DOOR_CLOSE,
			SoundEvents.IRON_DOOR_OPEN,
			SoundEvents.IRON_TRAPDOOR_CLOSE,
			SoundEvents.IRON_TRAPDOOR_OPEN,
			SoundEvents.STONE_PRESSURE_PLATE_CLICK_OFF,
			SoundEvents.STONE_PRESSURE_PLATE_CLICK_ON,
			SoundEvents.STONE_BUTTON_CLICK_OFF,
			SoundEvents.STONE_BUTTON_CLICK_ON));
	public static final BlockSetType GOLDENLEAF = BlockSetType.register(new BlockSetType("goldenleaf"));
	public static final BlockSetType SAKURA = BlockSetType.register(new BlockSetType(
			"sakura",
			true,
			true,
			true,
			BlockSetType.PressurePlateSensitivity.EVERYTHING,
			SoundType.CHERRY_WOOD,
			SoundEvents.CHERRY_WOOD_DOOR_CLOSE,
			SoundEvents.CHERRY_WOOD_DOOR_OPEN,
			SoundEvents.CHERRY_WOOD_TRAPDOOR_CLOSE,
			SoundEvents.CHERRY_WOOD_TRAPDOOR_OPEN,
			SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF,
			SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON,
			SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF,
			SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON));
	public static final BlockSetType FRIGID = BlockSetType.register(new BlockSetType("frigid"));
	public static final BlockSetType ARBOREAL_CACTUS = BlockSetType.register(new BlockSetType(
			"arboreal_cactus",
			true,
			true,
			true,
			BlockSetType.PressurePlateSensitivity.EVERYTHING,
			SoundType.NETHER_WOOD,
			SoundEvents.NETHER_WOOD_DOOR_CLOSE,
			SoundEvents.NETHER_WOOD_DOOR_OPEN,
			SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE,
			SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
			SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF,
			SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
			SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF,
			SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON));
}
