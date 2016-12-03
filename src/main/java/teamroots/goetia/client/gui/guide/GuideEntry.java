package teamroots.goetia.client.gui.guide;

import net.minecraft.item.ItemStack;

public class GuideEntry {
	
	public int x;
	public int y;
	
	public ItemStack stack;
	
	public String entryName;
	public String pageID;
	public String researchName;
	
	public GuideEntry(String pageID, String entryName, ItemStack stack, int x, int y){
		this.pageID = pageID;
		this.entryName = entryName;
		this.stack = stack;
		this.x = x;
		this.y = y;
	}
}
