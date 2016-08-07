package teamroots.goetia.common.symbol;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.common.entity.EntitySymbolImp;

public class SymbolManager {
	public static HashMap<String, Class> symbols = new HashMap<String, Class>();
	public static HashMap<String, ResourceLocation> symbolTextures = new HashMap<String, ResourceLocation>();
	
	public static void init(){
		symbols.put("impSymbol", EntitySymbolImp.class);
		symbolTextures.put("impSymbol", new ResourceLocation("goetia:textures/entity/impSymbol.png"));
	}
}
