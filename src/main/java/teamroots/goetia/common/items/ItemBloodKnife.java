package teamroots.goetia.common.items;

import com.google.common.collect.Multimap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ItemBloodKnife extends ItemSwordBase{
	private static ToolMaterial blood = EnumHelper.addToolMaterial("blood", 3, 50, 3, -3.0f, 3);
	
	public ItemBloodKnife(String name) {
		super(name, blood);
	}
	
	@Override
	public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot){
	    Multimap<String, AttributeModifier> multimap = super.getItemAttributeModifiers(equipmentSlot);

	    if (equipmentSlot == EntityEquipmentSlot.MAINHAND){	
	    	multimap.removeAll(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName());      
	    	multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getAttributeUnlocalizedName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", 0.0D, 0));
	    }
	    return multimap;
	}
	
	@Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        player.getFoodStats().addExhaustion(1.0f);
        return false;
    }

}
