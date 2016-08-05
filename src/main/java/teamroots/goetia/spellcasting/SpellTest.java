package teamroots.goetia.spellcasting;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class SpellTest extends CastSpell
{
    public SpellTest() {
        super("SpellTest");
    }

    @Override
    public void doEffect(World world, Entity caster,double x, double y, double z) {
        if(caster instanceof EntityPlayer)
        {
            ((EntityPlayer)caster).addChatComponentMessage(new TextComponentString("it works"));
        }
    }
}
