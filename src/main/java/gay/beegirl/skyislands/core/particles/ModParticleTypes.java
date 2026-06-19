package gay.beegirl.skyislands.core.particles;

import gay.beegirl.skyislands.SkysSkyIslands;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, SkysSkyIslands.MOD_ID);

    public static final Supplier<SimpleParticleType> SAKURA_PETALS = PARTICLE_TYPES.register("sakura_petals", () -> new SimpleParticleType(false));

    public static void registerParticleTypes(IEventBus modEventBus) {
        SkysSkyIslands.LOGGER.info("Registering Particle Types for " + SkysSkyIslands.MOD_ID);

        PARTICLE_TYPES.register(modEventBus);
    }
}
