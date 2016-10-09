package teamroots.goetia.capability.capabilites;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import teamroots.goetia.common.network.GoetiaPacketHandler;
import teamroots.goetia.common.network.KnowledgeUpdateMessage;
import teamroots.goetia.proxy.CommonProxy;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class DefaultKnowledgeCapability implements IKnowledgeCapability
{

    public ArrayList<String> knowledge = new ArrayList<String>();


    @Override
    public NBTTagCompound saveData() {
        return (NBTTagCompound)KnowledgeCapabilityStorage.storage.writeNBT(KnowledgeProvider.knowledgeCapability, this,null);
    }

    @Override
    public void loadNBTData(NBTTagCompound tagCompound) {
    	KnowledgeCapabilityStorage.storage.readNBT(KnowledgeProvider.knowledgeCapability,this,null,tagCompound);
    }

    @Override
    public void dataChanged(EntityPlayer player) {
    	if(player != null && !player.getEntityWorld().isRemote){
			GoetiaPacketHandler.INSTANCE.sendTo(new KnowledgeUpdateMessage(saveData()), (EntityPlayerMP) player);
		}
    }
    
    @Override
    public void setKnowledge(ArrayList<String> knowledge){
    	this.knowledge = knowledge;
    }
    
    @Override
    public void setKnowledgeWithNotify(EntityPlayer player, ArrayList<String> knowledge){
    	this.dataChanged(player);
    	this.knowledge = knowledge;
    }

	@Override
	public ArrayList<String> getKnowledge() {
		return knowledge;
	}

	@Override
	public boolean hasKnowledge(String name) {
		for (int i = 0; i < knowledge.size(); i ++){
			if (knowledge.get(i).equals(name)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void addKnowledge(EntityPlayer player, String name) {
		knowledge.add(name);
		if (player != null){
			dataChanged(player);
		}
	}
}
