package co.honobono.hncore;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import co.honobono.hncore.util.Other;
import co.honobono.hncore.util.TwitterUtil;
import twitter4j.TwitterException;

public class Twitters implements CommandExecutor, Listener {
	// private Plugin instance = HNCore.getInstance();
	private static String time = "\n" + Other.stamp();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String name = "";
		if (sender instanceof Player) {
			name = ((Player) sender).getName();
		} else {
			name = "Console";
		}
		String msg = "";
		for (String s : args) {
			msg = msg + s;
		}
		try {
			TwitterUtil.tweet(msg + "\nTweet by." + name + time);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return true;
	}

	@EventHandler
	public void JoinPlayer(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		String msg = "さんがログインしました！";
		if (!player.hasPlayedBefore()) {
			msg = msg + "\nほのぼのサーバーへようこそ！";
		}
		msg = msg + "\n現在のログイン人数は" + Bukkit.getServer().getOnlinePlayers().size() + "人です";
		Map<String, InputStream> map = new HashMap<String, InputStream>();
		try {
			map.put(name, new URL("https://minotar.net/helm/" + name + "/100.png").openStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			TwitterUtil.tweet(name + msg + time, map);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	/*
	@EventHandler
	public void QuitPlayer(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		Map<String, InputStream> map = new HashMap<String, InputStream>();
		try {
			map.put(name, new URL("https://minotar.net/helm/" + name + "/100.png").openStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			TwitterUtil.tweet(name + "さんがログアウトしました！" + time, map);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	@EventHandler
	public void Achievement(PlayerAchievementAwardedEvent event) {
		String achieve = Other.AchievementtoJP(event.getAchievement());
		String name = event.getPlayer().getName();
		Map<String, InputStream> map = new HashMap<String, InputStream>();
		try {
			map.put(name, new URL("https://minotar.net/helm/" + name + "/100.png").openStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			TwitterUtil.tweet(name + "さんが" + achieve + "の実績を解除しました！" + time, map);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
	*/

	@EventHandler
	public void Death(PlayerDeathEvent event) {
		String msg = event.getDeathMessage();
		String name = event.getEntity().getName();
		Map<String, InputStream> map = new HashMap<String, InputStream>();
		try {
			map.put(name, new URL("https://minotar.net/helm/" + name + "/100.png").openStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			TwitterUtil.tweet(msg, map);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
