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
import org.bukkit.plugin.Plugin;

import so.wktk.honobonoserver.hncore.util.sendPacket;

public class mute implements Listener, CommandExecutor{
	private Plugin instance = HNCore.getInstance();
	private static Map<Player, Player> muters= new HashMap<Player, Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("mute")){
			if (args.length == 1) {
				Player player = instance.getServer().getPlayer(args[0]);
				muters.put(player, player);
				sendPacket.sendPlayer((Player) sender, sendPacket.setActionBar("§a" + player.getName() + "をMuteにしました"));
				return true;
			}
			return false;
		} else if(cmd.getName().equalsIgnoreCase("unmute")) {
			if(args.length == 1) {
				Player player = instance.getServer().getPlayer(args[0]);
				muters.remove(player);
				sendPacket.sendPlayer((Player) sender, sendPacket.setActionBar("§a" + player.getName() + "をUnMuteにしました"));
				return true;
			}
			return false;
		} else if(cmd.getName().equalsIgnoreCase("mutelist")) {
			Player player = (Player) sender;
			for (Player key : muters.keySet()) { player.sendMessage(key.getName()); }
			return true;
		} else {
			return false;
		}
	}

	@EventHandler
	public void Mute(AsyncPlayerChatEvent event) {
		for (Player key : muters.keySet()) {
			if(event.getPlayer() == key) {
				for (Player player : Bukkit.getOnlinePlayers()) {
					if(player.hasPermission("hncore.mute.tell")){
						player.sendMessage("<" + key.getName() + ">" + event.getMessage().toLowerCase());
					}
				}
				event.setCancelled(true);
			}
		}
	}
}
