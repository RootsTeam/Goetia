package teamroots.goetia.registry;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import teamroots.goetia.common.blocks.BlockBase;
import teamroots.goetia.common.blocks.BlockDemonCandle;
import teamroots.goetia.common.blocks.BlockDemonCandleStand;
import teamroots.goetia.common.items.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class MainRegistry
{
    public static List<ItemBase>ITEMS = new ArrayList<ItemBase>();
    public static List<ItemSwordBase>ITEM_SWORD = new ArrayList<ItemSwordBase>();
    public static List<BlockBase>BLOCKS = new ArrayList<BlockBase>();

    public static BlockBase altar = new BlockBase("altar",Material.ROCK);
    public static BlockBase demonCandle = new BlockDemonCandle("demonCandle",Material.CLOTH);
    public static BlockBase demonCandleStand = new BlockDemonCandleStand("demonCandleStand",Material.CLOTH);

    public static ItemBase demonHorn = new ItemDemonHorn("demonHorn");
    public static ItemBase impTallow = new ItemBase("impTallow");
    public static ItemBase demonHide = new ItemBase("demonHide");
    public static ItemSwordBase demonHornSpear = new ItemDemonicSpear();
    public static ItemSwordBase abyssalBlade = new ItemAbyssalBlade();
    public static ItemBase soulFocus = new ItemBase("soulFocus");
    public static ItemBase demonicChalk = new ItemDemonicChalk("demonicChalk");
    
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
    	for (ItemSwordBase itemSwordBase : ITEM_SWORD)
        {
            GameRegistry.register(itemSwordBase);
        }
    	
        for (BlockBase blockBase : BLOCKS)
        {
            GameRegistry.register(blockBase);
            GameRegistry.register(new ItemBlock(blockBase).setRegistryName(blockBase.getRegistryName()));
        }

        recipesRegistry();

    }

    private static void recipesRegistry()
    {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(demonHornSpear), true,new Object[]{"  H"," B ","B  ",'H', new ItemStack(demonHorn),'B',new ItemStack(Items.BONE)}));
    }

    @SideOnly(Side.CLIENT)
    public static void initTextures()
    {
        //Item textures
        ITEMS.forEach(ItemBase::initModelsAndVariants);
        ITEM_SWORD.forEach(ItemSwordBase::initModelsAndVariants);
        //Block textures
        BLOCKS.forEach(BlockBase::initModels);
    }
}
