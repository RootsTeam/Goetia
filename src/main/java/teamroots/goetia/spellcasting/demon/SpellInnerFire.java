package teamroots.goetia.spellcasting.demon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.AlignmentType;

public class SpellInnerFire extends CastSpell{

	public SpellInnerFire() {
		super("innerFire", 111, new int[]{3,2,1,4,5,6}, new ItemStack(Items.FLINT_AND_STEEL), AlignmentType.DEMON);
	}
	
	@Override
	public void doEffect(EntityPlayer caster){
		for (float i = 0; i < 360; i += 7.5){
			float offX = 0.75f*(float)Math.sin(Math.toRadians(i));
			float offZ = 0.75f*(float)Math.cos(Math.toRadians(i));
			float offY = 2*random.nextFloat();
			if (random.nextInt(3) == 0){
				caster.getEntityWorld().spawnParticle(EnumParticleTypes.FLAME, caster.posX+offX, caster.posY+offY, caster.posZ+offZ, 0, 0, 0, 0);
			}
			if (random.nextInt(3) == 0){
				caster.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, caster.posX+offX, caster.posY+offY, caster.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
			}
			caster.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, caster.posX+offX, caster.posY+offY, caster.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
		}
		caster.getEntityData().setInteger(LibMain.LibNBT.inner_firegrace_tag, 2400);
	}
}
