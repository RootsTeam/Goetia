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
		addSymbol("impSymbol", EntitySymbolImp.class, 5 ,new ResourceLocation("goetia:textures/entity/impSymbol.png"));
		addSymbol("fiendSymbol", EntitySymbolFiend.class, 9 ,new ResourceLocation("goetia:textures/entity/fiendSymbol.png"));
		addSymbol("demonSymbol", EntitySymbolDemon.class, 13 ,new ResourceLocation("goetia:textures/entity/demonSymbol.png"));
		addSymbol("devilsTrap", EntitySymbolDevilsTrap.class, 3 ,new ResourceLocation("goetia:textures/entity/devilTrap.png"));
		addSymbol("forgeSymbol", EntitySymbolForge.class, 11 ,new ResourceLocation("goetia:textures/entity/forgeSymbol.png"));
		addSymbol("openSoulSymbol", EntitySymbolOpenSoul.class, 23 , new ResourceLocation("goetia:textures/entity/openSoulSymbol.png"));
	}
	
	public static void addSymbol(String name, Class c, int cost, ResourceLocation texture){
		symbols.put(name, c);
		symbolTextures.put(name, texture);
		symbolCosts.put(name, cost);
	}
}
