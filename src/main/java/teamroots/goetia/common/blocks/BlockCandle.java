package teamroots.goetia.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class BlockCandle extends BlockBase
{
	private AxisAlignedBB BOUNDS = new AxisAlignedBB(0.375,0,0.375,0.625,0.375,0.625);
    public BlockCandle(String blockName, Material material) {
        super(blockName,material);
        setLightLevel(1.0f);
        setHardness(0.4f);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos){
    	if (world.getBlockState(pos.down()).getBlock().isSideSolid(world.getBlockState(pos.down()), world, pos.down(), EnumFacing.UP)){
    		return true;
    	}
    	return false;
    }
    
    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block){
    	if (!world.getBlockState(pos.down()).getBlock().isSideSolid(world.getBlockState(pos.down()), world, pos.down(), EnumFacing.UP)){
    		this.dropBlockAsItem(world, pos, state, 0);
    		world.setBlockToAir(pos);
    	}
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos){
    	return BOUNDS;
    }
    
    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random){
    	switch (random.nextInt(3)){
    	case 0:
    		world.spawnParticle(EnumParticleTypes.FLAME, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 0, 0, 0, 0);
    	case 1:
    		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 0, random.nextFloat()*0.015f, 0, 0);
    	case 2:
    		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX()+0.5, pos.getY()+0.5, pos.getZ()+0.5, 0, random.nextFloat()*0.015f, 0, 0);
    	}
    }
}
