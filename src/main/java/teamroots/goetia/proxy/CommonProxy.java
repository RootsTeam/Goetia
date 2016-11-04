package teamroots.goetia.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamroots.goetia.ConfigHandler;
import teamroots.goetia.Goetia;
import teamroots.goetia.KeyHandler;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.capability.GoetiaCapabilityManager;
import teamroots.goetia.client.GuiHandler;
import teamroots.goetia.common.AngelEventHandler;
import teamroots.goetia.common.DemonEventHandler;
import teamroots.goetia.common.GeneralEventHandler;
import teamroots.goetia.common.network.GoetiaPacketHandler;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.common.tiles.TileEntityBowl;
import teamroots.goetia.common.tiles.TileEntityScroll;
import teamroots.goetia.fluids.Fluids;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.rituals.RitualManager;
import teamroots.goetia.spellcasting.SpellRegistry;

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
        SpellRegistry.register();
        GameRegistry.registerTileEntity(TileEntityScroll.class, "lostnote");
        GameRegistry.registerTileEntity(TileEntityBowl.class, "bowl");
        KeyHandler.init();
        Fluids.preInit();
        RitualManager.init();
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
        MinecraftForge.EVENT_BUS.register(new GeneralEventHandler());
        MinecraftForge.EVENT_BUS.register(new DemonEventHandler());
        MinecraftForge.EVENT_BUS.register(new AngelEventHandler());
    }
}
