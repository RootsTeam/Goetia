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
import teamroots.goetia.capability.capabilites.KnowledgeProvider;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.lib.EnumIDs;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemSymbolIcon extends ItemBase
{
    public ItemSymbolIcon(String name) {
        super(name);
        setCreativeTab(null);
        setMaxStackSize(1);
    }
    
    public static void createData(ItemStack stack, String knowledge){
    	stack.setTagCompound(new NBTTagCompound());
    	stack.setStackDisplayName(I18n.format("goetia.tooltip.symbol")+I18n.format("goetia.knowledge."+knowledge));
    	stack.getTagCompound().setString("knowledge", knowledge);
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
    	if (stack.hasTagCompound()){
    		if (stack.getTagCompound().hasKey("knowledge")){
    			tooltip.add(TextFormatting.DARK_PURPLE+I18n.format("goetia.knowledge."+stack.getTagCompound().getString("knowledge")+".desc"));
    			tooltip.add(TextFormatting.DARK_PURPLE+I18n.format("goetia.tooltip.levelcost")+SymbolManager.symbolCosts.get(stack.getTagCompound().getString("knowledge"))+I18n.format("goetia.tooltip.levelcost2"));
        	}
    	}
    }
}
