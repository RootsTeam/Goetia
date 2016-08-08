package teamroots.goetia.proxy;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import teamroots.goetia.Goetia;
import teamroots.goetia.capability.GoetiaCapabilityManager;
import teamroots.goetia.common.EventManager;
import teamroots.goetia.client.GuiHandler;
import teamroots.goetia.common.EventManager;
import teamroots.goetia.common.network.GoetiaPacketHandler;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.common.util.handler.ConfigHandler;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.registry.MainRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
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

        registerEvents();
        SymbolManager.init();
        MainRegistry.register();
        GoetiaPacketHandler.registerMessages();
        MainRegistry.registerEntities();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        
        LootTableList.register(new ResourceLocation(LibMain.LibCore.MOD_ID, "inject/simple_dungeon"));
        GoetiaCapabilityManager.register();
        NetworkRegistry.INSTANCE.registerGuiHandler(Goetia.instance,new GuiHandler());
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {

    }

    private void registerEvents()
    {
        MinecraftForge.EVENT_BUS.register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new GoetiaCapabilityManager());
        MinecraftForge.EVENT_BUS.register(new EventManager());
    }
}
