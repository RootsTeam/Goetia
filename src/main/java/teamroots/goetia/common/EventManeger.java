package teamroots.goetia.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.goetia.registry.MainRegistry;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class EventManeger
{
    @SubscribeEvent
    public void onEntityHurt(LivingHurtEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) event.getEntity();
        if (player.inventory.getCurrentItem().getItem() == new ItemStack(MainRegistry.demonHornSpear).getItem()) {
            player.getFoodStats().addStats(1, 0.5f);
            // player.addChatComponentMessage(new TextComponentString("It works!"));
        } else {
            //player.addChatComponentMessage(new TextComponentString("Debug: doesnt work"));
        }
    }
}
