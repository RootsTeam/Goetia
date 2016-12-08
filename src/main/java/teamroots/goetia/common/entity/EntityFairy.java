package teamroots.goetia.common.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.common.util.Utils;

/**
 * Created by Andrew Graber on 12/7/2016.
 */
public class EntityFairy extends EntityCreature implements IAngelic {

    public EntityFairy(World worldIn) {
        super(worldIn);
        this.setSize(0.6f*0.2f, 1.95f*0.2f);
        this.experienceValue = 10;
    }

    @Override
    protected void entityInit(){
        super.entityInit();
        this.isAirBorne = true;
    }

    protected void initEntityAI()
    {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.46D));
        this.tasks.addTask(7, new EntityAIWander(this, 0.46D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    protected void applyEntityAI()
    {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    public void onUpdate(){
        super.onUpdate();
        if (this.getEntityBoundingBox() != null){
            for (int i = 0; i < 4; i ++){
                Vec3d particle = Utils.randomPointInAABB(this.getEntityBoundingBox());
                if(rand.nextInt(2) == 0) {
                    //getEntityWorld().spawnParticle(EnumParticleTypes.CRIT, particle.xCoord, particle.yCoord, particle.zCoord, 0, 0, 0, 0);
                }
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return MainRegistry.fairyMagic;
    }

    @Override
    protected SoundEvent getDeathSound(){
        return MainRegistry.sad;
    }
}
