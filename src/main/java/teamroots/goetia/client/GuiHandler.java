package teamroots.goetia.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.client.gui.GuiAltar;
import teamroots.goetia.client.gui.GuiChalk;
import teamroots.goetia.client.gui.GuiFocus;
import teamroots.goetia.client.gui.guide.GuiGuide;
import teamroots.goetia.lib.EnumIDs;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (EnumIDs.values()[ID])
        {
		case GUI_ID_ALTAR:
			break;
		default:
			break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (EnumIDs.values()[ID])
        {
        case GUI_ID_CHALK:
        	return new GuiChalk(player.getHeldItem(EnumHand.MAIN_HAND), player);
        case GUI_ID_FOCUS:
        	return new GuiFocus(player);
		case GUI_ID_ALTAR:
			return new GuiAltar(player);
		case GUI_GUIDE:
			return new GuiGuide(player, MainRegistry.manager);
		default:
			break;

        }
        return null;
    }
}
