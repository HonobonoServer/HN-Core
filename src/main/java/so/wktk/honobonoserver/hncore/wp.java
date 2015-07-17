package so.wktk.honobonoserver.hncore;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class wp implements CommandExecutor{
	private Plugin instance = HNCore.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(cmd.getName().equalsIgnoreCase("swp")){
			if(args.length == 1) {
				List<String> l = instance.getConfig().getStringList("wp");
				Player player = (Player) sender;
				l.add(LtoS(player.getLocation(),args[0]));
				instance.getConfig().set("wp", l);
				instance.saveConfig();
				return true;
			}
			return false;
		} else if(cmd.getName().equalsIgnoreCase("twp")) {
			if(args.length == 1) {
				Player player = (Player) sender;
				List<String> l = instance.getConfig().getStringList("wp");
				for(String lo : l) {
					if(lo.startsWith(args[0])) {
						Location loc = StoL(lo);
						loc.setPitch(player.getLocation().getPitch());
						loc.setYaw(player.getLocation().getYaw());
						player.teleport(loc);
					}
				}
			return true;
			}
			return false;
		} else if(cmd.getName().equalsIgnoreCase("lwp")) {
			List<String> l = instance.getConfig().getStringList("wp");
			for(String lo : l) {
				sender.sendMessage(lo);
			}
			return true;
		} else {
			return false;
		}
	}

	public static Location StoL(String Location) {
		String[] loc = Location.split(",");
		double[] parsed = new double[3];
		for (int i = 0; i < 3; i++) {
		    parsed[i] = Double.parseDouble(loc[i + 2]);
		}
		Location location = new Location (Bukkit.getWorld(loc[1]), parsed[0], parsed[1], parsed[2]);
		return location;
	}

	public static String LtoS(Location loc,String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(name);
		sb.append(",");
		sb.append(loc.getWorld().getName());
		sb.append(",");
		sb.append((int)loc.getX());
		sb.append(",");
		sb.append((int)loc.getY());
		sb.append(",");
		sb.append((int)loc.getZ());
		return sb.toString();
	}
}
