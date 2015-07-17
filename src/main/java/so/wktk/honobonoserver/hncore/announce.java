package so.wktk.honobonoserver.hncore;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import so.wktk.honobonoserver.hncore.util.Other;
import so.wktk.honobonoserver.hncore.util.announceEvent;

public class announce implements Listener, CommandExecutor {
	private Plugin instance = HNCore.getInstance();
	//private FileConfiguration conf = HNCore.getConf();
	private int count = 0;

	@EventHandler
	public void Announce(announceEvent event) {
		List<String> messages = instance.getConfig().getStringList("announcement.messages");
		if (instance.getConfig().getBoolean("announcement.random") == true) {
			count = new Random().nextInt(messages.size());
		} else {
			count++;
			if(messages.size() < count) {
				count = 0;
			}
		}
		String msg = Other.color(instance.getConfig().getString("announcement.prefix") + messages.get(count),null);
		Bukkit.broadcastMessage(msg);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length == 0) { return false;}
		List<String> messages = instance.getConfig().getStringList("announcement.messages");
		if (args[0].equalsIgnoreCase("list")) {
			for (int i = 0; messages.size() <= i; i++) {
				sender.sendMessage(i + ": " + Other.color(messages.get(i),null));
			}
			return true;
		} else if (args[0].equalsIgnoreCase("add")) {
			if (args.length != 2) {
				return false;
			}
			messages.add(args[1]);
			instance.getConfig().set("announcement.messages", messages);
			instance.saveConfig();
			return true;
		} else if (args[0].equalsIgnoreCase("edit")) {
			if (args.length != 3) {
				return false;
			}
			messages.remove(Integer.valueOf(args[1]));
			messages.add(args[2]);
			instance.getConfig().set("announcement.messages", messages);
			instance.saveConfig();
			return true;
		}
		return false;
	}
}
