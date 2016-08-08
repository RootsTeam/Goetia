package teamroots.goetia.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.common.symbol.SymbolManager;

public class RenderSymbolForge extends RenderLiving<EntitySymbolForge> {

	public RenderSymbolForge(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
		super(renderManager, modelBase, shadowSize);
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
