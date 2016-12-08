package teamroots.goetia.common.items;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import teamroots.goetia.Goetia;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.common.blocks.BlockBowl;
import teamroots.goetia.common.tiles.TileEntityBowl;
import teamroots.goetia.lib.EnumIDs;
import teamroots.goetia.spellcasting.AlignmentType;

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
    	GoetiaProvider.get(player).dataChanged(player);
    	player.openGui(Goetia.instance, EnumIDs.GUI_ID_FOCUS.ordinal(), world, (int)player.posX, (int)player.posY, (int)player.posZ);
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack old, ItemStack newS, boolean slotChanged){
    	return slotChanged;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected){
        if(!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }
        if(!stack.getTagCompound().hasKey("cooldown")) {
            stack.getTagCompound().setInteger("cooldown", 20);
        }
        stack.getTagCompound().setInteger("cooldown", stack.getTagCompound().getInteger("cooldown")-1);

        if(stack.getTagCompound().getInteger("cooldown") <= 0){
            stack.getTagCompound().setString("alignment", AlignmentType.getString(GoetiaProvider.get((EntityPlayer)entity).getAligningTowards()));
            stack.getTagCompound().setInteger("cooldown", 20);
        }
    }

    public static class ColorHandler implements IItemColor {
        @Override
        public int getColorFromItemstack(ItemStack stack, int tintIndex) {
            if(!stack.hasTagCompound()){
                return 0xFFFFFF;
            }
            if(tintIndex == 1) {
                if (stack.getTagCompound().hasKey("alignment")) {
                    if (stack.getTagCompound().getString("alignment").equals("demon")) {
                        return 16729156;
                    } else if (stack.getTagCompound().getString("alignment").equals("angel")) {
                        return 3006972;
                    } else {
                        return 0xFFFFFF;
                    }
                } else return 0xFFFFFF;
            }
            return 0xFFFFFF;
        }
    }
}
