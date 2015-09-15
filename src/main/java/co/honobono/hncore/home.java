package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

public class home implements CommandExecutor, Listener {
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

	@EventHandler
	public void Respawn(PlayerRespawnEvent event) {
		Player player =event.getPlayer();
		Location loc = player.getLocation();
		Location loc1 = event.getRespawnLocation();
		if(!equals(loc, loc1)) {
			return;
		}
		event.setRespawnLocation(new Location(Bukkit.getWorld("world_1_5"), -190D, 67D, 248D));
	}

	private boolean equals(Location loc1, Location loc2) {
		if(loc1.getBlockX() == loc2.getBlockX() &&
				loc1.getBlockY() == loc2.getBlockY() &&
				loc1.getBlockZ() == loc2.getBlockZ()) {
			return true;
		}
		return false;
	}
}
