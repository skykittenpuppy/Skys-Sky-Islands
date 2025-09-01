package gay.beegirl.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class SakuraPetalParticle {
    public static class SakuraPetalProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public SakuraPetalProvider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity) {
            return new FallingLeavesParticle(clientLevel, x, y, z, this.sprites, 0.25F, 2.0F, false, true, 1.0F, 0.0F);
        }
    }
}
