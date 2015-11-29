package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.command.ServerCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import co.honobono.hncore.util.FoodItemStack;

public class test implements CommandExecutor, Listener {
	// private static Plugin instance = HNCore.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof ServerCommandSender) {
			return true;
		}
		Player player = (Player) sender;
		if (player == Bukkit.getPlayer("syu_chan_1005")) {
			player.getInventory().addItem(new ItemStack(Material.getMaterial(args[0]), Integer.valueOf(args[1]), Short.valueOf(args[2])));
			for(FoodItemStack s : AddFood.cfood) player.sendMessage(s.toString());
			player.sendMessage("実行終了");
			return true;
		} else {
			player.sendMessage("このコマンドは開発者専用です。");
			return true;
		}
	}
}
