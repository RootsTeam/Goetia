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

    public static int manaBarOffset;

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

        manaBarOffset = config.getInt("manaBarOffset", Configuration.CATEGORY_GENERAL, 59, 0, 32767, "The number of pixels above the bottom of the screen that the mana bar should be rendered. If it's conflicting with a bar from another mod, raising it by 10 will normally position it right.");


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
