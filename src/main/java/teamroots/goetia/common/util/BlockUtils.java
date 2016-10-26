package teamroots.goetia.common.util;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockUtils {
	
	public static boolean hasBlockOnAnySide(World world, BlockPos pos, Block block){
		if(world.getBlockState(pos.east()).getBlock() == block){
			return true;
		}
		if(world.getBlockState(pos.west()).getBlock() == block){
			return true;
		}
		if(world.getBlockState(pos.north()).getBlock() == block){
			return true;
		}
		if(world.getBlockState(pos.south()).getBlock() == block){
			return true;
		}
		if(world.getBlockState(pos.up()).getBlock() == block){
			return true;
		}
		if(world.getBlockState(pos.down()).getBlock() == block){
			return true;
		}
		return false;
	}
}
