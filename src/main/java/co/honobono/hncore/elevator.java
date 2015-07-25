package co.honobono.hncore;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.util.Particle;
import net.minecraft.server.v1_8_R3.EnumParticle;

public class elevator implements Listener {

	private Plugin instance = HNCore.getInstance();

	@EventHandler
	public void elevatorup(PlayerMoveEvent event) {
		if (event.getTo().getY() - event.getFrom().getY() > 0.4 && event.getTo().getY() - event.getFrom().getY() < 0.5
				&& event.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR
				&& event.getPlayer().getLocation().getBlock().getType().isTransparent()) {
			List<String> blocks = instance.getConfig().getStringList("elevator");
			for (String blk : blocks) {
				Matcher m = Pattern.compile(":").matcher(blk);
				if (!m.find()) {
					break;
				}
				Location loc = event.getPlayer().getLocation();
				Material block = Material.getMaterial(blk.substring(0, m.start()));
				int scope = Integer.parseInt(blk.substring(m.start() + 1));
				if (loc.subtract(0, 1, 0).getBlock().getType() != block) {
					continue;
				}
				for (int i = 0; i < scope; i++) {
					loc.setY(loc.getY() + 1);
					if (loc.getBlock().getType() != block) {
						continue;
					}
					if (loc.add(0, 1, 0).getBlock().getType().isTransparent()
							&& loc.add(0, 1, 0).getBlock().getType().isTransparent()
							&& loc.add(0, 1, 0).getBlock().getType().isTransparent()) {
						event.getPlayer().teleport(loc.subtract(0, 2, 0));
						event.getPlayer().playSound(loc, Sound.ENDERMAN_TELEPORT, 10, 1);
						Particle.normal(event.getPlayer(), EnumParticle.PORTAL, loc.subtract(0, 1, 0), 50);
						break;
					}
				}
			}
		}
	}

	@EventHandler
	public void elevatordown(PlayerToggleSneakEvent event) {
		if (event.isSneaking() == false) {
			return;
		}
		List<String> blocks = instance.getConfig().getStringList("elevator");
		for (String blk : blocks) {
			Matcher m = Pattern.compile(":").matcher(blk);
			if (!m.find()) {
				break;
			}
			Location loc = event.getPlayer().getLocation();
			Material block = Material.getMaterial(blk.substring(0, m.start()));
			int scope = Integer.parseInt(blk.substring(m.start() + 1));
			if (loc.subtract(0, 1, 0).getBlock().getType() != block) {
				continue;
			}
			for (int i = 0; i < scope; i++) {
				loc.setY(loc.getY() - 1);
				if (loc.getBlock().getType() != block) {
					continue;
				}
				if (loc.add(0, 1, 0).getBlock().getType().isTransparent()
						&& loc.add(0, 1, 0).getBlock().getType().isTransparent()
						&& loc.add(0, 1, 0).getBlock().getType().isTransparent()) {
					event.getPlayer().teleport(loc.subtract(0, 2, 0));
					event.getPlayer().playSound(loc, Sound.ENDERMAN_TELEPORT, 10, 1);
					Particle.normal(event.getPlayer(), EnumParticle.PORTAL, loc.subtract(0, 1, 0), 50);
					break;
				}
			}
		}
	}
}
