package teamroots.goetia.common.items.base;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemAbyssalBlade extends ItemSwordBase {

    private static ToolMaterial abyssal = EnumHelper.addToolMaterial("abyssal", 3, 749, 3, 6.0f, 3);

    public ItemAbyssalBlade() {
        super("abyssalBlade", abyssal);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        entity.setFire(3);
        player.getFoodStats().addExhaustion(1.0f);
        return false;
    }
}

