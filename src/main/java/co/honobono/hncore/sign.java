package co.honobono.hncore;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;

import co.honobono.hncore.util.Train;

public class sign implements Listener{

	@EventHandler
	public void redstone(BlockRedstoneEvent event) {
		Block block = Train.isTrain(event);
		if(block == null) { return; }
		Sign sign = (Sign)block.getState();
		switch (sign.getLine(1).toLowerCase()) {
		case "spawn":
			Train.Spawn(block);
			break;
		}
	}


	@EventHandler
	public void Train(VehicleMoveEvent event) {
		Map<String, Location> line = Train.isTrain(event.getVehicle().getLocation());
		if (line == null) { return; }
		if (line.containsKey("Station")){
			Train.Station(event.getVehicle(),line.get("Station"));
		} else if (line.containsKey("Destroy")) {
			Train.Destroy(event.getVehicle());
		}
	}
}
