package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.BlockIterator;

import co.honobono.hncore.util.Particle;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class Bomb implements Listener {

	@EventHandler
	public void effect(ProjectileHitEvent event) {
		Entity e = event.getEntity();
		if (!(e instanceof Arrow)) {
			return;
		}
		Arrow a = (Arrow) e;
		World world = a.getWorld();
		BlockIterator iterator = new BlockIterator(world, a.getLocation().toVector(), a.getVelocity().normalize(), 0,
				4);
		Block b = null;
		while (iterator.hasNext()) {
			b = iterator.next();
			if (b.getType() != Material.AIR) {
				break;
			}
		}
		String name = "";
		if (b.getType() == Material.CHEST) {
			Chest c = (Chest) b.getState();
			name = c.getInventory().getName();
		} else if (b.getType() == Material.FURNACE) {
			Furnace f = (Furnace) b.getState();
			name = f.getInventory().getName();
		} else {
			return;
		}
		Location loc = b.getLocation();
		if (name.equalsIgnoreCase("bomb")) {
			for (Player player : Bukkit.getServer().getOnlinePlayers()) {
				Particle.normal(player, EnumParticle.EXPLOSION_LARGE, loc, 10);
			}
		}
	}

}
