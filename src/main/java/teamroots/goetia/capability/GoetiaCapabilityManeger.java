package teamroots.goetia.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.goetia.capability.impurity.DefaultImpurityCapability;
import teamroots.goetia.capability.impurity.IImpurityCapability;
import teamroots.goetia.capability.impurity.ImpurityCapabilityStorage;
import teamroots.goetia.capability.impurity.ImpurityProvider;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class GoetiaCapabilityManeger
{
    public static void register()
    {
        CapabilityManager.INSTANCE.register(IImpurityCapability.class,new ImpurityCapabilityStorage(),DefaultImpurityCapability.class);
    }

    @SubscribeEvent
    public void addCapabilitys(AttachCapabilitiesEvent.Entity event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            if(!event.getEntity().hasCapability(ImpurityProvider.impurityCapability,null))
            {
                event.addCapability(new ResourceLocation("goetia:impurityCapability"),new ImpurityProvider(new DefaultImpurityCapability()));
            }
        }
    }
}
