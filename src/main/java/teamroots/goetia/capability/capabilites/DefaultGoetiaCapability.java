package teamroots.goetia.capability.capabilites;

import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import teamroots.goetia.common.network.GoetiaPacketHandler;
import teamroots.goetia.common.network.ImpurityUpdateMessage;
import teamroots.goetia.common.util.handler.ConfigHandler;
import teamroots.goetia.spellcasting.AlignmentType;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class DefaultGoetiaCapability implements IGoetiaCapability
{
	private int thresh = ConfigHandler.alignmentThreshold;
	
    public int impurity = 0;
    public int purity = 0;
    

    @Override
    public int getImpurity() {
        return impurity;
    }

    @Override
    public void setImpurity(EntityPlayer player, int impurity) {
        this.impurity = impurity;
        if(impurity < 0)
        {
            this.impurity = 0;
        }
        dataChanged(player);
    }
    
    @Override
	public void addImpurity(EntityPlayer player, int impurity) {
    	if(purity < thresh){
    		this.impurity += impurity;
    		dataChanged(player);
    	}	
	}
    
    @Override
   	public void addImpurityRegardless(EntityPlayer player, int impurity) {
       	this.impurity += impurity;
       	dataChanged(player);
   	}
    
    @Override
	public int getPurity() {
		return purity;
	}

	@Override
	public void setPurity(EntityPlayer player, int purity) {
		 this.purity = purity;
	     if(purity < 0)
	     {
	        this.purity = 0;
	     }
	     dataChanged(player);
	}
	
	@Override
	public void addPurity(EntityPlayer player, int purity) {
		if(impurity < thresh){
			this.purity += purity;
			dataChanged(player);
		}
	}
	
	@Override
	public void addPurityRegardless(EntityPlayer player, int purity) {
		this.purity += purity;
		dataChanged(player);
	}

    @Override
    public NBTTagCompound saveData() {
        return (NBTTagCompound)GoetiaCapabilityStorage.storage.writeNBT(GoetiaProvider.goetiaCapability, this,null);
    }

    @Override
    public void loadNBTData(NBTTagCompound tagCompound) {
    	GoetiaCapabilityStorage.storage.readNBT(GoetiaProvider.goetiaCapability,this,null,tagCompound);
    }

    @Override
    public void dataChanged(EntityPlayer player) {
    	if(player != null && !player.getEntityWorld().isRemote){
			GoetiaPacketHandler.INSTANCE.sendTo(new ImpurityUpdateMessage(player, saveData()), (EntityPlayerMP) player);
			EntityTracker entitytracker = ((WorldServer)player.worldObj).getEntityTracker();
			entitytracker.sendToAllTrackingEntity(player, GoetiaPacketHandler.INSTANCE.getPacketFrom(new ImpurityUpdateMessage(player, saveData())));
    	}
    }

	@Override
	public AlignmentType getAligningTowards() {
		if(this.impurity > this.purity){
			return AlignmentType.DEMON;
		}
		return AlignmentType.ANGEL;
	}

	@Override
	public AlignmentType getAlignment() {
		if(purity >= thresh){
			return AlignmentType.ANGEL;
		} else if(impurity >= thresh) {
			return AlignmentType.DEMON;
		}
		return AlignmentType.HUMAN;
	}
}
