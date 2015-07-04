package so.wktk.honobonoserver.hncore;

import java.io.File;
import java.math.BigDecimal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import so.wktk.honobonoserver.hncore.util.Other;

public class kennzou implements CommandExecutor {
	private Plugin instance = HNCore.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("f")){
			Player player = (Player) sender;
			BigDecimal bd = new BigDecimal(player.getLocation().getYaw());
			BigDecimal bd4 = bd.setScale(1, BigDecimal.ROUND_DOWN);
			String csv = (","+ bd4.doubleValue() + "°,");
			File fl = new File(instance.getDataFolder(), "Location.csv");
			Other.filewrite(fl, csv);
			return true;
		} else if(cmd.getName().equalsIgnoreCase("l")){
			Player player = (Player) sender;
			BigDecimal X = new BigDecimal(player.getLocation().getX());
			BigDecimal X1 = X.setScale(1, BigDecimal.ROUND_DOWN);
			BigDecimal Z = new BigDecimal(player.getLocation().getZ());
			BigDecimal Z1 = Z.setScale(1, BigDecimal.ROUND_DOWN);
			String csv = ("X " + X1.doubleValue() + "Z " + Z1.doubleValue() + "\r\n");
			File fl = new File(instance.getDataFolder(), "Location.csv");
			Other.filewrite(fl, csv);
			return true;
		} else {
			return false;
		}
	}
}
