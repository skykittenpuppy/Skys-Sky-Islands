package gay.beegirl.mixin;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.*;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    @Final
    protected SynchedEntityData entityData;

    @Shadow
    protected @Final RandomSource random;
}
