package teamroots.goetia.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import teamroots.goetia.capability.impurity.GoetiaProvider;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class ImpurityUpdateMessage implements IMessage
{
    private NBTTagCompound tagCompounds;
    private int playerID;

    public ImpurityUpdateMessage(){};

    public ImpurityUpdateMessage(EntityPlayer player, NBTTagCompound tagCompound)
    {
      this.tagCompounds = tagCompound;
      this.playerID = player.getEntityId();
    };

    @Override
    public void fromBytes(ByteBuf buf) {
        tagCompounds = ByteBufUtils.readTag(buf);
        playerID = 	buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf,tagCompounds);
        buf.writeInt(playerID);
    }
    
    public static class CampsMessageHolder implements IMessageHandler<ImpurityUpdateMessage,IMessage>
    {
        @Override
		public IMessage onMessage( final ImpurityUpdateMessage message, final MessageContext ctx) {
			IThreadListener mainThread = (ctx.side.isClient())? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().playerEntity.worldObj;
            mainThread.addScheduledTask(new Runnable() 
            {
                @Override
                public void run() {     
                    GoetiaProvider.get((EntityPlayer)Minecraft.getMinecraft().theWorld.getEntityByID(message.playerID)).loadNBTData(message.tagCompounds);
                }
            });
            return null;
		}

    }
}
