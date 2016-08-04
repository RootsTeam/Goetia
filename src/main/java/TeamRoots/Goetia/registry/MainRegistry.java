package teamroots.goetia.registry;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.common.blocks.BlockBase;
import teamroots.goetia.common.items.base.ItemBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class MainRegistry
{
    public static List<ItemBase>ITEMS = new ArrayList<ItemBase>();

    public static List<BlockBase>BLOCKS = new ArrayList<BlockBase>();

    public static BlockBase altar = new BlockBase("altar",Material.ROCK);
    public static BlockBase demonCandle = new BlockBase("demonCandle",Material.CLOTH).isOpaqueAndFullCube(false);
    public static BlockBase demonCandleStand = new BlockBase("demonCandleStand",Material.CLOTH).isOpaqueAndFullCube(false);

    public static ItemBase demonHorn = new ItemBase("demonHorn");
    public static ItemBase impTallow = new ItemBase("impTallow");
    public static ItemBase demonHide = new ItemBase("demonHide");
    public static ItemBase demonHornSpear = new ItemBase("demonHornSpear");
    public static ItemBase abyssalBlade = new ItemBase("abyssalBlade");
    public static ItemBase soulFocus = new ItemBase("soulFocus");
    public static ItemBase demonicChalk = new ItemBase("demonicChalk");
    
    public static void register()
    {
        /**
         * Register items
         */

        /**
         * Register blocks
         */
    	for (ItemBase itemBase : ITEMS){
    		GameRegistry.register(itemBase);
    	}
    	
        for (BlockBase blockBase : BLOCKS)
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
