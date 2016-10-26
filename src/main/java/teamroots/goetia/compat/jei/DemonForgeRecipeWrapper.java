package teamroots.goetia.compat.jei;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class DemonForgeRecipeWrapper extends BlankRecipeWrapper{
	private final List<List<ItemStack>> input;
	private final ItemStack output;
	private int cost;
	
	public DemonForgeRecipeWrapper(List<ItemStack> input, ItemStack output, int cost){
		this.input = Collections.singletonList(input);
		this.output = output;
		this.cost = cost;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(ItemStack.class, input);
		ingredients.setOutput(ItemStack.class, output);
	}
	
	@Override
	public List<List<ItemStack>> getInputs() {
		return input;
	}

	@Override
	public List<ItemStack> getOutputs() {
		return Collections.singletonList(output);
	}
	
	@Override
	public void drawInfo(@Nonnull Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY)
	{
	        minecraft.fontRendererObj.drawString(Integer.toString(cost), 55, 10, Color.RED.getRGB());
	}

}
