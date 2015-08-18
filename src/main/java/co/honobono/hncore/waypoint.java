package co.honobono.hncore;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import co.honobono.hncore.util.CustomConfig;
import co.honobono.hncore.util.Other;

public class waypoint implements CommandExecutor{
	private static CustomConfig cWay = HNCore.getWaypoint();
	private static FileConfiguration f = cWay.getConfig();
	private static List<String> list = f.getStringList("list");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		if(args.length == 0) {
			for (String l : list) {
				player.sendMessage(l);
			}
			return true;
		} else if(args.length == 1) {
			Location loc = get(args[0]);
			if(loc == null) {
				player.sendMessage("ポイントが見つかりません");
			} else {
				player.teleport(loc);
			}
			f.set("list", list);
			cWay.save();
			return true;
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("set")) {
				Location loc = get(args[1]);
				if(loc == null) {
					add(args[1], player.getLocation());
					player.sendMessage("ポイントを追加しました ポイント名:" + args[1]);
				} else {
					player.sendMessage("すでにそのポイントは存在します");
				}
			} else if(args[0].equalsIgnoreCase("del")) {
				Location loc = get(args[1]);
				if(loc == null) {
					player.sendMessage("そのポイントは存在しません");
				} else {
					del(args[1]);
					player.sendMessage("ポイントを削除しました ポイント名:" + args[1]);
				}
			}
			f.set("list", list);
			cWay.setConfig(f);
			cWay.save();
			return true;
		}
		return false;
	}

	public static Location get(String name) {
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			if(!s.startsWith(name)) {
				continue;
			}
			s = s.substring(name.length());
			return Other.toLocation(s);
		}
		return null;
	}

	public static void add(String name, Location loc) {
		list.add(name + loc.toString());
	}

	public static void del(String name) {
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			if(!s.startsWith(name)) {
				continue;
			}
			list.remove(i);
		}
	}
}
