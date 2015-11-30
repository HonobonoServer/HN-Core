package co.honobono.hncore;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import co.honobono.hncore.util.sendPacket;

public class freeze implements CommandExecutor, Listener {
	private Plugin instance = HNCore.getInstance();
	private static List<Player> freezer = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("freeze")) {
			if (args.length == 1) {
				Player player = instance.getServer().getPlayer(args[0]);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1, 1));
				Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
					@Override
					public void run() {
						freezer.add(player);
						player.getLocation().getBlock().setType(Material.ICE);
						player.getLocation().add(0, 1, 0).getBlock().setType(Material.ICE);
					}
				}, 20L);
				sendPacket.sendActionBar((Player) sender, "§a" + player.getName() + "をFreezeしました");
				return true;
			}
			return false;
		} else if (cmd.getName().equalsIgnoreCase("unfreeze")) {
			if (args.length == 1) {
				Player player = instance.getServer().getPlayer(args[0]);
				freezer.remove(player);
				sendPacket.sendActionBar((Player) sender, "§a" + player.getName() + "をUnFreezeしました");
				return true;
			}
			return false;
		} else {
			return false;
		}
	}

	@EventHandler
	public void moveoff(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (freezer.contains(player)) {
			Location from = event.getFrom();
			Location to = event.getTo();
			if (from.getBlockX() == to.getBlockX() && from.getBlockZ() == to.getBlockZ()) {
				return;
			}
			player.teleport(from);
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void dropoff(PlayerDropItemEvent event) {
		if (freezer.contains(event.getPlayer()))
			event.setCancelled(true);
	}

	@EventHandler
	public void interactoff(PlayerInteractEvent event) {
		if (freezer.contains(event.getPlayer()))
			event.setCancelled(true);
	}

	@EventHandler
	public void EntityDamageEvent(EntityDamageEvent event) {
		if(!(event.getEntityType() == EntityType.PLAYER)) return;
		if(freezer.contains((Player)event.getEntity())) event.setCancelled(true);
	}
}
