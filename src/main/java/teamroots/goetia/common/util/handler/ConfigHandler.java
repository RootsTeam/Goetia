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
    
    public static int alignmentBarPosX;
    public static int alignmentBarPosY;
    public static int alignmentThreshold;

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
        alignmentBarPosX = config.getInt("alignmentIconOffsetX", Configuration.CATEGORY_GENERAL, 425, 0, 32767, "The alignment icons x offset");
        alignmentBarPosY = config.getInt("alignmentIconOffsetY", Configuration.CATEGORY_GENERAL, 20, 0, 32767, "The alignment icons y offset");
        alignmentThreshold = config.getInt("alignmentThreshold", Configuration.CATEGORY_GENERAL, 1000, 0, Integer.MAX_VALUE, "How much impurity/purity you must have before becoming a demon/angel");


        if (config.hasChanged())
        {
            config.save();
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.getModID().equalsIgnoreCase(LibMain.LibCore.MOD_ID))
        {
            loadConfigs();
        }
    }
}
