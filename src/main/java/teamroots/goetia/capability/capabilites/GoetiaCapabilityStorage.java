package teamroots.goetia.capability.capabilites;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import teamroots.goetia.lib.LibMain;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class GoetiaCapabilityStorage implements IStorage<IGoetiaCapability>
{

    public static final GoetiaCapabilityStorage storage = new GoetiaCapabilityStorage();

    @Override
    public NBTBase writeNBT(Capability<IGoetiaCapability> capability, IGoetiaCapability instance, EnumFacing side) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setInteger(LibMain.LibNBT.impurity_tag,((DefaultGoetiaCapability)instance).impurity);
        tagCompound.setInteger(LibMain.LibNBT.purity_tag, ((DefaultGoetiaCapability)instance).purity);
        tagCompound.setBoolean("locked", ((DefaultGoetiaCapability)instance).locked);
        return tagCompound;
    }

    @Override
    public void readNBT(Capability<IGoetiaCapability> capability, IGoetiaCapability instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound tag =(NBTTagCompound)nbt;
        ((DefaultGoetiaCapability)instance).impurity = tag.getInteger(LibMain.LibNBT.impurity_tag);
        ((DefaultGoetiaCapability)instance).purity = tag.getInteger(LibMain.LibNBT.purity_tag);
        ((DefaultGoetiaCapability)instance).locked = tag.getBoolean("locked");
    }
}
