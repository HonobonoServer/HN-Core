package co.honobono.hncore;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class home implements CommandExecutor {
	private static Plugin instance = HNCore.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("ゲーム内より実行してください。");
			return false;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			Location bed = player.getBedSpawnLocation();
			if (bed != null) {
				player.teleport(bed);
			} else {
				player.sendMessage("Homeが見つかりません");
			}
			return true;
		} else if (args.length == 1) {
			if (!player.isOp()) {
				return false;
			}
			Player player1 = instance.getServer().getPlayer(args[0]);
			Location bed = player1.getBedSpawnLocation();
			if (bed != null) {
				player.teleport(bed);
			} else {
				player.sendMessage("Homeが見つかりません");
			}
			return true;
		} else {
			return false;
		}
	}
}
