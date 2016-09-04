package teamroots.goetia.common.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemDemonicSpear extends ItemSwordBase {
    public static ToolMaterial demonic = EnumHelper.addToolMaterial("demonic", 3, 749, 3, 2.5f, 3);

    public ItemDemonicSpear() {
        super("demonHornSpear", demonic);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        entity.setFire(3);
        player.getFoodStats().addStats(1,0.5f);
        return false;
    }
}