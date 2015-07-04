package so.wktk.honobonoserver.hncore;

import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.Plugin;

import so.wktk.honobonoserver.hncore.util.Other;

public class ChangeMotd implements Listener{
	private Plugin instance = HNCore.getInstance();

	@EventHandler(ignoreCancelled = true)
	public void onServerListPing(final ServerListPingEvent event){
		List<String> Motd = instance.getConfig().getStringList("Motd");
		String motd = Motd.get(new java.util.Random().nextInt(Motd.size()));
		if (!(motd.length() == 0)) {
			motd = Other.figuration(motd, null);
			event.setMotd(motd);
		}
	}
}
