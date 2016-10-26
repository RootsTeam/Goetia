package teamroots.goetia.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.client.model.ModelManager;
import teamroots.goetia.common.symbol.SymbolManager;

public class RenderSymbolDevilsTrap extends RenderLiving<EntitySymbolDevilsTrap> {

	public RenderSymbolDevilsTrap(RenderManager renderManager) {
		super(renderManager, ModelManager.entityModels.get("symbol"), 0f);
	}
	
	@Override
	public boolean canRenderName(EntitySymbolDevilsTrap entity){
		return false;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySymbolDevilsTrap entity) {
		return SymbolManager.symbolTextures.get(entity.getSymbolName());
	}
}
