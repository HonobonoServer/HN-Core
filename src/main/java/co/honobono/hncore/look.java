package co.honobono.hncore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class look implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player1 = null;
		if (args.length == 0) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("このコマンドはゲーム以内より実行してください。");
				return false;
			}
			player1 = (Player) sender;
		} else if (args.length == 1) {
			player1 = Bukkit.getPlayer(args[0]);
		}
		sender.sendMessage(
				ChatColor.YELLOW + "-----------" + ChatColor.AQUA + "Player Status" + ChatColor.YELLOW + "-----------");
		if (player1 == null) {
			OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
			sender.sendMessage("名前: " + p.getName());
			sender.sendMessage("UUID: " + p.getUniqueId());
			sender.sendMessage("最終ログイン: " + getTime(p.getLastPlayed()));
			sender.sendMessage("経験値: " + ChatColor.GRAY + "オフラインのため参照できません");
			sender.sendMessage("体力: " + ChatColor.GRAY + "オフラインのため参照できません");
			sender.sendMessage("IPアドレス: " + ChatColor.GRAY + "オフラインのため参照できません");
		} else {
			sender.sendMessage("名前: " + player1.getName());
			sender.sendMessage("UUID: " + player1.getUniqueId());
			sender.sendMessage("最終ログイン: " + getTime(player1.getLastPlayed()));
			sender.sendMessage("経験値: " + player1.getExpToLevel());
			sender.sendMessage("体力: " + player1.getHealth());
			sender.sendMessage("IPアドレス: " + player1.getAddress());
		}
		return true;
	}

	public static String getTime(long Mills) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(Mills);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(cl.getTime());
	}
}
