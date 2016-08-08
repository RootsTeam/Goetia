package teamroots.goetia.spellcasting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import teamroots.goetia.capability.impurity.ImpurityProvider;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class CastSpell
{
	public String name;
	public int impurity;
	public ItemStack icon;
	List<Integer> shape = new ArrayList<Integer>();
	
	public CastSpell(String name, int impurity, int[] parShape, ItemStack icon){
		this.name = name;
		this.impurity = impurity;
		this.icon = icon;
		for (int i = 0; i < parShape.length; i ++){
			this.shape.add(parShape[i]);
		}
	}
	
	/**
	 * This is what should be overridden
	 * @param caster
	 */
	public void effect(EntityPlayer caster){
		
	}
	
	/**
	 * Do not override this override the effect function
	 * Call this when you want to use the spell effect
	 * @param caster
	 */
	public void doEffect(EntityPlayer caster){
		if(this.impurity >= ImpurityProvider.get(caster).getImpurity()){
			this.effect(caster);
		}
	}
}
