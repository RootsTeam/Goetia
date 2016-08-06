package teamroots.goetia.spellcasting;

import java.util.ArrayList;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class SpellRegistry
{
    public static ArrayList<CastSpell> spells = new ArrayList<CastSpell>();

    public static void register()
    {
    }

    public static void addSpell(CastSpell castSpell)
    {
        spells.add(castSpell);
    }
}
