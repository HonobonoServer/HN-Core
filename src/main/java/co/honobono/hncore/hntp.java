package co.honobono.hncore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class hntp implements CommandExecutor {
	private Plugin instance = HNCore.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(args.length == 1) {
			Player player = instance.getServer().getPlayer(args[0]);
			Player player1 = (Player) sender;
			player1.teleport(player);
			return true;
		} else if (args.length == 3 || args.length == 4) {
			Player player = (Player) sender;
			Location loc = player.getLocation();
			loc = toloc(loc,args[0],args[1],args[2]);
			World world = instance.getServer().getWorld(args[3]); if(world != null) { loc.setWorld(world); }
			player.teleport(loc);
			return true;
		} else {
			return false;
		}
	}

	public static Location toloc(Location loc,String x,String y,String z) {
		try{
			Pattern p = Pattern.compile("~");
			Matcher xm = p.matcher(x);
			if(xm.find()) {
				x = x.substring(xm.start() + 1);
				int xi = (Integer.valueOf(x));
				loc.setX(loc.getX() + xi);
			} else {
				int ix2 = (Integer.valueOf(x));
				loc.setX(ix2);
			}
			Matcher ym = p.matcher(y);
			if(ym.find()) {
				y = y.substring(xm.start() + 1);
				int yi = (Integer.valueOf(y));
				loc.setY(loc.getY() + yi);
			} else {
				int iy2 = (Integer.valueOf(y));
				loc.setY(iy2);
			}
			Matcher zm = p.matcher(z);
			if(zm.find()) {
				z = z.substring(xm.start() + 1);
				int zi = (Integer.valueOf(z));
				loc.setZ(loc.getZ() + zi);
			} else {
				int iz2 = (Integer.valueOf(z));
				loc.setZ(iz2);
			}

			return loc;
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
