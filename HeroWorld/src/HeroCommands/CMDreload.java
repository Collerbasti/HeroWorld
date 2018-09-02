package HeroCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import Main.main;

public class CMDreload implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender.hasPermission("Noneless.Admin")) {
		Main.main.reload();
		
			sender.sendMessage("Config Reloaded");
		}
		return false;
	}

}
 