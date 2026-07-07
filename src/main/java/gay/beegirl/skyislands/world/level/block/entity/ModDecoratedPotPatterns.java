package gay.beegirl.skyislands.world.level.block.entity;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.world.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;

public class ModDecoratedPotPatterns {
	// TODO: THIS SHIT AIN'T WORK
	public static final DeferredRegister<DecoratedPotPattern> DECORATED_POT_PATTERN = DeferredRegister.create(Registries.DECORATED_POT_PATTERN, SkysSkyIslands.MOD_ID);
	public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> TESTING = DECORATED_POT_PATTERN.register("testing_pottery_pattern", DecoratedPotPattern::new);
	public static final DeferredHolder<DecoratedPotPattern, DecoratedPotPattern> TESTING2 = DECORATED_POT_PATTERN.register("testing2_pottery_pattern", DecoratedPotPattern::new);
	public static final Map<Item, DeferredHolder<DecoratedPotPattern, DecoratedPotPattern>> ITEM_TO_POT_TEXTURE;


	public static void registerPotPatterns(IEventBus modEventBus) {
		SkysSkyIslands.LOGGER.info("Registering Pot Patterns for " + SkysSkyIslands.MOD_ID);

		DECORATED_POT_PATTERN.register(modEventBus);
	}

	static {
		ITEM_TO_POT_TEXTURE = Map.ofEntries(Map.entry(ModItems.TESTING_POTTERY_SHERD.get(), TESTING), Map.entry(ModItems.TESTING2_POTTERY_SHERD.get(), TESTING2));
	}
}
