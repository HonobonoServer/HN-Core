package co.honobono.hncore;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.util.CustomConfig;
import co.honobono.hncore.util.Light;

public class light implements CommandExecutor, Listener {
	private static CustomConfig cLight = HNCore.cLight;
	private static FileConfiguration f = cLight.getConfig();
	private static List<String> lightlist = f.getStringList("list");
	private static Plugin instance = HNCore.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("list")) {
				for (String s : lightlist) {
					player.sendMessage(s);
				}
			} else if (args[0].equalsIgnoreCase("del")) {
				player.setMetadata("light", new FixedMetadataValue(instance, "del"));
				player.sendMessage("光源を削除する場所を右クリックしてください。");
			} else if(args[0].equalsIgnoreCase("reload")) {
				Light.sendUpdateChunks();
			}
			return true;
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("set")) {
				player.setMetadata("light", new FixedMetadataValue(instance, "set"));
				player.setMetadata("lightlevel", new FixedMetadataValue(instance, Integer.valueOf(args[1])));
				player.sendMessage("光源を設置する場所を右クリックしてください。");
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void lightsetting(PlayerInteractEvent event) {
		if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		Player player = event.getPlayer();
		String l = player.getMetadata("light").get(0).asString();
		if (l.equals("non")) {
			return;
		}
		Location loc = event.getClickedBlock().getLocation();
		if (l.equals("set")) {
			if (get(loc)) {
				player.sendMessage("すでに光源が設置されています。");
			} else {
				int i = player.getMetadata("lightlevel").get(0).asInt();
				Light.createLight(loc, i);
				Light.sendUpdateChunks();
				add(loc, i);
				player.sendMessage("光源を設置しました。");
			}
		} else if (l.equals("del")) {
			if (get(loc)) {
				Light.deleteLight(event.getClickedBlock().getLocation());
				Light.sendUpdateChunks();
				del(loc);
				player.sendMessage("光源を削除しました。");
			} else {
				player.sendMessage("光源が設置されていません。");
			}
		}
		player.setMetadata("light", new FixedMetadataValue(instance, "non"));
		f.set("list", lightlist);
		cLight.setConfig(f);
		cLight.save();
		return;
	}

	@EventHandler
	public void destorylight(BlockBreakEvent event) {
		Location loc = event.getBlock().getLocation();
		Light.deleteLight(loc);
		if(get(loc)) {
			del(loc);
			event.getPlayer().sendMessage("光源を削除しました");
		}
	}

	public static boolean get(Location loc) {
		String l = loc.toString();
		for (String s : lightlist) {
			if (s.startsWith(l)) {
				return true;
			}
		}
		return false;
	}

	public static void add(Location loc, int level) {
		lightlist.add(loc.toString() + level);
	}

	public static void del(Location loc) {
		String l = loc.toString();
		for (int i = 0; i < lightlist.size(); i++) {
			if (lightlist.get(i).startsWith(l)) {
				lightlist.remove(i);
			}
		}
	}
}
