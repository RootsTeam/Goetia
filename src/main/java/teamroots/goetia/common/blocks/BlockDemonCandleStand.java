package teamroots.goetia.common.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class BlockDemonCandleStand extends BlockBase
{
    public BlockDemonCandleStand(String blockName, Material material) {
        super(blockName, material);
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
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, World world, BlockPos pos){
    	return new AxisAlignedBB(0.375,0,0.375,0.625,0.625,0.625);
    }
    
    @Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random random){
    	switch (random.nextInt(3)){
    	case 0:
    		world.spawnParticle(EnumParticleTypes.FLAME, pos.getX()+0.5, pos.getY()+0.625, pos.getZ()+0.5, 0, 0, 0, 0);
    	case 1:
    		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX()+0.5, pos.getY()+0.625, pos.getZ()+0.5, 0, random.nextFloat()*0.05f, 0, 0);
    	case 2:
    		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, pos.getX()+0.5, pos.getY()+0.625, pos.getZ()+0.5, 0, random.nextFloat()*0.05f, 0, 0);
    	}
    }
}
