package teamroots.goetia.common.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import teamroots.goetia.Goetia;
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
    	if (hand == EnumHand.MAIN_HAND){
    		player.openGui(Goetia.instance, EnumIDs.GUI_ID_CHALK.ordinal(), world, (int)player.posX, (int)player.posY, (int)player.posZ);
    	}
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected){
    	if (!stack.hasTagCompound()){
    		stack.setTagCompound(new NBTTagCompound());
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
