package teamroots.goetia.common.items;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.Goetia;
import teamroots.goetia.capability.impurity.KnowledgeProvider;
import teamroots.goetia.lib.EnumIDs;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.registry.MainRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemNote extends ItemBase
{

    public ItemNote(String name)
    {
        super(name);
        this.setCustomMaxStackSize(1);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
    	if (player.hasCapability(KnowledgeProvider.knowledgeCapability, null) && stack.hasTagCompound()){
    		if (!world.isRemote && !KnowledgeProvider.get(player).hasKnowledge(stack.getTagCompound().getString("knowledge"))){
    			KnowledgeProvider.get(player).addKnowledge(player, stack.getTagCompound().getString("knowledge"));
    			player.setItemStackToSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND : EntityEquipmentSlot.OFFHAND, null);
    			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    	    }
    		if (world.isRemote){
    			System.out.println(KnowledgeProvider.get(player).getKnowledge().toString());
    		}
    	}
    	return new ActionResult<ItemStack>(EnumActionResult.FAIL,stack);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected){
    	if (!stack.hasTagCompound()){
    		stack.setTagCompound(new NBTTagCompound());
    		stack.getTagCompound().setString("knowledge", LibMain.LibKnowledge.validKnowledge[(itemRand.nextInt(LibMain.LibKnowledge.validKnowledge.length))]);
    	}
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
    	if (stack.hasTagCompound()){
    		tooltip.add(TextFormatting.DARK_PURPLE+"Knowledge: "+I18n.format("goetia.knowledge."+stack.getTagCompound().getString("knowledge")));
    	}
    	if (player.hasCapability(KnowledgeProvider.knowledgeCapability, null)){
    		tooltip.addAll(KnowledgeProvider.get(player).getKnowledge());
    	}
    }
}
