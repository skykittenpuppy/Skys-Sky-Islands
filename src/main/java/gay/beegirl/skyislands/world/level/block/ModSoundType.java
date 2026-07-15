package gay.beegirl.skyislands.world.level.block;

import gay.beegirl.skyislands.sounds.ModSoundEvents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.neoforged.bus.api.IEventBus;

public class ModSoundType {
	public static SoundType CLOUDSHALE_GRASS;
	public static SoundType CLOUDSHALE_SNOW_GRASS;
	public static SoundType POINTED_CLOUDSHALE;
	public static SoundType CLOUDSHALE;
	public static SoundType CLOUDSHALE_BRICKS;

	public static void createSoundTypes() {
		CLOUDSHALE_GRASS = new SoundType(1.0F, 1.0F,
				SoundEvents.GRASS_BREAK,
				SoundEvents.GRASS_STEP,
				SoundEvents.GRASS_PLACE,
				SoundEvents.GRASS_HIT,
				SoundEvents.GRASS_FALL);
		CLOUDSHALE_SNOW_GRASS = new SoundType(1.0F, 1.0F,
				SoundEvents.SNOW_BREAK,
				SoundEvents.SNOW_STEP,
				SoundEvents.SNOW_PLACE,
				SoundEvents.SNOW_HIT,
				SoundEvents.SNOW_FALL);
		POINTED_CLOUDSHALE = new SoundType(1.0F, 1.0F,
				SoundEvents.POINTED_DRIPSTONE_BREAK,
				SoundEvents.POINTED_DRIPSTONE_STEP,
				SoundEvents.POINTED_DRIPSTONE_PLACE,
				SoundEvents.POINTED_DRIPSTONE_HIT,
				SoundEvents.POINTED_DRIPSTONE_FALL);
		CLOUDSHALE = new SoundType(1.0F, 1.0F,
				SoundEvents.TUFF_BREAK,
				SoundEvents.TUFF_STEP,
				SoundEvents.TUFF_PLACE,
				SoundEvents.TUFF_HIT,
				SoundEvents.TUFF_FALL);
		CLOUDSHALE_BRICKS = new SoundType(1.0F, 1.0F,
				SoundEvents.TUFF_BRICKS_BREAK,
				SoundEvents.TUFF_BRICKS_STEP,
				SoundEvents.TUFF_BRICKS_PLACE,
				SoundEvents.TUFF_BRICKS_HIT,
				SoundEvents.TUFF_BRICKS_FALL);

		/* TODO: I FUCKING HATE DEFERRED REGISTER !!!
		CLOUDSHALE = new SoundType(1.0F, 1.0F,
				ModSoundEvents.CLOUDSHALE_BREAK.get(),
				ModSoundEvents.CLOUDSHALE_STEP.get(),
				ModSoundEvents.CLOUDSHALE_PLACE.get(),
				ModSoundEvents.CLOUDSHALE_HIT.get(),
				ModSoundEvents.CLOUDSHALE_FALL.get());
		CLOUDSHALE_BRICKS = new SoundType(1.0F, 1.0F,
				ModSoundEvents.CLOUDSHALE_BRICKS_BREAK.get(),
				ModSoundEvents.CLOUDSHALE_BRICKS_STEP.get(),
				ModSoundEvents.CLOUDSHALE_BRICKS_PLACE.get(),
				ModSoundEvents.CLOUDSHALE_BRICKS_HIT.get(),
				ModSoundEvents.CLOUDSHALE_BRICKS_FALL.get());
		*/
	}
}
