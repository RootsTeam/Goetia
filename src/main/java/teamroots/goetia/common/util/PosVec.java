package teamroots.goetia.common.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

/**
 * Created by Andrew Graber on 10/15/2016.
 */
public class PosVec {
    private double x, y, z;

    public PosVec(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public PosVec(Entity e){
        this.x = e.posX;
        this.y = e.posY;
        this.z = e.posZ;
    }

    public PosVec(Vec3d vec){
        this.x = vec.xCoord;
        this.y = vec.yCoord;
        this.z = vec.zCoord;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public PosVec copy() {
        return new PosVec(x, y, z);
    }

    public PosVec add(double x, double y, double z){
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public PosVec add(PosVec vec){
        this.x += vec.getX();
        this.y += vec.getY();
        this.z += vec.getZ();
        return this;
    }

    public PosVec multiply(double d){
        x *= d;
        y *= d;
        z *= d;
        return this;
    }

    public PosVec multiply(PosVec f){
        x *= f.getX();
        y *= f.getY();
        z *= f.getZ();
        return this;
    }

    public double mag(){
        return Math.sqrt(x*x + y*y + z*z);
    }

    public PosVec normalize(){
        double d = mag();
        if(d != 0)
            multiply(1/d);
        return this;
    }

    public Vec3d toVec3D(){
        return new Vec3d(x, y, z);
    }
}
