package teamroots.goetia.common.entity;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.common.symbol.SymbolManager;

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
		return SymbolManager.symbolCosts.get(getSymbolName());
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
	public void activate(EntityPlayer player) {
		getDataManager().set(activated, true);
		getDataManager().setDirty(activated);
		if (!player.getEntityWorld().isRemote && player.hasCapability(GoetiaProvider.goetiaCapability, null)){
			player.getCapability(GoetiaProvider.goetiaCapability, null).setImpurity(player,player.getCapability(GoetiaProvider.goetiaCapability, null).getImpurity()+rand.nextInt(3)+3);
		}
	}

	@Override
	public ResourceLocation getTextureLocation() {
		return new ResourceLocation("goetia:textures/entity/impSymbol.png");
	}

	@Override
	public String getSymbolName() {
		return "impSymbol";
	}
}
