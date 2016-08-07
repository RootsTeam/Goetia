package teamroots.goetia.common.entity;

public interface ISymbol {
	public int getLevelCost();
	
	public boolean isActivated();
	
	public float getAngle(float partialTicks);
	
	public float getReady();
	
	public float getFade(float partialTicks);
	
	public void activate();
}
