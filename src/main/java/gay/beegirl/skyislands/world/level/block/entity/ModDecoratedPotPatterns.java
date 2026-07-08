package gay.beegirl.skyislands.world.level.block.entity;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDecoratedPotPatterns {
	public static final DeferredRegister<DecoratedPotPattern> DECORATED_POT_PATTERN = DeferredRegister.create(Registries.DECORATED_POT_PATTERN, SkysSkyIslands.MOD_ID);
	public static final ResourceKey<DecoratedPotPattern> TESTING = DECORATED_POT_PATTERN.register("testing_pottery_pattern", DecoratedPotPattern::new).getKey();

	public static void registerPotPatterns(IEventBus modEventBus) {
		SkysSkyIslands.LOGGER.info("Registering Pot Patterns for " + SkysSkyIslands.MOD_ID);

		DECORATED_POT_PATTERN.register(modEventBus);
	}
}
