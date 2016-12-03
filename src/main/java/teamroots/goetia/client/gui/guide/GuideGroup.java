package teamroots.goetia.client.gui.guide;

import java.util.ArrayList;

public class GuideGroup {
	public String name;
	
	public ArrayList<GuideEntry> entries = new ArrayList<GuideEntry>();
	
	public GuideGroup(String name){
		this.name = name;
	}
	
	public void addEntry(GuideEntry entry)
	{
		entries.add(entry);
	}
}
