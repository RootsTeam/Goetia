package teamroots.goetia.common.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.capability.capabilites.KnowledgeProvider;
import teamroots.goetia.common.blocks.BlockLostNote;
import teamroots.goetia.common.tiles.TileEntityScroll;
import teamroots.goetia.lib.LibMain;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemNote extends ItemBase
{
	public Block block;
	public String[] knowledge;
    public ItemNote(String name, Block block, String[] knowledge)
    {
        super(name,"0","1","2","3","4","5");
        this.block = block;
        this.setCustomMaxStackSize(1);
        this.setHasSubtypes(true);
        this.knowledge = knowledge;
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
    	if (player.hasCapability(KnowledgeProvider.knowledgeCapability, null) && stack.hasTagCompound() && !player.isSneaking() && this != MainRegistry.lostAngelNotes){
    		if (!world.isRemote && !KnowledgeProvider.get(player).hasKnowledge(stack.getTagCompound().getString("knowledge"))){
    			KnowledgeProvider.get(player).addKnowledge(player, stack.getTagCompound().getString("knowledge"));
    			player.setItemStackToSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, null);
    			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    	    }
    	}
    	return new ActionResult<ItemStack>(EnumActionResult.FAIL,stack);
    }
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(playerIn.isSneaking()){
    		if(worldIn.getBlockState(pos.up()).getBlock() == Blocks.AIR){
    			worldIn.setBlockState(pos.up(), block.getDefaultState().withProperty(BlockLostNote.FACING, playerIn.getHorizontalFacing().getOpposite()), 2);
        		TileEntityScroll tes = (TileEntityScroll)worldIn.getTileEntity(pos.up());
        		tes.setKnowledge(stack.getTagCompound().getString("knowledge"));
        		stack.stackSize--;
        		return EnumActionResult.PASS;
    		}
    		return EnumActionResult.FAIL;
    	}
    	return EnumActionResult.FAIL;
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected){
    	if (!stack.hasTagCompound()){
    		stack.setTagCompound(new NBTTagCompound());
    		stack.getTagCompound().setString("knowledge", this.knowledge[(itemRand.nextInt(this.knowledge.length))]);
    	}
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
    	if (!stack.hasTagCompound()){
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	
    	NBTTagCompound tag = stack.getTagCompound();
    	if(!tag.hasKey("knowledge")){
    		tag.setString("knowledge", this.knowledge[stack.getItemDamage()]);
    	}
    	if(this == MainRegistry.lostDemonNotes){
    		tooltip.add(TextFormatting.DARK_RED+"Knowledge: "+I18n.format("goetia.knowledge."+tag.getString("knowledge")));
    	} else {
    		tooltip.add(TextFormatting.AQUA+"Knowledge: "+I18n.format("goetia.knowledge."+tag.getString("knowledge")));
    	}
    	
    }
    
    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {
    	ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()+"_0"));
    	ModelLoader.setCustomModelResourceLocation(this, 1, new ModelResourceLocation(getRegistryName().toString()+"_1"));
    	ModelLoader.setCustomModelResourceLocation(this, 2, new ModelResourceLocation(getRegistryName().toString()+"_2"));
    	ModelLoader.setCustomModelResourceLocation(this, 3, new ModelResourceLocation(getRegistryName().toString()+"_3"));
    	ModelLoader.setCustomModelResourceLocation(this, 4, new ModelResourceLocation(getRegistryName().toString()+"_4"));
    	ModelLoader.setCustomModelResourceLocation(this, 5, new ModelResourceLocation(getRegistryName().toString()+"_5"));
    }
}
