package teamroots.goetia.common.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import teamroots.goetia.client.model.ModelManager;

public class RenderImp extends RenderLiving<EntityImp> implements IRenderFactory{

	public RenderImp(RenderManager renderManager) {
		super(renderManager, ModelManager.entityModels.get("imp"), 0.3F);
	}
	
	@Override
	public boolean canRenderName(EntityImp entity){
		return false;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityImp entity) {
		return new ResourceLocation("goetia:textures/entity/impTexture.png");
	}

	@Override
	public Render createRenderFor(RenderManager manager) {
		return this;
	}
}
