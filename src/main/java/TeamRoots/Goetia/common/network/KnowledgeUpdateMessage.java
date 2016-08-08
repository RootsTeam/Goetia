package teamroots.goetia.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.capability.impurity.ImpurityProvider;
import teamroots.goetia.capability.impurity.KnowledgeProvider;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class KnowledgeUpdateMessage implements IMessage
{
    private NBTTagCompound tagCompounds;

    public KnowledgeUpdateMessage(){};

    public KnowledgeUpdateMessage(NBTTagCompound tagCompound)
    {
      this.tagCompounds = tagCompound;
    };

    @Override
    public void fromBytes(ByteBuf buf) {
        tagCompounds = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf,tagCompounds);
    }

    public static class CampsMessageHolder implements IMessageHandler<KnowledgeUpdateMessage,IMessage>
    {
    	@Override
		public IMessage onMessage( final KnowledgeUpdateMessage message, final MessageContext ctx) {
			IThreadListener mainThread = (ctx.side.isClient())? Minecraft.getMinecraft() : (WorldServer) ctx.getServerHandler().playerEntity.worldObj;
            mainThread.addScheduledTask(new Runnable() 
            {
                @Override
                public void run() {
                    KnowledgeProvider.get(Minecraft.getMinecraft().thePlayer).loadNBTData(message.tagCompounds);
                }
            });
            return null;
		}
    }
}
