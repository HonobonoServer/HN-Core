package co.honobono.hncore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class test implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		long date = player.getLastPlayed();
		Calendar cl = Calendar.getInstance();
		cl.setTimeInMillis(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		player.sendMessage("あなたの最終ログインは" + sdf.format(cl.getTime()) + "です");
		return true;
		/*
		if (player == Bukkit.getPlayer("syu_chan_1005")) {
			DB db = new DB();
			db.create("test");
			Statement st = db.getStatement();
			try {
				st.executeQuery("CAREATE TABLE test(name TEXT, args TEXT)");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			player.sendMessage("実行終了");
			return true;
		} else {
			player.sendMessage("このコマンドは開発者専用です。");
			return true;
		}
		*/
	}
}
