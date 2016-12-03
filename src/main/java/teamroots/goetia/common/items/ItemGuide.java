package teamroots.goetia.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import teamroots.goetia.Goetia;
import teamroots.goetia.client.gui.guide.GuideManager;
import teamroots.goetia.lib.EnumIDs;

public class ItemGuide extends ItemBase{
	public GuideManager manager;
	
	public ItemGuide(GuideManager manager){
		super("goetiaGuide");
		this.manager = manager;
	}
	
	@Override
	public int getItemStackLimit(){
		return 1;
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand){
		if (hand == EnumHand.MAIN_HAND){
			player.openGui(Goetia.instance, EnumIDs.GUI_GUIDE.ordinal(), world, (int)player.posX, (int)player.posY, (int)player.posZ);
		}
		return new ActionResult(EnumActionResult.PASS, stack);
	}
}
