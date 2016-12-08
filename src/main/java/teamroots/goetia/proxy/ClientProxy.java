package teamroots.goetia.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.client.model.ModelManager;
import teamroots.goetia.common.blocks.BlockBowl;
import teamroots.goetia.common.items.ItemBloodBottle;
import teamroots.goetia.fluids.Fluids;
import teamroots.goetia.renderlayers.LayerAngel;
import teamroots.goetia.renderlayers.LayerDemon;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ModelManager.init();
        MainRegistry.initTextures();
        MainRegistry.registerEntityRenderers();
        Fluids.preInit();
        MainRegistry.guideInit();
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        
        RenderPlayer render = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default");
        render.addLayer(new LayerDemon());
        render.addLayer(new LayerAngel());
  
        RenderPlayer renderSlim = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim");
        renderSlim.addLayer(new LayerDemon());
        renderSlim.addLayer(new LayerAngel());
        MainRegistry.registerColorHandlers();
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}
