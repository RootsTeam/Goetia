package teamroots.goetia.spellcasting.angel;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.AlignmentType;

public class SpellAngelicWings extends CastSpell{

	public SpellAngelicWings() {
		super("angelicWings", 196, new int[]{1,2,3,4,5,6,1,4,6,3,1}, new ItemStack(Items.FEATHER), AlignmentType.ANGEL);
	}
	
	@Override
	public void doEffect(EntityPlayer caster){
		for (float i = 0; i < 360; i += 7.5){
			float offX = 0.75f*(float)Math.sin(Math.toRadians(i));
			float offZ = 0.75f*(float)Math.cos(Math.toRadians(i));
			if (random.nextInt(2) == 0){
				caster.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT_MAGIC, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
			}
			caster.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
		}
		caster.getEntityData().setInteger(LibMain.LibNBT.wings_tag, 1800);
		if (!caster.capabilities.isCreativeMode){
			caster.capabilities.allowFlying = true;
		}
	}
}
