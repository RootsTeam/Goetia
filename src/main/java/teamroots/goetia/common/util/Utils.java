package teamroots.goetia.common.util;

import java.util.Random;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class Utils {
	static Random random = new Random();
	public static Vec3d randomPointInAABB(AxisAlignedBB box){
		return new Vec3d(box.minX+random.nextDouble()*(box.maxX-box.minX),box.minY+random.nextDouble()*(box.maxY-box.minY),box.minZ+random.nextDouble()*(box.maxZ-box.minZ));
	}
	
	public static int randomNumber(int min, int max){
		return random.nextInt(max - min + 1) - max;
	}

	public static String localizeName(String key){
		if(I18n.canTranslate(key)){
			return I18n.translateToLocal(key);
		} else {
			return I18n.translateToFallback(key);
		}
	}
}
