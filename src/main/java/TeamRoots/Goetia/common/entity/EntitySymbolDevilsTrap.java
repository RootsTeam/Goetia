package teamroots.goetia.common.entity;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
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
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.common.util.Utils;

public class EntitySymbolDevilsTrap extends EntityFlying implements ISymbol, IClickableSymbol {
    public static DataParameter<Boolean> activated = EntityDataManager.<Boolean>createKey(EntitySymbolDevilsTrap.class, DataSerializers.BOOLEAN);
    public static DataParameter<Float> ready = EntityDataManager.<Float>createKey(EntitySymbolDevilsTrap.class, DataSerializers.FLOAT);
    public static DataParameter<Float> fading = EntityDataManager.<Float>createKey(EntitySymbolDevilsTrap.class, DataSerializers.FLOAT);
    public static DataParameter<Boolean> tracking = EntityDataManager.<Boolean>createKey(EntitySymbolDevilsTrap.class, DataSerializers.BOOLEAN);
    public static DataParameter<Boolean> forceFade = EntityDataManager.<Boolean>createKey(EntitySymbolDevilsTrap.class, DataSerializers.BOOLEAN);
    
    public float angle = 0;
    public UUID trackId = null;
    public EntityLivingBase trackedEntity = null;
    public EntitySymbolDevilsTrap(World worldIn) {
    	super(worldIn);
    	this.isAirBorne = true;
		this.setSize(2.0F, 0.5F);
        this.noClip = false;
        this.entityCollisionReduction = 0.0f;
	}
    
    @Override
    public boolean canBePushed(){
    	return false;
    }
	
	@Override
    protected void entityInit(){
    	super.entityInit();
        this.getDataManager().register(activated, Boolean.valueOf(false));
        this.getDataManager().register(tracking, Boolean.valueOf(false));
        this.getDataManager().register(forceFade, Boolean.valueOf(false));
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
	protected void collideWithNearbyEntities()
    {
        //
    }
    
    @Override
    public void onUpdate(){
    	super.onUpdate();
    	if (this.getHealth() < 1.0f){
    		this.setDead();
    	}
    	if (this.ticksExisted % 2 == 0){
    		for (int i = -1; i < 2; i ++){
    			for (int j = -1; j < 2; j ++){
    				if (!getEntityWorld().getBlockState(getPosition().down().add(new Vec3i(i,0,j))).getBlock().isSideSolid(getEntityWorld().getBlockState(getPosition().down().add(new Vec3i(i,0,j))), getEntityWorld(), getPosition().down().add(new Vec3i(i,0,j)), EnumFacing.UP)){
    					setDead();
    				}
    			}
    		}
    	}
    	if (getDataManager().get(activated) && getDataManager().get(ready).floatValue() < 1.0f){
    		getDataManager().set(ready, getDataManager().get(ready)+0.01f);
    		getDataManager().setDirty(ready);
    		if (getDataManager().get(ready).floatValue() >= 1.0f){
    			for (int i = 0; i < 80; i ++){
    				getEntityWorld().spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
    			}
    		}
        	angle += 5.0f*getDataManager().get(ready);
    	}
    	if (getDataManager().get(activated) && getDataManager().get(ready).floatValue() >= 1.0f){
    		List<EntityLivingBase> nearby = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX-1.5,posY,posZ-1.5,posX+1.5,posY+3.0,posZ+1.5));
    		for (int i = 0; i < nearby.size(); i ++){
    			if (nearby.get(i) instanceof IDemonic){
    				if (nearby.get(i).getHealth() <= nearby.get(i).getMaxHealth()*0.3f){
    					((IDemonic)nearby.get(i)).setTrapped();
    					getDataManager().set(tracking, true);
    					getDataManager().setDirty(tracking);
    					trackedEntity = nearby.get(i);
    					break;
    				}
    			}
    		}
    		if (!getDataManager().get(tracking)){
    			angle += 5.0f*getDataManager().get(fading)*getDataManager().get(ready);
    		}
    	}
    	if (getDataManager().get(tracking)){
    		if (trackedEntity == null){
        		getDataManager().set(fading, getDataManager().get(fading)-0.05f);
        		getDataManager().setDirty(fading);
        		if (getDataManager().get(fading).floatValue() <= 0.0f){
        			setDead();
        		}
    		}
    		if (getDataManager().get(forceFade).booleanValue()){
	    		getDataManager().set(fading, getDataManager().get(fading)-0.05f);
	    		getDataManager().setDirty(fading);
	    		if (getDataManager().get(fading).floatValue() <= 0.0f){
	    			setDead();
	    		}
    		}
    		if (!getEntityWorld().isRemote && trackedEntity != null && trackedEntity.getHealth() > 0){
    			trackedEntity.setPosition(posX, trackedEntity.posY, posZ);
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
		return SymbolManager.symbolCosts.get(getSymbolName());
	}

	@Override
	public boolean isActivated() {
		return getDataManager().get(activated);
	}
	
	@Override
	public float getAngle(float partialTicks){
		if (getDataManager().get(tracking)){
			return angle+partialTicks*30.0f*getDataManager().get(ready);
		}
		return angle+partialTicks*5.0f*getDataManager().get(ready);
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

	@Override
	public ResourceLocation getTextureLocation() {
		return new ResourceLocation("goetia:textures/entity/devilsTrap.png");
	}

	@Override
	public String getSymbolName() {
		return "devilsTrap";
	}

	@Override
	public void onRightClick(World world, Entity entity, EntityPlayer player) {
		if (trackedEntity != null){
			trackedEntity.setDead();
			for (int i = 0; i < 30; i ++){
				getEntityWorld().spawnParticle(EnumParticleTypes.FLAME, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
			}
			for (int i = 0; i < 30; i ++){
				getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
			}
			for (int i = 0; i < 80; i ++){
				getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
			}
			trackedEntity = null;
			getDataManager().set(tracking, true);
			getDataManager().setDirty(tracking);
			getDataManager().set(forceFade, true);
			getDataManager().setDirty(forceFade);
		}
	}
}
