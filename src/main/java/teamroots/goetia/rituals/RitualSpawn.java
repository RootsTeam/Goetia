package teamroots.goetia.rituals;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.common.tiles.TileEntityBowl;

public class RitualSpawn extends RitualBase{

	@Override
	public void onActivated(World world, BlockPos pos, EntityPlayer player) {
		Random random = new Random();
		TileEntityBowl bowl = (TileEntityBowl)world.getTileEntity(pos.up());
		ItemStack stack = player.getHeldItemMainhand();
		if(!world.isRemote){
			Entity entity = EntityList.createEntityByName(bowl.getEntity(), world);
			entity.setPosition(pos.getX()+0.5, pos.getY()+1.3, pos.getZ()+0.5);
			world.spawnEntityInWorld(entity);
			world.setBlockState(pos.up(), MainRegistry.emptyBowl.getDefaultState());
		}
		if(world.isRemote){
			for(int i = 0; i < 360; i++){
				world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, pos.getX()+0.4*Math.cos(Math.toRadians(i))+0.5, pos.getY()+1, pos.getZ()+0.4*Math.sin(Math.toRadians(i))+0.5, 0, 0.05, 0, 0);
			}
		}
	}

	@Override
	public boolean matches(World world, BlockPos blockpos) {
		BlockPos pos = blockpos.add(0, 1, 0);
		if(world.getBlockState(pos).getBlock() == MainRegistry.fullBowl){
			return true;
		}
		return false;
	}
	
}
