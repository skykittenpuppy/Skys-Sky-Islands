package gay.beegirl.skyislands.world.level.block;

import gay.beegirl.skyislands.sounds.ModSoundEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;

public class ModSoundType {
	public static DeferredSoundType CLOUDSHALE_GRASS = new DeferredSoundType(1.0F, 1.0F,
			ModSoundEvents.CLOUDSHALE_GRASS_BREAK,
			ModSoundEvents.CLOUDSHALE_GRASS_STEP,
			ModSoundEvents.CLOUDSHALE_GRASS_PLACE,
			ModSoundEvents.CLOUDSHALE_GRASS_HIT,
			ModSoundEvents.CLOUDSHALE_GRASS_FALL);
	public static DeferredSoundType CLOUDSHALE_SNOW = new DeferredSoundType(1.0F, 1.0F,
			ModSoundEvents.CLOUDSHALE_SNOW_BREAK,
			ModSoundEvents.CLOUDSHALE_SNOW_STEP,
			ModSoundEvents.CLOUDSHALE_SNOW_PLACE,
			ModSoundEvents.CLOUDSHALE_SNOW_HIT,
			ModSoundEvents.CLOUDSHALE_SNOW_FALL);
	public static DeferredSoundType CLOUDSHALE = new DeferredSoundType(1.0F, 1.0F,
			ModSoundEvents.CLOUDSHALE_BREAK,
			ModSoundEvents.CLOUDSHALE_STEP,
			ModSoundEvents.CLOUDSHALE_PLACE,
			ModSoundEvents.CLOUDSHALE_HIT,
			ModSoundEvents.CLOUDSHALE_FALL);
	public static DeferredSoundType CLOUDSHALE_BRICKS = new DeferredSoundType(1.0F, 1.0F,
			ModSoundEvents.CLOUDSHALE_BRICKS_BREAK,
			ModSoundEvents.CLOUDSHALE_BRICKS_STEP,
			ModSoundEvents.CLOUDSHALE_BRICKS_PLACE,
			ModSoundEvents.CLOUDSHALE_BRICKS_HIT,
			ModSoundEvents.CLOUDSHALE_BRICKS_FALL);
	public static DeferredSoundType POINTED_CLOUDSHALE = new DeferredSoundType(1.0F, 1.0F,
			ModSoundEvents.POINTED_CLOUDSHALE_BREAK,
			ModSoundEvents.POINTED_CLOUDSHALE_STEP,
			ModSoundEvents.POINTED_CLOUDSHALE_PLACE,
			ModSoundEvents.POINTED_CLOUDSHALE_HIT,
			ModSoundEvents.POINTED_CLOUDSHALE_FALL);

	public static SoundType ARBOREAL_CACTUS_PLANTS = new SoundType(1.0F, 1.0F,
			SoundEvents.FUNGUS_BREAK,
			SoundEvents.FUNGUS_STEP,
			SoundEvents.FUNGUS_PLACE,
			SoundEvents.FUNGUS_HIT,
			SoundEvents.FUNGUS_FALL);
}
