package teamroots.goetia.common.items;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import teamroots.goetia.MainRegistry;
import teamroots.goetia.capability.capabilites.GoetiaProvider;
import teamroots.goetia.common.entity.IDemonic;
import teamroots.goetia.common.util.BlockUtils;
import teamroots.goetia.common.util.EntityRaycasting;
import teamroots.goetia.fluids.FluidBlockHolyWater;
import teamroots.goetia.spellcasting.AlignmentType;

public class ItemHolyCross extends ItemBase{
	private int range = 5;
	
	public ItemHolyCross(String name) {
		super(name);
		this.setMaxDamage(20);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected){
		if(!stack.hasTagCompound()){
			stack.setTagCompound(new NBTTagCompound());
		}
		if(!stack.getTagCompound().hasKey("cooldown")){
			stack.getTagCompound().setInteger("cooldown", 0);
		} else if(stack.getTagCompound().getInteger("cooldown") > 0){
			stack.getTagCompound().setInteger("cooldown", stack.getTagCompound().getInteger("cooldown")-1);
		}
		EntityPlayer player = (EntityPlayer) entity;
		if(GoetiaProvider.get(player).getAlignment() == AlignmentType.DEMON){
			player.attackEntityFrom(DamageSource.magic, 1F);
		}
		
		List<EntityLivingBase> nearby = player.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX-range,player.posY,player.posZ-range,player.posX+range,player.posY+range,player.posZ+range));
		for(int i = 0; i < nearby.size(); i++){
			Entity mob = nearby.get(i);
			if(mob instanceof IDemonic){
				if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == MainRegistry.holyCross){
					mob.attackEntityFrom(DamageSource.magic, 1F);
				}
			}
			if(mob instanceof EntityPlayer && GoetiaProvider.get((EntityPlayer)mob).getAlignment() == AlignmentType.DEMON){
				if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == MainRegistry.holyCross){
					mob.attackEntityFrom(DamageSource.magic, 1F);
				}	
			}
			Entity target;
			if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof ItemHolyCross && stack.getTagCompound().getInteger("cooldown") <=0) {
				if ((target = EntityRaycasting.raycastEntity(player, 5)) != null) {
					if (target instanceof IDemonic || (target instanceof EntityPlayer && GoetiaProvider.get((EntityPlayer) target).getAlignment() == AlignmentType.DEMON)) {
						target.addVelocity(player.getLookVec().xCoord, player.getLookVec().yCoord, player.getLookVec().zCoord);
						stack.getTagCompound().setInteger("cooldown", 10);
					}
				}
			}
			if(player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() instanceof ItemHolyCross && stack.getTagCompound().getInteger("cooldown") <=0){
				if ((target = EntityRaycasting.raycastEntity(player, 5)) != null) {
					if (target instanceof IDemonic || (target instanceof EntityPlayer && GoetiaProvider.get((EntityPlayer) target).getAlignment() == AlignmentType.DEMON)) {
						target.addVelocity(player.getLookVec().xCoord, player.getLookVec().yCoord, player.getLookVec().zCoord);
						stack.getTagCompound().setInteger("cooldown", 10);
					}
				}
			}
		}
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand){
		RayTraceResult raytrace = this.rayTrace(worldIn, playerIn, true);
		if(raytrace == null){
			return new ActionResult(EnumActionResult.PASS, itemStackIn);
		} else if(worldIn.getBlockState(raytrace.getBlockPos()).getBlock() == Blocks.WATER){
			BlockPos pos = raytrace.getBlockPos();
			if(!BlockUtils.hasBlockOnAnySide(worldIn, pos, Blocks.WATER)){
				worldIn.setBlockState(pos, FluidBlockHolyWater.instance.getDefaultState());
				itemStackIn.damageItem(1, playerIn);
				return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
			} else {
				return new ActionResult(EnumActionResult.PASS, itemStackIn);
			}
			
		}
		return new ActionResult(EnumActionResult.FAIL, itemStackIn);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack old, ItemStack newS, boolean slotChanged){
		return slotChanged;
	}
}
