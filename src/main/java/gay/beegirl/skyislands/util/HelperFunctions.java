package gay.beegirl.skyislands.util;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class HelperFunctions {
	public static Vec3 rotate3dX(Vec3 original, float radians) {
		float sin = Mth.sin(radians);
		float cos = Mth.cos(radians);
		double newY = original.y * cos - original.z * sin;
		double newZ = original.y * sin + original.z * cos;
		return new Vec3(original.x, newY, newZ);
	}
	public static Vec3 rotate3dY(Vec3 original, float radians) {
		float sin = Mth.sin(radians);
		float cos = Mth.cos(radians);
		double newX = original.x * cos + original.z * sin;
		double newZ = -original.x * sin + original.z * cos;
		return new Vec3(newX, original.y, newZ);
	}
	public static Vec3 rotate3dZ(Vec3 original, float radians) {
		float sin = Mth.sin(radians);
		float cos = Mth.cos(radians);
		double newX = original.x * cos - original.y * sin;
		double newY = original.x * sin + original.y * cos;
		return new Vec3(newX, newY, original.z);
	}
}
