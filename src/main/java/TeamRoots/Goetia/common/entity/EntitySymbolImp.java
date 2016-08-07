package teamroots.goetia.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
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
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityLargeFireball;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.common.util.Utils;

public class EntitySymbolImp extends EntityFlying implements ISymbol {
    public static DataParameter<Boolean> activated = EntityDataManager.<Boolean>createKey(EntitySymbolImp.class, DataSerializers.BOOLEAN);
    public static DataParameter<Float> ready = EntityDataManager.<Float>createKey(EntitySymbolImp.class, DataSerializers.FLOAT);
    public static DataParameter<Float> fading = EntityDataManager.<Float>createKey(EntitySymbolImp.class, DataSerializers.FLOAT);
    public float angle = 0;
    public EntitySymbolImp(World worldIn) {
    	super(worldIn);
    	this.isAirBorne = true;
		this.setSize(2.0F, 0.5F);
        this.noClip = false;
        this.entityCollisionReduction = 1.0f;
	}
    
    @Override
    public boolean canBePushed(){
    	return false;
    }
	
	@Override
    protected void entityInit(){
    	super.entityInit();
        this.getDataManager().register(activated, Boolean.valueOf(false));
        this.getDataManager().register(ready, Float.valueOf(0));
        this.getDataManager().register(fading, Float.valueOf(1.0f));
    }
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
    }
	
	@Override
	public int getBrightnessForRender(float partialTicks){
		if (getDataManager().get(activated).booleanValue()){
			return 255;
		}
		else {
			return super.getBrightnessForRender(partialTicks);
		}
	}
    
    @Override
    public void onUpdate(){
    	super.onUpdate();
    	if (this.getHealth() <= 0){
    		this.setDead();
    	}
    	if (getDataManager().get(activated) && getDataManager().get(ready).floatValue() < 1.0f){
    		getDataManager().set(ready, getDataManager().get(ready)+0.01f);
    		getDataManager().setDirty(ready);
    		if (getDataManager().get(ready).floatValue() >= 1.0f){
    			if (!getEntityWorld().isRemote){
	    			EntityImp imp = new EntityImp(getEntityWorld());
	    			imp.onInitialSpawn(getEntityWorld().getDifficultyForLocation(getPosition()), null);
	    			imp.setPosition(posX, posY+0.5f, posZ);
	    			getEntityWorld().spawnEntityInWorld(imp);
    			}
    			for (int i = 0; i < 30; i ++){
    				getEntityWorld().spawnParticle(EnumParticleTypes.FLAME, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
    			}
    			for (int i = 0; i < 30; i ++){
    				getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
    			}
    			for (int i = 0; i < 80; i ++){
    				getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
    			}
    		}
        	angle += 30.0f*getDataManager().get(ready);
    	}
    	if (getDataManager().get(activated) && getDataManager().get(ready).floatValue() >= 1.0f){
    		getDataManager().set(fading, getDataManager().get(fading)-0.05f);
    		getDataManager().setDirty(fading);
    		if (getDataManager().get(fading).floatValue() <= 0.0f){
    			setDead();
    		}
        	angle += 30.0f*getDataManager().get(fading)*getDataManager().get(ready);
    	}
    }
    
    @Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		getDataManager().set(activated,compound.getBoolean("activated"));
		getDataManager().setDirty(activated);
		getDataManager().set(ready,compound.getFloat("ready"));
		getDataManager().setDirty(ready);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setBoolean("activated", getDataManager().get(activated).booleanValue());
		compound.setFloat("ready", getDataManager().get(ready).floatValue());
	}

	@Override
	public int getLevelCost() {
		return 5;
	}

	@Override
	public boolean isActivated() {
		return getDataManager().get(activated);
	}
	
	@Override
	public float getAngle(float partialTicks){
		return angle+partialTicks*30.0f*getDataManager().get(ready);
	}
	
	@Override
	public float getFade(float partialTicks){
		if (getDataManager().get(ready).floatValue() >= 1.0f){
			return -partialTicks*0.05f+getDataManager().get(fading);
		}
		return 1.0f;
	}
	
	@Override
	public float getReady(){
		return getDataManager().get(ready).floatValue();
	}

	@Override
	public void activate() {
		getDataManager().set(activated, true);
		getDataManager().setDirty(activated);
	}
}
