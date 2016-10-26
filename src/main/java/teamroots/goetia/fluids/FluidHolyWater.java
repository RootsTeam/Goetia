package teamroots.goetia.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import teamroots.goetia.lib.LibMain;

public class FluidHolyWater extends Fluid{
	public static final FluidHolyWater instance = new FluidHolyWater();
	
	public FluidHolyWater() {
		super("goetia.holywater", new ResourceLocation(LibMain.LibCore.MOD_ID, "fluid/holy_still"), new ResourceLocation(LibMain.LibCore.MOD_ID, "fluid/holy_flowing"));
		FluidRegistry.registerFluid(this);
	}
}
