package teamroots.goetia.spellcasting.angel;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.AlignmentType;

public class SpellInnerGrace extends CastSpell{
	
	public SpellInnerGrace() {
		super("innerGrace", 111, new int[]{3,2,1,4,5,6}, new ItemStack(Items.DIAMOND), AlignmentType.ANGEL);
	}
	
	@Override
	public void doEffect(EntityPlayer caster){
		for (float i = 0; i < 360; i += 7.5){
			float offX = 0.75f*(float)Math.sin(Math.toRadians(i));
			float offZ = 0.75f*(float)Math.cos(Math.toRadians(i));
			if (random.nextInt(3) == 0){
				caster.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0, 0, 0);
			}
			if (random.nextInt(3) == 0){
				caster.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT_MAGIC, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
			}
			caster.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
		}
		caster.getEntityData().setInteger(LibMain.LibNBT.inner_firegrace_tag, 2400);
	}
}
