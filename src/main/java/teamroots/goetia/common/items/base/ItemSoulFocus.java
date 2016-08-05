package teamroots.goetia.common.items.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.SpellRegistry;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class ItemSoulFocus extends ItemBase
{
    public ItemSoulFocus() {
        super("soulFocus");
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (stack.hasTagCompound()) {
            CastSpell comp = SpellRegistry.getSpellsFromName("SpellTest");
            comp.doEffect(world, player, player.posX + 3.0 * player.getLookVec().xCoord, player.posY + 3.0 * player.getLookVec().yCoord, player.posZ + 3.0 * player.getLookVec().zCoord);
        }
        return new ActionResult(EnumActionResult.SUCCESS,stack);
    }

    public static void createData(ItemStack stack, String effect){
        stack.setTagCompound(new NBTTagCompound());
        stack.getTagCompound().setString("effect", effect);
    }
}
