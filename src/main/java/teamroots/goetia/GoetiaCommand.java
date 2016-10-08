package teamroots.goetia;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import teamroots.goetia.capability.impurity.GoetiaProvider;

public class GoetiaCommand implements ICommand{

	@Override
	public int compareTo(ICommand o) {
		return getCommandName().compareTo(o.getCommandName());
	}

	@Override
	public String getCommandName() {
		return "goetia";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/" + getCommandName() + " <subcommand> <args> (try '" + getCommandName() + " help' for more info)";
	}

	@Override
	public List<String> getCommandAliases() {
		ArrayList<String> aliases = new ArrayList<String>();
		aliases.add("goetia");
		return aliases;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0){
			sender.addChatMessage(new TextComponentString(TextFormatting.GREEN+"Use \"/goetia help\" for a list of subcommands."));
		}
		if (args.length >= 1){
			if (args[0].equals("help")){
				sender.addChatMessage(new TextComponentString(TextFormatting.BOLD+""+TextFormatting.GREEN+"/goetia subcommands:"));
				sender.addChatMessage(new TextComponentString("  "));
				sender.addChatMessage(new TextComponentString("  "+TextFormatting.DARK_GREEN+"/goetia setpurity <amount> [player] :: Sets a player's purity, leave blank to target yourself."));
				sender.addChatMessage(new TextComponentString("  "));
				sender.addChatMessage(new TextComponentString("  "+TextFormatting.DARK_GREEN+"/goetia setimpurity <amount> [player] :: Sets a player's impurity, leave blank to target yourself."));
			}
			if (args[0].equals("setpurity")){
				if (args.length >= 3){
					EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[2]);
				}
				else {
					if (sender.getCommandSenderEntity() instanceof EntityPlayer){
						if (((EntityPlayer)sender.getCommandSenderEntity()).hasCapability(GoetiaProvider.goetiaCapability, null)){
							((EntityPlayer)sender.getCommandSenderEntity()).getCapability(GoetiaProvider.goetiaCapability, null).setPurity(((EntityPlayer)sender.getCommandSenderEntity()), Integer.valueOf(args[1]));
						}
					}
				}
			}
			if (args[0].equals("setimpurity")){
				if (args.length >= 3){
					EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[2]);
				}
				else {
					if (sender.getCommandSenderEntity() instanceof EntityPlayer){
						if (((EntityPlayer)sender.getCommandSenderEntity()).hasCapability(GoetiaProvider.goetiaCapability, null)){
							((EntityPlayer)sender.getCommandSenderEntity()).getCapability(GoetiaProvider.goetiaCapability, null).setImpurity(((EntityPlayer)sender.getCommandSenderEntity()), Integer.valueOf(args[1]));
						}
					}
				}
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		if (server.isSinglePlayer()){
			return true;
		}
		if (server.getPlayerList().getOppedPlayers().getGameProfileFromName(sender.getName()) != null){
			return true;
		}
		return false;
	}

	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index) {
		return false;
	}

}
