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

public class mute implements Listener, CommandExecutor{
	private static Map<Player, Player> muters= new HashMap<Player, Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(args.length == 1) {
			Player player = Bukkit.getServer().getPlayer(args[0]);
			if(muters.containsKey(player)) {
				muters.remove(player);
				sendPacket.sendPlayer(player, sendPacket.setActionBar("§aIt Leave Muter"));
				return true;
			} else {
				muters.put(player, player);
				sendPacket.sendPlayer(player, sendPacket.setActionBar("§aIt Join Muter"));
				return true;
			}
		}
		return false;
	}

	@EventHandler
	public void Mute(AsyncPlayerChatEvent event) {
		if(muters.containsKey(event.getPlayer())) {
			Bukkit.broadcast("<" + event.getPlayer().getDisplayName() + "> " + "§c" + event.getMessage(), "hncore.mute");
			event.setCancelled(true);
		}
	}
}
