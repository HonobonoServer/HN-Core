package co.honobono.hncore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;


public class blockreplace implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true)
    public void blockplace(BlockPlaceEvent event) {
		String block = event.getBlock().getType().toString();
		if (block.equals("BEDROCK")) {
			String name = event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName();
			if (!( name == null || name.length() == 0 )) {
				Matcher m = Pattern.compile(":").matcher(name);
				if (m.find()){
					int start = m.start();
					try {
						event.getBlock().setType(Material.getMaterial(Integer.parseInt(name.substring(0 ,start))));
						event.getBlock().setData((byte) Integer.parseInt(name.substring(start +1)));
					} catch (NumberFormatException e) {
					}
				}
			}
		}
	}

	@EventHandler
	public void piston(BlockPhysicsEvent event) {
		if(event.getBlock().getType() != Material.PISTON_EXTENSION) {
			return;
		}
		event.setCancelled(true);
	}
}
