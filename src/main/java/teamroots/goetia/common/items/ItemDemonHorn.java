package teamroots.goetia.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import teamroots.goetia.capability.impurity.GoetiaProvider;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemDemonHorn extends ItemBase
{
    public ItemDemonHorn(String name) {
        super(name);
        setMaxStackSize(1);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
    	GoetiaProvider.get(player).setImpurity(player, GoetiaProvider.get(player).getImpurity()+15);
    	return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
}
