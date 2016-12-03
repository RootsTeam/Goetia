package teamroots.goetia.client.gui.guide;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class GuiGuide extends GuiScreen{
	public GuideManager manager;
	
	private int leftArrowXoffset = 185;
	private int rightArrowXoffset = 285;
	private int backArrowXoffset = 26;
	private int arrowsYoffset = 28;
	
	public int groupId = 0;
	
	public GuideInfoPage info = null;
	public GuideEntry tempEntry = null;
	
	public int entryWidth = 16;
	public int entryHeight = 16;
	
	EntityPlayer player = null;
	
	public GuiGuide(EntityPlayer player, GuideManager manager){
		this.player = player;
		this.manager = manager;
		info = manager.info;
	}
	
	@Override
	public void onGuiClosed(){
		this.groupId = 0;
		manager.info = info;
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return true;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton){
		if(info != null){
			info.onClicked(this.player, this.itemRender,this.fontRendererObj, mouseX, mouseY, this.width, this.height, mouseButton);
		}
		
		for(GuideEntry entry: manager.groups.get(groupId).entries){
			GlStateManager.color(1, 1, 1, 1);
			if((mouseX >= entry.x && mouseX <= entry.x + entryWidth) && (mouseY >= entry.y && mouseY <= entry.y + entryHeight) && info == null){
				info = manager.pages.get(entry.pageID);	
				tempEntry = entry;
			}
		}
		
		//Arrows clicked
		  //Page arrows
		if((mouseX >= this.width - leftArrowXoffset && mouseX <= (this.width - leftArrowXoffset) + 26) && (mouseY >= this.height - arrowsYoffset && mouseY <= (this.height - arrowsYoffset) + 26)){
			nextPage(true);
		}
		if((mouseX >= this.width - rightArrowXoffset && mouseX <= (this.width - rightArrowXoffset) + 26) && (mouseY >= this.height - arrowsYoffset && mouseY <= (this.height - arrowsYoffset) + 26)){
			nextPage(false);
		}
		  //Back arrow
		if((mouseX >= this.width - backArrowXoffset && mouseX <= (this.width - backArrowXoffset) + 26) && (mouseY >= this.height - arrowsYoffset && mouseY <= (this.height - arrowsYoffset) + 26)){
			this.info = null;
		}
		//*************
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		Minecraft.getMinecraft().getTextureManager().bindTexture(manager.imgGuideBG);
		this.drawModalRectWithCustomSizedTexture(0, 0, 0, 256, this.width, this.height, 256, 256);
		if(info == null){
			for(GuideEntry entry: manager.groups.get(groupId).entries){
				GlStateManager.enableBlend();
				GlStateManager.color(1, 1, 1, 1);
				//Item background Icon
				Minecraft.getMinecraft().getTextureManager().bindTexture(manager.imgGuide);
				this.drawModalRectWithCustomSizedTexture(entry.x, entry.y, 0, 0, 16, 16, 256, 256);
				//****************
				this.itemRender.renderItemIntoGUI(entry.stack, entry.x, entry.y);
				if((mouseX >= entry.x && mouseX <= entry.x + entryWidth) && (mouseY >= entry.y && mouseY <= entry.y + entryHeight)){
					this.fontRendererObj.drawString(entry.entryName, entry.x, entry.y + 17, manager.textColor);
				}
			}
			this.fontRendererObj.drawString(manager.groups.get(groupId).name, 1, this.height - 10, manager.textColor);
			
			//Arrow drawing
			Minecraft.getMinecraft().getTextureManager().bindTexture(manager.imgGuide);
			GlStateManager.color(1, 1, 1, 1);
			if((mouseX >= this.width - leftArrowXoffset && mouseX <= (this.width - leftArrowXoffset) + 26) && (mouseY >= this.height - arrowsYoffset && mouseY <= (this.height - arrowsYoffset) + 26)){
				this.drawModalRectWithCustomSizedTexture(this.width - leftArrowXoffset, this.height - arrowsYoffset, 30, 230, 26, 26, 256, 256);
			} else {
				this.drawModalRectWithCustomSizedTexture(this.width - leftArrowXoffset, this.height - arrowsYoffset, 1, 230, 26, 26, 256, 256);
			}
			
			if((mouseX >= this.width - rightArrowXoffset && mouseX <= (this.width - rightArrowXoffset) + 26) && (mouseY >= this.height - arrowsYoffset && mouseY <= (this.height - arrowsYoffset) + 26)){
				this.drawModalRectWithCustomSizedTexture(this.width - rightArrowXoffset, this.height - arrowsYoffset, 29, 201, 26, 26, 256, 256);
			} else {
				this.drawModalRectWithCustomSizedTexture(this.width - rightArrowXoffset, this.height - arrowsYoffset, 0, 201, 26, 26, 256, 256);
			}
			this.fontRendererObj.drawString(Integer.toString(groupId+1)+"/"+manager.groups.size(), this.width - 230, this.height - 18, manager.textColor);
			//****************
		} else {
			drawInfoPage(mouseX, mouseY);
			if(info.type == EnumPageType.CUSTOM){
				info.drawOnPage(this.tempEntry, this.player, this.itemRender,this.fontRendererObj, mouseX, mouseY, this.width, this.height);
			}
		}
		//Debug positioning
		//this.fontRendererObj.drawStringWithShadow(mouseX+","+mouseY, mouseX, mouseY, 16777215);
		RenderHelper.enableStandardItemLighting();
	}
	
	public void drawInfoPage(int mouseX, int mouseY){
		this.fontRendererObj.drawString(info.title, 175, 1, manager.textColor);
		ArrayList<String> lines = info.makeLines(info.desc, this.width);
		for(int i = 0; i < lines.size(); i++){
			this.fontRendererObj.drawString(lines.get(i), 1, 25 + (i * 11), manager.textColor);
		}
		
		//Back Arrow
		Minecraft.getMinecraft().getTextureManager().bindTexture(manager.imgGuide);
		GlStateManager.color(1, 1, 1, 1);
		if((mouseX >= this.width - backArrowXoffset && mouseX <= (this.width - backArrowXoffset) + 26) && (mouseY >= this.height - arrowsYoffset && mouseY <= (this.height - arrowsYoffset) + 26)){
			this.drawModalRectWithCustomSizedTexture(this.width - backArrowXoffset, this.height - arrowsYoffset, 86, 201, 26, 26, 256, 256);
		} else {
			this.drawModalRectWithCustomSizedTexture(this.width - backArrowXoffset, this.height - arrowsYoffset, 59, 201, 26, 26, 256, 256);
		}
		//##########
	}
	
	private void nextPage(boolean forward){
		if(forward){
			if(groupId >= manager.groups.size() - 1){
				groupId = 0;
			} else {
				groupId++;
			}
		} else {
			if(groupId <= 0){
				groupId = manager.groups.size() - 1;
			} else {
				groupId--;
			}
		}
	}

}
