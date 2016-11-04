package teamroots.goetia.compat.jei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.plugins.vanilla.furnace.SmeltingRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import teamroots.goetia.recipes.DemonForgeRecipe;
import teamroots.goetia.recipes.DemonForgeRecipeManager;

public class DemonForgeRecipeMaker {
	
	public static List<DemonForgeRecipeWrapper> getForgeRecipes(IJeiHelpers helpers){
		IStackHelper stackHelper = helpers.getStackHelper();
		DemonForgeRecipeManager forgeRecipes = DemonForgeRecipeManager.instance;
		Map<Item, DemonForgeRecipe> forgeMap = forgeRecipes.recipes;

		List<DemonForgeRecipeWrapper> recipes = new ArrayList<DemonForgeRecipeWrapper>();
			
		
		for (Map.Entry<Item, DemonForgeRecipe> itemStackItemStackEntry : forgeRecipes.recipes.entrySet()) {
			Item input = itemStackItemStackEntry.getKey();
			DemonForgeRecipe output = itemStackItemStackEntry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(new ItemStack(input));
			DemonForgeRecipeWrapper recipe = new DemonForgeRecipeWrapper(inputs, output.result, output.cost);
			recipes.add(recipe);
		}

		return recipes;
	}
}
