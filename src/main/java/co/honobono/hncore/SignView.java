package co.honobono.hncore;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import co.honobono.hncore.util.Other;

public class SignView implements Listener{

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if(!Other.include(event.getAction(), Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return;
		Block b = event.getClickedBlock(); if(b == null || b.getType().name().indexOf("SIGN") == -1) return;
		Player player = event.getPlayer();
		Sign s = (Sign) b.getState();
		String[] str = s.getLines();
		for(int i = 0; i < str.length; i++) {
			player.sendMessage(i+1 + "行目: " +Other.color(str[i], null) );
		}
	}
}
