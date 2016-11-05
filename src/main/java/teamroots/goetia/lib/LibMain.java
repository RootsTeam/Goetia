package teamroots.goetia.lib;

import java.util.ArrayList;

import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.DungeonHooks;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class LibMain
{
    public class LibCore
    {
        public static final String
        MOD_ID = "goetia",
        MOD_NAME = "Goetia",
        VERSION = "0.3",
        CLIENT_PROXY_CLASS = "teamroots.goetia.proxy.ClientProxy",
        SERVER_PROXY_CLASS = "teamroots.goetia.proxy.ServerProxy",
        GUI_FACTORY_CLASS = "teamroots.goetia.client.gui.GuiFactory",
        JEI_DEMON_FORGE_UUID = "demon_forge";
    }
    public class LibNBT
    {
        public static final String
        impurity_tag = "impurity",
        purity_tag = "purity",
        max_impurity_tag = "maxImpurity",
        max_purity_tag = "maxPurity",
        KNOWLEDGE_TAG = "knowledge",
        rebuke_tag = "rebuke",
        fallen_armor_tag = "fallen_armor",
        chained_strikes_tag = "chained_strikes",
        wings_tag = "wings_tag",
        burning_touch_tag = "burning_touch",
        voracious_strikes_tag = "voracious_strikes",
        inner_firegrace_tag = "inner_fire_grace",
        smite = "smite",
        liquidWalker = "liquidWalker";
    }
    public static class LibKnowledge
    {
        public static final String[] validDemonKnowledge = new String[]{"impSymbol","fiendSymbol","demonSymbol","devilsTrap","forgeSymbol","openSoulSymbol"};
        public static final String[] validAngelKnowledge = new String[]{"WIP","WIP","WIP","WIP","WIP","WIP"};
    }
}
