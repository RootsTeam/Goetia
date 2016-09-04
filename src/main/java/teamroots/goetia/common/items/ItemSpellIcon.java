package teamroots.goetia.common.items;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import teamroots.goetia.Goetia;
import teamroots.goetia.capability.impurity.KnowledgeProvider;
import teamroots.goetia.common.symbol.SymbolManager;
import teamroots.goetia.lib.EnumIDs;
import teamroots.goetia.spellcasting.CastSpell;
import teamroots.goetia.spellcasting.SpellRegistry;

/**
 * Created by TeamRoots on 4.8.2016.
 */
public class ItemSpellIcon extends ItemBase
{
    public ItemSpellIcon(String name) {
        super(name);
        setCreativeTab(null);
        setMaxStackSize(1);
    }
    
    public static void createData(ItemStack stack, CastSpell spell){
    	stack.setTagCompound(new NBTTagCompound());
    	stack.setStackDisplayName(I18n.format("goetia.tooltip.spell")+I18n.format("goetia.spell."+spell.name));
    	stack.getTagCompound().setString("spell", spell.name);
    }
    
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced){
    	if (stack.hasTagCompound()){
    		if (stack.getTagCompound().hasKey("spell")){
    			CastSpell spell = SpellRegistry.getSpellFromName(stack.getTagCompound().getString("spell"));
    			if (spell != null){
    				tooltip.add(TextFormatting.DARK_PURPLE+I18n.format("goetia.spell."+stack.getTagCompound().getString("spell")+".desc"));
    				tooltip.add(TextFormatting.DARK_PURPLE+I18n.format("goetia.tooltip.impurity")+spell.impurity);
    				String stepString = TextFormatting.DARK_PURPLE+I18n.format("goetia.tooltip.spellSteps");
    				if (spell.shape.size() > 0){
    					stepString += spell.shape.get(0);
    					for (int i = 1; i < spell.shape.size(); i ++){
    						stepString += ", ";
    						stepString += spell.shape.get(i);
    					}
    				}
    				tooltip.add(stepString);
    			}
    		}
    	}
    }
}
