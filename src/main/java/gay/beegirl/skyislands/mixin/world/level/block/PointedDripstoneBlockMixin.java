package gay.beegirl.skyislands.mixin.world.level.block;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;
import gay.beegirl.skyislands.tags.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PointedDripstoneBlock.class)
public abstract class PointedDripstoneBlockMixin {
	// Several hours of wasted time:

	/*
	@Redirect(method = "lambda$findTip$3", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
	private static boolean islands$findTip$3(BlockState instance, Block block) {
		return instance.is(ModBlockTags.SPELEOTHEMS);
	}

	@Redirect(method = "lambda$findRootBlock$5", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
	private static boolean islands$findRootBlock$5(BlockState instance, Block block) {
		return instance.is(ModBlockTags.SPELEOTHEMS);
	}
	@Redirect(method = "lambda$findRootBlock$6", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
	private static boolean islands$findRootBlock$6(BlockState instance, Block block) {
		return instance.is(ModBlockTags.SPELEOTHEMS);
	}

	@Redirect(method = "isTip", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
	private static boolean islands$isTip(BlockState instance, Block block) {
		return instance.is(ModBlockTags.SPELEOTHEMS);
	}

	@Redirect(method = "isStalactiteStartPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
	private static boolean islands$isStalactiteStartPos(BlockState instance, Block block) {
		return instance.is(ModBlockTags.SPELEOTHEMS);
	}

	@Redirect(method = "isPointedDripstoneWithDirection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
	private static boolean islands$isPointedDripstoneWithDirection(BlockState instance, Block block) {
		return instance.is(ModBlockTags.SPELEOTHEMS);
	}

	@Redirect(method = "canGrow", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"))
	private static boolean islands$canGrow(BlockState instance, Block block) {
		return instance.is(ModBlockTags.SPELEOTHEMS);
	}
	*/
}
