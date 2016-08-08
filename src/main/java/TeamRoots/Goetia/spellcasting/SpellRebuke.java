package teamroots.goetia.spellcasting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import teamroots.goetia.lib.LibMain;

public class SpellRebuke extends CastSpell{

	public SpellRebuke() {
		super("rebuke", 73, new int[]{5,2,4,6,2}, new ItemStack(Items.BOW));
	}
	
	@Override
	public void doEffect(EntityPlayer caster){
		caster.getEntityData().setInteger(LibMain.LibNBT.rebuke_tag, 600);
	}
}
