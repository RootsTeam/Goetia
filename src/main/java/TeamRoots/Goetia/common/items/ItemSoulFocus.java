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
import teamroots.goetia.capability.impurity.ImpurityProvider;
import teamroots.goetia.capability.impurity.KnowledgeProvider;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.lib.EnumIDs;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemSoulFocus extends ItemBase
{
    public ItemSoulFocus(String name) {
        super(name);
        setMaxStackSize(1);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
    	ImpurityProvider.get(player).dataChanged(player);
    	player.openGui(Goetia.instance, EnumIDs.GUI_ID_FOCUS.ordinal(), world, (int)player.posX, (int)player.posY, (int)player.posZ);
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack old, ItemStack newS, boolean slotChanged){
    	return slotChanged;
    }
}
