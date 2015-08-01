package co.honobono.hncore;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import co.honobono.hncore.util.sendPacket;


public class itemremove implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("ir")){
			if(args.length == 1) {
				try{
					Player player = (Player) sender;
					Location loc = player.getLocation();
					double range = Double.parseDouble(args[0]);
					List<Entity> entities = new ArrayList<Entity>(loc.getWorld().getNearbyEntities(loc, range, range, range));
					for(Entity entity : entities) {
						if(entity instanceof Item) {
							entity.remove();
						}
					}
					sendPacket.sendActionBar((Player) sender, "§a削除しました");
					return true;
				} catch(NumberFormatException e) {
					return false;
				}
			} else {
				return false;
			}
		} else if(cmd.getName().equalsIgnoreCase("al")) {
			if(args.length == 1) {
				try{
					Player player = (Player) sender;
					Location loc = player.getLocation();
					double range = Double.parseDouble(args[0]);
					List<Entity> entities = new ArrayList<Entity>(loc.getWorld().getNearbyEntities(loc, range, range, range));
					for(Entity entity : entities) {
						if(!(entity instanceof Player)) {
						entity.remove();
						}
					}
					sendPacket.sendActionBar((Player) sender, "§a削除しました");
					return true;
				} catch(NumberFormatException e) {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
