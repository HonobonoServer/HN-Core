package co.honobono.hncore;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class Boat implements Listener/*, CommandExecutor*/{

	@EventHandler
	public void onBlock(VehicleDestroyEvent event) {
		event.setCancelled(event.getVehicle().getMetadata("Break").get(0).asBoolean());
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		ItemStack i = event.getItem();
		if(i == null) return;
		if(i.getType() != Material.MONSTER_EGG || i.getDurability() != 1) return;
		Entity e = player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.BOAT);
		e.setMetadata("Break", new FixedMetadataValue(HNCore.getInstance(), true));
	}

	@EventHandler
	public void onInteractEntity(PlayerInteractAtEntityEvent event) {
		if(!event.getPlayer().isOp()) return;
		ItemStack i = event.getPlayer().getItemInHand();
		if(i == null || i.getType() != Material.STICK || !i.hasItemMeta() || !i.getItemMeta().getDisplayName().equalsIgnoreCase("clear")) return;
		event.getRightClicked().remove();
		event.setCancelled(true);
	}
}
