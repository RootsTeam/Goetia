package teamroots.goetia.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileEntityBowl extends TileEntity{
	public int color = 0;
	public String entity = "";
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.color = compound.getInteger("color");
        this.entity = compound.getString("entity");
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
		super.writeToNBT(compound);
		compound.setInteger("color", this.color);
		compound.setString("entity", this.entity);
        return compound;
    }
	
	public void setColor(BlockPos pos, IBlockState state, int color){
		this.color = color;
		this.markDirty();
		this.getWorld().notifyBlockUpdate(pos, state, this.getWorld().getBlockState(pos), 3);
	}
	
	public int getColor(){
		return this.color;
	}
	
	public void setEntity(BlockPos pos, IBlockState state, String entity){
		this.entity = entity;
		this.markDirty();
		this.getWorld().notifyBlockUpdate(pos, state, this.getWorld().getBlockState(pos), 3);
	}
	
	public String getEntity(){
		return this.entity;
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet){
		readFromNBT(packet.getNbtCompound());
	}
	
	@Override
	public NBTTagCompound getUpdateTag(){
		return writeToNBT(new NBTTagCompound());
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket(){
		return new SPacketUpdateTileEntity(getPos(), 0, getUpdateTag());
	}

}
