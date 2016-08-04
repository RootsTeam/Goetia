package teamroots.goetia.proxy;

import teamroots.goetia.common.EventManeger;
import teamroots.goetia.common.util.handler.ConfigHandler;
import teamroots.goetia.registry.MainRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.beans.EventHandler;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public abstract class CommonProxy implements IProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        ConfigHandler.init(e.getSuggestedConfigurationFile());
        MainRegistry.register();
        registerEvents();
    }

    @Override
    public void init(FMLInitializationEvent e) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {

    }

    private void registerEvents()
    {
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new EventManeger());
    }
}
