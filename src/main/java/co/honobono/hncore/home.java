package co.honobono.hncore;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class home implements CommandExecutor, Listener {
	private static Plugin instance = HNCore.getInstance();
	private int time = 10;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("ゲーム内より実行してください。");
			return true;
		}
		Player player = (Player) sender;
		if (args.length == 0) {
			Location bed = player.getBedSpawnLocation();
			time = 10;
			if (bed != null) {
				Location loc1 = player.getLocation();
				new BukkitRunnable(){
					@Override
					public void run() {
						player.sendMessage("テレポートまで残り" + time + "秒...");
						time--;
						if(loc1.getBlockX() != player.getLocation().getBlockX() || loc1.getBlockZ() != player.getLocation().getBlockZ()) {
							player.sendMessage("動いたため中止します...");
							this.cancel();
						}
						if(time == -1) {
							player.teleport(bed);
							this.cancel();
						}
					}
				}.runTaskTimer(instance, 0, 20);
			} else {
				player.sendMessage("Homeが見つかりません");
			}
			return true;
		} else if (args.length == 1) {
			if (!player.isOp())
				return false;
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
