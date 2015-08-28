package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import co.honobono.hncore.util.Other;

public class report implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)) {
			return false;
		}
		if(args.length != 2) {
			return false;
		}
		Player player = (Player) sender;
		Player player1 = Bukkit.getPlayer(args[0]);
		String write = Other.stamp() + "," + player.getName() + "->" + player1.getName() + "," + args[1];
		Other.filewrite("report.csv", write);
		player.sendMessage(player1.getName() + "さんを" + args[1] + "の理由で報告しました。");
		return true;
	}
}
