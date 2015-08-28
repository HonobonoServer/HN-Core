package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class test implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(player == Bukkit.getPlayer("syu_chan_1005")) {
			Bukkit.getPlayer(args[0]).kickPlayer(args[1]);
			player.sendMessage("実行終了");
			return true;
		} else {
			player.sendMessage("このコマンドは開発者専用です。");
			return false;
		}
	}
}
