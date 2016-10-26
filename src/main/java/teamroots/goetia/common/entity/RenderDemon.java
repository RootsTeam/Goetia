package teamroots.goetia.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.client.model.ModelManager;

public class RenderDemon extends RenderLiving<EntityDemon> {

	public RenderDemon(RenderManager renderManager) {
		super(renderManager, ModelManager.entityModels.get("demon"), 0.8F);
	}
	
	@Override
	public boolean canRenderName(EntityDemon entity){
		return false;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityDemon entity) {
		return new ResourceLocation("goetia:textures/entity/demonTexture.png");
	}
}
