package teamroots.goetia.capability.capabilites;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import teamroots.goetia.spellcasting.AlignmentType;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public interface IGoetiaCapability
{
    int getImpurity();
    void setImpurity(EntityPlayer player, int impurity);
    void addImpurity(EntityPlayer player, int impurity);
    void addImpurityRegardless(EntityPlayer player, int impurity);
    int getPurity();
    void setPurity(EntityPlayer player, int purity);
    void addPurity(EntityPlayer player, int purity);
    void addPurityRegardless(EntityPlayer player, int purity);
    AlignmentType getAligningTowards();
    AlignmentType getAlignment();
    NBTTagCompound saveData();
    void loadNBTData(NBTTagCompound tagCompound);
    void dataChanged(EntityPlayer player);
}
