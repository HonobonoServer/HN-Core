package co.honobono.hncore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.util.sendPacket;

public class hnreload implements CommandExecutor {
	private Plugin instance = HNCore.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		instance.reloadConfig();
		sendPacket.sendPlayer((Player) sender, sendPacket.setActionBar("§aReloadしました"));
		return true;
	}
}
