package teamroots.goetia.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHalo extends ModelBase
{
	ModelRenderer Elem8;//Cube
	ModelRenderer Elem7;//Cube
	ModelRenderer Elem6;//Cube
	ModelRenderer Elem5;//Cube

	public ModelHalo()
	{
		textureWidth = 32;
		textureHeight = 32;

		//Cube
		Elem8 = new ModelRenderer(this, 0, 0);
		Elem8.addBox(0F, 0F, 0F, 1, 1, 6);
		Elem8.setRotationPoint(2F, 21F, -3F);
		Elem8.setTextureSize(16, 16);
		setRotation(Elem8, -0F, -0F, -0F);
		//Cube
		Elem7 = new ModelRenderer(this, 0, 0);
		Elem7.addBox(0F, 0F, 0F, 1, 1, 6);
		Elem7.setRotationPoint(-3F, 21F, -3F);
		Elem7.setTextureSize(16, 16);
		setRotation(Elem7, -0F, -0F, -0F);
		//Cube
		Elem6 = new ModelRenderer(this, 0, 0);
		Elem6.addBox(0F, 0F, 0F, 4, 1, 1);
		Elem6.setRotationPoint(-2F, 21F, 2F);
		Elem6.setTextureSize(16, 16);
		setRotation(Elem6, -0F, -0F, -0F);
		//Cube
		Elem5 = new ModelRenderer(this, 0, 0);
		Elem5.addBox(0F, 0F, 0F, 4, 1, 1);
		Elem5.setRotationPoint(-2F, 21F, -3F);
		Elem5.setTextureSize(16, 16);
		setRotation(Elem5, -0F, -0F, -0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Elem8.render(f5);//Cube
		Elem7.render(f5);//Cube
		Elem6.render(f5);//Cube
		Elem5.render(f5);//Cube
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