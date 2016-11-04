package teamroots.goetia.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import teamroots.goetia.Goetia;
import teamroots.goetia.lib.EnumIDs;
import teamroots.goetia.rituals.RitualBase;
import teamroots.goetia.rituals.RitualManager;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class BlockAltar extends BlockBase
{
    public BlockAltar(String blockName, Material material) {
        super(blockName,material);
        this.setHarvestLevel("pickaxe", 0);
        setHardness(1.0f);
    }
    
    @Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
    	if(player.getHeldItemMainhand() != null){
    		for(int i = 0; i < RitualManager.rituals.size(); i++){
    			RitualBase ritual = RitualManager.rituals.get(i);
    			if(ritual.matches(world, pos)){
    				ritual.onActivated(world, pos, player);
    				break;
    			}
    		}
    	} else {
    		player.openGui(Goetia.instance, EnumIDs.GUI_ID_ALTAR.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
    	}
		
        return true;
    }
}
