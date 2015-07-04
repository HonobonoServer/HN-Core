package so.wktk.honobonoserver.hncore;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import so.wktk.honobonoserver.hncore.util.Other;

public class LoginMessage implements Listener{
	private Plugin instance = HNCore.getInstance();

	@EventHandler
	public void LoginMsg(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		String JoinMessage = instance.getConfig().getString("LoginMessage.LoginMessage");
		JoinMessage = Other.figuration(JoinMessage, player);
		event.setJoinMessage(JoinMessage);
		if(!(player.hasPlayedBefore())) {
			List<String> First = instance.getConfig().getStringList("LoginMessage.FirstLogin");
			for(String first : First) {
				first = Other.figuration(first, player);
				player.sendMessage(first);
			}
		}

		List<String> LoginMessage = instance.getConfig().getStringList("LoginMessage.Login");
		new BukkitRunnable() {
			@Override
			public void run() {
				for (String message : LoginMessage) {
					message = Other.color(message, player);
					player.sendMessage(message);
				}
			}
		 }.runTaskLater(instance, (int)20);
		//hideコマンド使用者を適応
		Map<Player, Player> hiders = show_hide.gethider();
		for (Entry<Player, Player> entry : hiders.entrySet()) {
            Player hider = entry.getValue();
            player.hidePlayer(hider);
        }
	}

	@EventHandler
	public void LogoutMsg(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		String LogoutMessage = instance.getConfig().getString("LoginMessage.LogoutMessage");
		LogoutMessage = Other.figuration(LogoutMessage, player);
		event.setQuitMessage(LogoutMessage);
	}
}
