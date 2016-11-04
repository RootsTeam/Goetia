package teamroots.goetia.rituals;

import java.util.ArrayList;
import java.util.List;

public class RitualManager {
	public static List<RitualBase> rituals = new ArrayList<RitualBase>();
	
	public static void init(){
		addRitual(new RitualCleanse());
		addRitual(new RitualSpawn());
	}
	
	public static void addRitual(RitualBase ritual){
		rituals.add(ritual);
	}
}
