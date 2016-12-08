package teamroots.goetia.common.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.client.model.ModelManager;

/**
 * Created by Andrew Graber on 12/7/2016.
 */
public class RenderFairy extends RenderLiving<EntityFairy> {
    public RenderFairy(RenderManager renderManager) {
        super(renderManager, ModelManager.entityModels.get("fairy"), 0.25f);
    }

    @Override
    public boolean canRenderName(EntityFairy entity){
        return false;
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityFairy entity) {
        return new ResourceLocation("goetia:textures/entity/fairyTexture.png");
    }
}
