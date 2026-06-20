package gay.beegirl.skyislands.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.CherryParticle;
import net.minecraft.client.particle.SpriteSet;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SakuraParticle extends CherryParticle {
	private static final float ACCELERATION_SCALE = 0.0025F;
	private static final int INITIAL_LIFETIME = 300;
	private static final int CURVE_ENDPOINT_TIME = 300;
	private static final float FALL_ACC = 0.25F;
	private static final float WIND_BIG = 2.0F;
	private float rotSpeed;

	public SakuraParticle(ClientLevel level, double x, double y, double z, SpriteSet spriteSet) {
		super(level, x, y, z, spriteSet);
	}
}
