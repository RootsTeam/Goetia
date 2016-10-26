package teamroots.goetia.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.common.entity.ISymbol;

public class ModelSymbol extends ModelBase {

    ModelRenderer symbol;
    public ModelSymbol()
    {
    	textureWidth = 128;
        textureHeight = 64;
        
          symbol = new ModelRenderer(this, 0, 0);
          symbol.addBox(-16F, 0F, -16F, 32, 32, 32);
          symbol.setRotationPoint(0F, 0F, 0F);
          symbol.setTextureSize(128, 64);
          symbol.mirror = true;
          setRotation(symbol, 0F, 0F, 0F);
    }
    
	public void drawQuad(VertexBuffer vertexbuffer, float x1, float z1, float x2, float z2, float x3, float z3, float x4, float z4){
		vertexbuffer.pos((double)(x1 + 0.0F), 0, (double)(z1 + 0.0F)).tex(0,0).endVertex();
        vertexbuffer.pos((double)(x2 + 0.0F), 0, (double)(z2 + 0.0F)).tex(1,0).endVertex();
        vertexbuffer.pos((double)(x3 + 0.0F), 0, (double)(z3 + 0.0F)).tex(1,1).endVertex();
        vertexbuffer.pos((double)(x4 + 0.0F), 0, (double)(z4 + 0.0F)).tex(0,1).endVertex();
    }
    
    public void render(Entity entity, float f, float f1, float age, float f3, float f4, float f5)
    {
    	super.render(entity, f, f1, age, f3, f4, f5);
        setRotationAngles(f, f1, age, f3, f4, f5);
        GlStateManager.enableBlend();
        GlStateManager.color(1.0f*(((ISymbol)entity).isActivated() ? 1.0f : 0.6f), (237f/255f)*(((ISymbol)entity).isActivated() ? 1.0f : 0.6f), (199f/255f)*(((ISymbol)entity).isActivated() ? 1.0f : 0.6f), ((ISymbol)entity).getFade(age-(int)age));
        GlStateManager.translate(0, (((ISymbol)entity).getReady()*-0.5f)+1.35f, 0);
        GlStateManager.rotate(((ISymbol)entity).getAngle(age-(int)age),0,1,0);
        symbol.render(f5);
        GlStateManager.rotate(-((ISymbol)entity).getAngle(age-(int)age),0,1,0);
        GlStateManager.translate(0, (((ISymbol)entity).getReady()*0.5f)-1.35f, 0);
        GlStateManager.disableBlend();
    }
    
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
    }
    
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    }
}
