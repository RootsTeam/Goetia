package teamroots.goetia.renderlayers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.ConfigHandler;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.client.model.ModelHalo;
import teamroots.goetia.spellcasting.AlignmentType;

public class LayerAngel implements LayerRenderer{
	private static final ResourceLocation AURA_TEXTURE = new ResourceLocation("goetia:textures/entity/angel_aura.png");
	ModelPlayer model = new ModelPlayer(0.1F, false);
	ModelPlayer model2 = new ModelPlayer(0.6F, false);
	ModelPlayer model3 = new ModelPlayer(1F, false);
	
	@Override
	public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		EntityPlayer player = (EntityPlayer)entity;
		if(GoetiaProvider.get(player).getAlignment() == AlignmentType.ANGEL && !player.isInvisible() && ConfigHandler.alignmentModels == true){
			boolean flag = entity.isInvisible();
	        GlStateManager.depthMask(flag);
	        Minecraft.getMinecraft().renderEngine.bindTexture(AURA_TEXTURE);
	        GlStateManager.matrixMode(5890);
	        GlStateManager.loadIdentity();
	        float f = (float)entity.ticksExisted + partialTicks;
	        GlStateManager.translate(f * -0.01F, f * -0.01F, 0.0F);
	        GlStateManager.matrixMode(5888);
	        GlStateManager.enableBlend();
	        GlStateManager.color(0.5F, 0.5F, 0.5F, 0.8F);
	        GlStateManager.disableLighting();
	        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
	        model.isChild = false;
	        model2.isChild = false;
	        model3.isChild = false;
	        model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	        GlStateManager.color(0.5F, 0.5F, 0.5F, 0.4F);
	        GlStateManager.disableLighting();
	        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
	        model2.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	        GlStateManager.color(0.5F, 0.5F, 0.5F, 0.2F);
	        GlStateManager.disableLighting();
	        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);
	        model3.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	        GlStateManager.matrixMode(5890);
	        GlStateManager.loadIdentity();
	        GlStateManager.matrixMode(5888);
	        GlStateManager.enableLighting();
	        GlStateManager.disableBlend();
	        GlStateManager.depthMask(!flag);
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
