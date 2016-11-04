package teamroots.goetia.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DemonForgeRecipeManager {
	public static Map<Item, DemonForgeRecipe> recipes = new HashMap<Item, DemonForgeRecipe>();
	public static DemonForgeRecipeManager instance = new DemonForgeRecipeManager();
	
	public void addDemonForgeRecipe(Item ingredient, DemonForgeRecipe r){
		recipes.put(ingredient, r);
	}
}
