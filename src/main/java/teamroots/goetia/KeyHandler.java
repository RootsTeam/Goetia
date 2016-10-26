package teamroots.goetia;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyHandler {
	
	public static KeyBinding spellKey;
	
	public static void init(){
		//spellKey = new KeyBinding("key.spell", Keyboard.KEY_X, "key.categories.goetia");
		//ClientRegistry.registerKeyBinding(spellKey);
	}

}
