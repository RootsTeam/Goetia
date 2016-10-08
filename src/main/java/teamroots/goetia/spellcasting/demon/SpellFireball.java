package teamroots.goetia.spellcasting.demon;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.AlignmentType;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class SpellFireball extends CastSpell
{
	
	public SpellFireball(){
		super("fireball",47,new int[]{6,4,2},new ItemStack(Items.FIRE_CHARGE), AlignmentType.DEMON);
	}
	
	@Override
	public void doEffect(EntityPlayer caster){
		if (!caster.getEntityWorld().isRemote){
			Vec3d targetVector = caster.getLookVec().scale(0.01);
			EntityLargeFireball fireball = new EntityLargeFireball(caster.getEntityWorld(), caster.posX+caster.getLookVec().xCoord, caster.posY+caster.getEyeHeight()+caster.getLookVec().yCoord, caster.posZ+caster.getLookVec().zCoord, targetVector.xCoord, targetVector.yCoord, targetVector.zCoord);
			caster.getEntityWorld().spawnEntityInWorld(fireball);
		}
	}
}
