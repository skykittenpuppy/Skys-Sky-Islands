package gay.beegirl.skyislands.mixin.world.level.block.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.world.level.block.entity.ModDecoratedPotPatterns;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import net.neoforged.neoforge.registries.DeferredItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.lang.System.in;

@Mixin(DecoratedPotPatterns.class)
public abstract class DecoratedPotPatternsMixin {
	@ModifyReturnValue(method = "getPatternFromItem", at = @At("RETURN"))
	private static ResourceKey<DecoratedPotPattern> islands$checkIslandsSherds(ResourceKey<DecoratedPotPattern> original, Item item) {
		if (item == ModItems.TESTING_POTTERY_SHERD.get())
			return ModDecoratedPotPatterns.TESTING;
		//else if (item == ModItems.TESTING2_POTTERY_SHERD.get())
		//	return ModDecoratedPotPatterns.TESTING2;
		return original;
	}
}
