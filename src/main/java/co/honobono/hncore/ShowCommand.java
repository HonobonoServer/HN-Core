package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.util.Other;

public class ShowCommand implements Listener{
	private static Plugin instance = HNCore.getInstance();

	@EventHandler
	public void sendCommand(PlayerCommandPreprocessEvent event) {
		String format = instance.getConfig().getString("ShowCommand.format");
		format = format.replaceAll("<prefix>", instance.getConfig().getString("ShowCommand.prefix"));
		format = format.replaceAll("<command>", event.getMessage());
		format = Other.color(format, event.getPlayer());
		Bukkit.broadcast(format, "hncore.showcommand");
	}
}
