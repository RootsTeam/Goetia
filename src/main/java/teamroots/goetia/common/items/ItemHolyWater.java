package teamroots.goetia.common.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import teamroots.goetia.common.entity.IDemonic;

public class ItemHolyWater extends ItemBase{

	public ItemHolyWater(String name) {
		super(name);
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack itemstack, net.minecraft.entity.player.EntityPlayer player, EntityLivingBase entity, net.minecraft.util.EnumHand hand){
		if(entity instanceof IDemonic){
			itemstack.stackSize--;
			((IDemonic) entity).onHolyWaterContact();
			if(!player.capabilities.isCreativeMode){
				player.inventory.addItemStackToInventory(new ItemStack(Items.GLASS_BOTTLE));
			}
			return true;
		}
		return false;
	}
}
