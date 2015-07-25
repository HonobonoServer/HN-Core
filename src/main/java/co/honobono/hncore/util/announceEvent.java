package co.honobono.hncore.util;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class announceEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	public announceEvent() {
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
