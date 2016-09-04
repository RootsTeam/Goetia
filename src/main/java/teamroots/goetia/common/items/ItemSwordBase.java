package teamroots.goetia.common.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import teamroots.goetia.Goetia;
import teamroots.goetia.registry.MainRegistry;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemSwordBase extends ItemSword {
    private final String BaseName;

    public ItemSwordBase(String name, ToolMaterial material) {
        super(material);
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Goetia.tab);
        setMaxStackSize(1);
        setNoRepair();
        BaseName = name;

        MainRegistry.ITEM_SWORD.add(this);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        return super.getUnlocalizedName(itemStack);
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
    }
}
