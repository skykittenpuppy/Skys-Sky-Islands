package gay.beegirl.particle;

import gay.beegirl.SkysSkyIslands;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class ModParticleTypes {
    private static SimpleParticleType registerParticleType(String name, SimpleParticleType particleType) {
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceKey.create(Registries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(SkysSkyIslands.MOD_ID, name)), particleType);
    }

    public static void registerParticleTypes() {
        SkysSkyIslands.LOGGER.info("Registering Particle Types for " + SkysSkyIslands.MOD_ID);
    }

    public static final SimpleParticleType SAKURA_PETALS = registerParticleType("sakura_petals", FabricParticleTypes.simple());
}