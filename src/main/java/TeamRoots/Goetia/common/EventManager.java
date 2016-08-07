package teamroots.goetia.common;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.goetia.common.entity.EntitySymbolImp;
import teamroots.goetia.common.entity.IClickableSymbol;
import teamroots.goetia.common.entity.ISymbol;

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
				((ISymbol)event.getTarget()).activate();
			}
			else if (event.getTarget() instanceof IClickableSymbol && ((ISymbol)event.getTarget()).isActivated()){
				((IClickableSymbol)event.getTarget()).onRightClick(event.getWorld(), event.getTarget(), event.getEntityPlayer());
			}
		}
	}
}
