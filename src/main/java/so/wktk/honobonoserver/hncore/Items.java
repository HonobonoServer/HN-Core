package so.wktk.honobonoserver.hncore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class Items implements Listener{

	@EventHandler
	public static void pocketcrafter(PlayerInteractEvent event) {
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) { return; }
		if(event.getPlayer().getItemInHand().getType() == Material.STICK && event.getPlayer().getItemInHand().getItemMeta().getDisplayName() == "§RPocket-Crafter") {
			Inventory inv = Bukkit.createInventory(null, InventoryType.WORKBENCH);
			event.getPlayer().openInventory(inv);
		}
	}
}
