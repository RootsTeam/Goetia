package teamroots.goetia.client.gui.guide;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import teamroots.goetia.lib.LibMain;

public class GuideInfoPage{
	public String title;
	public String desc;
	
	public int wordWrapOffSet = 275;
	
	public EnumPageType type;
	
	private String format = "guide."+LibMain.LibCore.MOD_ID+".";
	
	public GuideInfoPage(String title){
		this.title = I18n.format(format+title+".title");
		this.desc = I18n.format(format+title+".desc");
		this.type = EnumPageType.INFO;
	}
	
	public GuideInfoPage(String title, EnumPageType type){
		this.title = I18n.format(format+title+".title");
		this.desc = I18n.format(format+title+".desc");
		this.type = type;
	}
	
	/**
	 * Takes a string and breaks it apart to fit on screen. Will wrap if needed.
	 * @param s
	 * @param guiWidth
	 * @return
	 */
	public ArrayList<String> makeLines(String s, int guiWidth){
		int lengthBeforeWrap = guiWidth - wordWrapOffSet;
		if(type == EnumPageType.INFO){
			lengthBeforeWrap = guiWidth;
		}
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> words = new ArrayList<String>();
		String temp = "";
		int counter = 0;
		for (int i = 0; i < s.length(); i ++){
			temp += s.charAt(i);
			if (s.charAt(i) == ' '){
				words.add(temp);
				temp = "";
			}
		}
		words.add(temp);
		temp = "";
		for (int i = 0; i < words.size(); i ++){
			counter += Minecraft.getMinecraft().fontRendererObj.getStringWidth(words.get(i));
			if (counter > lengthBeforeWrap){
				list.add(temp);
				temp = words.get(i);
				counter = Minecraft.getMinecraft().fontRendererObj.getStringWidth(words.get(i));
			}
			else {
				temp += words.get(i);
			}
		}
		list.add(temp);
		return list;
	}
	
	/**
	 * Runs when the page is clicked
	 * @param player
	 * @param itemRender
	 * @param font
	 * @param mouseX
	 * @param mouseY
	 * @param guiWidth
	 * @param guiHeight
	 * @param mouseButton
	 */
	public void onClicked(EntityPlayer player, RenderItem itemRender, FontRenderer font, int mouseX, int mouseY, int guiWidth, int guiHeight, int mouseButton) {
		
	}
	
	/**
	 * Used to draw things on the info page see GuideCraftingTablePage for example
	 * @param entry
	 * @param player
	 * @param itemRender
	 * @param font
	 * @param mouseX
	 * @param mouseY
	 * @param guiWidth
	 * @param guiHeight
	 */
	public void drawOnPage(GuideEntry entry, EntityPlayer player, RenderItem itemRender, FontRenderer font, int mouseX, int mouseY, int guiWidth, int guiHeight) {
		
	}
}
