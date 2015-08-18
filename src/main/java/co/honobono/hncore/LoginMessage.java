package co.honobono.hncore;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.util.Other;

public class LoginMessage implements Listener {
	private Plugin instance = HNCore.getInstance();

	@EventHandler
	public void LoginMsg(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		event.setJoinMessage("");

		String JoinMessage = instance.getConfig().getString("LoginMessage.LoginMessage");
		Bukkit.broadcastMessage(Other.color(JoinMessage, player));
		if (!(player.hasPlayedBefore())) {
			List<String> First = instance.getConfig().getStringList("LoginMessage.FirstLogin");
			for (String first : First) {
				first = Other.color(first, player);
				Bukkit.broadcastMessage(first);
			}
		}

		List<String> LoginMessage = instance.getConfig().getStringList("LoginMessage.Login");
		for (String m : LoginMessage) {
			player.sendMessage(Other.color(m, player));
		}
		// hideコマンド使用者を適応
		Map<Player, Player> hiders = show_hide.gethider();
		for (Entry<Player, Player> entry : hiders.entrySet()) {
			Player hider = entry.getValue();
			player.hidePlayer(hider);
		}
		//Lightの前準備
		player.setMetadata("light", new FixedMetadataValue(instance, "non"));
	}

	@EventHandler
	public void LogoutMsg(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String LogoutMessage = instance.getConfig().getString("LoginMessage.LogoutMessage");
		event.setQuitMessage(Other.color(LogoutMessage, player));
	}
}
