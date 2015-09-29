package co.honobono.hncore;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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

}
