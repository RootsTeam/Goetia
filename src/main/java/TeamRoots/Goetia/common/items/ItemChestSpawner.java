package teamroots.goetia.common.items;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraft.world.storage.loot.LootTableManager;
import net.minecraftforge.common.MinecraftForge;
import teamroots.goetia.Goetia;
import teamroots.goetia.capability.impurity.KnowledgeProvider;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.lib.EnumIDs;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemChestSpawner extends ItemBase
{
    public ItemChestSpawner(String name) {
        super(name);
        setMaxStackSize(1);
    }
    
    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ){
    	if (world.getBlockState(pos).getBlock() == Blocks.CHEST){
    		TileEntityChest chest = (TileEntityChest)world.getTileEntity(pos);
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
