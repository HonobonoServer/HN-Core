package co.honobono.hncore;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import co.honobono.hncore.util.Item;

public class hnget implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length == 2) {
			ItemStack myitem = new ItemStack(Material.BEDROCK);
			ItemMeta im = myitem.getItemMeta();
			im.setDisplayName(args[0] + ":" + args[1]);
			myitem.setItemMeta(im);
			Player player = (Player) sender;
			PlayerInventory inventory = player.getInventory();
			inventory.addItem(myitem);
			return true;
		} else if (args[0].equals("FlyBoots")) {
			Player player = (Player) sender;
			PlayerInventory inventory = player.getInventory();
			inventory.addItem(Item.flyboots());
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true)
	public void blockplace(BlockPlaceEvent event) {
		String block = event.getBlock().getType().toString();
		if (!block.equals("BEDROCK")) {
			return;
		}
		String name = event.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName();
		if (name == null || name.length() == 0) {
			return;
		}
		String[] n = name.split(":");
		event.getBlock().setType(Material.getMaterial(Integer.parseInt(n[0])));
		event.getBlock().setData((byte) Integer.parseInt(n[1]));
	}
}
