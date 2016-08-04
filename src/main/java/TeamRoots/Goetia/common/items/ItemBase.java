package teamroots.goetia.common.items;

import teamroots.goetia.Goetia;
import teamroots.goetia.lib.LibMain;
import teamroots.goetia.registry.MainRegistry;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemBase extends Item implements IItemVariantHolder<ItemBase>
{
    private final String BaseName;
    private final String[] Variants;

    public ItemBase(String name,String ... variants)
    {
        super();
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Goetia.tab);
        setMaxStackSize(1);
        setNoRepair();
        BaseName = name;

        if(variants.length == 0)
        {
            Variants = new String[]{"normal"};
        }else {
            Variants = variants;
        }

        MainRegistry.ITEMS.add(this);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        if (hasSubtypes && itemStack.getMetadata() < Variants.length) {
            return String.format("item." + ":%s", Variants[itemStack.getMetadata()]);
        }
        return super.getUnlocalizedName(itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        if (!getHasSubtypes()) {
            super.getSubItems(itemIn, tab, subItems);
        }
        else {
            for (int meta = 0; meta < Variants.length; ++meta) {
                subItems.add(new ItemStack(this, 1, meta));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public void initModelsAndVariants() {
        if (getCustomMeshDefinition() != null)
        {
            ModelLoader.setCustomMeshDefinition(this, getCustomMeshDefinition());
            for (int i = 0; i < Variants.length; i++) {
                ModelBakery.registerItemVariants(this, getCustomModelResourceLocation(Variants[i]));
            }
        }
        else
            {
            if (!getHasSubtypes())
            {
                ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName().toString()));
            }
            else
                {
                for (int i = 0; i < Variants.length; i++) {
                    ModelLoader.setCustomModelResourceLocation(this, i, getCustomModelResourceLocation(Variants[i]));
                }
            }
        }


    }

    protected ModelResourceLocation getCustomModelResourceLocation(String variant) {
        return new ModelResourceLocation(LibMain.LibCore.MOD_ID + ":" + variant);
    }

    @Override
    public ItemBase getGoetiaItem() {
        return this;
    }

    @Override
    public String[] getItemVariant() {
        return Variants;
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }
}
