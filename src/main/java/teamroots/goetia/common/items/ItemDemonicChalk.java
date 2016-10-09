package teamroots.goetia.common.items;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import teamroots.goetia.Goetia;
import teamroots.goetia.capability.capabilites.KnowledgeProvider;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.lib.EnumIDs;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemDemonicChalk extends ItemBase
{
    public ItemDemonicChalk(String name) {
        super(name);
        setMaxStackSize(1);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
    	if (hand == EnumHand.MAIN_HAND && player.isSneaking()){
    		if (player.hasCapability(KnowledgeProvider.knowledgeCapability, null)){
    			player.getCapability(KnowledgeProvider.knowledgeCapability, null).dataChanged(player);
    		}
    		player.openGui(Goetia.instance, EnumIDs.GUI_ID_CHALK.ordinal(), world, (int)player.posX, (int)player.posY, (int)player.posZ);
    		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    	}
    	return new ActionResult<ItemStack>(EnumActionResult.FAIL,stack);
    }
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
    	if (stack.hasTagCompound() && world.getBlockState(pos).getBlock().isSideSolid(world.getBlockState(pos), world, pos, EnumFacing.UP) && side == EnumFacing.UP){
			if (stack.getTagCompound().hasKey("knowledge")){
    			EntityLivingBase toSpawn;
	    		try {
					toSpawn = (EntityLivingBase)SymbolManager.symbols.get(stack.getTagCompound().getString("knowledge")).getConstructor(World.class).newInstance(world);
					toSpawn.setPosition(pos.getX()+0.5, pos.getY()+1.0, pos.getZ()+0.5);	
					if (!world.isRemote){
						world.spawnEntityInWorld(toSpawn);
					}
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
    		}
    	}
    	return EnumActionResult.FAIL;
    }
    
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack old, ItemStack newS, boolean slotChanged){
    	return slotChanged;
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected){
    	if (!stack.hasTagCompound()){
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	else {
    		if (stack.getTagCompound().hasKey("knowledge")){
    			stack.getTagCompound().setString("knowledge", stack.getTagCompound().getString("knowledge"));
    		}
    	}
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
    	if (stack.hasTagCompound()){
    		if (stack.getTagCompound().hasKey("knowledge")){
    			tooltip.add(TextFormatting.DARK_PURPLE+"Symbol: "+I18n.format("goetia.knowledge."+stack.getTagCompound().getString("knowledge")));
    		}
    	}
    }
}
