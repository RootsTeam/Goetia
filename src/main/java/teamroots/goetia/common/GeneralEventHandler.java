package teamroots.goetia.common;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.Goetia;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.capability.capabilites.KnowledgeProvider;
import teamroots.goetia.common.entity.IClickableSymbol;
import teamroots.goetia.common.entity.ISymbol;
import teamroots.goetia.common.network.GoetiaPacketHandler;
import teamroots.goetia.common.network.ImpurityUpdateMessage;
import teamroots.goetia.common.util.handler.ConfigHandler;
import teamroots.goetia.lib.EnumIDs;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.renderlayers.LayerHalo;
import teamroots.goetia.renderlayers.LayerHorns;
import teamroots.goetia.spellcasting.AlignmentType;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class GeneralEventHandler
{
	Random random = new Random();
	@SubscribeEvent
	public void playerTracking(PlayerEvent.StartTracking e){
		 if(e.getTarget() instanceof EntityPlayer){
             if(!e.getEntityPlayer().worldObj.isRemote){
            	 System.out.println("sent");
                    GoetiaPacketHandler.INSTANCE.sendTo(new ImpurityUpdateMessage((EntityPlayer)e.getTarget(), GoetiaProvider.get((EntityPlayer)e.getTarget()).saveData()), (EntityPlayerMP) e.getEntityPlayer());
             }
		 }
	}
	
	@SubscribeEvent
	public void entityJoinWorld(EntityJoinWorldEvent e) {
		if (e.getEntity() instanceof EntityPlayer && !e.getEntity().worldObj.isRemote)
		{
			GoetiaProvider.get((EntityPlayer) e.getEntity()).dataChanged((EntityPlayer) e.getEntity());
		}
	}
	
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
			
			decreaseEffect(LibMain.LibNBT.burning_touch_tag, player);
			decreaseEffect(LibMain.LibNBT.rebuke_tag, player);
			decreaseEffect(LibMain.LibNBT.fallen_armor_tag, player);
			decreaseEffect(LibMain.LibNBT.chained_strikes_tag, player);
			decreaseEffect(LibMain.LibNBT.wings_tag, player);
			decreaseEffect(LibMain.LibNBT.voracious_strikes_tag, player);
			decreaseEffect(LibMain.LibNBT.inner_firegrace_tag, player);
		}
	}
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onGameOverlayRender(RenderGameOverlayEvent.Post e){
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (e.getType() == ElementType.TEXT){
			int w = e.getResolution().getScaledWidth();
			int h = e.getResolution().getScaledHeight();
			
			GlStateManager.popMatrix();
			GlStateManager.color(1, 1, 1, 1);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(	new ResourceLocation("goetia:textures/gui/guiOverlay.png"));
			
			int offsetX = ConfigHandler.alignmentBarPosX;
			int offsetY = ConfigHandler.alignmentBarPosY;
			if(GoetiaProvider.get(player).isMoreImpure()){
				Gui.drawModalRectWithCustomSizedTexture(e.getResolution().getScaledWidth() - offsetX, e.getResolution().getScaledHeight() - offsetY, 0, 0, 20, 20, 256, 256);
				Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(Integer.toString(GoetiaProvider.get(player).getImpurity()), (w - offsetX) + 20, (h - offsetY) + 8, LibMain.LibColors.demon_color);
			} else {
				Gui.drawModalRectWithCustomSizedTexture(e.getResolution().getScaledWidth() - offsetX, e.getResolution().getScaledHeight() - offsetY, 21, 0, 20, 20, 256, 256);
				Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(Integer.toString(GoetiaProvider.get(player).getPurity()), (w - offsetX) + 20, (h - offsetY) + 8, LibMain.LibColors.angel_color);
			}
			
			GlStateManager.pushMatrix();
		}
	}
	
	@SubscribeEvent
	public void entityDeath(LivingDeathEvent e){
		if(e.getEntity() instanceof EntityMob && e.getSource().damageType == "player"){
			EntityPlayer player = (EntityPlayer) e.getSource().getEntity();
			if(!player.worldObj.isRemote){
				GoetiaProvider.get(player).addPurity(player, 1);
			}
			
		}
		if(e.getEntity() instanceof EntityAnimal && e.getSource().damageType == "player"){
			EntityPlayer player = (EntityPlayer) e.getSource().getEntity();
			if(!player.worldObj.isRemote){
				GoetiaProvider.get(player).addImpurity(player, 1);
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
			
			if(player.getEntityData().hasKey(LibMain.LibNBT.inner_firegrace_tag)){
				float amount = evt.getAmount();
				float newAmount = amount + evt.getAmount() * 0.4F;
				evt.setAmount(newAmount);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerRespawn(PlayerEvent.Clone event){
		if (event.isWasDeath()){
			event.getEntityPlayer().getCapability(GoetiaProvider.goetiaCapability, null).setImpurity(event.getEntityPlayer(), event.getOriginal().getCapability(GoetiaProvider.goetiaCapability, null).getImpurity());
			event.getEntityPlayer().getCapability(KnowledgeProvider.knowledgeCapability, null).setKnowledgeWithNotify(event.getEntityPlayer(), event.getOriginal().getCapability(KnowledgeProvider.knowledgeCapability, null).getKnowledge());
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
