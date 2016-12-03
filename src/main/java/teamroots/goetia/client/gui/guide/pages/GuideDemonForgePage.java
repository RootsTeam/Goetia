package teamroots.goetia.client.gui.guide.pages;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import teamroots.goetia.client.gui.guide.EnumPageType;
import teamroots.goetia.client.gui.guide.GuideEntry;
import teamroots.goetia.client.gui.guide.GuideInfoPage;
import teamroots.goetia.spellcasting.AlignmentType;

public class GuideDemonForgePage extends GuideInfoPage{

	public ItemStack input;
	public ItemStack output;
	public int alignmentCost;
	public GuideDemonForgePage(String title, ItemStack input, ItemStack output, int cost) {
		super(title, EnumPageType.CUSTOM);
		this.input = input;
		this.output = output;
		this.alignmentCost = cost;
	}
	
	@Override
	public void drawOnPage(GuideEntry entry, EntityPlayer player, RenderItem itemRender, FontRenderer font, int mouseX, int mouseY, int guiWidth, int guiHeight) {
		itemRender.renderItemIntoGUI(input, 84, 100);
		itemRender.renderItemIntoGUI(output, 116, 100);
		font.drawString(Integer.toString(alignmentCost), 100, 100, AlignmentType.DEMON.color);
	}
}
