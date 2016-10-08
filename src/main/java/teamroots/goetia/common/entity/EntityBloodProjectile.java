package teamroots.goetia.common.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntityBloodProjectile extends Entity {
    int lifetime = 0;
    UUID uuid = null;
    EntityLivingBase trackingEntity = null;
	public EntityBloodProjectile(World worldIn) {
		super(worldIn);
        this.setSize(0.5F, 0.5F);
        this.setInvisible(true);
	}
	
	@Override
	public boolean canBeCollidedWith(){
		return true;
	}
	
	@Override
	public void applyEntityCollision(Entity entity){
		if (entity instanceof EntityLivingBase && entity.getUniqueID().compareTo(uuid) != 0){
			((EntityLivingBase)entity).attackEntityFrom(DamageSource.generic, 3.0f);
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("slowness"),400,1));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("weakness"),400,1));
		}
	}
	
	@Override
	public void onUpdate(){
		lifetime ++;
		if (lifetime > 100){
			setDead();
		}
		if (this.isCollided){
			setDead();
		}
		if (trackingEntity != null){
			Vec3d dVec = new Vec3d(trackingEntity.posX-posX,trackingEntity.posY-posY,trackingEntity.posZ-posZ);
			dVec = dVec.normalize().scale(0.05f);
			setVelocity(dVec.xCoord,dVec.yCoord,dVec.zCoord);
		}
		else {
			setDead();
		}
		this.getEntityWorld().spawnParticle(EnumParticleTypes.REDSTONE, posX+0.1*(rand.nextFloat()-0.5f), posY+0.1*(rand.nextFloat()-0.5f), posZ+0.1*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
		this.getEntityWorld().spawnParticle(EnumParticleTypes.REDSTONE, posX+0.1*(rand.nextFloat()-0.5f), posY+0.1*(rand.nextFloat()-0.5f), posZ+0.1*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
	}
	
	public void initCustom(World world, EntityPlayer caster, double posX, double posY, double posZ){
		setPosition(posX, posY, posZ);
		List<EntityLivingBase> targets = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX-13.0,posY-13.0,posZ-13.0,posX+13.0,posY+13.0,posZ+13.0));
		List<EntityLivingBase> trimmedTargets = new ArrayList<EntityLivingBase>();
		for (int i = 0; i < targets.size(); i ++){
			if (targets.get(i).getUniqueID().compareTo(caster.getUniqueID()) != 0){
				trimmedTargets.add(targets.get(i));
			}
		}
		if (trimmedTargets.size() == 0){
			setDead();
		}
		else {
			trackingEntity = trimmedTargets.get(rand.nextInt(trimmedTargets.size()));
		}
		uuid = caster.getUniqueID();
	}

	@Override
	protected void entityInit() {
		//
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		setDead();
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
	}
}
