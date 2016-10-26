package teamroots.goetia.compat.jei;

import mezz.jei.api.recipe.IRecipeHandler;
import mezz.jei.api.recipe.IRecipeWrapper;
import teamroots.goetia.lib.LibMain;

public class DemonForgeRecipeHandler implements IRecipeHandler<DemonForgeRecipeWrapper>{

	@Override
	public Class<DemonForgeRecipeWrapper> getRecipeClass() {
		return DemonForgeRecipeWrapper.class;
	}

	@Override
	public String getRecipeCategoryUid() {
		return LibMain.LibCore.JEI_DEMON_FORGE_UUID;
	}

	@Override
	public String getRecipeCategoryUid(DemonForgeRecipeWrapper recipe) {
		return LibMain.LibCore.JEI_DEMON_FORGE_UUID;
	}

	@Override
	public IRecipeWrapper getRecipeWrapper(DemonForgeRecipeWrapper recipe) {
		return recipe;
	}

	@Override
	public boolean isRecipeValid(DemonForgeRecipeWrapper recipe) {
		if (recipe.getInputs().isEmpty()) {
			
		}
		if (recipe.getOutputs().isEmpty()) {
			
		}
		return true;
	}

}
