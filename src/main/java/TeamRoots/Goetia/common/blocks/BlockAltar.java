package teamroots.goetia.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamroots.goetia.Goetia;
import teamroots.goetia.lib.EnumIDs;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class BlockAltar extends BlockBase
{
    public BlockAltar(String blockName, Material material) {
        super(blockName,material);
        setHardness(1.0f);
    }
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ){
    	player.openGui(Goetia.instance, EnumIDs.GUI_ID_ALTAR.ordinal(), world, (int)player.posX, (int)player.posY, (int)player.posZ);
    	return true;
    }
}
