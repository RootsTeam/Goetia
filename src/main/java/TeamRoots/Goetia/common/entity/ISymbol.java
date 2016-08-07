package teamroots.goetia.common.entity;

import net.minecraft.util.ResourceLocation;

public interface ISymbol {
	public String getSymbolName();
	
	public int getLevelCost();
	
	public boolean isActivated();
	
	public float getAngle(float partialTicks);
	
	public float getReady();
	
	public float getFade(float partialTicks);
	
	public ResourceLocation getTextureLocation();
	
	public void activate();
}
