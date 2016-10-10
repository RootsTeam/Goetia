package teamroots.goetia.common;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.spellcasting.AlignmentType;

public class DemonEventHandler {
	Random random = new Random();
	
	@SubscribeEvent
	public void livingTickEvent(LivingUpdateEvent event){
		if (event.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if(GoetiaProvider.get(player).getAligningTowards() == AlignmentType.DEMON){
				
				if (player.getEntityData().hasKey(LibMain.LibNBT.wings_tag) && !player.onGround){
					for (float i = 0; i < 360; i += 45.0f+45.0f*random.nextFloat()){
						float offX = 0.5f*(float)Math.sin(Math.toRadians(i));
						float offZ = 0.5f*(float)Math.cos(Math.toRadians(i));
						if (random.nextInt(2) == 0){
							player.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
						}
						player.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
					}
				}
				
				if (player.getEntityData().hasKey(LibMain.LibNBT.inner_firegrace_tag)){
					for (float i = 0; i < 15; i += 1){
						float offX = random.nextFloat() - 0.5F;
						float offZ = random.nextFloat() - 0.5F;
						player.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0, 0, 0);
					}
					if(player.onGround){
						player.motionX *= 1.4;
						player.motionZ *= 1.4;
					}	
				}
				
			}
		}
	}
	
	@SubscribeEvent
	public void entityHit(LivingAttackEvent evt){
		if(evt.getSource().getDamageType() == "player"){
			EntityPlayer player = (EntityPlayer)evt.getSource().getEntity();
			if(player.getEntityData().hasKey(LibMain.LibNBT.fallen_armor_tag)){
				player.getEntityData().setInteger(LibMain.LibNBT.fallen_armor_tag, player.getEntityData().getInteger(LibMain.LibNBT.fallen_armor_tag)+60);
				for (float i = 0; i < 360; i += 7.5){
					float offX = 0.75f*(float)Math.sin(Math.toRadians(i));
					float offZ = 0.75f*(float)Math.cos(Math.toRadians(i));
					if (random.nextInt(3) == 0){
						player.getEntityWorld().spawnParticle(EnumParticleTypes.FLAME, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0, 0, 0);
					}
					if (random.nextInt(3) == 0){
						player.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
					}
					player.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
				}
			}
			if(player.getEntityData().hasKey(LibMain.LibNBT.burning_touch_tag)){
				evt.getEntity().setFire(5);
			}
			if(player.getEntityData().hasKey(LibMain.LibNBT.chained_strikes_tag)){
				List<EntityLivingBase> targets = player.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(evt.getEntityLiving().posX-2.5,evt.getEntityLiving().posY-2.0,evt.getEntityLiving().posZ-2.5,evt.getEntityLiving().posX+2.5,evt.getEntityLiving().posY+2.0,evt.getEntityLiving().posZ+2.5));
				for (int i = 0; i < targets.size(); i ++){
					if (targets.get(i).getUniqueID().compareTo(player.getUniqueID()) != 0 && targets.get(i).getUniqueID().compareTo(evt.getEntityLiving().getUniqueID()) != 0){
						targets.get(i).attackEntityFrom(DamageSource.generic, 3.0f);
						targets.get(i).setLastAttacker(player);
						targets.get(i).setRevengeTarget(player);
						for (int j = 0; j < 80; j ++){
							if (random.nextInt(2) == 0){
								targets.get(i).getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, targets.get(i).posX, targets.get(i).posY+targets.get(i).getEyeHeight()/2.0, targets.get(i).posZ, 0.05*(random.nextFloat()-0.5), 0.05*(random.nextFloat()-0.5), 0.05*(random.nextFloat()-0.5), 0);
							}
							targets.get(i).getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, targets.get(i).posX, targets.get(i).posY+targets.get(i).getEyeHeight()/2.0, targets.get(i).posZ, 0.15*(random.nextFloat()-0.5), 0.15*(random.nextFloat()-0.5), 0.15*(random.nextFloat()-0.5), 0);
						}
					}
				}
			}
		}
		if (evt.getEntityLiving() instanceof EntityPlayer){
			if(evt.getEntityLiving().getEntityData().hasKey(LibMain.LibNBT.fallen_armor_tag) && evt.getPhase() == EventPriority.NORMAL){
				if (random.nextInt(2) == 0){
					evt.setCanceled(true);
				}
			}
			if (evt.getSource().getEntity() instanceof EntityLivingBase){
				if(evt.getEntityLiving().getEntityData().hasKey(LibMain.LibNBT.rebuke_tag)){
					Vec3d diffVec = new Vec3d(evt.getEntityLiving().posX-evt.getSource().getEntity().posX,evt.getEntityLiving().posY-evt.getSource().getEntity().posY,evt.getEntityLiving().posZ-evt.getSource().getEntity().posZ);
					diffVec = diffVec.normalize();
					((EntityLivingBase)evt.getSource().getEntity()).knockBack(evt.getSource().getEntity(), 0.55f, diffVec.xCoord, diffVec.zCoord);
					((EntityLivingBase)evt.getSource().getEntity()).setFire(3);
				}
			}
		}
	}
}
