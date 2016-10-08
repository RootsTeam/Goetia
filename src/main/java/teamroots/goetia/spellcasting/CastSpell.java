package teamroots.goetia.spellcasting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class CastSpell
{
	protected Random random = new Random();
	public String name;
	public int cost;
	public ItemStack icon;
	public List<Integer> shape = new ArrayList<Integer>();
	public AlignmentType type;
	
	public CastSpell(String name, int impurity, int[] parShape, ItemStack icon, AlignmentType type){
		this.name = name;
		this.cost = impurity;
		this.icon = icon;
		for (int i = 0; i < parShape.length; i ++){
			this.shape.add(parShape[i]);
		}
		this.type = type;
	}
	
	public void doEffect(EntityPlayer caster){
		
	}
}
