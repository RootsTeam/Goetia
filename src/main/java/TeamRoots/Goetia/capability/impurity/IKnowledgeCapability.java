package teamroots.goetia.capability.impurity;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public interface IKnowledgeCapability
{
    ArrayList<String> getKnowledge();
    boolean hasKnowledge(String name);
    void addKnowledge(EntityPlayer player, String name);
    NBTTagCompound saveData();
    void loadNBTData(NBTTagCompound tagCompound);
    void dataChanged(EntityPlayer player);
	void setKnowledge(ArrayList<String> knowledge);
}
