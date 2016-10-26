package teamroots.goetia.common.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.spellcasting.SpellRegistry;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class FocusCastMessage implements IMessage
{
    private int spellId;
    private UUID id;

    public FocusCastMessage(){};

    public FocusCastMessage(String name, EntityPlayer player)
    {
      this.spellId = SpellRegistry.getSpellIndexFromName(name);
      this.id = player.getUniqueID();
    };

    @Override
    public void fromBytes(ByteBuf buf) {
        spellId = buf.readInt();
        id = new UUID(buf.readLong(),buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(spellId);
        buf.writeLong(id.getMostSignificantBits());
        buf.writeLong(id.getLeastSignificantBits());
    }

    public static class CampsMessageHolder implements IMessageHandler<FocusCastMessage,IMessage>
    {
        @Override
        public IMessage onMessage(final FocusCastMessage message, final MessageContext ctx) {
        	SpellRegistry.spells.get(message.spellId).doEffect(FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList().getPlayerByUUID(message.id));
        	return null;
        }
    }
}
