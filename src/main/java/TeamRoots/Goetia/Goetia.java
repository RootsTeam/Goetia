package teamroots.goetia;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.proxy.IProxy;
import teamroots.goetia.registry.MainRegistry;
/**
 * Created by TeamRoots on 4.8.2016.
 */
@Mod(modid = LibMain.LibCore.MOD_ID,name = LibMain.LibCore.MOD_NAME,version = LibMain.LibCore.VERSION,guiFactory = LibMain.LibCore.GUI_FACTORY_CLASS,useMetadata = true)
public class Goetia
{
    @Mod.Instance(LibMain.LibCore.MOD_ID)
    public static Goetia instance;

    @SidedProxy(clientSide = LibMain.LibCore.CLIENT_PROXY_CLASS,serverSide = LibMain.LibCore.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    
    public static CreativeTabs tab = new CreativeTabs("goetia") {
    	@Override
    	public String getTabLabel(){
    		return "goetia";
    	}
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem(){
			return Item.getItemFromBlock(MainRegistry.altar);
		}
	};
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
