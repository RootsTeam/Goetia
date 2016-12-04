package teamroots.goetia.common.util;

import net.minecraft.client.renderer.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import teamroots.goetia.common.util.PosVec;

import java.util.List;

/**
 * Created by Andrew Graber on 10/14/2016.
 */
public class EntityRaycasting {
    public static Entity raycastEntity(EntityPlayer player, double distance){
        Entity foundEntity = null;
        final double finalDistance = distance;
        RayTraceResult pos = raycast(player, finalDistance);
        Vec3d positionVector = player.getPositionVector();
        positionVector = positionVector.addVector(0, player.getEyeHeight(), 0);
        if(pos != null)
            distance = pos.hitVec.distanceTo(positionVector);

        Vec3d lookVec = player.getLookVec();
        Vec3d reachVector = positionVector.addVector(lookVec.xCoord * finalDistance, lookVec.yCoord * finalDistance, lookVec.zCoord * finalDistance);
        Entity lookedEntity = null;
        List<Entity> entitiesInBoundingBox = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().addCoord(lookVec.xCoord * 32, lookVec.yCoord * 32, lookVec.zCoord * 32).expand(1F, 1F, 1F));
        double minDistance = distance;
        for(Entity entity : entitiesInBoundingBox) {
            if(entity.canBeCollidedWith()) {
                float collisionBoderSize = entity.getCollisionBorderSize();
                AxisAlignedBB hitbox = entity.getEntityBoundingBox().expand(collisionBoderSize, collisionBoderSize, collisionBoderSize);
                RayTraceResult interceptPosition = hitbox.calculateIntercept(positionVector, reachVector);

                if(hitbox.isVecInside(positionVector)) {
                    if(0.0D < minDistance || minDistance == 0.0D){
                        lookedEntity = entity;
                        minDistance = 0.0D;
                    }
                } else if(interceptPosition != null){
                    double distanceToEntity = positionVector.distanceTo(interceptPosition.hitVec);

                    if(distanceToEntity < minDistance || minDistance == 0.0D){
                        lookedEntity = entity;
                        minDistance = distanceToEntity;
                    }
                }
            }

            if(lookedEntity != null && (minDistance < distance || pos == null))
                foundEntity = lookedEntity;
        }
        return foundEntity;
    }

    public static RayTraceResult raycast(EntityPlayer player, double distance){
        PosVec vec = new PosVec(player.posX, player.posY, player.posZ);
        vec.add(0, player.getEyeHeight(), 0);

        Vec3d look = player.getLookVec();
        if(look == null)
            player.addChatMessage(new TextComponentString("NULL VECTOR!!!"));
        return raycast(player.worldObj, vec, new PosVec(look), distance);
    }

    public static RayTraceResult raycast(World world, PosVec origin, PosVec ray, double distance){
        PosVec end = origin.copy().add(ray.copy().normalize().multiply(distance));
        RayTraceResult pos = world.rayTraceBlocks(origin.toVec3D(), end.toVec3D());
        return pos;
    }
}
