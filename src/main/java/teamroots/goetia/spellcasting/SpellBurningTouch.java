package teamroots.goetia.spellcasting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import teamroots.goetia.lib.LibMain;

public class SpellBurningTouch extends CastSpell{

	public SpellBurningTouch(String name, int impurity, int[] parShape, ItemStack icon) {
		super("burning touch", 30, parShape, new ItemStack(Items.BLAZE_POWDER));
	}
	
	@Override
	public void effect(EntityPlayer caster){
		caster.getEntityData().setInteger(LibMain.LibNBT.burning_touch_tag, 600);
	}

}
