package teamroots.goetia.spellcasting.angel;

import net.minecraft.block.BlockCarrot;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.AlignmentType;

public class SpellAngelicGrowth extends CastSpell{

	public SpellAngelicGrowth() {
		super("angelicGrowth", 325, new int[]{1,2,3,4,5,6}, new ItemStack(Items.DYE, 1, 15), AlignmentType.ANGEL);
	}
	
	@Override
	public void doEffect(EntityPlayer caster){
		for (float i = 0; i < 360; i += 7.5){
			float offX = 0.75f*(float)Math.sin(Math.toRadians(i));
			float offZ = 0.75f*(float)Math.cos(Math.toRadians(i));
			if (random.nextInt(2) == 0){
				caster.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT_MAGIC, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
			}
			caster.getEntityWorld().spawnParticle(EnumParticleTypes.CRIT, caster.posX+offX, caster.posY+caster.getEyeHeight()/2.0, caster.posZ+offZ, 0, 0.015*random.nextFloat(), 0, 0);
		}
		
		for(int x = 0; x < 10; x++){
			for(int z = 0; z < 10; z++){
				for(int y = 0; y < 2; y++){
					BlockPos pos = new BlockPos(caster.posX + x, caster.posY + y, caster.posZ + z);
					if(caster.getEntityWorld().getBlockState(pos).getBlock() instanceof IGrowable){
						IGrowable igrowable = (IGrowable)caster.getEntityWorld().getBlockState(pos).getBlock();
						igrowable.grow(caster.getEntityWorld(), random, pos, caster.getEntityWorld().getBlockState(pos));
					}
				}
			}
		}
	}
}
