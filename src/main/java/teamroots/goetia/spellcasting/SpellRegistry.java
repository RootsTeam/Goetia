package teamroots.goetia.spellcasting;

import java.util.ArrayList;

import teamroots.goetia.spellcasting.angel.SpellAngelicGrowth;
import teamroots.goetia.spellcasting.angel.SpellAngelicWings;
import teamroots.goetia.spellcasting.angel.SpellInnerGrace;
import teamroots.goetia.spellcasting.angel.SpellSmite;
import teamroots.goetia.spellcasting.angel.SpellStorm;
import teamroots.goetia.spellcasting.demon.SpellBurningTouch;
import teamroots.goetia.spellcasting.demon.SpellChainedStrikes;
import teamroots.goetia.spellcasting.demon.SpellEbonWings;
import teamroots.goetia.spellcasting.demon.SpellFallenArmor;
import teamroots.goetia.spellcasting.demon.SpellFireball;
import teamroots.goetia.spellcasting.demon.SpellInnerFire;
import teamroots.goetia.spellcasting.demon.SpellRebuke;
import teamroots.goetia.spellcasting.demon.SpellVoraciousStrike;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class SpellRegistry
{
    public static ArrayList<CastSpell> spells = new ArrayList<CastSpell>();

    public static void register()
    {
    	//DEMON
    	addSpell(new SpellBurningTouch());
    	addSpell(new SpellFireball());
    	addSpell(new SpellRebuke());
    	//addSpell(new SpellBloodRain());
    	addSpell(new SpellFallenArmor());
    	addSpell(new SpellChainedStrikes());
    	addSpell(new SpellEbonWings());
    	addSpell(new SpellVoraciousStrike());
    	addSpell(new SpellInnerFire());
    	
    	//ANGEL
    	addSpell(new SpellInnerGrace());
    	addSpell(new SpellAngelicWings());
    	addSpell(new SpellAngelicGrowth());
    	addSpell(new SpellSmite());
    	addSpell(new SpellStorm());
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
