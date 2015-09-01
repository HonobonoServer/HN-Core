package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class test implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (player == Bukkit.getPlayer("syu_chan_1005")) {
			/*
			DB db = new DB();
			db.create("test");
			Statement st = db.getStatement();
			try {
				st.executeQuery("CAREATE TABLE test(name TEXT, args TEXT)");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			*/
			player.teleport(player.getLocation().add(0, Integer.valueOf(args[0]), 0));
			player.sendMessage("実行終了");
			return true;
		} else {
			player.sendMessage("このコマンドは開発者専用です。");
			return true;
		}
	}
}
