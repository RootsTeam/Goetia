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
    	addSpell(new SpellBurningTouch());
    	addSpell(new SpellFireball());
    	addSpell(new SpellRebuke());
    	addSpell(new SpellVoraciousStrike());
    }

    public static void addSpell(CastSpell castSpell)
    {
        spells.add(castSpell);
    }
    
    public static int getSpellIndexFromName(String name){
    	for (int i = 0; i < spells.size(); i ++){
    		if (spells.get(i).name.equals(name)){
    			return i;
    		}
    	}
    	return -1;
    }
    
    public static CastSpell getSpellFromName(String name){
    	for (int i = 0; i < spells.size(); i ++){
    		if (spells.get(i).name.equals(name)){
    			return spells.get(i);
    		}
    	}
    	return null;
    }
}
