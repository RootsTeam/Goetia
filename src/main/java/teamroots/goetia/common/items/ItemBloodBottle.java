package teamroots.goetia.common.items;

import java.util.List;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemBloodBottle extends ItemBase{

	public ItemBloodBottle(String name) {
		super(name);
	}
	
	public ItemStack setBloodType(ItemStack stack, Entity entity){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setString("entityName", "null");
			stack.getTagCompound().setInteger("color", 0);
		}
		String entityName = EntityList.CLASS_TO_NAME.get(entity.getClass());
		
		stack.getTagCompound().setString("name",entity.getName());
		stack.getTagCompound().setString("entityName", entityName);
		stack.getTagCompound().setInteger("color", EntityList.ENTITY_EGGS.get(EntityList.CLASS_TO_NAME.get(entity.getClass())).primaryColor);
		return stack;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
		if (stack.hasTagCompound()){
			tooltip.add(stack.getTagCompound().getString("name"));
		}
	}
	
	public static class ColorHandler implements IItemColor {
		@Override
		public int getColorFromItemstack(ItemStack stack, int layer) {
			if (stack.getItem() instanceof ItemBloodBottle){
				if (layer == 0){
					if(stack.hasTagCompound()){
						return stack.getTagCompound().getInteger("color");
					}
					return 16777215;
				}
			}
			return 16777215;
		}
	}
}
