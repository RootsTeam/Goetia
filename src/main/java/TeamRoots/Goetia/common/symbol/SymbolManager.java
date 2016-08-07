package teamroots.goetia.common.symbol;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import teamroots.goetia.common.entity.EntitySymbolImp;

public class SymbolManager {
	public static HashMap<String, Class> symbols = new HashMap<String, Class>();
	
	public static void init(){
		symbols.put("imp", EntitySymbolImp.class);
	}
}
