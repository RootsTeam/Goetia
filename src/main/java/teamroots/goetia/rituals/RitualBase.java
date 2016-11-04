package teamroots.goetia.rituals;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class RitualBase {
	
	public RitualBase(){
		
	}
	
	public abstract void onActivated(World world, BlockPos pos, EntityPlayer player);
	
	public abstract boolean matches(World world, BlockPos pos);
}
