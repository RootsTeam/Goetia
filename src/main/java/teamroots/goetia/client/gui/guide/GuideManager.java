package teamroots.goetia.client.gui.guide;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.util.ResourceLocation;

public class GuideManager {
	public ResourceLocation imgGuide = new ResourceLocation("goetia:textures/gui/guide.png");
	public ResourceLocation imgGuideBG = new ResourceLocation("goetia:textures/gui/guideBG.png");
	
	public int textColor = 16777215;
	
	public ArrayList<GuideGroup> groups = new ArrayList<GuideGroup>();
	public HashMap<String, GuideInfoPage> pages = new HashMap<String, GuideInfoPage>();
	
	public GuideInfoPage info;
	
	public void registerEntryGroup(GuideGroup entryGroup){
		groups.add(entryGroup);
	}
	
	public void registerInfoPage(String entryId, GuideInfoPage infoPage){
		pages.put(entryId, infoPage);
	}
	
	public GuideManager setGuideImage(ResourceLocation location){
		this.imgGuide = location;
		return this;
	}
	
	public GuideManager setGuideBackground(ResourceLocation location){
		this.imgGuideBG = location;
		return this;
	}
}
