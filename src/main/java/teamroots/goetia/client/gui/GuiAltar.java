package teamroots.goetia.client.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.common.items.ItemSpellIcon;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.SpellRegistry;
import teamroots.goetia.spellcasting.AlignmentType;

public class GuiAltar extends GuiScreen{
	EntityPlayer player = null;
	public GuiAltar(EntityPlayer player){
		this.player = player;
	}
	
	int validSpellSize;
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
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
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("goetia:textures/gui/guiSlot.png"));
		ArrayList<CastSpell> validSpells = new ArrayList<CastSpell>();
		
		for (int i = 0; i < SpellRegistry.spells.size(); i ++){
			if(player.getCapability(GoetiaProvider.goetiaCapability, null).getImpurity() > player.getCapability(GoetiaProvider.goetiaCapability, null).getPurity()){
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
		
		this.validSpellSize = validSpells.size();
		if(this.validSpellSize == 0){
			this.fontRendererObj.drawStringWithShadow("You cannot use any spells!", (int)width/2-this.fontRendererObj.getStringWidth("You cannot use any spells!")/2, (int)height/2-this.fontRendererObj.FONT_HEIGHT/2+24, 0xFFFFFF);
		}
		
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("goetia:textures/gui/guiSlot.png"));
		for (int i = 0; i < validSpells.size(); i ++){
			float fract = ((float)i)/((float)validSpells.size());
			float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
			float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
			this.drawTexturedModalRect(xPos-16, yPos-16, 160, 0, 32, 32);
		}
		
		for (int i = 0; i < validSpells.size(); i ++){
			float fract = ((float)i)/((float)validSpells.size());
			float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
			float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
			this.itemRender.renderItemIntoGUI(validSpells.get(i).icon, (int)xPos-8, (int)yPos-8);
		}
		
		for (int i = 0; i < validSpells.size(); i ++){
			float fract = ((float)i)/((float)validSpells.size());
			float xPos = width/2.0f + 80f*(float)Math.cos(fract*2.0f*Math.PI-(Math.PI/2.0f));
			float yPos = height/2.0f+ 80f*(float)Math.sin(fract*2.0f*Math.PI-(Math.PI/2.0f));
			if (mouseX >= xPos-16 && mouseY >= yPos-16 && mouseX <= xPos+16 && mouseY <= yPos+16){
				ItemStack stack = new ItemStack(MainRegistry.spellIcon,1);
				ItemSpellIcon.createData(stack, validSpells.get(i));
				this.renderToolTip(stack, mouseX, mouseY);
			}
		}
		RenderHelper.enableStandardItemLighting();
	}
}
