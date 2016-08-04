package TeamRoots.Goetia;

import TeamRoots.Goetia.lib.LibMain;
import TeamRoots.Goetia.proxy.IProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by TeamRoots on 4.8.2016.
 */
@Mod(modid = LibMain.LibCore.MOD_ID,name = LibMain.LibCore.MOD_NAME,version = LibMain.LibCore.VERSION,useMetadata = true)
public class Goetia
{
    @Mod.Instance(LibMain.LibCore.MOD_ID)
    public static Goetia instance;

    @SidedProxy(clientSide = LibMain.LibCore.CLIENT_PROXY_CLASS,serverSide = LibMain.LibCore.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
