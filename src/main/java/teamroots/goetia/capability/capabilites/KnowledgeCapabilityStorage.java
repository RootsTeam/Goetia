package teamroots.goetia.capability.capabilites;

import java.util.ArrayList;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.util.Constants;
import teamroots.goetia.lib.LibMain;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class KnowledgeCapabilityStorage implements IStorage<IKnowledgeCapability>
{

    public static final KnowledgeCapabilityStorage storage = new KnowledgeCapabilityStorage();

    @Override
    public NBTBase writeNBT(Capability<IKnowledgeCapability> capability, IKnowledgeCapability instance, EnumFacing side) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        NBTTagList list = new NBTTagList();
        ArrayList<String> knowledge = instance.getKnowledge();
        for (int i = 0; i < knowledge.size(); i ++){
        	list.appendTag(new NBTTagString(knowledge.get(i)));
        }
        tagCompound.setTag("knowledge", list);
        return tagCompound;
    }

    @Override
    public void readNBT(Capability<IKnowledgeCapability> capability, IKnowledgeCapability instance, EnumFacing side, NBTBase nbt) {
        NBTTagCompound tag =(NBTTagCompound)nbt;
        NBTTagList list = tag.getTagList("knowledge", Constants.NBT.TAG_STRING);
        ArrayList<String> knowledge = new ArrayList<String>();
        for (int i = 0; i < list.tagCount(); i ++){
        	knowledge.add(list.getStringTagAt(i));
        }
        instance.setKnowledge(knowledge);
    }
}
