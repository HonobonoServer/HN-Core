package co.honobono.hncore;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class announce implements CommandExecutor {
	private Plugin instance = HNCore.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length == 0) {
			return false;
		}
		List<String> messages = instance.getConfig().getStringList("announcement.messages");
		if (args[0].equalsIgnoreCase("list")) {
			;
			for (int i = 0; messages.size() > i; i++) {
				sender.sendMessage(i + ":" + messages.get(i));
			}
			return true;
		} else if (args[0].equalsIgnoreCase("add")) {
			StringBuilder msg = new StringBuilder();
			for (int i = 1; args.length > i; i++) {
				msg.append(args[i]);
			}
			messages.add(msg.toString());
			instance.getConfig().set("announcement.messages", messages);
			instance.saveConfig();
			sender.sendMessage("追加しました");
			return true;
		} else if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("remove")) {
			if (args.length != 2) {
				return false;
			}
			messages.remove(Integer.valueOf(args[1]));
			instance.getConfig().set("announcement.messages", messages);
			instance.saveConfig();
			sender.sendMessage("削除しました");
			return true;
		}
		return false;
	}
}
