package co.honobono.hncore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Statistic;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.util.Particle;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class elevator implements Listener {
	private Plugin instance = HNCore.getInstance();
	private Map<Material, Integer> bls = new HashMap<Material, Integer>();

	{
		for (String a : instance.getConfig().getStringList("elevator")) {
			String[] b = a.split(":");
			bls.put(Material.getMaterial(b[0]), Integer.valueOf(b[1]));
		}
	}

	@EventHandler
	public void elevatorup(PlayerStatisticIncrementEvent event) {
		if (event.getStatistic() != Statistic.JUMP) {
			return;
		}
		Location loc = event.getPlayer().getLocation();
		Material m = loc.subtract(0, 1, 0).getBlock().getType();
		if (!bls.containsKey(m)) {
			return;
		}
		int scope = bls.get(m);
		for (int i = 0; i < scope; i++) {
			loc.setY(loc.getY() + 1);
			if (loc.getBlock().getType() != m) {
				continue;
			}
			if (loc.add(0, 1, 0).getBlock().getType().isTransparent()
					&& loc.add(0, 1, 0).getBlock().getType().isTransparent()) {
				event.getPlayer().teleport(loc.subtract(0, 1, 0));
				event.getPlayer().playSound(loc, Sound.ENDERMAN_TELEPORT, 10, 1);
				Particle.normal(event.getPlayer(), EnumParticle.PORTAL, loc.subtract(0, 1, 0), 5);
				break;
			}
		}
	}

	@EventHandler
	public void elevatordown(PlayerToggleSneakEvent event) {
		if (event.isSneaking() == false) {
			return;
		}
		Location loc = event.getPlayer().getLocation();
		Material m = loc.subtract(0, 1, 0).getBlock().getType();
		if (!bls.containsKey(m)) {
			return;
		}
		int scope = bls.get(m);
		for (int i = 0; i < scope; i++) {
			loc.setY(loc.getY() - 1);
			if (loc.getBlock().getType() != m) {
				continue;
			}
			if (loc.add(0, 1, 0).getBlock().getType().isTransparent()
					&& loc.add(0, 1, 0).getBlock().getType().isTransparent()) {
				event.getPlayer().teleport(loc.subtract(0, 1, 0));
				event.getPlayer().playSound(loc, Sound.ENDERMAN_TELEPORT, 10, 1);
				Particle.normal(event.getPlayer(), EnumParticle.PORTAL, loc.subtract(0, 1, 0), 5);
				break;
			}
		}
	}
}
