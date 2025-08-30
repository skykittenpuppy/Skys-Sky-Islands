package gay.beegirl.mixin.client;

import gay.beegirl.registry.armor_trim.ModTrimMaterials;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators.TrimMaterialData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemModelGenerators.class)
public class ItemModelGeneratorsMixin {
    @Mutable
    @Shadow
    @Final
    public static List<TrimMaterialData> TRIM_MATERIAL_MODELS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void modifyTrimMaterialModels(CallbackInfo ci){
        List<TrimMaterialData> TEMP = new java.util.ArrayList<>(TRIM_MATERIAL_MODELS);
        TEMP.add(new TrimMaterialData(ModTrimMaterials.ALEXANDRITE_GROUP, ModTrimMaterials.ALEXANDRITE));
        TRIM_MATERIAL_MODELS = TEMP;
    }
}
