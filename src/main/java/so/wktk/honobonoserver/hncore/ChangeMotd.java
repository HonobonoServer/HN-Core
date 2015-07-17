package so.wktk.honobonoserver.hncore;

import java.util.List;
import java.util.Random;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

import so.wktk.honobonoserver.hncore.util.Other;

public class ChangeMotd implements Listener{
	private Plugin instance = HNCore.getInstance();

	@EventHandler(ignoreCancelled = true)
	public void onServerListPing(ServerListPingEvent event){
		List<String> Motd = instance.getConfig().getStringList("Motd");
		String motd = Motd.get(new Random().nextInt(Motd.size()));
		if (!(motd.length() == 0)) {
			motd = Other.color(motd, null);
			event.setMotd(motd);
		}
	}
}
