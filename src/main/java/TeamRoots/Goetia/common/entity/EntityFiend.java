package teamroots.goetia.common.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import teamroots.goetia.common.util.Utils;

public class EntityFiend extends EntityMob {
    public static final DataParameter<Boolean> trapped = EntityDataManager.<Boolean>createKey(EntityFiend.class, DataSerializers.BOOLEAN);

	public EntityFiend(World worldIn) {
		super(worldIn);
        this.setSize(0.8F, 2.0F);
		this.experienceValue = 10;
	}
	
	@Override
    protected void entityInit(){
    	super.entityInit();
        this.getDataManager().register(trapped, Boolean.valueOf(false));
        this.isImmuneToFire = true;
    }

	protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 0.46D, true));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.46D));
        this.tasks.addTask(7, new EntityAIWander(this, 0.46D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(52.0D);
    }

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }
    
    @Override
    public void onUpdate(){
    	super.onUpdate();
    	if (this.getHealth() > 0 && this.ticksExisted % 80 == 0 && this.rand.nextBoolean() && this.getAttackTarget() != null && !getDataManager().get(trapped).booleanValue()){
    		Vec3d targetVector = (new Vec3d(getAttackTarget().posX-posX,getAttackTarget().posY-posY, getAttackTarget().posZ-posZ)).normalize().scale(0.01);
    		EntitySmallFireball fireball = new EntitySmallFireball(getEntityWorld(), posX+this.getLookVec().xCoord, posY+1.8+this.getLookVec().yCoord, posZ+getLookVec().zCoord, targetVector.xCoord, targetVector.yCoord, targetVector.zCoord);
    		getEntityWorld().spawnEntityInWorld(fireball);
    	}
    	if (this.getEntityBoundingBox() != null){
    		for (int i = 0; i < 4; i ++){
    			Vec3d particle = Utils.randomPointInAABB(this.getEntityBoundingBox());
        		getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, particle.xCoord, particle.yCoord, particle.zCoord, 0, 0, 0, 0);
    		}
    	}
    	if (getDataManager().get(trapped)){
    		this.setPosition(prevPosX, prevPosY, prevPosZ);
    	}
    }
    
    @Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		getDataManager().set(trapped, compound.getBoolean("trapped"));
		getDataManager().setDirty(trapped);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("trapped", getDataManager().get(trapped));
	}
}
