package so.wktk.honobonoserver.hncore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class hnconfig implements CommandExecutor {
	private Plugin instance = HNCore.getInstance();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 2) {
			instance.getConfig().set(args[0], args[1]);
			instance.saveConfig();
			return true;
		} else {
			return false;
		}
	}
}
