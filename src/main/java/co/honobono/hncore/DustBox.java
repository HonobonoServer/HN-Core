package co.honobono.hncore;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import co.honobono.hncore.util.Other;

public class DustBox implements Listener{

	@EventHandler
	public void clear(InventoryOpenEvent event) {
		Inventory inv = event.getInventory();
		if(!(inv.getHolder() instanceof BlockState)) {
			return;
		}
		Block block = ((BlockState)inv.getHolder()).getBlock();
		if(block.getType() != Material.CHEST) {
			return;
		}
		if(Relative(block)) {
			inv.clear();
		}
	}

	private static boolean Relative(Block block) {
		BlockFace bf = BlockFace.UP;
		Block b = block.getRelative(bf);
		if(is(b)) {
			return true;
		}
		bf = BlockFace.EAST;
		b = block.getRelative(bf);
		if(is(b)) {
			return true;
		}
		bf = BlockFace.NORTH;
		b = block.getRelative(bf);
		if(is(b)) {
			return true;
		}
		bf = BlockFace.SOUTH;
		b = block.getRelative(bf);
		if(is(b)) {
			return true;
		}
		bf = BlockFace.WEST;
		b = block.getRelative(bf);
		if(is(b)) {
			return true;
		}
		return false;
	}

	private static boolean is(Block b) {
		if(Other.include(b.getType(), Material.SIGN, Material.SIGN_POST, Material.WALL_SIGN)) {
			for(String line : ((Sign)b.getState()).getLines()) {
				if(line.equalsIgnoreCase("[DustBox]")) {
					return true;
				}
			}
		}
		return false;
	}
}
