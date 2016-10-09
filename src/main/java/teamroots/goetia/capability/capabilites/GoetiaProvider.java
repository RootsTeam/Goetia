package teamroots.goetia.capability.capabilites;

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
public class GoetiaProvider implements ICapabilityProvider, INBTSerializable<NBTTagCompound>
{

    private IGoetiaCapability GoetiaCapability = null;

    public GoetiaProvider()
    {
    	GoetiaCapability = new DefaultGoetiaCapability();
    }

    public GoetiaProvider(IGoetiaCapability c)
    {
        this.GoetiaCapability = c;
    }

    @CapabilityInject(IGoetiaCapability.class)
    public static final Capability<IGoetiaCapability> goetiaCapability = null;

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == goetiaCapability;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(goetiaCapability != null && capability == goetiaCapability)return (T)GoetiaCapability;
        return null;
    }

    public static IGoetiaCapability get(EntityPlayer player)
    {
        return player != null && player.hasCapability(goetiaCapability,null) ? player.getCapability(goetiaCapability,null): null;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        return GoetiaCapability.saveData();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
    	GoetiaCapability.loadNBTData(nbt);
    }
}
