package teamroots.goetia.client.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.capability.impurity.KnowledgeProvider;
import teamroots.goetia.common.items.ItemSymbolIcon;
import teamroots.goetia.common.network.ChalkUpdateMessage;
import teamroots.goetia.common.network.GoetiaPacketHandler;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.registry.MainRegistry;

public class GuiChalk extends GuiScreen{
	ItemStack chalkItem = null;
	EntityPlayer player = null;
	float ticksOpen = 0;
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
				float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				if (mouseX >= xPos-24 && mouseY >= yPos-24 && mouseX <= xPos+24 && mouseY <= yPos+24){
					if (!chalkItem.hasTagCompound()){
						chalkItem.setTagCompound(new NBTTagCompound());
					}
					chalkItem.getTagCompound().setString("knowledge", knowledge.get(i));
					player.setHeldItem(EnumHand.MAIN_HAND, chalkItem);
					GoetiaPacketHandler.INSTANCE.sendToServer(new ChalkUpdateMessage(chalkItem,player));
				}
			}
		}
	}
	
	public void drawQuad(VertexBuffer vertexbuffer, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int minU, int minV, int maxU, int maxV){
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        vertexbuffer.pos((double)(x4 + 0.0F), (double)(y4 + 0.0F), (double)this.zLevel).tex((double)((float)(minU + 0) * f), (double)((float)(minV + maxV) * f1)).endVertex();
        vertexbuffer.pos((double)(x3 + 0.0F), (double)(y3 + 0.0F), (double)this.zLevel).tex((double)((float)(minU + maxU) * f), (double)((float)(minV + maxV) * f1)).endVertex();
        vertexbuffer.pos((double)(x2 + 0.0F), (double)(y2 + 0.0F), (double)this.zLevel).tex((double)((float)(minU + maxU) * f), (double)((float)(minV + 0) * f1)).endVertex();
        vertexbuffer.pos((double)(x1 + 0.0F), (double)(y1 + 0.0F), (double)this.zLevel).tex((double)((float)(minU + 0) * f), (double)((float)(minV + 0) * f1)).endVertex();
    }
	
	public void drawColoredQuad(VertexBuffer vertexbuffer, float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4, int minU, int minV, int maxU, int maxV, 
			float r1, float g1, float b1, float a1, float r2, float g2, float b2, float a2, float r3, float g3, float b3, float a3, float r4, float g4, float b4, float a4){
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        vertexbuffer.pos((double)(x4 + 0.0F), (double)(y4 + 0.0F), (double)this.zLevel).tex((double)((float)(minU + 0) * f), (double)((float)(minV + maxV) * f1)).color(r4, g4, b4, a4).endVertex();
        vertexbuffer.pos((double)(x3 + 0.0F), (double)(y3 + 0.0F), (double)this.zLevel).tex((double)((float)(minU + maxU) * f), (double)((float)(minV + maxV) * f1)).color(r3, g3, b3, a3).endVertex();
        vertexbuffer.pos((double)(x2 + 0.0F), (double)(y2 + 0.0F), (double)this.zLevel).tex((double)((float)(minU + maxU) * f), (double)((float)(minV + 0) * f1)).color(r2, g2, b2, a2).endVertex();
        vertexbuffer.pos((double)(x1 + 0.0F), (double)(y1 + 0.0F), (double)this.zLevel).tex((double)((float)(minU + 0) * f), (double)((float)(minV + 0) * f1)).color(r1, g1, b1, a1).endVertex();
    }
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		String selected = "null";
		if (chalkItem.hasTagCompound()){
			if (chalkItem.getTagCompound().hasKey("knowledge")){
				selected = chalkItem.getTagCompound().getString("knowledge");
			}
		}
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("goetia:textures/gui/guiSlot.png"));
		if (player.hasCapability(KnowledgeProvider.knowledgeCapability, null)){
			ArrayList<String> knowledge = player.getCapability(KnowledgeProvider.knowledgeCapability, null).getKnowledge();
			
			for (int i = 0; i < knowledge.size(); i ++){
				float fract = ((float)i)/((float)knowledge.size());
				float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				this.drawTexturedModalRect(xPos-24, yPos-24, 0, 0, 48, 48);
				if (knowledge.get(i).equals(selected)){
					this.drawTexturedModalRect(xPos-32, yPos-32, 0, 48, 64, 64);
				}
			}
			GlStateManager.color(1.0f, 237f/255f, 199f/255f);
			for (int i = 0; i < knowledge.size(); i ++){
					Minecraft.getMinecraft().getTextureManager().bindTexture(SymbolManager.symbolTextures.get(knowledge.get(i)));
				float fract = ((float)i)/((float)knowledge.size());
				float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				this.drawModalRectWithCustomSizedTexture((int)xPos-16, (int)yPos-16, 32.0f, 0.0f, 32, 32, 128.0f, 64.0f);
			}
			for (int i = 0; i < knowledge.size(); i ++){
				float fract = ((float)i)/((float)knowledge.size());
				float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				if (mouseX >= xPos-24 && mouseY >= yPos-24 && mouseX <= xPos+24 && mouseY <= yPos+24){
					ItemStack stack = new ItemStack(MainRegistry.symbolIcon,1);
					ItemSymbolIcon.createData(stack, knowledge.get(i));
					this.renderToolTip(stack, mouseX, mouseY);
				}
			}
		GlStateManager.color(1.0f, 1.0f, 1.0f);
		}
		RenderHelper.enableStandardItemLighting();
	}
}
