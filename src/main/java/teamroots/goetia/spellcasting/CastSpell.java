package teamroots.goetia.spellcasting;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class CastSpell
{
    String name;

    public CastSpell(String spellName)
    {
        this.name = spellName;
    }

    public String getName()
    {
        return name;
    }

    public String getSpellName()
    {
        return I18n.format("goetia.spell."+name+".name");
    }

    public void doEffect(World world, Entity caster,double x, double y, double z)
    {

    }
}
