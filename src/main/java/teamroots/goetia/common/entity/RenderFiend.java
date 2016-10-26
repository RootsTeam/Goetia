package teamroots.goetia.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.client.model.ModelManager;

public class RenderFiend extends RenderLiving<EntityFiend> {

	public RenderFiend(RenderManager renderManager) {
		super(renderManager, ModelManager.entityModels.get("fiend"), 0.5F);
	}
	
	@Override
	public boolean canRenderName(EntityFiend entity){
		return false;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFiend entity) {
		return new ResourceLocation("goetia:textures/entity/fiendTexture.png");
	}
}
