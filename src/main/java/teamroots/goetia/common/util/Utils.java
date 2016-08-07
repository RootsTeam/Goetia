package teamroots.goetia.common.util;

import java.util.Random;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class Utils {
	static Random random = new Random();
	public static Vec3d randomPointInAABB(AxisAlignedBB box){
		return new Vec3d(box.minX+random.nextDouble()*(box.maxX-box.minX),box.minY+random.nextDouble()*(box.maxY-box.minY),box.minZ+random.nextDouble()*(box.maxZ-box.minZ));
	}
}
