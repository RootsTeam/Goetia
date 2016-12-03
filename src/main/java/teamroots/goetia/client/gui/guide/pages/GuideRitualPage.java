package teamroots.goetia.client.gui.guide.pages;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.client.gui.guide.EnumPageType;
import teamroots.goetia.client.gui.guide.GuideEntry;
import teamroots.goetia.client.gui.guide.GuideInfoPage;

public class GuideRitualPage extends GuideInfoPage{

	public GuideRitualPage(String title) {
		super(title, EnumPageType.CUSTOM);
	}

	@Override
	public void drawOnPage(GuideEntry entry, EntityPlayer player, RenderItem itemRender, FontRenderer font, int mouseX, int mouseY, int guiWidth, int guiHeight) {
		itemRender.renderItemIntoGUI(new ItemStack(MainRegistry.altar), 30, 30);
	}
}
