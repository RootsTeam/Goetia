package teamroots.goetia.capability.impurity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class KnowledgeProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{

    private IKnowledgeCapability KnowledgeCapability = null;

    public KnowledgeProvider()
    {
    	KnowledgeCapability = new DefaultKnowledgeCapability();
    }

    public KnowledgeProvider(IKnowledgeCapability c)
    {
        this.KnowledgeCapability = c;
    }

    @CapabilityInject(IKnowledgeCapability.class)
    public static final Capability<IKnowledgeCapability> knowledgeCapability = null;

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == knowledgeCapability;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(knowledgeCapability != null && capability == knowledgeCapability)return (T)KnowledgeCapability;
        return null;
    }

    public static IKnowledgeCapability get(EntityPlayer player)
    {
        return player != null && player.hasCapability(knowledgeCapability,null) ? player.getCapability(knowledgeCapability,null): null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return KnowledgeCapability.saveData();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        KnowledgeCapability.loadNBTData(nbt);
    }
}
