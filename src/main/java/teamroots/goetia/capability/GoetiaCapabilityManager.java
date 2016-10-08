package teamroots.goetia.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamroots.goetia.capability.impurity.DefaultGoetiaCapability;
import teamroots.goetia.capability.impurity.DefaultKnowledgeCapability;
import teamroots.goetia.capability.impurity.GoetiaCapabilityStorage;
import teamroots.goetia.capability.impurity.GoetiaProvider;
import teamroots.goetia.capability.impurity.IGoetiaCapability;
import teamroots.goetia.capability.impurity.IKnowledgeCapability;
import teamroots.goetia.capability.impurity.KnowledgeCapabilityStorage;
import teamroots.goetia.capability.impurity.KnowledgeProvider;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class GoetiaCapabilityManager
{
    public static void register()
    {
        CapabilityManager.INSTANCE.register(IGoetiaCapability.class,new GoetiaCapabilityStorage(),DefaultGoetiaCapability.class);
        CapabilityManager.INSTANCE.register(IKnowledgeCapability.class,new KnowledgeCapabilityStorage(),DefaultKnowledgeCapability.class);
    }

    @SubscribeEvent
    public void addCapabilities(AttachCapabilitiesEvent.Entity event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            if(!event.getEntity().hasCapability(GoetiaProvider.goetiaCapability,null))
            {
                event.addCapability(new ResourceLocation("goetia:impurityCapability"),new GoetiaProvider(new DefaultGoetiaCapability()));
            }
            if(!event.getEntity().hasCapability(KnowledgeProvider.knowledgeCapability,null))
            {
                event.addCapability(new ResourceLocation("goetia:knowledgeCapability"),new KnowledgeProvider(new DefaultKnowledgeCapability()));
            }
        }
    }
}
