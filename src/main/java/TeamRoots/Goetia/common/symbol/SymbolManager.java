package teamroots.goetia.common.symbol;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.common.entity.EntitySymbolDemon;
import teamroots.goetia.common.entity.EntitySymbolDevilsTrap;
import teamroots.goetia.common.entity.EntitySymbolFiend;
import teamroots.goetia.common.entity.EntitySymbolForge;
import teamroots.goetia.common.entity.EntitySymbolImp;
import teamroots.goetia.common.entity.EntitySymbolOpenSoul;

public class SymbolManager {
	public static HashMap<String, Class> symbols = new HashMap<String, Class>();
	public static HashMap<String, ResourceLocation> symbolTextures = new HashMap<String, ResourceLocation>();
	public static HashMap<String, Integer> symbolCosts = new HashMap<String, Integer>();
	
	public static void init(){
		symbols.put("impSymbol", EntitySymbolImp.class);
		symbolTextures.put("impSymbol", new ResourceLocation("goetia:textures/entity/impSymbol.png"));
		symbolCosts.put("impSymbol", 5);
		symbols.put("fiendSymbol", EntitySymbolFiend.class);
		symbolTextures.put("fiendSymbol", new ResourceLocation("goetia:textures/entity/fiendSymbol.png"));
		symbolCosts.put("fiendSymbol", 9);
		symbols.put("demonSymbol", EntitySymbolDemon.class);
		symbolTextures.put("demonSymbol", new ResourceLocation("goetia:textures/entity/demonSymbol.png"));
		symbolCosts.put("demonSymbol", 13);
		symbols.put("devilsTrap", EntitySymbolDevilsTrap.class);
		symbolTextures.put("devilsTrap", new ResourceLocation("goetia:textures/entity/devilTrap.png"));
		symbolCosts.put("devilsTrap", 3);
		symbols.put("forgeSymbol", EntitySymbolForge.class);
		symbolTextures.put("forgeSymbol", new ResourceLocation("goetia:textures/entity/forgeSymbol.png"));
		symbolCosts.put("forgeSymbol", 11);
		symbols.put("openSoulSymbol", EntitySymbolOpenSoul.class);
		symbolTextures.put("openSoulSymbol", new ResourceLocation("goetia:textures/entity/openSoulSymbol.png"));
		symbolCosts.put("openSoulSymbol", 23);
	}
}
