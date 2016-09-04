package teamroots.goetia.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ModelImp extends ModelBase {
    ModelRenderer head1;
    ModelRenderer body1;
    ModelRenderer horn1;
    ModelRenderer horn2;
    ModelRenderer armL;
    ModelRenderer armR;
    ModelRenderer legL1;
    ModelRenderer legL2;
    ModelRenderer legR1;
    ModelRenderer legR2;
    ModelRenderer wingL1;
    ModelRenderer wingL2;
    ModelRenderer wingL3;
    ModelRenderer wingL4;
    ModelRenderer wingR1;
    ModelRenderer wingR2;
    ModelRenderer wingR3;
    ModelRenderer wingR4;
    
    public ModelImp()
    {
      textureWidth = 64;
      textureHeight = 64;
      
        head1 = new ModelRenderer(this, 0, 0);
        head1.addBox(-3F, -3F, -3F, 6, 6, 6);
        head1.setRotationPoint(0F, 0F, 0F);
        head1.setTextureSize(64, 64);
        head1.mirror = true;
        setRotation(head1, 0F, 0F, 0F);
        body1 = new ModelRenderer(this, 0, 16);
        body1.addBox(-3F, -3F, -1F, 5, 8, 3);
        body1.setRotationPoint(0.5F, 4F, 1F);
        body1.setTextureSize(64, 64);
        body1.mirror = true;
        setRotation(body1, 0.2617994F, 0F, 0F);
        horn1 = new ModelRenderer(this, 32, 0);
        horn1.addBox(-1F, -5F, -1F, 2, 5, 2);
        horn1.setRotationPoint(-2F, -2F, -1.5F);
        horn1.setTextureSize(64, 64);
        horn1.mirror = true;
        setRotation(horn1, -1.047198F, -1.047198F, 0F);
        horn2 = new ModelRenderer(this, 32, 0);
        horn2.addBox(-1F, -5F, -1F, 2, 5, 2);
        horn2.setRotationPoint(2F, -2F, -1.5F);
        horn2.setTextureSize(64, 64);
        horn2.mirror = true;
        setRotation(horn2, -1.047198F, 1.047198F, 0F);
        armL = new ModelRenderer(this, 16, 16);
        armL.addBox(-1F, -5F, -1F, 2, 5, 2);
        armL.setRotationPoint(2F, 3F, 1.5F);
        armL.setTextureSize(64, 64);
        armL.mirror = true;
        setRotation(armL, -2.617994F, 1.308997F, 0F);
        armR = new ModelRenderer(this, 16, 16);
        armR.addBox(-1F, -5F, -1F, 2, 5, 2);
        armR.setRotationPoint(-2F, 3F, 1.5F);
        armR.setTextureSize(64, 64);
        armR.mirror = true;
        setRotation(armR, 2.617994F, 1.308997F, 0F);
        legL1 = new ModelRenderer(this, 32, 16);
        legL1.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
        legL1.setRotationPoint(1.5F, 8F, 2F);
        legL1.setTextureSize(64, 64);
        legL1.mirror = true;
        setRotation(legL1, -0.2617994F, 0F, -0.2617994F);
        legL2 = new ModelRenderer(this, 48, 16);
        legL2.addBox(-1F, 0F, -1F, 2, 3, 2);
        legL2.setRotationPoint(0F, 2.5F, 0.5F);
        legL2.setTextureSize(64, 64);
        legL2.mirror = true;
        setRotation(legL2, 2F*0.2617994F, 0F, 0.1308997F);
        legR1 = new ModelRenderer(this, 32, 16);
        legR1.addBox(-1.5F, 0F, -1.5F, 3, 3, 3);
        legR1.setRotationPoint(-1.5F, 8F, 2F);
        legR1.setTextureSize(64, 64);
        legR1.mirror = true;
        setRotation(legR1, -0.2617994F, 0F, 0.2617994F);
        legR2 = new ModelRenderer(this, 48, 16);
        legR2.addBox(-1F, 0F, -1F, 2, 3, 2);
        legR2.setRotationPoint(0F, 2.5F, 0.5F);
        legR2.setTextureSize(64, 64);
        legR2.mirror = true;
        setRotation(legR2, 2F*0.2617994F, 0F, -0.1308997F);
        wingL1 = new ModelRenderer(this, 0, 32);
        wingL1.addBox(-1F, 0F, -1F, 2, 8, 2);
        wingL1.setRotationPoint(1F, 3.5F, 1.5F);
        wingL1.setTextureSize(64, 64);
        wingL1.mirror = true;
        setRotation(wingL1, -2.356194F, -2.617994F, 0F);
        wingL2 = new ModelRenderer(this, 16, 32);
        wingL2.addBox(-0.5F, 1F, 1F, 1, 7, 7);
        wingL2.setRotationPoint(1F, 3.5F, 1.5F);
        wingL2.setTextureSize(64, 64);
        wingL2.mirror = true;
        setRotation(wingL2, -2.356194F, -2.617994F, 0F);
        wingL3 = new ModelRenderer(this, 32, 16);
        wingL3.addBox(-1.5F, 7.5F, -1.5F, 3, 3, 3);
        wingL3.setRotationPoint(1F, 3.5F, 1.5F);
        wingL3.setTextureSize(64, 64);
        wingL3.mirror = true;
        setRotation(wingL3, -2.356194F, -2.617994F, 0F);
        wingL4 = new ModelRenderer(this, 0, 48);
        wingL4.addBox(-1F, 8F, 1F, 2, 2, 8);
        wingL4.setRotationPoint(1F, 3.5F, 1.5F);
        wingL4.setTextureSize(64, 64);
        wingL4.mirror = true;
        setRotation(wingL4, -2.356194F, -2.617994F, 0F);
        wingR1 = new ModelRenderer(this, 0, 32);
        wingR1.addBox(-1F, 0F, -1F, 2, 8, 2);
        wingR1.setRotationPoint(-1F, 3.5F, 1.5F);
        wingR1.setTextureSize(64, 64);
        wingR1.mirror = true;
        setRotation(wingR1, -2.356194F, 2.617994F, 0F);
        wingR2 = new ModelRenderer(this, 16, 32);
        wingR2.addBox(-0.5F, 1F, 1F, 1, 7, 7);
        wingR2.setRotationPoint(-1F, 3.5F, 1.5F);
        wingR2.setTextureSize(64, 64);
        wingR2.mirror = true;
        setRotation(wingR2, -2.356194F, 2.617994F, 0F);
        wingR3 = new ModelRenderer(this, 32, 16);
        wingR3.addBox(-1.5F, 7.5F, -1.5F, 3, 3, 3);
        wingR3.setRotationPoint(-1F, 3.5F, 1.5F);
        wingR3.setTextureSize(64, 64);
        wingR3.mirror = true;
        setRotation(wingR3, -2.356194F, 2.617994F, 0F);
        wingR4 = new ModelRenderer(this, 0, 48);
        wingR4.addBox(-1F, 8F, 1F, 2, 2, 8);
        wingR4.setRotationPoint(-1F, 3.5F, 1.5F);
        wingR4.setTextureSize(64, 64);
        wingR4.mirror = true;
        setRotation(wingR4, -2.356194F, 2.617994F, 0F);
        
        legL1.addChild(legL2);
        legR1.addChild(legR2);
    }
    
    public void render(Entity entity, float f, float f1, float age, float f3, float f4, float f5)
    {
      float speed = (float) ((new Vec3d(entity.motionX,0,entity.motionZ)).lengthVector() * 2.0f);
      GlStateManager.translate(0, 0.65, 0);
      super.render(entity, f, f1, age, f3, f4, f5);
      setRotationAngles(f, f1, age, f3, f4, f5);
      head1.render(f5);
      body1.render(f5);
      horn1.render(f5);
      horn2.render(f5);
      GlStateManager.rotate(speed*60f*(float) Math.sin(Math.toRadians(age % 360)*24F), 1, 0, 0);
      armR.render(f5);
      GlStateManager.rotate(-120f*speed*(float) Math.sin(Math.toRadians(age % 360)*24F), 1, 0, 0);
      armL.render(f5);
      GlStateManager.rotate(speed*60f*(float) Math.sin(Math.toRadians(age % 360)*24F), 1, 0, 0);
      GlStateManager.rotate(speed*60f*(float) Math.sin(Math.toRadians(age % 360)*24F), 1, 0, 0);
      legL1.render(f5);
      GlStateManager.rotate(-120f*speed*(float) Math.sin(Math.toRadians(age % 360)*24F), 1, 0, 0);
      legR1.render(f5);
      GlStateManager.rotate(speed*60f*(float) Math.sin(Math.toRadians(age % 360)*24F), 1, 0, 0);
      GlStateManager.rotate(30F*(float)Math.abs(Math.sin(Math.toRadians(age % 360)*15F)), 0, 1, 0);
      wingL1.render(f5);
      wingL2.render(f5);
      wingL3.render(f5);
      wingL4.render(f5);
      GlStateManager.rotate(-60F*(float)Math.abs(Math.sin(Math.toRadians(age % 360)*15F)), 0, 1, 0);
      wingR1.render(f5);
      wingR2.render(f5);
      wingR3.render(f5);
      wingR4.render(f5);
      GlStateManager.rotate(30F*(float)Math.abs(Math.sin(Math.toRadians(age % 360)*15F)), 0, 1, 0);
      GlStateManager.translate(0, -0.65, 0);
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
