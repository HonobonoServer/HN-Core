package co.honobono.hncore;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import co.honobono.hncore.util.Other;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Chat implements Listener{

	@EventHandler
	public void Kana(AsyncPlayerChatEvent event) {
		String msg = event.getMessage();
		boolean katakana = false;
		boolean ime = false;
		StringBuilder sb = new StringBuilder();
		if(msg.startsWith("&k")) {
			msg = msg.substring(2);
			katakana = true;
		}
		if(msg.startsWith("&i")) {
			msg = msg.substring(2);
			ime = true;
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
			sb.append(Other.toJP(m, katakana, ime));
			}
			sb.append(")");
		}
		event.setFormat(sb.toString());
	}
}
