package co.honobono.hncore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class wp implements CommandExecutor{
	private Plugin instance = HNCore.getInstance();
	@SuppressWarnings("unchecked")
	private Map<String,Location> wplist = (Map<String, Location>) instance.getConfig().get("wp");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		if(wplist == null) {
			wplist = new HashMap<String, Location>();
		}
		if(args.length == 0) {
			if(wplist.isEmpty()) {
				player.sendMessage("設定されていません。");
				return true;
			}
			for (String key : wplist.keySet()) {
	            player.sendMessage(key + "(X:" + wplist.get(key).getX() + " Y:" + wplist.get(key).getY() + " Z:" + wplist.get(key).getZ() + ")");
	        }
			return true;
		} else if(args.length == 1) {
			if(wplist.containsKey(args[0])) {
				player.teleport(wplist.get(args[0]));
				return true;
			} else {
				player.sendMessage("ポイントが見つかりません。");
				return true;
			}
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("set")) {
				if(wplist.containsKey(args[1])) {
					player.sendMessage("すでにそのポイントは存在します。");
					player.sendMessage("/wp del " + args[1] + " と入力し削除してから再実行して下さい。");
					return true;
				} else {
					wplist.put(args[1], player.getLocation());
					player.sendMessage("ポイントを設定しました。 ポイント名:" + args[1]);
					instance.getConfig().set("wp", wplist);
					instance.saveConfig();
					return true;
				}
			} else if(args[0].equalsIgnoreCase("del")) {
				if(wplist.containsKey(args[1])) {
					wplist.remove(args[1]);
					player.sendMessage("ポイントを削除しました。 ポイント名:" + args[1]);
					instance.getConfig().set("wp", wplist);
					instance.saveConfig();
					return true;
				} else {
					player.sendMessage("ポイント" + args[1] + "は存在しません。");
					return true;
				}
			}
			return true;
		}
		return true;
	}
}
