package teamroots.goetia.common;

import java.util.Random;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.goetia.Goetia;
import teamroots.goetia.common.entity.IClickableSymbol;
import teamroots.goetia.common.entity.ISymbol;
import teamroots.goetia.lib.EnumIDs;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.registry.MainRegistry;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class EventManager
{
	Random random = new Random();
	@SubscribeEvent
	public void onRightClickEntity(PlayerInteractEvent.EntityInteract event){
		if (event.getTarget() instanceof ISymbol){
			if (!((ISymbol)event.getTarget()).isActivated() && event.getEntityPlayer().experienceLevel > ((ISymbol)event.getTarget()).getLevelCost()){
				event.getEntityPlayer().removeExperienceLevel(((ISymbol)event.getTarget()).getLevelCost());
				((ISymbol)event.getTarget()).activate(event.getEntityPlayer());
			}
			else if (event.getTarget() instanceof IClickableSymbol && ((ISymbol)event.getTarget()).isActivated()){
				((IClickableSymbol)event.getTarget()).onRightClick(event.getWorld(), event.getTarget(), event.getEntityPlayer());
			}
		}
	}
	
	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event){
		if (event.getWorld().getBlockState(event.getPos()).getBlock() == MainRegistry.altar){
			event.getEntityPlayer().openGui(Goetia.instance, EnumIDs.GUI_ID_ALTAR.ordinal(), event.getWorld(), (int)event.getEntityPlayer().posX, (int)event.getEntityPlayer().posY, (int)event.getEntityPlayer().posZ);
		}
	}
	
	@SubscribeEvent
    public void lootLoad(LootTableLoadEvent evt) {
        String prefix = "minecraft:chests/";
        String name = evt.getName().toString();

        if (name.startsWith(prefix)) {
            String file = name.substring(name.indexOf(prefix) + prefix.length());
            if (file.equals("simple_dungeon")){
                evt.getTable().addPool(getInjectPool(file));
            }
        }
    }
	
	@SubscribeEvent
	public void livingTickEvent(LivingUpdateEvent event){
		if (event.getEntityLiving() instanceof EntityPlayer){
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if (player.getEntityData().hasKey(LibMain.LibNBT.ebon_wings_tag) && !player.onGround){
				for (float i = 0; i < 360; i += 45.0f+45.0f*random.nextFloat()){
					float offX = 0.5f*(float)Math.sin(Math.toRadians(i));
					float offZ = 0.5f*(float)Math.cos(Math.toRadians(i));
					if (random.nextInt(2) == 0){
						player.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
					}
					player.getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, player.posX+offX, player.posY+player.getEyeHeight()/2.0, player.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
				}
			}
			if (player.getEntityData().hasKey(LibMain.LibNBT.inner_fire_tag)){
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
			
			decreaseEffect(LibMain.LibNBT.burning_touch_tag, player);
			decreaseEffect(LibMain.LibNBT.rebuke_tag, player);
			decreaseEffect(LibMain.LibNBT.fallen_armor_tag, player);
			decreaseEffect(LibMain.LibNBT.chained_strikes_tag, player);
			decreaseEffect(LibMain.LibNBT.ebon_wings_tag, player);
			decreaseEffect(LibMain.LibNBT.voracious_strikes_tag, player);
			decreaseEffect(LibMain.LibNBT.inner_fire_tag, player);
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
				if (random.nextInt(3) == 0){
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
	

	@SubscribeEvent
	public void entityHurt(LivingHurtEvent evt){
		if(evt.getSource().getDamageType() == "player"){
			EntityPlayer player = (EntityPlayer)evt.getSource().getEntity();
			if(player.getEntityData().hasKey(LibMain.LibNBT.voracious_strikes_tag)){
				if(player.getHeldItemMainhand() == null){
					evt.getEntity().hurtResistantTime = 0;
					evt.setAmount(2.0F);
				}
			}
			
			if(player.getEntityData().hasKey(LibMain.LibNBT.inner_fire_tag)){
				float amount = evt.getAmount();
				float newAmount = amount + evt.getAmount() * 0.4F;
				System.out.println(amount + "  " + newAmount);
				evt.setAmount(newAmount);
			}
		}
	}
	

    private LootPool getInjectPool(String entryName) {
        return new LootPool(new LootEntry[] { getInjectEntry(entryName, 1) }, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "goetiaInjectedPool");
    }

    private LootEntryTable getInjectEntry(String name, int weight) {
        return new LootEntryTable(new ResourceLocation(LibMain.LibCore.MOD_ID, "inject/" + name), weight, 0, new LootCondition[0], "goetiaInjectedLoot");
    }
    
    public void decreaseEffect(String tag, EntityPlayer player){
    	if (player.getEntityData().hasKey(tag)){
			player.getEntityData().setInteger(tag, player.getEntityData().getInteger(tag)-1);
			if (player.getEntityData().getInteger(tag) <= 0){
				player.getEntityData().removeTag(tag);
			}
		}
    }
}
