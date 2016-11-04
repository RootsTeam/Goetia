package teamroots.goetia.common.blocks;

import javax.annotation.Nullable;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.common.tiles.TileEntityBowl;

public class BlockBowl extends BlockBase implements ITileEntityProvider{
	private AxisAlignedBB BOUNDS = new AxisAlignedBB(0.25,0,0.25,0.75,0.2,0.75);
	public BlockBowl(String blockName, boolean showTab) {
		super(blockName);
		if(!showTab){
			setCreativeTab(null);	
		}
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(heldItem != null && heldItem.getItem() == MainRegistry.liquidBottle){
			if(this == MainRegistry.emptyBowl){
				worldIn.setBlockState(pos, MainRegistry.fullBowl.getDefaultState());
				TileEntityBowl bowl = (TileEntityBowl) worldIn.getTileEntity(pos);
				bowl.setColor(pos, state, heldItem.getTagCompound().getInteger("color"));
				bowl.setEntity(pos, state, heldItem.getTagCompound().getString("entityName"));
				heldItem.stackSize--;
			} else {
				worldIn.setBlockState(pos, MainRegistry.emptyBowl.getDefaultState());
			}
		}
        return true;
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
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
	    return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos){
    	return BOUNDS;
    }
	
	public static class ColorHandler implements IBlockColor{

		@Override
		public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
			if(pos != null & state != null){
				if(tintIndex == 0){
					TileEntityBowl bowl = (TileEntityBowl) worldIn.getTileEntity(pos);
					return bowl.getColor();
				}
			}
			return 0;
		}
		
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityBowl();
	}

}
