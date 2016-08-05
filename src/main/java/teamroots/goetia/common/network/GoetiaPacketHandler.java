package teamroots.goetia.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import teamroots.goetia.lib.LibMain;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class GoetiaPacketHandler
{
    public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(LibMain.LibCore.MOD_ID);

    private static int id = 0;

    public static void registerMessages()
    {
        INSTANCE.registerMessage(ImpurityUpdateMessage.CampsMessageHolder.class,ImpurityUpdateMessage.class,id++, Side.CLIENT);
    }
}
