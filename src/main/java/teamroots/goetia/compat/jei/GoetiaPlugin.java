package teamroots.goetia.compat.jei;

import javax.annotation.Nonnull;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.lib.LibMain;

@JEIPlugin
public class GoetiaPlugin extends BlankModPlugin{
	 public static IJeiHelpers jeiHelper;
	 
	@Override
	public void register(@Nonnull IModRegistry registry)
	{
		jeiHelper = registry.getJeiHelpers();
		IGuiHelper guiHelper = jeiHelper.getGuiHelper();
		registry.addRecipeCategories(new DemonForgeCategory(guiHelper));
		registry.addRecipeHandlers(new DemonForgeRecipeHandler());
		registry.addRecipes(DemonForgeRecipeMaker.getForgeRecipes(jeiHelper));
		registry.addRecipeCategoryCraftingItem(new ItemStack(MainRegistry.altar), LibMain.LibCore.JEI_DEMON_FORGE_UUID);
	}
}
