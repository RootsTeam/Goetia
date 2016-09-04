package teamroots.goetia.capability.impurity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import teamroots.goetia.common.network.GoetiaPacketHandler;
import teamroots.goetia.common.network.ImpurityUpdateMessage;
import teamroots.goetia.common.network.KnowledgeUpdateMessage;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class DefaultImpurityCapability implements IImpurityCapability
{

    public float impurity = 0;

    @Override
    public float getImpurity() {
        return impurity;
    }

    @Override
    public void setImpurity(EntityPlayer player, float impurity) {
        this.impurity = impurity;
        if(impurity < 0)
        {
            this.impurity = 0;
        }
        dataChanged(player);
    }

    @Override
    public NBTTagCompound saveData() {
        return (NBTTagCompound)ImpurityCapabilityStorage.storage.writeNBT(ImpurityProvider.impurityCapability, this,null);
    }

    @Override
    public void loadNBTData(NBTTagCompound tagCompound) {
            ImpurityCapabilityStorage.storage.readNBT(ImpurityProvider.impurityCapability,this,null,tagCompound);
    }

    @Override
    public void dataChanged(EntityPlayer player) {
    	if(player != null && !player.getEntityWorld().isRemote){
			GoetiaPacketHandler.INSTANCE.sendTo(new ImpurityUpdateMessage(saveData()), (EntityPlayerMP) player);
		}
    }
}
