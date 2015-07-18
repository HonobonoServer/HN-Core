package so.wktk.honobonoserver.hncore;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import ru.tehkode.permissions.bukkit.PermissionsEx;
import so.wktk.honobonoserver.hncore.util.Other;

public class Chat implements Listener{

	@EventHandler
	public void Kana(AsyncPlayerChatEvent event) {
		String msg = event.getMessage();
		boolean katakana = false;
		StringBuilder sb = new StringBuilder();
		if(msg.startsWith("&k")) {
			msg = msg.substring(2);
			katakana = true;
		}
		sb.append("<");
		sb.append(Other.color(PermissionsEx.getUser(event.getPlayer()).getPrefix() + "&r", null));
		sb.append(event.getPlayer().getDisplayName());
		sb.append("> ");
		sb.append(msg);
		if (!Other.isZenkaku(msg)) {
			msg = msg.replaceAll("(\"[^\"]*\")", "\t$1\t");
			List<String> lmsg = Arrays.asList(msg.split("\t"));
			sb.append("(");
			for(String m : lmsg) {
			sb.append(Other.toJP(m, katakana));
			}
			sb.append(")");
		}
		event.setFormat(sb.toString());
	}
}
