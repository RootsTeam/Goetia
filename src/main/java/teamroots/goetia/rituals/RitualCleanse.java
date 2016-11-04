package teamroots.goetia.rituals;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.fluids.FluidBlockHolyWater;

public class RitualCleanse extends RitualBase{
	
	@Override
	public void onActivated(World world, BlockPos pos, EntityPlayer player) {
		Random random = new Random();
		GoetiaProvider.get(player).setImpurity(player, 0);
		GoetiaProvider.get(player).setPurity(player, 0);
		if(world.isRemote){
			for(int i = 0; i < 360; i++){
				world.spawnParticle(EnumParticleTypes.FLAME, pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, 0, 0.03*random.nextFloat(), 0, 0);
				world.spawnParticle(EnumParticleTypes.REDSTONE, pos.getX()+0.4*Math.cos(Math.toRadians(i))+0.5, pos.getY()+1, pos.getZ()+0.4*Math.sin(Math.toRadians(i))+0.5, 0, 2, 0, 0);
			}
		}
	}
	
	@Override
	public boolean matches(World world, BlockPos blockpos){
		BlockPos pos = blockpos.add(0, 1, 0);
		if(world.getBlockState(pos.north()).getBlock() == MainRegistry.angelCandleStand && world.getBlockState(pos.south()).getBlock() == MainRegistry.demonCandleStand){
			return true;
		}
		if(world.getBlockState(pos.north()).getBlock() == MainRegistry.demonCandleStand && world.getBlockState(pos.south()).getBlock() == MainRegistry.angelCandleStand){
			return true;
		}
		if(world.getBlockState(pos.west()).getBlock() == MainRegistry.angelCandleStand && world.getBlockState(pos.east()).getBlock() == MainRegistry.demonCandleStand){
			return true;
		}
		if(world.getBlockState(pos.west()).getBlock() == MainRegistry.demonCandleStand && world.getBlockState(pos.east()).getBlock() == MainRegistry.angelCandleStand){
			return true;
		}
		return false;
	}
}
