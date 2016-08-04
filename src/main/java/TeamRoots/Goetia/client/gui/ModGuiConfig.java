package teamroots.goetia.client.gui;

import teamroots.goetia.common.util.handler.ConfigHandler;
import teamroots.goetia.lib.LibMain;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ModGuiConfig extends GuiConfig
{

    public ModGuiConfig(GuiScreen parentScreen)
    {
        super(parentScreen, new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), LibMain.LibCore.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
    }
}
