package teamroots.goetia.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import teamroots.goetia.Goetia;
import teamroots.goetia.lib.LibMain;

public class Fluids {
	 private static ModelResourceLocation holyWaterfluidLocation = new ModelResourceLocation(LibMain.LibCore.MOD_ID + ":" + "holyWater", "fluid");

	 public static void preInit() {
		 buildRender(FluidHolyWater.instance, FluidBlockHolyWater.instance, "holyWater", holyWaterfluidLocation);
	 }

	 private static void buildRender(Fluid fluid, Block block, String name, ModelResourceLocation resource){
		 Item item = Item.getItemFromBlock(block);
		 if(item != null) ModelLoader.setCustomMeshDefinition(item, stack -> resource);
		 
		 ModelLoader.setCustomStateMapper(block, new StateMapperBase(){
			 protected ModelResourceLocation getModelResourceLocation(IBlockState state){
				 return resource;
			 }
		 });
		 FluidRegistry.addBucketForFluid(fluid);
	 }
}
