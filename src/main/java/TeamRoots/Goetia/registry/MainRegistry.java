package TeamRoots.Goetia.registry;

import TeamRoots.Goetia.common.blocks.base.BlockBase;
import TeamRoots.Goetia.common.items.base.ItemBase;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class MainRegistry
{
    public static final List<ItemBase>ITEMS = new ArrayList<ItemBase>();

    public static final List<BlockBase>BLOCKS = new ArrayList<BlockBase>();


    public static void register()
    {
        /**
         * Register items
         */

        /**
         * Register blocks
         */
        for(BlockBase blockBase : BLOCKS)
        {
            GameRegistry.register(blockBase);
            GameRegistry.register(new ItemBlock(blockBase).setRegistryName(blockBase.getRegistryName()));
        }

    }

    @SideOnly(Side.CLIENT)
    public static void initTextures()
    {
        //Item textures
        ITEMS.forEach(ItemBase::initModelsAndVariants);
        //Block textures
        BLOCKS.forEach(BlockBase::initModels);
    }
}
