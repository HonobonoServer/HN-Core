package co.honobono.hncore.util;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.HNCore;

public class announceEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private Plugin instance = HNCore.getInstance();
	private boolean cancelled = false;
	private String msg = "";
	private int count = 0;

	public announceEvent() {
		List<String> messages = instance.getConfig().getStringList("announcement.messages");
		if (instance.getConfig().getBoolean("announcement.random") == true) {
			count = new Random().nextInt(messages.size());
		} else {
			count++;
			if (messages.size() <= count) {
				count = 0;
			}
		}
		msg = Other.color(instance.getConfig().getString("announcement.prefix") + messages.get(count), null);
		if(!cancelled) {
			Bukkit.broadcastMessage(msg);
		}
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancel) {
		cancelled = cancel;
	}

	public String getMessage() {
		return msg;
	}

	public void setMessage(String message) {
		msg = message;
	}
}
