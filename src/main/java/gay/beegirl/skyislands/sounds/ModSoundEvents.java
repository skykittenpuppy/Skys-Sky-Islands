package gay.beegirl.skyislands.sounds;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, SkysSkyIslands.MOD_ID);

	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_GRASS_BREAK		= SOUND_EVENTS.register("block.cloudshale_grass.break",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_grass.break")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_GRASS_FALL		= SOUND_EVENTS.register("block.cloudshale_grass.fall",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_grass.fall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_GRASS_HIT			= SOUND_EVENTS.register("block.cloudshale_grass.hit",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_grass.hit")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_GRASS_PLACE		= SOUND_EVENTS.register("block.cloudshale_grass.place",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_grass.place")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_GRASS_STEP		= SOUND_EVENTS.register("block.cloudshale_grass.step",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_grass.step")));

	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_SNOW_GRASS_BREAK	= SOUND_EVENTS.register("block.cloudshale_snow_grass.break",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_snow_grass.break")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_SNOW_GRASS_FALL	= SOUND_EVENTS.register("block.cloudshale_snow_grass.fall",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_snow_grass.fall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_SNOW_GRASS_HIT	= SOUND_EVENTS.register("block.cloudshale_snow_grass.hit",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_snow_grass.hit")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_SNOW_GRASS_PLACE	= SOUND_EVENTS.register("block.cloudshale_snow_grass.place",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_snow_grass.place")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_SNOW_GRASS_STEP	= SOUND_EVENTS.register("block.cloudshale_snow_grass.step",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_snow_grass.step")));

	public static final DeferredHolder<SoundEvent, SoundEvent> POINTED_CLOUDSHALE_BREAK		= SOUND_EVENTS.register("block.pointed_cloudshale.break",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.pointed_cloudshale.break")));
	public static final DeferredHolder<SoundEvent, SoundEvent> POINTED_CLOUDSHALE_FALL		= SOUND_EVENTS.register("block.pointed_cloudshale.fall",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.pointed_cloudshale.fall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> POINTED_CLOUDSHALE_HIT		= SOUND_EVENTS.register("block.pointed_cloudshale.hit",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.pointed_cloudshale.hit")));
	public static final DeferredHolder<SoundEvent, SoundEvent> POINTED_CLOUDSHALE_PLACE		= SOUND_EVENTS.register("block.pointed_cloudshale.place",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.pointed_cloudshale.place")));
	public static final DeferredHolder<SoundEvent, SoundEvent> POINTED_CLOUDSHALE_STEP		= SOUND_EVENTS.register("block.pointed_cloudshale.step",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.pointed_cloudshale.step")));
	public static final DeferredHolder<SoundEvent, SoundEvent> POINTED_CLOUDSHALE_LAND		= SOUND_EVENTS.register("block.pointed_cloudshale.land",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.pointed_cloudshale.land")));

	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_BREAK				= SOUND_EVENTS.register("block.cloudshale.break",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale.break")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_FALL				= SOUND_EVENTS.register("block.cloudshale.fall",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale.fall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_HIT				= SOUND_EVENTS.register("block.cloudshale.hit",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale.hit")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_PLACE				= SOUND_EVENTS.register("block.cloudshale.place",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale.place")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_STEP				= SOUND_EVENTS.register("block.cloudshale.step",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale.step")));

	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_BRICKS_BREAK		= SOUND_EVENTS.register("block.cloudshale_bricks.break",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_bricks.break")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_BRICKS_FALL		= SOUND_EVENTS.register("block.cloudshale_bricks.fall",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_bricks.fall")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_BRICKS_HIT		= SOUND_EVENTS.register("block.cloudshale_bricks.hit",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_bricks.hit")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_BRICKS_PLACE		= SOUND_EVENTS.register("block.cloudshale_bricks.place",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_bricks.place")));
	public static final DeferredHolder<SoundEvent, SoundEvent> CLOUDSHALE_BRICKS_STEP		= SOUND_EVENTS.register("block.cloudshale_bricks.step",
			() -> SoundEvent.createVariableRangeEvent(SkysSkyIslands.createId("block.cloudshale_bricks.step")));

	public static void registerSoundEvents(IEventBus modEventBus) {
		SkysSkyIslands.LOGGER.info("Registering Sound Events for " + SkysSkyIslands.MOD_ID);

		SOUND_EVENTS.register(modEventBus);
	}
}
