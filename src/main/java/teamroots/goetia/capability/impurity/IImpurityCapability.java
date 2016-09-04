package teamroots.goetia.capability.impurity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public interface IImpurityCapability
{
    float getImpurity();
    void setImpurity(EntityPlayer player, float impurity);
    NBTTagCompound saveData();
    void loadNBTData(NBTTagCompound tagCompound);
    void dataChanged(EntityPlayer player);
}
