package co.honobono.hncore;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hat implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) { return false; }
		Player player = (Player) sender;
		ItemStack item = null;
		if(args.length == 0) {
			item = player.getItemInHand();
			if(item.getType() == Material.AIR) { return false; }
		} else if(args.length == 1) {

		}
		player.setItemInHand(player.getInventory().getHelmet());
		player.getInventory().setHelmet(item);
		return true;
	}
}
