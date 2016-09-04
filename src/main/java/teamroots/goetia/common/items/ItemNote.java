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
    	}
    	return new ActionResult<ItemStack>(EnumActionResult.FAIL,stack);
    }
    
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected){
    	if (!stack.hasTagCompound()){
    		stack.setTagCompound(new NBTTagCompound());
    		stack.getTagCompound().setString("knowledge", LibMain.LibKnowledge.validKnowledge[(itemRand.nextInt(LibMain.LibKnowledge.validKnowledge.length))]);
    	}
    	else {
    		stack.getTagCompound().setString("knowledge", LibMain.LibKnowledge.validKnowledge[stack.getItemDamage()]);
    	}
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
    	if (!stack.hasTagCompound()){
    		stack.setTagCompound(new NBTTagCompound());
    	}
    	stack.getTagCompound().setString("knowledge", LibMain.LibKnowledge.validKnowledge[stack.getItemDamage()]);
    	tooltip.add(TextFormatting.DARK_PURPLE+"Knowledge: "+I18n.format("goetia.knowledge."+stack.getTagCompound().getString("knowledge")));
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
