package teamroots.goetia.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class BlockDemonCandleStand extends BlockBase
{
    public BlockDemonCandleStand(String blockName, Material material) {
        super(blockName, material);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
}
