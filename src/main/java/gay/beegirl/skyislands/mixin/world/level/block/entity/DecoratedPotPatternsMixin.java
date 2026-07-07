package gay.beegirl.skyislands.mixin.world.level.block.entity;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.world.level.block.entity.ModDecoratedPotPatterns;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(DecoratedPotPatterns.class)
public abstract class DecoratedPotPatternsMixin {
    @ModifyReturnValue(method = "getPatternFromItem", at = @At("RETURN"))
    private static ResourceKey<DecoratedPotPattern> islands$checkIslandsSherds(ResourceKey<DecoratedPotPattern> original, Item item) {
        if (item == ModItems.TESTING_POTTERY_SHERD.get()) return ModDecoratedPotPatterns.TESTING.getKey();
        //if (BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(SkysSkyIslands.MOD_ID)) return ModDecoratedPotPatterns.ITEM_TO_POT_TEXTURE.get(item).getKey();

        return original;
    }
}
