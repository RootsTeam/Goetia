package teamroots.goetia.proxy;

import teamroots.goetia.MainRegistry;
import teamroots.goetia.client.model.ModelManager;
import teamroots.goetia.renderlayers.LayerHalo;
import teamroots.goetia.renderlayers.LayerHorns;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        MainRegistry.registerEntityRenderers();
        RenderPlayer render = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default");
        render.addLayer(new LayerHorns());
        render.addLayer(new LayerHalo());
  
        RenderPlayer renderSlim = Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim");
        renderSlim.addLayer(new LayerHorns());
        renderSlim.addLayer(new LayerHalo());
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}
