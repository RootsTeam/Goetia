package teamroots.goetia.common;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryTable;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
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
			if (event.getEntityLiving().getEntityData().hasKey(LibMain.LibNBT.burning_touch_tag)){
				event.getEntityLiving().getEntityData().setInteger(LibMain.LibNBT.burning_touch_tag, event.getEntityLiving().getEntityData().getInteger(LibMain.LibNBT.burning_touch_tag)-1);
				if (event.getEntityLiving().getEntityData().getInteger(LibMain.LibNBT.burning_touch_tag) <= 0){
					event.getEntityLiving().getEntityData().removeTag(LibMain.LibNBT.burning_touch_tag);
				}
			}
			if (event.getEntityLiving().getEntityData().hasKey(LibMain.LibNBT.rebuke_tag)){
				event.getEntityLiving().getEntityData().setInteger(LibMain.LibNBT.rebuke_tag, event.getEntityLiving().getEntityData().getInteger(LibMain.LibNBT.rebuke_tag)-1);
				if (event.getEntityLiving().getEntityData().getInteger(LibMain.LibNBT.rebuke_tag) <= 0){
					event.getEntityLiving().getEntityData().removeTag(LibMain.LibNBT.rebuke_tag);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void entityHit(LivingAttackEvent evt){
		if(evt.getSource().getDamageType() == "player"){
			EntityPlayer player = (EntityPlayer)evt.getSource().getEntity();
			if(player.getEntityData().hasKey(LibMain.LibNBT.burning_touch_tag)){
				evt.getEntity().setFire(5);
			}
		}
		if (evt.getEntityLiving() instanceof EntityPlayer){
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

    private LootPool getInjectPool(String entryName) {
        return new LootPool(new LootEntry[] { getInjectEntry(entryName, 1) }, new LootCondition[0], new RandomValueRange(1), new RandomValueRange(0, 1), "goetiaInjectedPool");
    }

    private LootEntryTable getInjectEntry(String name, int weight) {
        return new LootEntryTable(new ResourceLocation(LibMain.LibCore.MOD_ID, "inject/" + name), weight, 0, new LootCondition[0], "goetiaInjectedLoot");
    }
}
