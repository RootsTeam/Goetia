package teamroots.goetia.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityScroll extends TileEntity{
	public String knowledge = "";
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.knowledge = compound.getString("knowledge");
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
		super.writeToNBT(compound);
		compound.setString("knowledge", this.knowledge);
        return compound;
    }
	
	public void setKnowledge(String knowledge){
		this.knowledge = knowledge;
	}
	
	public String getKnowledge(){
		return this.knowledge;
	}

}
