package teamroots.goetia.compat.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import teamroots.goetia.lib.LibMain;

public class DemonForgeCategory extends BlankRecipeCategory{

	private static final int inputSlot = 0;
	private static final int outputSlot = 1;
	
	private final IDrawable background;
	private final IDrawable slotDrawable;
	
	private final String title;
	
	public DemonForgeCategory(IGuiHelper gui){
		background = gui.createDrawable(new ResourceLocation(LibMain.LibCore.MOD_ID, "textures/gui/jei_demon_forge.png"), 30, 16, 110, 60);
		title = Translator.translateToLocal("gui.jei.category.demon_forge");
		slotDrawable = gui.getSlotDrawable();
	}
	
	@Override
	public String getUid() {
		return LibMain.LibCore.JEI_DEMON_FORGE_UUID;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup itemstacks = recipeLayout.getItemStacks();
		
		recipeLayout.getItemStacks().init(inputSlot, true, 13, 17);
		recipeLayout.getItemStacks().init(outputSlot, false, 85, 17);
		
		itemstacks.set(ingredients);
		
	}

}
