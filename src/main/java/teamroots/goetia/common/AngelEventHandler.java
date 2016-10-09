package teamroots.goetia.common;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.spellcasting.AlignmentType;

public class AngelEventHandler {
	Random random = new Random();
	
	@SubscribeEvent
	public void livingTickEvent(LivingUpdateEvent event){
		if (event.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if(!GoetiaProvider.get(player).isMoreImpure()){
				
				if (player.getEntityData().hasKey(LibMain.LibNBT.wings_tag) && !player.onGround){
					for (float i = 0; i < 360; i += 45.0f+45.0f*random.nextFloat()){
						float offX = 0.5f*(float)Math.sin(Math.toRadians(i));
						float offZ = 0.5f*(float)Math.cos(Math.toRadians(i));
						if (random.nextInt(2) == 0){
							player.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT_MAGIC, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
						}
						player.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
					}
				}
				
				if (player.getEntityData().hasKey(LibMain.LibNBT.inner_firegrace_tag)){
					for (float i = 0; i < 15; i += 1){
						float offX = random.nextFloat() - 0.5F;
						float offZ = random.nextFloat() - 0.5F;
						player.getEntityWorld().spawnParticle(EnumParticleTypes.CLOUD, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0, 0, 0);
					}
					if(player.onGround){
						player.motionX *= 1.4;
						player.motionZ *= 1.4;
					}	
				}
				
			}
		}
	}
}
