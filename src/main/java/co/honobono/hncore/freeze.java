package co.honobono.hncore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.util.sendPacket;

public class freeze implements CommandExecutor,Listener{
	private Plugin instance = HNCore.getInstance();
	private static Map<Player, Player> freezer= new HashMap<Player, Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("freeze")){
			if (args.length == 1) {
				Player player = instance.getServer().getPlayer(args[0]);
				freezer.put(player, player);
				sendPacket.sendPlayer((Player) sender, sendPacket.setActionBar("§a" + player.getName() + "をFreezeしました"));
				return true;
			}
			return false;
		} else if(cmd.getName().equalsIgnoreCase("unfreeze")){
			if(args.length == 1) {
				Player player = instance.getServer().getPlayer(args[0]);
				freezer.remove(player);
				sendPacket.sendPlayer((Player) sender, sendPacket.setActionBar("§a" + player.getName() + "をUnFreezeしました"));
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	@EventHandler
	public void Mute(PlayerMoveEvent event) {
		if(freezer.containsKey(event.getPlayer())) {
			event.setCancelled(true);
		}
	}
}
