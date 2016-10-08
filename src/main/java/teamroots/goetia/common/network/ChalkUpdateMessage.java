package teamroots.goetia.common.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class ChalkUpdateMessage implements IMessage
{
    private ItemStack stack;
    private UUID id;

    public ChalkUpdateMessage(){};

    public ChalkUpdateMessage(ItemStack stack, EntityPlayer player)
    {
      this.stack = stack;
      this.id = player.getUniqueID();
    };

    @Override
    public void fromBytes(ByteBuf buf) {
        stack = ByteBufUtils.readItemStack(buf);
        id = new UUID(buf.readLong(),buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeItemStack(buf, stack);
        buf.writeLong(id.getMostSignificantBits());
        buf.writeLong(id.getLeastSignificantBits());
    }

    public static class CampsMessageHolder implements IMessageHandler<ChalkUpdateMessage,IMessage>
    {

        @Override
        public IMessage onMessage(final ChalkUpdateMessage message, final MessageContext ctx) {
        	FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(message.id).setItemStackToSlot(EntityEquipmentSlot.MAINHAND, message.stack);
        	return null;
        }
    }
}
