package teamroots.goetia.spellcasting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import teamroots.goetia.lib.LibMain;

public class SpellInnerFire extends CastSpell{

	public SpellInnerFire() {
		super("innerFire", 111, new int[]{2,1,4,5,6}, new ItemStack(Items.FLINT_AND_STEEL));
	}
	
	@Override
	public void doEffect(EntityPlayer caster){
		for (float i = 0; i < 360; i += 7.5){
			float offX = 0.75f*(float)Math.sin(Math.toRadians(i));
			float offZ = 0.75f*(float)Math.cos(Math.toRadians(i));
			if (random.nextInt(2) == 0){
				caster.getEntityWorld().spawnParticle(EnumParticleTypes.FLAME, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0, 0, 0);
			}
			if (random.nextInt(2) == 0){
				caster.getEntityWorld().spawnParticle(EnumParticleTypes.CLOUD, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0.5*random.nextFloat(), 0, 0);
			}
			caster.getEntityWorld().spawnParticle(EnumParticleTypes.CLOUD, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0.5*random.nextFloat(), 0, 0);
		}
		caster.getEntityData().setInteger(LibMain.LibNBT.inner_fire_tag, 2400);
	}
}
