package teamroots.goetia.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFiend extends RenderLiving<EntityFiend> {

	public RenderFiend(RenderManager renderManager, ModelBase modelBase, float shadowSize) {
		super(renderManager, modelBase, shadowSize);
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
