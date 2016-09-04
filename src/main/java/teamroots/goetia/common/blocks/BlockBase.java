package teamroots.goetia.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.Goetia;
import teamroots.goetia.registry.MainRegistry;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class BlockBase extends Block
{
    public BlockBase(String blockName)
    {
        this(blockName,Material.ROCK);
    }

    public BlockBase(String blockName, Material material)
    {
        super(material);
        setRegistryName(blockName);
        setUnlocalizedName(blockName);
        setCreativeTab(Goetia.tab);
        MainRegistry.BLOCKS.add(this);
    }

    @SideOnly(Side.CLIENT)
    public void initModels()
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),0,new ModelResourceLocation(getRegistryName().toString()))   ;
    }
}
