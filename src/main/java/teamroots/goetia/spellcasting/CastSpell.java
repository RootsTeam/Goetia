package teamroots.goetia.spellcasting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by TeamRoots on 5.8.2016.
 */
public class CastSpell
{
	public int impurity;
	List<Integer> shape = new ArrayList<Integer>();
	
	public CastSpell(int impurity){
		this.impurity = impurity;
	}
	
	public void doEffect(EntityPlayer caster){
		
	}
}
