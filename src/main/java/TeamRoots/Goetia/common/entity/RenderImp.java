package teamroots.goetia.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderImp extends RenderLiving<EntityImp> {

	public RenderImp(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
		super(renderManager, modelBase, shadowSize);
	}
	
	@Override
	public boolean canRenderName(EntityImp entity){
		return false;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityImp entity) {
		return new ResourceLocation("goetia:textures/entity/impTexture.png");
	}
}
