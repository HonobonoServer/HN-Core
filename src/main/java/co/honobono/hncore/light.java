package co.honobono.hncore;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.util.Light;

public class light implements CommandExecutor, Listener {
	private static Plugin instance = HNCore.getInstance();
	//private static Light Light;

	@SuppressWarnings("unchecked")
	private static List<Location> list = (List<Location>) instance.getConfig().get("light");
	private static MetadataValue set = new FixedMetadataValue(instance, "set");
	private static MetadataValue del = new FixedMetadataValue(instance, "del");
	private static MetadataValue nul = new FixedMetadataValue(instance, "null");

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (list == null) {
			list = new ArrayList<Location>();
		}
		if (args.length == 0) {
			if (list.size() == 0) {
				player.sendMessage("設置されていません。");
			}
			for (Location l : list) {
				player.sendMessage(l.toString());
			}
			return true;
		} else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("del")) {
				player.setMetadata("light", del);
				player.sendMessage("光源を削除する場所に右クリックして下さい。");
				return true;
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("set")) {
				player.setMetadata("light", set);
				player.setMetadata("lightlevel", new FixedMetadataValue(instance, Integer.valueOf(args[1])));
				player.sendMessage("光源を設置する場所に右クリックして下さい。");
				return true;
			}
		}
		return false;
	}

	@EventHandler
	public void setlight(PlayerInteractEvent event) {
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		Player player = event.getPlayer();
		if (player.getMetadata("light").get(0) == null) {
			player.setMetadata("light", nul);
			return;
		}
		String s = player.getMetadata("light").get(0).asString();
		Location loc = event.getClickedBlock().getLocation();
		if (s.equalsIgnoreCase("set")) {
			if (list.contains(loc)) {
				player.sendMessage("その場所にはすでに設置されています");
			} else {
				list.add(loc);
				Light.createLight(loc, player.getMetadata("lightlevel").get(0).asInt());
				instance.getConfig().set("light", list);
				instance.saveConfig();
				player.sendMessage("光源を設置しました。");
			}
		} else if (s.equalsIgnoreCase("del")) {
			if (list.contains(list)) {
				list.remove(loc);
				Light.deleteLight(loc);
				instance.getConfig().set("light", list);
				instance.saveConfig();
				player.sendMessage("光源を削除しました");
			} else {
				player.sendMessage("その場所には設置されていません");
			}
		}
		player.setMetadata("light", nul);
		return;
	}

	@EventHandler
	public void chatlight(AsyncPlayerChatEvent event) {
		event.getPlayer().setMetadata("light", nul);
	}

	@EventHandler
	public void breakblock(BlockBreakEvent event) {
		Light.deleteLight(event.getBlock().getLocation());
	}
}
