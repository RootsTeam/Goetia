package teamroots.goetia.common.entity;

import java.util.List;
import java.util.UUID;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import teamroots.goetia.capability.impurity.GoetiaProvider;
import teamroots.goetia.common.symbol.SymbolManager;

public class EntitySymbolOpenSoul extends EntityFlying implements ISymbol, IClickableSymbol {
    public static DataParameter<Boolean> activated = EntityDataManager.<Boolean>createKey(EntitySymbolOpenSoul.class, DataSerializers.BOOLEAN);
    public static DataParameter<Float> ready = EntityDataManager.<Float>createKey(EntitySymbolOpenSoul.class, DataSerializers.FLOAT);
    public static DataParameter<Float> fading = EntityDataManager.<Float>createKey(EntitySymbolOpenSoul.class, DataSerializers.FLOAT);
    public static DataParameter<Boolean> forceFade = EntityDataManager.<Boolean>createKey(EntitySymbolOpenSoul.class, DataSerializers.BOOLEAN);
    
    public float angle = 0;
    public UUID trackId = null;
    public EntitySymbolOpenSoul(World worldIn) {
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
    	if (getDataManager().get(activated) && getDataManager().get(ready).floatValue() < 1.0f && !getDataManager().get(forceFade).booleanValue()){
    		getDataManager().set(ready, getDataManager().get(ready)+0.01f);
    		getDataManager().setDirty(ready);
    		if (getDataManager().get(ready).floatValue() >= 1.0f){
    			for (int i = 0; i < 80; i ++){
    				getEntityWorld().spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
    			}
    		}
        	angle += 5.0f*getDataManager().get(ready);
    	}
		if (getDataManager().get(forceFade).booleanValue()){
    		getDataManager().set(fading, getDataManager().get(fading)-0.05f);
    		getDataManager().setDirty(fading);
    		if (getDataManager().get(fading).floatValue() <= 0.0f){
    			setDead();
    		}
		}
		if (getDataManager().get(fading) < 1.0f){
        	angle += 30.0f*getDataManager().get(ready);
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
		if (getDataManager().get(forceFade).booleanValue()){
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
	public void activate(EntityPlayer player) {
		getDataManager().set(activated, true);
		getDataManager().setDirty(activated);
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return new ResourceLocation("goetia:textures/entity/openSoulSymbol.png");
	}

	@Override
	public String getSymbolName() {
		return "openSoulSymbol";
	}

	@Override
	public void onRightClick(World world, Entity entity, EntityPlayer player) {
		List<EntityLivingBase> nearby = getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posX-20.5,posY,posZ-20.5,posX+20.5,posY+41,posZ+20.5));
		int value = 0;
		for (int i = 0; i < nearby.size(); i ++){
			if (nearby.get(i) instanceof IDemonic){
				if (nearby.get(i) instanceof EntityImp){
					value += rand.nextInt(5)+6;
					player.setHealth(player.getHealth()-1);
				}
				if (nearby.get(i) instanceof EntityFiend){
					value += rand.nextInt(7)+10;
					player.setHealth(player.getHealth()-2);
				}
				if (nearby.get(i) instanceof EntityDemon){
					value += rand.nextInt(11)+22;
					player.setHealth(player.getHealth()-4);
				}
				nearby.get(i).setDead();
			}
		}
		GoetiaProvider.get(player).setImpurity(player, GoetiaProvider.get(player).getImpurity()+value);
		for (int k = 0; k < 30; k ++){
			getEntityWorld().spawnParticle(EnumParticleTypes.FLAME, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
		}
		for (int k = 0; k < 30; k ++){
			getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
		}
		for (int k = 0; k < 80; k ++){
			getEntityWorld().spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX+1.0f*(rand.nextFloat()-0.5f), posY+1.0f*(rand.nextFloat()), posZ+1.0f*(rand.nextFloat()-0.5f), 0, 0, 0, 0);
		}
		getDataManager().set(forceFade, true);
		getDataManager().setDirty(forceFade);
	}
}
