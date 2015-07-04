package so.wktk.honobonoserver.hncore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import so.wktk.honobonoserver.hncore.util.sendPacket;

public class adminchat implements Listener, CommandExecutor{
	private static Map<Player, Player> admins= new HashMap<Player, Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(args.length == 0) {
			Player player = (Player) sender;
			if(admins.containsKey(player)) {
				admins.remove(player);
				sendPacket.sendPlayer(player, sendPacket.setActionBar("§aYour Leave AdminsChat"));
				return true;
			} else {
				admins.put(player, player);
				sendPacket.sendPlayer(player, sendPacket.setActionBar("§aYour Join AdminsChat"));
				return true;
			}
		} else {
			for (Player admin : admins.keySet()) {
				for(String msg : args) {
					admin.sendMessage(msg);
				}
			}
			return true;
		}
	}

	@EventHandler
	public void Mute(AsyncPlayerChatEvent event) {
		for (Player admin : admins.keySet()) {
			if(event.getPlayer() == admin) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if(player.hasPermission("hncore.adminchat"))
					player.sendMessage("§a<" + admin.getName() + ">" + event.getMessage().toLowerCase());
				}
				event.setCancelled(true);
			}
		}
	}
}
