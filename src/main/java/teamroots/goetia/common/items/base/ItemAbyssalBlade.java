package teamroots.goetia.common.items.base;

import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemAbyssalBlade extends ItemSwordBase {

    private static ToolMaterial abyssal = EnumHelper.addToolMaterial("abyssal", 3, 749, 3, 6.0f, 3);

    public ItemAbyssalBlade() {
        super("abyssalBlade", abyssal);
    }
}

