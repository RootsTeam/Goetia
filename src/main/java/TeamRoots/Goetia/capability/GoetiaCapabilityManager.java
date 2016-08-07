package teamroots.goetia.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.goetia.capability.impurity.DefaultImpurityCapability;
import teamroots.goetia.capability.impurity.DefaultKnowledgeCapability;
import teamroots.goetia.capability.impurity.IImpurityCapability;
import teamroots.goetia.capability.impurity.IKnowledgeCapability;
import teamroots.goetia.capability.impurity.ImpurityCapabilityStorage;
import teamroots.goetia.capability.impurity.ImpurityProvider;
import teamroots.goetia.capability.impurity.KnowledgeCapabilityStorage;
import teamroots.goetia.capability.impurity.KnowledgeProvider;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class GoetiaCapabilityManager
{
    public static void register()
    {
        CapabilityManager.INSTANCE.register(IImpurityCapability.class,new ImpurityCapabilityStorage(),DefaultImpurityCapability.class);
        CapabilityManager.INSTANCE.register(IKnowledgeCapability.class,new KnowledgeCapabilityStorage(),DefaultKnowledgeCapability.class);
    }

    @SubscribeEvent
    public void addCapabilities(AttachCapabilitiesEvent.Entity event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            if(!event.getEntity().hasCapability(ImpurityProvider.impurityCapability,null))
            {
                event.addCapability(new ResourceLocation("goetia:impurityCapability"),new ImpurityProvider(new DefaultImpurityCapability()));
            }
            if(!event.getEntity().hasCapability(KnowledgeProvider.knowledgeCapability,null))
            {
                event.addCapability(new ResourceLocation("goetia:knowledgeCapability"),new KnowledgeProvider(new DefaultKnowledgeCapability()));
            }
        }
    }
}
