package teamroots.goetia.common.items.base;

import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemDemonicSpear extends ItemSwordBase {
    public static ToolMaterial demonic = EnumHelper.addToolMaterial("demonic", 3, 749, 3, 3.5f, 3);

    public ItemDemonicSpear() {
        super("demonHornSpear", demonic);
    }
}
