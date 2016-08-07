package teamroots.goetia.capability.impurity;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import teamroots.goetia.lib.LibMain;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class ImpurityCapabilityStorage implements IStorage<IImpurityCapability>
{

    public static final ImpurityCapabilityStorage storage = new ImpurityCapabilityStorage();

    @Override
    public NBTBase writeNBT(Capability<IImpurityCapability> capability, IImpurityCapability instance, EnumFacing side) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setFloat(LibMain.LibNBT.impurity_tag,((DefaultImpurityCapability)instance).impurity);
        return tagCompound;
    }

    @Override
    public void readNBT(Capability<IImpurityCapability> capability, IImpurityCapability instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound tag =(NBTTagCompound)nbt;
        ((DefaultImpurityCapability)instance).impurity = tag.getFloat(LibMain.LibNBT.impurity_tag);
    }
}
