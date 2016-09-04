package teamroots.goetia.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IClickableSymbol {
	public void onRightClick(World world, Entity entity, EntityPlayer player);
}
