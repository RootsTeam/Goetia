package teamroots.goetia.fluids;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.registry.GameRegistry;
import teamroots.goetia.common.entity.IDemonic;
import teamroots.goetia.lib.LibMain;

public class FluidBlockHolyWater extends BlockFluidClassic{
	public static final FluidBlockHolyWater instance = new FluidBlockHolyWater();
	
	public FluidBlockHolyWater() {
		super(FluidHolyWater.instance, Material.WATER);
        GameRegistry.register(this, new ResourceLocation(LibMain.LibCore.MOD_ID, "holyWater"));
        this.setQuantaPerBlock(8);
        this.setUnlocalizedName("holyWater");
	}

	@Override
	public Fluid getFluid() {
		return FluidHolyWater.instance;
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		if(!world.isRemote){
			if(entity instanceof IDemonic){
				((IDemonic) entity).onHolyWaterContact();
			}
		}
	}
}
