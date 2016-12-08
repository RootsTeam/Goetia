package teamroots.goetia.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelFairy extends ModelBase
{
  //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer leftwing;
    ModelRenderer rightwing;
    ModelRenderer wand;
  
  public ModelFairy()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 12, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, 0F, -2F, 8, 12, 4);
      body.setRotationPoint(0F, 0F, 0F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-3F, -2F, -2F, 4, 12, 4);
      rightarm.setRotationPoint(-5F, 2F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 0, 16);
      leftarm.addBox(-1F, -2F, -2F, 4, 12, 4);
      leftarm.setRotationPoint(5F, 2F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = true;
      setRotation(leftarm, 0F, 0F, 0F);
      rightleg = new ModelRenderer(this, 28, 32);
      rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
      rightleg.setRotationPoint(-2F, 12F, 0F);
      rightleg.setTextureSize(64, 64);
      rightleg.mirror = true;
      setRotation(rightleg, 0F, 0F, 0F);
      leftleg = new ModelRenderer(this, 12, 32);
      leftleg.addBox(-3F, 0F, -2F, 4, 12, 4);
      leftleg.setRotationPoint(3F, 12F, 0F);
      leftleg.setTextureSize(64, 64);
      leftleg.mirror = true;
      setRotation(leftleg, 0F, 0F, 0F);
      leftwing = new ModelRenderer(this, 0, 48);
      leftwing.addBox(0F, 0F, 0F, 16, 14, 1);
      leftwing.setRotationPoint(1F, -1F, 2F);
      leftwing.setTextureSize(64, 64);
      leftwing.mirror = true;
      setRotation(leftwing, 0F, 0F, 0F);
      rightwing = new ModelRenderer(this, 0, 48);
      rightwing.addBox(0F, 0F, 0F, 16, 14, 1);
      rightwing.setRotationPoint(-17F, -1F, 2F);
      rightwing.setTextureSize(64, 64);
      rightwing.mirror = true;
      setRotation(rightwing, 0F, 0F, 0F);
      wand = new ModelRenderer(this, 34, 48);
      wand.addBox(0F, 0F, 0F, 2, 2, 12);
      wand.setRotationPoint(5F, 7F, -8F);
      wand.setTextureSize(64, 64);
      wand.mirror = true;
      setRotation(wand, -0.3717861F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    GL11.glTranslatef(0, 1.2f, 0);
    GL11.glScalef(0.2f, 0.2f, 0.2f);
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    rightarm.render(f5);
    leftarm.render(f5);
    rightleg.render(f5);
    leftleg.render(f5);
    leftwing.render(f5);
    rightwing.render(f5);
    wand.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entityIn)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entityIn);
  }

}
