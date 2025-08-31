package gay.beegirl.mixin.client;

import gay.beegirl.entity.ModEntityTypes;
import gay.beegirl.entity.ModModelLayers;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderers.class)
public abstract class EntityRenderersMixin {
    @Shadow
    private static <T extends Entity> void register(EntityType<? extends T> entityType, EntityRendererProvider<T> entityRendererProvider) {}

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void injected(CallbackInfo ci) {
        register(ModEntityTypes.GOLDENLEAF_BOAT, (context) -> new BoatRenderer(context, ModModelLayers.GOLDENLEAF_BOAT));
        register(ModEntityTypes.GOLDENLEAF_CHEST_BOAT, (context) -> new BoatRenderer(context, ModModelLayers.GOLDENLEAF_CHEST_BOAT));
        register(ModEntityTypes.SAKURA_BOAT, (context) -> new BoatRenderer(context, ModModelLayers.SAKURA_BOAT));
        register(ModEntityTypes.SAKURA_CHEST_BOAT, (context) -> new BoatRenderer(context, ModModelLayers.SAKURA_CHEST_BOAT));
        register(ModEntityTypes.FRIGID_BOAT, (context) -> new BoatRenderer(context, ModModelLayers.FRIGID_BOAT));
        register(ModEntityTypes.FRIGID_CHEST_BOAT, (context) -> new BoatRenderer(context, ModModelLayers.FRIGID_CHEST_BOAT));
        register(ModEntityTypes.ARBOREAL_CACTUS_BOAT, (context) -> new BoatRenderer(context, ModModelLayers.ARBOREAL_CACTUS_BOAT));
        register(ModEntityTypes.ARBOREAL_CACTUS_CHEST_BOAT, (context) -> new BoatRenderer(context, ModModelLayers.ARBOREAL_CACTUS_CHEST_BOAT));
    }
}
