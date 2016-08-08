package teamroots.goetia.registry;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.Goetia;
import teamroots.goetia.client.model.ModelManager;
import teamroots.goetia.common.blocks.BlockBase;
import teamroots.goetia.common.blocks.BlockDemonCandle;
import teamroots.goetia.common.blocks.BlockDemonCandleStand;
import teamroots.goetia.common.entity.EntityDemon;
import teamroots.goetia.common.entity.EntityFiend;
import teamroots.goetia.common.entity.EntityImp;
import teamroots.goetia.common.entity.EntitySymbolDemon;
import teamroots.goetia.common.entity.EntitySymbolDevilsTrap;
import teamroots.goetia.common.entity.EntitySymbolFiend;
import teamroots.goetia.common.entity.EntitySymbolForge;
import teamroots.goetia.common.entity.EntitySymbolImp;
import teamroots.goetia.common.entity.EntitySymbolOpenSoul;
import teamroots.goetia.common.entity.RenderDemon;
import teamroots.goetia.common.entity.RenderFiend;
import teamroots.goetia.common.entity.RenderImp;
import teamroots.goetia.common.entity.RenderSymbolDemon;
import teamroots.goetia.common.entity.RenderSymbolDevilsTrap;
import teamroots.goetia.common.entity.RenderSymbolFiend;
import teamroots.goetia.common.entity.RenderSymbolForge;
import teamroots.goetia.common.entity.RenderSymbolImp;
import teamroots.goetia.common.entity.RenderSymbolOpenSoul;
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
    public static ItemBase soulFocus = new ItemSoulFocus("soulFocus");
    public static ItemBase demonicChalk = new ItemDemonicChalk("demonicChalk");
    public static ItemBase lostNotes = new ItemNote("lostNotes");
    public static ItemBase symbolIcon = new ItemSymbolIcon("symbolIcon");
    public static ItemBase spellIcon = new ItemSpellIcon("spellIcon");
    
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
    
	public static void registerEntities(){
		EntityRegistry.registerModEntity(EntityImp.class, "imp", 0, Goetia.instance, 64, 3, true);
		EntityRegistry.registerEgg(EntityImp.class, 0x6A1024, 0xA06774);
		EntityRegistry.registerModEntity(EntityFiend.class, "fiend", 1, Goetia.instance, 64, 3, true);
		EntityRegistry.registerEgg(EntityFiend.class, 0x6A1024, 0xA06774);
		EntityRegistry.registerModEntity(EntityDemon.class, "demon", 2, Goetia.instance, 64, 3, true);
		EntityRegistry.registerEgg(EntityDemon.class, 0x6A1024, 0xA06774);
		EntityRegistry.registerModEntity(EntitySymbolImp.class, "symbolImp", 3, Goetia.instance, 64, 3, true);
		EntityRegistry.registerModEntity(EntitySymbolFiend.class, "symbolFiend", 4, Goetia.instance, 64, 3, true);
		EntityRegistry.registerModEntity(EntitySymbolDemon.class, "symbolDemon", 5, Goetia.instance, 64, 3, true);
		EntityRegistry.registerModEntity(EntitySymbolDevilsTrap.class, "symbolDevilsTrap", 6, Goetia.instance, 64, 3, true);
		EntityRegistry.registerModEntity(EntitySymbolForge.class, "symbolForge", 7, Goetia.instance, 64, 3, true);
		EntityRegistry.registerModEntity(EntitySymbolOpenSoul.class, "symbolOpenSoul", 8, Goetia.instance, 64, 3, true);
	}
    
	@SideOnly(Side.CLIENT)
	public static void registerEntityRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityImp.class, new RenderImp(Minecraft.getMinecraft().getRenderManager(),ModelManager.entityModels.get("imp"),0.3f));
		RenderingRegistry.registerEntityRenderingHandler(EntityFiend.class, new RenderFiend(Minecraft.getMinecraft().getRenderManager(),ModelManager.entityModels.get("fiend"),0.5f));
		RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, new RenderDemon(Minecraft.getMinecraft().getRenderManager(),ModelManager.entityModels.get("demon"),0.8f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolImp.class, new RenderSymbolImp(Minecraft.getMinecraft().getRenderManager(),ModelManager.entityModels.get("symbol"),0f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolFiend.class, new RenderSymbolFiend(Minecraft.getMinecraft().getRenderManager(),ModelManager.entityModels.get("symbol"),0f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolDemon.class, new RenderSymbolDemon(Minecraft.getMinecraft().getRenderManager(),ModelManager.entityModels.get("symbol"),0f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolDevilsTrap.class, new RenderSymbolDevilsTrap(Minecraft.getMinecraft().getRenderManager(),ModelManager.entityModels.get("symbol"),0f));
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolForge.class, new RenderSymbolForge(Minecraft.getMinecraft().getRenderManager(),ModelManager.entityModels.get("symbol"),0f));
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
