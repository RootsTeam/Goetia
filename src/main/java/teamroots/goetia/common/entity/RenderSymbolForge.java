package teamroots.goetia.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.client.model.ModelManager;
import teamroots.goetia.common.symbol.SymbolManager;

public class RenderSymbolForge extends RenderLiving<EntitySymbolForge> {

	public RenderSymbolForge(RenderManager renderManager) {
		super(renderManager, ModelManager.entityModels.get("symbol"), 0f);
	}
	
	@Override
	public boolean canRenderName(EntitySymbolForge entity){
		return false;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySymbolForge entity) {
		return SymbolManager.symbolTextures.get(entity.getSymbolName());
	}
}
