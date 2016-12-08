package teamroots.goetia.client.model;

import java.util.HashMap;

import net.minecraft.client.model.ModelBase;
public class ModelManager {
	public static HashMap<String, ModelBase> entityModels = new HashMap<String, ModelBase>();
	
	public static void init(){
		entityModels.put("null", new ModelNull());
		entityModels.put("imp", new ModelImp());
		entityModels.put("fiend", new ModelFiend());
		entityModels.put("demon", new ModelDemon());
		entityModels.put("symbol", new ModelSymbol());
		entityModels.put("fairy", new ModelFairy());
	}
}
