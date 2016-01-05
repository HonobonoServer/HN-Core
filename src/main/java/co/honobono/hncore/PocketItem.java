package co.honobono.hncore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.honobono.hncore.util.Item;
import co.honobono.hncore.util.Other;

public class PocketItem implements Listener {

	@EventHandler
	public void click(PlayerInteractEvent event) {
		if (!Other.include(event.getAction(), Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) {
			return;
		}
		ItemStack item = event.getPlayer().getItemInHand();
		if(item == null || item.getItemMeta() == null) {
			return;
		}
		String name = item.getItemMeta().getDisplayName();
		short dura = item.getDurability();
		if (name != null && name.equalsIgnoreCase(Item.WorkBench().getItemMeta().getDisplayName())
				&& dura == Item.WorkBench().getDurability()) {
			event.getPlayer().openWorkbench(null, true);
		}
	}

	public static Map<Player, ItemStack[]> PocketInv = new HashMap<>();

	@EventHandler
	public void inv(PlayerInteractEvent event) {
		if (!Other.include(event.getAction(), Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) { return; }
		Player player = event.getPlayer();
		ItemStack i = player.getItemInHand();
		if(i == null || i.getType() != Material.STICK || !i.hasItemMeta()) return;
		if(i.getItemMeta().getDisplayName().indexOf("PocketChest") == -1) return;
		event.setCancelled(true);
		Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST);
		if(PocketInv.containsKey(player)) { inv.setContents(PocketInv.get(player)); } // else { PocketInv.put(player, inv.getContents()); }
		player.openInventory(inv);
		PocketInv.put(player, inv.getContents());
	}
}
