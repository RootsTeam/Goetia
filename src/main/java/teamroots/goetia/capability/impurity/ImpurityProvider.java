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
public class ImpurityProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{

    private IImpurityCapability ImpureCapability = null;

    public ImpurityProvider()
    {
        ImpureCapability = new   DefaultImpurityCapability();
    }

    public ImpurityProvider(IImpurityCapability c)
    {
        this.ImpureCapability = c;
    }

    @CapabilityInject(IImpurityCapability.class)
    public static final Capability<IImpurityCapability> impurityCapability = null;

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == impurityCapability;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(impurityCapability != null && capability == impurityCapability)return (T)ImpureCapability;
        return null;
    }

    public static IImpurityCapability get(EntityPlayer player)
    {
        return player != null && player.hasCapability(impurityCapability,null) ? player.getCapability(impurityCapability,null): null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return ImpureCapability.saveData();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        ImpureCapability.loadNBTData(nbt);
    }
}
