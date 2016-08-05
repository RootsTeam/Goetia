package teamroots.goetia.capability.impurity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class DefaultImpurityCapability implements IImpurityCapability
{

    public float impurity = 0,maxImpurity = 40;

    @Override
    public float getImputity() {
        return impurity;
    }

    @Override
    public float getMaxImpurity() {
        return maxImpurity;
    }

    @Override
    public void setImpurity(EntityPlayer player, float impurity) {
        this.impurity = impurity;
        if(impurity < 0)
        {
            this.impurity = 0;
        }
        if(impurity > getMaxImpurity())
        {
            this.impurity = getMaxImpurity();
        }
        dataChanged(player);
    }

    @Override
    public void setMaxImpurity(float maxImpurity) {
            this.maxImpurity = maxImpurity;
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

    }
}
