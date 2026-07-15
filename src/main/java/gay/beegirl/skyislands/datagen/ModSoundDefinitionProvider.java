package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.sounds.ModSoundEvents;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.SoundDefinition;
import net.neoforged.neoforge.common.data.SoundDefinitionsProvider;

public class ModSoundDefinitionProvider extends SoundDefinitionsProvider {
	public ModSoundDefinitionProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, SkysSkyIslands.MOD_ID, existingFileHelper);
	}

	@Override
	public void registerSounds() {
		// Cloudshale Grass
		add(ModSoundEvents.CLOUDSHALE_GRASS_BREAK, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_grass/break1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/break2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/break3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/break4", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.break")
		);
		add(ModSoundEvents.CLOUDSHALE_GRASS_FALL, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_grass/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step6", SoundDefinition.SoundType.SOUND)
				)
		);
		add(ModSoundEvents.CLOUDSHALE_GRASS_HIT, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_grass/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.hit")
		);
		add(ModSoundEvents.CLOUDSHALE_GRASS_PLACE, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_grass/break1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/break2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/break3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/break4", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.place")
		);
		add(ModSoundEvents.CLOUDSHALE_GRASS_STEP, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_grass/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_grass/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.footsteps")
		);
		// Cloudshale Snow Grass
		add(ModSoundEvents.CLOUDSHALE_SNOW_GRASS_BREAK, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_snow_grass/break1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/break2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/break3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/break4", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.break")
		);
		add(ModSoundEvents.CLOUDSHALE_SNOW_GRASS_FALL, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_snow_grass/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step6", SoundDefinition.SoundType.SOUND)
				)
		);
		add(ModSoundEvents.CLOUDSHALE_SNOW_GRASS_HIT, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_snow_grass/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.hit")
		);
		add(ModSoundEvents.CLOUDSHALE_SNOW_GRASS_PLACE, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_snow_grass/break1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/break2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/break3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/break4", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.place")
		);
		add(ModSoundEvents.CLOUDSHALE_SNOW_GRASS_STEP, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_snow_grass/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_snow_grass/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.footsteps")
		);
		// Pointed Cloudshale
		add(ModSoundEvents.POINTED_CLOUDSHALE_BREAK, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/break1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break4", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.break")
		);
		add(ModSoundEvents.POINTED_CLOUDSHALE_FALL, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step6", SoundDefinition.SoundType.SOUND)
				)
		);
		add(ModSoundEvents.POINTED_CLOUDSHALE_HIT, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.hit")
		);
		add(ModSoundEvents.POINTED_CLOUDSHALE_PLACE, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/break1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break4", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.place")
		);
		add(ModSoundEvents.POINTED_CLOUDSHALE_STEP, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.footsteps")
		);
		add(ModSoundEvents.POINTED_CLOUDSHALE_LAND, SoundDefinition.definition()
				.with(
						sound("skyislands:block/pointed_cloudshale/land1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/pointed_cloudshale/land2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/pointed_cloudshale/land3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/pointed_cloudshale/land4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/pointed_cloudshale/land5", SoundDefinition.SoundType.SOUND)
				)
		);
		// Cloudshale
		add(ModSoundEvents.CLOUDSHALE_BREAK, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/break1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break4", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.break")
		);
		add(ModSoundEvents.CLOUDSHALE_FALL, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step6", SoundDefinition.SoundType.SOUND)
				)
		);
		add(ModSoundEvents.CLOUDSHALE_HIT, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.hit")
		);
		add(ModSoundEvents.CLOUDSHALE_PLACE, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/break1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/break4", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.place")
		);
		add(ModSoundEvents.CLOUDSHALE_STEP, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.footsteps")
		);
		// Cloudshale Bricks
		add(ModSoundEvents.CLOUDSHALE_BRICKS_BREAK, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_bricks/place1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/place2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/place3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/place4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/place5", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.break")
		);
		add(ModSoundEvents.CLOUDSHALE_BRICKS_FALL, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_bricks/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step6", SoundDefinition.SoundType.SOUND)
				)
		);
		add(ModSoundEvents.CLOUDSHALE_BRICKS_HIT, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_bricks/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.hit")
		);
		add(ModSoundEvents.CLOUDSHALE_BRICKS_PLACE, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_bricks/place1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/place2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/place3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/place4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/place5", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.place")
		);
		add(ModSoundEvents.CLOUDSHALE_BRICKS_STEP, SoundDefinition.definition()
				.with(
						sound("skyislands:block/cloudshale_bricks/step1", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step2", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step3", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step4", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step5", SoundDefinition.SoundType.SOUND),
						sound("skyislands:block/cloudshale_bricks/step6", SoundDefinition.SoundType.SOUND)
				)
				.subtitle("subtitles.block.generic.footsteps")
		);
	}
}
