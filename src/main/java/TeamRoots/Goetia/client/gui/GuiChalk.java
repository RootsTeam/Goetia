package teamroots.goetia.client.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.capability.impurity.KnowledgeProvider;
import teamroots.goetia.common.symbol.SymbolManager;

public class GuiChalk extends GuiScreen{
	ItemStack chalkItem = null;
	EntityPlayer player = null;
	public GuiChalk(ItemStack stack, EntityPlayer player){
		chalkItem = stack;
		this.player = player;
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton){
		if (player.hasCapability(KnowledgeProvider.knowledgeCapability, null)){
			ArrayList<String> knowledge = player.getCapability(KnowledgeProvider.knowledgeCapability, null).getKnowledge();
		
			for (int i = 0; i < knowledge.size(); i ++){
				float fract = ((float)i)/((float)knowledge.size());
				float xPos = width/2.0f + 108f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 108f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				if (mouseX >= xPos-24 && mouseY >= yPos-24 && mouseX <= xPos+24 && mouseY <= yPos+24){
					if (!chalkItem.hasTagCompound()){
						chalkItem.setTagCompound(new NBTTagCompound());
					}
					chalkItem.getTagCompound().setString("knowledge", knowledge.get(i));
				}
			}
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		if (player.hasCapability(KnowledgeProvider.knowledgeCapability, null)){
			ArrayList<String> knowledge = player.getCapability(KnowledgeProvider.knowledgeCapability, null).getKnowledge();
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("goetia:textures/gui/guiSlot.png"));
			for (int i = 0; i < knowledge.size(); i ++){
				float fract = ((float)i)/((float)knowledge.size());
				float xPos = width/2.0f + 108f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 108f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				this.drawTexturedModalRect(xPos-24, yPos-24, 0, 0, 48, 48);
			}
			
			for (int i = 0; i < knowledge.size(); i ++){
				Minecraft.getMinecraft().getTextureManager().bindTexture(SymbolManager.symbolTextures.get(knowledge.get(i)));
				float fract = ((float)i)/((float)knowledge.size());
				float xPos = width/2.0f + 108f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 108f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				this.drawModalRectWithCustomSizedTexture((int)xPos-16, (int)yPos-16, 32.0f, 0.0f, 32, 32, 128.0f, 64.0f);
			}
		}
	}
}
