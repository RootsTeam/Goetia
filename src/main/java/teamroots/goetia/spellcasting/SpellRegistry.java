package teamroots.goetia.spellcasting;

import java.util.HashMap;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class SpellRegistry
{
    public static HashMap<String,CastSpell> spellMap = new HashMap<String,CastSpell>();

    public static void register()
    {
        addComponent(new SpellTest(),"SpellTest");
    }

    public static void addComponent(CastSpell spell,String key)
    {
        spell.name = key;
        spellMap.put(key,spell);
    }

    public static CastSpell getSpellsFromName(String spellName)
    {
        for(int i = 0;i < spellMap.size();i++) {
            if (spellMap.get(i).getName().equals(spellName)) {
                return spellMap.get(i);
            }
        }
        return null;
    }
}
