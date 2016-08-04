package teamroots.goetia.common.util.handler;

import teamroots.goetia.lib.LibMain;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ConfigHandler
{
    public static Configuration config;

    public static boolean testBoolean;

    public static void init(File configFile)
    {
        if(config == null)
        {
            config = new Configuration(configFile);
            loadConfigs();
        }
    }

    public static void loadConfigs()
    {
        testBoolean = config.getBoolean("Are we awesome?",Configuration.CATEGORY_GENERAL,false,"No comment m8!");

        if (config.hasChanged())
        {
            config.save();
        }
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.getModID().equalsIgnoreCase(LibMain.LibCore.MOD_ID))
        {
            loadConfigs();
        }
    }
}
