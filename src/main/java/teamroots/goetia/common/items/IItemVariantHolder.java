package teamroots.goetia.common.items;

import net.minecraft.client.renderer.ItemMeshDefinition;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public interface IItemVariantHolder<T extends ItemBase>
{
    T getGoetiaItem();

    String[] getItemVariant();

    ItemMeshDefinition getCustomMeshDefinition();
}
