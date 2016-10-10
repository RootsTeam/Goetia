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
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.common.network.FocusCastMessage;
import teamroots.goetia.common.network.GoetiaPacketHandler;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.SpellRegistry;
import teamroots.goetia.spellcasting.AlignmentType;

public class GuiFocus extends GuiScreen{
	EntityPlayer player = null;
	float ticksOpen = 0;
	ArrayList<Integer> steps = new ArrayList<Integer>();
	ArrayList<CastSpell> validSpells = new ArrayList<CastSpell>();
	boolean tracking = false;
	float lifetime = 0;
	public GuiFocus(EntityPlayer player){
		this.player = player;
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton){
		for (int i = 0; i < 6; i ++){
			float fract = ((float)i)/((float)6);
			float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
			float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
			if (mouseX >= xPos-16 && mouseY >= yPos-16 && mouseX <= xPos+16 && mouseY <= yPos+16){
				tracking = true;
				steps.add(i);
			}
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int mouseButton){
		if (tracking){
			for (int i = 0; i < validSpells.size(); i ++){
				if (validSpells.get(i).shape.size() == this.steps.size()){
					boolean doesMatch = true;
					for (int j = 0; j < validSpells.get(i).shape.size(); j ++){
						if (validSpells.get(i).shape.get(j) != this.steps.get(j)+1){
							doesMatch = false;
						}
					}
					if (doesMatch){
						System.out.println(validSpells.get(i).name);
						validSpells.get(i).doEffect(player);
						GoetiaPacketHandler.INSTANCE.sendToServer(new FocusCastMessage(validSpells.get(i).name,player));
						this.mc.displayGuiScreen(null);
						return;
					}
				}
			}
			this.mc.displayGuiScreen(null);
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
	
	public void drawLine(VertexBuffer vertexbuffer, float x1, float y1, float x2, float y2, float r1, float g1, float b1, float a1, float r2, float g2, float b2, float a2){
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        vertexbuffer.pos(x1, y1, this.zLevel).tex(0.5, 0.5).color(r1, g1, b1, a1).endVertex();
        vertexbuffer.pos(x2, y2, this.zLevel).tex(0.5, 0.5).color(r2, g2, b2, a2).endVertex();
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
	
	public void onGuiClosed(){
		validSpells.clear();
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		float r;
		float g;
		float b;
		
		lifetime += 10.0f;
		RenderHelper.disableStandardItemLighting();
		RenderHelper.enableGUIStandardItemLighting();
		String selected = "null";
		
		if (!tracking){
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("goetia:textures/gui/guiSlot.png"));
			String text;
			if(GoetiaProvider.get(player).getAligningTowards() == AlignmentType.DEMON){
				//DEMONS
				text = I18n.format("goetia.tooltip.impurity") + player.getCapability(GoetiaProvider.goetiaCapability, null).getImpurity();
			} else {
				//ANGELS
				text = I18n.format("goetia.tooltip.purity") + player.getCapability(GoetiaProvider.goetiaCapability, null).getPurity();
			}
			this.fontRendererObj.drawStringWithShadow(text, (int)width/2-this.fontRendererObj.getStringWidth(text)/2, (int)height/2-this.fontRendererObj.FONT_HEIGHT/2+24, GoetiaProvider.get(player).getAligningTowards().color);
		}
		
		for (int i = 0; i < SpellRegistry.spells.size(); i ++){
			if(GoetiaProvider.get(player).getAligningTowards() == AlignmentType.DEMON){
				//DEMONS
				if (SpellRegistry.spells.get(i).cost <= GoetiaProvider.get(player).getImpurity() && SpellRegistry.spells.get(i).type == AlignmentType.DEMON){
					validSpells.add(SpellRegistry.spells.get(i));
				}
			} else {
				//ANGELS
				if (SpellRegistry.spells.get(i).cost <= GoetiaProvider.get(player).getPurity() && SpellRegistry.spells.get(i).type == AlignmentType.ANGEL){
					validSpells.add(SpellRegistry.spells.get(i));
				}
			}
		}
		
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("goetia:textures/gui/guiSlot.png"));
		for (int i = 0; i < 6; i ++){
			float fract = ((float)i)/((float)6);
			float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
			float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
			this.drawTexturedModalRect(xPos-16, yPos-16, 96, 0, 32, 32);
		}
		
		for (int i = 0; i < 6; i ++){
			float fract = ((float)i)/((float)6);
			float xPos = width/2.0f + 105f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
			float yPos = height/2.0f+ 105f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
			this.fontRendererObj.drawStringWithShadow(String.valueOf(i+1), xPos-this.fontRendererObj.getStringWidth(String.valueOf(i))/2, yPos-this.fontRendererObj.FONT_HEIGHT/2, 0xFFFFFF);
		}
		
		if (tracking){
			GlStateManager.disableLighting();
			GlStateManager.glLineWidth(6.0f);
			GlStateManager.enableAlpha();
			GlStateManager.enableBlend();
			GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			Tessellator tess = Tessellator.getInstance();
			VertexBuffer buff = tess.getBuffer();
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("goetia:textures/gui/guiWhite.png"));
			buff.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_TEX_COLOR);
			if(player.getCapability(GoetiaProvider.goetiaCapability, null).getImpurity() > player.getCapability(GoetiaProvider.goetiaCapability, null).getPurity()){
				r = 1.0F;
				g = 0.0F;
				b = 0.0F;
			} else {
				r = 0.0F;
				g = 0.0F;
				b = 1.0F;
			}
			
			for (int i = 0; i < steps.size()-1; i ++){
				float fract = ((float)steps.get(i))/((float)6);
				float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				fract = ((float)steps.get(i+1))/((float)6);
				float xPos2 = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos2 = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				drawLine(buff, xPos, yPos, (xPos+xPos2)/2.0f, (yPos+yPos2)/2.0f, r, g, b, 1.0f, r, g, b, 1.0f);
				drawLine(buff, xPos2, yPos2, (xPos+xPos2)/2.0f, (yPos+yPos2)/2.0f, r, g, b, 1.0f, r, g, b, 1.0f);
			}
			float tfract = ((float)steps.get(steps.size()-1))/((float)6);
			float txPos = width/2.0f + 80f*(float)Math.cos(tfract*2.0f*Math.PI-(Math.PI/2.0f));
			float tyPos = height/2.0f+ 80f*(float)Math.sin(tfract*2.0f*Math.PI-(Math.PI/2.0f));
			drawLine(buff, txPos, tyPos, (mouseX), (mouseY), r, g, b, 1.0f, r, g, b, 1.0f);
			tess.draw();
			buff.reset();
			GlStateManager.glLineWidth(18.0f);
			buff.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_TEX_COLOR);
			for (int i = 0; i < steps.size()-1; i ++){
				float fract = ((float)steps.get(i))/((float)6);
				float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				fract = ((float)steps.get(i+1))/((float)6);
				float xPos2 = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos2 = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				drawLine(buff, xPos, yPos, (xPos+xPos2)/2.0f, (yPos+yPos2)/2.0f, r, g, b, 0.5f, r, g, b, 0.5f);
				drawLine(buff, xPos2, yPos2, (xPos+xPos2)/2.0f, (yPos+yPos2)/2.0f, r, g, b, 0.5f, r, g, b, 0.5f);
			}
			tfract = ((float)steps.get(steps.size()-1))/((float)6);
			txPos = width/2.0f + 80f*(float)Math.cos(tfract*2.0f*Math.PI-(Math.PI/2.0f));
			tyPos = height/2.0f+ 80f*(float)Math.sin(tfract*2.0f*Math.PI-(Math.PI/2.0f));
			drawLine(buff, txPos, tyPos, (mouseX), (mouseY), r, g, b, 0.5f, r, g, b, 0.5f);
			tess.draw();
			
			for (int i = 0; i < 6; i ++){
				float fract = ((float)i)/((float)6);
				float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				if (mouseX >= xPos-16 && mouseY >= yPos-16 && mouseX <= xPos+16 && mouseY <= yPos+16 && i != steps.get(steps.size()-1)){
					steps.add(i);
				}
			}

			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("goetia:textures/gui/guiSlot.png"));
			for (int i = 0; i < steps.size(); i ++){
				float fract = ((float)steps.get(i))/((float)6);
				float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
				float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
				GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
				GlStateManager.translate(xPos, yPos, 0);
				GlStateManager.rotate(lifetime, 0, 0, 1);
				if(player.getCapability(GoetiaProvider.goetiaCapability, null).getImpurity() > player.getCapability(GoetiaProvider.goetiaCapability, null).getPurity()){
					this.drawTexturedModalRect(-16, -16, 128, 0, 32, 32);
				} else {
					this.drawTexturedModalRect(-16, -16, 128, 32, 32, 32);
				}
				GlStateManager.rotate(-lifetime, 0, 0, 1);
				GlStateManager.translate(-xPos, -yPos, 0);
			}
			GlStateManager.enableLighting();
		}
		RenderHelper.enableStandardItemLighting();
	}
}
