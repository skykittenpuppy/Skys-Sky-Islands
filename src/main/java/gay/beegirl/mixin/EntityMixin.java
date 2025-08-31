package gay.beegirl.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    protected @Final SynchedEntityData entityData;
    @Shadow
    protected @Final RandomSource random;

    @Unique
    private static final EntityDataAccessor<Byte> DATA_SKY_ISLAND_FLAGS_ID;
    @Unique
    private static final int FLAG_HANG_GLIDING = 0; //Protected
    @Unique
    private static final int FLAG_DIVING = 1; //Protected
    @Unique
    private static final int FLAG_FREEFALLING = 2; //Protected

    @Unique
    protected boolean getSkyIslandFlag(int i) {
        return (entityData.get(DATA_SKY_ISLAND_FLAGS_ID) & 1 << i) != 0;
    }
    @Unique
    protected void setSkyIslandFlag(int i, boolean bl) {
        byte b = entityData.get(DATA_SKY_ISLAND_FLAGS_ID);
        if (bl) {
            entityData.set(DATA_SKY_ISLAND_FLAGS_ID, (byte)(b | 1 << i));
        } else {
            entityData.set(DATA_SKY_ISLAND_FLAGS_ID, (byte)(b & ~(1 << i)));
        }
    }

    static {
        DATA_SKY_ISLAND_FLAGS_ID = SynchedEntityData.defineId(Entity.class, EntityDataSerializers.BYTE);
    }

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;defineSynchedData(Lnet/minecraft/network/syncher/SynchedEntityData$Builder;)V", shift = At.Shift.BEFORE))
    private static void init(CallbackInfo ci, @Local SynchedEntityData.Builder builder) {
        builder.define(DATA_SKY_ISLAND_FLAGS_ID, (byte)0);
    }
}
