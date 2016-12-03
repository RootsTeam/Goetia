package teamroots.goetia;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import teamroots.goetia.client.gui.guide.GuideEntry;
import teamroots.goetia.client.gui.guide.GuideGroup;
import teamroots.goetia.client.gui.guide.GuideInfoPage;
import teamroots.goetia.client.gui.guide.GuideManager;
import teamroots.goetia.client.gui.guide.pages.GuideDemonForgePage;
import teamroots.goetia.client.gui.guide.pages.GuideRitualPage;
import teamroots.goetia.common.blocks.BlockAltar;
import teamroots.goetia.common.blocks.BlockBase;
import teamroots.goetia.common.blocks.BlockBowl;
import teamroots.goetia.common.blocks.BlockCandle;
import teamroots.goetia.common.blocks.BlockCandleStand;
import teamroots.goetia.common.blocks.BlockLostNote;
import teamroots.goetia.common.entity.EntityBloodProjectile;
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
import teamroots.goetia.common.items.ItemAbyssalBlade;
import teamroots.goetia.common.items.ItemBase;
import teamroots.goetia.common.items.ItemBloodBottle;
import teamroots.goetia.common.items.ItemBloodKnife;
import teamroots.goetia.common.items.ItemDemonHorn;
import teamroots.goetia.common.items.ItemDemonicChalk;
import teamroots.goetia.common.items.ItemDemonicSpear;
import teamroots.goetia.common.items.ItemGuide;
import teamroots.goetia.common.items.ItemHolyCross;
import teamroots.goetia.common.items.ItemHolyWater;
import teamroots.goetia.common.items.ItemNote;
import teamroots.goetia.common.items.ItemSoulFocus;
import teamroots.goetia.common.items.ItemSpellIcon;
import teamroots.goetia.common.items.ItemSwordBase;
import teamroots.goetia.common.items.ItemSymbolIcon;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.recipes.DemonForgeRecipe;
import teamroots.goetia.recipes.DemonForgeRecipeManager;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class MainRegistry
{
    public static List<ItemBase>ITEMS = new ArrayList<ItemBase>();
    public static List<ItemSwordBase>ITEM_SWORD = new ArrayList<ItemSwordBase>();
    public static List<BlockBase>BLOCKS = new ArrayList<BlockBase>();

    public static BlockBase altar = new BlockAltar("altar",Material.ROCK);
    public static BlockBase demonCandle = new BlockCandle("demonCandle",Material.CLOTH);
    public static BlockBase demonCandleStand = new BlockCandleStand("demonCandleStand",Material.CLOTH);
    public static BlockBase angelCandle = new BlockCandle("angelCandle",Material.CLOTH);
    public static BlockBase angelCandleStand = new BlockCandleStand("angelCandleStand",Material.CLOTH);
    public static BlockBase candle = new BlockCandle("candle",Material.CLOTH);
    public static BlockBase candleStand = new BlockCandleStand("candleStand",Material.CLOTH);
    public static BlockBase fullBowl = new BlockBowl("fullBowl", false);
    public static BlockBase emptyBowl = new BlockBowl("emptyBowl", true);
    
    public static BlockBase blockAngelLostNote = new BlockLostNote("blockAngelLostNote",Material.CLOTH);
    public static BlockBase blockDemonLostNote = new BlockLostNote("blockDemonLostNote",Material.CLOTH);

    public static ItemBase demonHorn = new ItemDemonHorn("demonHorn");
    public static ItemBase impTallow = new ItemBase("impTallow");
    public static ItemBase tallow = new ItemBase("tallow");
    public static ItemBase demonHide = new ItemBase("demonHide");
    public static ItemSwordBase demonHornSpear = new ItemDemonicSpear();
    public static ItemSwordBase abyssalBlade = new ItemAbyssalBlade();
    public static ItemBase demonicChalk = new ItemDemonicChalk("demonicChalk");
    public static ItemBase lostDemonNotes = new ItemNote("lostDemonNotes", blockDemonLostNote, LibMain.LibKnowledge.validDemonKnowledge);
    
    public static ItemBase soulFocus = new ItemSoulFocus("soulFocus");
    public static ItemBase liquidBottle = new ItemBloodBottle("liquidBottle");
    public static ItemSwordBase bloodKnife = new ItemBloodKnife("bloodKnife");
    
    public static ItemBase holyCross = new ItemHolyCross("holyCross");
    public static ItemBase bottleHolyWater = new ItemHolyWater("bottleHolyWater");
    public static ItemBase lostAngelNotes = new ItemNote("lostAngelNotes", blockAngelLostNote, LibMain.LibKnowledge.validAngelKnowledge);
    
    public static ItemBase symbolIcon = new ItemSymbolIcon("symbolIcon");
    public static ItemBase spellIcon = new ItemSpellIcon("spellIcon");
    
    public static GuideManager manager = new GuideManager();
    //public static ItemBase guide = new ItemGuide(manager);
    
    public static void guideInit(){
    	GuideGroup main = new GuideGroup("Getting started");
    	manager.registerEntryGroup(main);
    	
    	main.addEntry(new GuideEntry("goetia", "Goetia!", new ItemStack(demonCandle), 25, 25));
    	manager.registerInfoPage("goetia", new GuideInfoPage("goetia"));
    	main.addEntry(new GuideEntry("notes", "Lost Notes", new ItemStack(lostDemonNotes), 25, 45));
    	manager.registerInfoPage("notes", new GuideInfoPage("notes"));
    	main.addEntry(new GuideEntry("demonHorn", "Demon Spear", new ItemStack(demonHorn), 25, 65));
    	manager.registerInfoPage("demonHorn", new GuideDemonForgePage("demonHorn", new ItemStack(demonHorn), new ItemStack(demonHornSpear), 45));
    	main.addEntry(new GuideEntry("demonBlade", "Abyssal Blade", new ItemStack(abyssalBlade), 25, 85));
    	manager.registerInfoPage("demonBlade", new GuideDemonForgePage("demonBlade", new ItemStack(Items.DIAMOND_SWORD), new ItemStack(abyssalBlade), 45));
    	
    	GuideGroup rituals = new GuideGroup("Rituals");
    	manager.registerEntryGroup(rituals);
    	rituals.addEntry(new GuideEntry("clean", "Ritual: Cleanse", new ItemStack(angelCandle), 40, 25));
    	manager.registerInfoPage("clean", new GuideRitualPage("clean"));
    }
    
    public static void register()
    {
    
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
    	GameRegistry.addSmelting(new ItemStack(MainRegistry.demonHide,1), new ItemStack(Items.LEATHER,1), 1.0f);
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(soulFocus),true,new Object[]{" G ", "GDG", " G ", 'G', Items.GOLD_INGOT, 'D', Items.DIAMOND}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(demonCandle,4),true,new Object[]{" S ", " T ", 'S', Items.STRING, 'T', MainRegistry.impTallow}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(demonCandleStand,1),true,new Object[]{" S ", " T ", " T ", 'S', MainRegistry.demonCandle, 'T', "nuggetGold"}));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(demonicChalk,1),new Object[]{Items.GUNPOWDER,Items.REDSTONE,new ItemStack(Items.DYE,1,1),new ItemStack(Items.COAL,1,1)}));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(angelCandle,1),new Object[]{candle, bottleHolyWater}));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(angelCandleStand,1),new Object[]{candleStand, bottleHolyWater}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(candle,4),true,new Object[]{" S ", " T ", 'S', Items.STRING, 'T', MainRegistry.tallow}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(candleStand,1),true,new Object[]{" S ", " T ", " T ", 'S', MainRegistry.candle, 'T', "nuggetGold"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(altar,1),true,new Object[]{"SCS", "SCS",  'S', "stone", 'C', "blockCoal"}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(holyCross,1),true,new Object[]{" S ", "SSS", " S ",  'S', "stone"}));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(bottleHolyWater,1),new Object[]{new ItemStack(Items.POTIONITEM,1,0),new ItemStack(holyCross.setContainerItem(holyCross))}));
    	GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(tallow,1),new Object[]{Items.BEEF, Items.BEEF}));
    	GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(bloodKnife,1),true,new Object[]{" I ", "RGR", " S ", 'S', Items.STICK, 'I', Items.IRON_INGOT, 'R', Items.REDSTONE, 'G', Items.GOLD_INGOT}));
    	
    	DemonForgeRecipeManager.instance.addDemonForgeRecipe(demonHorn, new DemonForgeRecipe(new ItemStack(demonHornSpear,1),45));
    	DemonForgeRecipeManager.instance.addDemonForgeRecipe(Items.DIAMOND_SWORD, new DemonForgeRecipe(new ItemStack(abyssalBlade,1),45));
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
		EntityRegistry.registerModEntity(EntityBloodProjectile.class, "bloodProjectile", 9, Goetia.instance, 64, 3, true);
	}
    
	@SideOnly(Side.CLIENT)
	public static void registerEntityRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityImp.class,   RenderImp::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityFiend.class, RenderFiend::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, RenderDemon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolImp.class, RenderSymbolImp::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolFiend.class, RenderSymbolFiend::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolDemon.class, RenderSymbolDemon::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolDevilsTrap.class, RenderSymbolDevilsTrap::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolForge.class, RenderSymbolForge::new);
		RenderingRegistry.registerEntityRenderingHandler(EntitySymbolOpenSoul.class, RenderSymbolOpenSoul::new);
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
