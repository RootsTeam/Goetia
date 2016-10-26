package teamroots.goetia.spellcasting.angel;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import teamroots.goetia.common.util.Utils;
import teamroots.goetia.spellcasting.AlignmentType;
import teamroots.goetia.spellcasting.CastSpell;

public class SpellStorm extends CastSpell{
	public SpellStorm() {
		super("storm", 540, new int[]{4,5,3,1}, new ItemStack(Items.GHAST_TEAR), AlignmentType.ANGEL);
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
	   
	   double pY = caster.posY;
	   double pX = caster.posX;
	   double pZ = caster.posZ;
	   if(!caster.getEntityWorld().isRemote){
		   for(int i = 0; i < 8; i++){
			   EntityLightningBolt bolt = new EntityLightningBolt(caster.getEntityWorld(), pX + Utils.randomNumber(-8, 8) + 3, pY, pZ + Utils.randomNumber(-8, 8) + 3, false);
			   caster.getEntityWorld().addWeatherEffect(bolt);  
		   }
	   }
	}
}
