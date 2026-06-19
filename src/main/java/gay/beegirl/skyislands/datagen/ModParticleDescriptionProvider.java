package gay.beegirl.skyislands.datagen;

import gay.beegirl.skyislands.SkysSkyIslands;
import gay.beegirl.skyislands.core.particles.ModParticleTypes;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.ParticleDescriptionProvider;

public class ModParticleDescriptionProvider extends ParticleDescriptionProvider {
    protected ModParticleDescriptionProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, existingFileHelper);
    }

    @Override
    protected void addDescriptions() {
        spriteSet(ModParticleTypes.SAKURA_PETALS.get(), SkysSkyIslands.createId("sakura"), 12, false);
    }
}
