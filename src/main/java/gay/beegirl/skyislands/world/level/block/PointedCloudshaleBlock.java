package gay.beegirl.skyislands.world.level.block;

import gay.beegirl.skyislands.sounds.ModSoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class PointedCloudshaleBlock extends ProtoSpeleothemBlock {
    private static final int MAX_GROWTH_LENGTH = 2;

    public PointedCloudshaleBlock(final BlockState blockToGrowOn, final Properties properties) {
        super(blockToGrowOn, properties);
    }

    @Override
    protected SoundEvent getStalactiteLandingSound() {
        return ModSoundEvents.POINTED_CLOUDSHALE_LAND.get();
    }

    @Override
    protected int getMaxGrowthLength() {
        return MAX_GROWTH_LENGTH;
    }
}
