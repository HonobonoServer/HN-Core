package co.honobono.hncore;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;

import net.md_5.bungee.api.ChatColor;

public class look implements CommandExecutor {
	private static Plugin instance = HNCore.getInstance();

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
			sender.sendMessage("AllowFlight: " + ChatColor.GRAY + "オフラインのため参照できません");
			sender.sendMessage("IPアドレス: " + ChatColor.GRAY + "オフラインのため参照できません");
			sender.sendMessage("現在座標: " + ChatColor.GRAY + "オフラインのため参照できません");
		} else {
			sender.sendMessage("名前: " + player1.getName());
			sender.sendMessage("UUID: " + player1.getUniqueId());
			sender.sendMessage("最終ログイン: " + getTime(player1.getLastPlayed()));
			sender.sendMessage("経験値: " + player1.getExpToLevel());
			sender.sendMessage("体力: " + player1.getHealth() + "/" + player1.getMaxHealth());
			sender.sendMessage("AllowFlight: " + player1.getAllowFlight());
			sender.sendMessage("IPアドレス: " + player1.getAddress() + "(" + getLoc(player1.getAddress().toString()) + ")");
			sender.sendMessage("現在座標: " + player1.getLocation().toString());
		}
		return true;
	}

	public static String getTime(long Mills) {
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(Mills);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sdf.format(cl.getTime());
	}

	public static String getLoc(String Address) {
		final String IP = Address.split(":")[0].substring(1);
		File db = new File(instance.getDataFolder(), "GeoLite2-City.mmdb");
		if (!db.exists()) {
			return "DBファイルが存在しません。";
		}
		if(IP.equals("127.0.0.1")) {
			return ("localhostのため特定できません。");
		}
		try {
			InputStream in = new FileInputStream(db);
			DatabaseReader cityDB = new DatabaseReader.Builder(in).build();
			CityResponse res = cityDB.city(InetAddress.getByName(IP));
			String continent = res.getContinent().getNames().get("ja");
			String country = res.getCountry().getNames().get("ja");
			String area = res.getLeastSpecificSubdivision().getNames().get("ja");
			return continent + "/" + country + "/" + area;
		} catch (Exception e) {
			e.printStackTrace();
			return "特定できませんでした。";
		}
	}
}
