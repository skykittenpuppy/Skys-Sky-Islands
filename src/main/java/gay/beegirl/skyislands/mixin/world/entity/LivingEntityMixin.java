package gay.beegirl.skyislands.mixin.world.entity;

import gay.beegirl.skyislands.neoforge.ModDataAttachments;
import gay.beegirl.skyislands.world.item.ModItems;
import gay.beegirl.skyislands.util.LivingEntityAccess;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.extensions.ILivingEntityExtension;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements Attackable, ILivingEntityExtension, LivingEntityAccess {
    @Unique private static final float FALL_DISTANCE_TILL_CAN_GLIDE = 2.5F; // TODO: Replace with gamerule/config/attribute
    @Unique private static final float FALL_DISTANCE_TILL_FREE_FALL = 8.0F; // TODO: Replace with gamerule/config/attribute
    @Shadow
    public abstract ItemStack getItemInHand(InteractionHand hand);

    @Unique
    private boolean islands$isFreeFalling;

    @Unique
    private boolean islands$isDiving;

    @Unique
    private boolean islands$isGliding;

    @Unique
    private int islands$freeFallTicks;

    @Unique
    private int islands$diveTicks;

    @Unique
    private int islands$glideTicks;

    @Unique
    public boolean islands$isFreeFalling() {
        return this.islands$isFreeFalling;
    }

    @Unique
    public boolean islands$isDiving() {
        return this.islands$isDiving;
    }

    @Unique
    public boolean islands$isGliding() {
        return this.islands$isGliding;
    }

    @Unique
    public int islands$getFreeFallTicks() {
        return this.islands$freeFallTicks;
    }

    @Unique
    public int islands$getGlideTicks() {
        return this.islands$glideTicks;
    }

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyArgs(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V", ordinal = 3))
    private void islands$modifyMovement(Args args, Vec3 travelVector) {
        if (this.islands$isDiving()) {
            double xVel = args.get(0);
            double yVel = args.get(1);
            double zVel = args.get(2);
            this.move(MoverType.SELF, new Vec3(-(0.85F * xVel), yVel, -(0.85F * zVel)));
        } else if (this.islands$isFreeFalling()) {
            double xVel = args.get(0);
            double yVel = args.get(1);
            double zVel = args.get(2);
            this.move(MoverType.SELF, new Vec3(0.15F * xVel, -(0.05 * yVel), 0.15F * zVel));
        } else if (this.islands$isGliding()) {
            double xVel = args.get(0);
            double yVel = args.get(1);
            double zVel = args.get(2);
            double newYVel = Mth.lerp(Math.clamp(this.islands$getGlideTicks(), 0, 20)/20.0F, yVel, -this.getGravity() * 1.5F);
            this.move(MoverType.SELF, new Vec3(0.5F * xVel, -0.0000001F, 0.5F * zVel));
            args.set(1, newYVel);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void islands$setModStates(CallbackInfo ci) {
        this.setData(ModDataAttachments.IS_GLIDING,
                (this.fallDistance > FALL_DISTANCE_TILL_CAN_GLIDE ||
                this.islands$isGliding) && // INFO: Prevents instant gliding
                (this.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.GLIDER) ||
                this.getItemInHand(InteractionHand.OFF_HAND).is(ModItems.GLIDER)) &&
                !this.onGround() &&
                !this.isShiftKeyDown()); // TODO: Replace with proper glide trigger
        this.setData(ModDataAttachments.IS_FREEFALLING, this.fallDistance > FALL_DISTANCE_TILL_FREE_FALL); // not slowfalling, not levitation, is player, isnt gliding, not shift key down,,, etc
        //this.setData(ModDataAttachments.IS_FREEFALLING, this.getItemInHand(InteractionHand.MAIN_HAND).is(ModItems.TESTING_GLIDER_PATTERN_SEWING_TEMPLATE));
        this.setData(ModDataAttachments.IS_DIVING, this.islands$isFreeFalling() && this.isShiftKeyDown());
        this.islands$isFreeFalling = this.getData(ModDataAttachments.IS_FREEFALLING);
        this.islands$isDiving = this.getData(ModDataAttachments.IS_DIVING);
        this.islands$isGliding = this.getData(ModDataAttachments.IS_GLIDING);
        if (this.islands$isGliding) this.fallDistance = 0.0F;
        if (this.islands$isFreeFalling()) {
            this.makeBoundingBox().setMaxY(1.0F); // TODO: Look into resizing to 2 blocks wide
            ++this.islands$freeFallTicks;
        } else {
            this.islands$freeFallTicks = 0;
        }
        if (this.islands$isDiving()) {
            ++this.islands$diveTicks;
        } else {
            this.islands$diveTicks = 0;
        }
        if (this.islands$isGliding()) {
            ++this.islands$glideTicks;
        } else {
            this.islands$glideTicks = 0;
        }
    }
}
