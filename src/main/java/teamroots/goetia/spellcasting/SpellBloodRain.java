package teamroots.goetia.spellcasting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import teamroots.goetia.common.entity.EntityBloodProjectile;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class SpellBloodRain extends CastSpell
{
	
	public SpellBloodRain(){
		super("bloodrain",104,new int[]{5,6,1,2,3},new ItemStack(Items.DYE,1,1));
	}
	
	@Override
	public void doEffect(EntityPlayer caster){
		if (!caster.getEntityWorld().isRemote){
			EntityBloodProjectile projectile = new EntityBloodProjectile(caster.getEntityWorld());
			projectile.initCustom(caster.getEntityWorld(),caster,caster.posX+caster.getLookVec().xCoord, caster.posY+caster.getEyeHeight()+caster.getLookVec().yCoord, caster.posZ+caster.getLookVec().zCoord);
			caster.getEntityWorld().spawnEntityInWorld(projectile);
		}
	}
}
