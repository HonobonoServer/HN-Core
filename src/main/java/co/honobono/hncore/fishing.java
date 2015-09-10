package co.honobono.hncore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class fishing implements Listener{
	private static Plugin instance = HNCore.getInstance();
	private static Random random = new Random();

	@EventHandler
	public void explode(ExplosionPrimeEvent event) {
		Entity e = event.getEntity();
		if(e.getType() != EntityType.PRIMED_TNT) {
			return;
		}
		int water = 0;
		int fishmax = random.nextInt(2);
		Location loc = e.getLocation().getBlock().getLocation().subtract(2, 2, 2);
		List<Location> locs = new ArrayList<Location>();
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				for (int z = 0; z < 5; z++) {
					Location loc1 = loc.clone().add(x, y, z);
					Material m = loc1.getBlock().getType();
					if(m == Material.WATER || m == Material.STATIONARY_WATER) {
						water++;
						int a = random.nextInt(5);
						if(a == 1 || a == 3) {
							if(locs.size() <= fishmax) {
								locs.add(loc1);
							}
						}
					}
				}
			}
		}
		double par = (water / 125.0);
		if(par <= 0.5) {
			return;
		}
		instance.getServer().getScheduler().scheduleSyncDelayedTask(instance, new Runnable(){
			@Override
			public void run() {
				for(Location l : locs) {
					l.getWorld().dropItem(l, fish());
				}
			}
		}, 5L);
	}

	private static ItemStack fish = new ItemStack(Material.RAW_FISH, 1, (short)0);
	private static ItemStack salmon = new ItemStack(Material.RAW_FISH, 1, (short)1);
	private static ItemStack clown = new ItemStack(Material.RAW_FISH, 1, (short)2);
	private static ItemStack puffer = new ItemStack(Material.RAW_FISH, 1, (short)3);
	private static ItemStack fish() {
		int num = random.nextInt(100);
		if(num < 10) {
			return clown;
		} else if(num < 20 && num > 10) {
			return puffer;
		} else if(num > 20 && num < 40) {
			return salmon;
		} else {
			return fish;
		}
	}
}
