package so.wktk.honobonoserver.hncore.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import so.wktk.honobonoserver.hncore.HNCore;

public class Train {

	private static Plugin instance = HNCore.getInstance();

	private static double x;
	private static double z;
	/**
	 * MineCartが看板の上を通り、かつなんの看板か判定します。
	 * @param loc Cartの座標を入力します。
	 * @return
	 */
	public static Map<String,Location> isTrain(Location loc) {
		if(loc.getBlockX() == x && loc.getBlockZ() == z) { return null; }
		x = loc.getBlockX(); z = loc.getBlockZ();
		loc.subtract(0, 1, 0);
		Location sign = nearBlock(loc,Material.WALL_SIGN);
		if (sign == null) { return null; }
		Sign signs = (Sign) sign.getBlock().getState();
		if(!(signs.getLine(0).equalsIgnoreCase("[HNTrain]") || signs.getLine(0).equalsIgnoreCase("[HN-Train]"))) { return null; }
		Map<String,Location> sl = new HashMap<String, Location>();
		switch (signs.getLine(1).toLowerCase()) {
		case "station":
			sl.put("Station", sign);
			return sl;
		case "destroy":
			sl.put("Destroy", sign);
			return sl;
		}
		return null;
	}

	/**
	 * Redstoneが反応し近くに看板がありかつ１行目に[HNTrain]があるかどうかを判定します。
	 * @param event BlockRedstoneEventを指定
	 * @return あった場合には看板のLocationを、ない場合にはnullを返します。
	 */
	public static Block isTrain(BlockRedstoneEvent event) {
		if(event.getOldCurrent() > event.getNewCurrent() ) { return null; }
		Location block = nearBlock(event.getBlock().getLocation() , Material.WALL_SIGN);
		if(block == null) { return null; }
		Sign sign = (Sign) block.getBlock().getState();
		if(block.getBlock().getBlockPower() < 0) { return null; }
		String[] signs = sign.getLines();
		if(!(signs[0].equalsIgnoreCase("[HNTrain]"))) { return null; }
		return block.getBlock();
	}

	/**
	 * 周囲4Blockに指定のMaterialがあるか探します
	 * @param loc 中心となるBlockの座標
	 * @param m 探したいMaterial
	 * @return 探したいMaterialのLocation 複数ある場合にはどれか一つを返します。ない場合にはnull
	 */
	public static Location nearBlock(Location loc , Material m) {
		if(loc.add(1, 0, 0).getBlock().getType() == m) {
			return loc;
		} else if(loc.subtract(2, 0, 0).getBlock().getType() == m) {
			return loc;
		} else if(loc.add(1, 0, 1).getBlock().getType() == m) {
			return loc;
		} else if(loc.subtract(0, 0, 2).getBlock().getType() == m) {
			return loc;
		} else {
			return null;
		}
	}

	/**
	 * 周囲4Blockに指定のMaterialがあるか探します
	 * @param loc 中心となるBlockの座標
	 * @param m 探したい複数のMaterial
	 * @return 探したいMaterialのLocation 複数ある場合にはどれか一つを返します。ない場合にはnull
	 */
	public static Location nearBlock(Location loc , Material[] ma) {
		Location loc2 = new Location(loc.getWorld(),loc.getX(),loc.getY(),loc.getZ(),loc.getPitch(),loc.getYaw());
		for(Material m : ma) {
			if(loc.add(1, 0, 0).getBlock().getType() == m) {
				if(loc != loc2) { return loc; }
			} else if(loc.subtract(2, 0, 0).getBlock().getType() == m) {
				if(loc != loc2) { return loc; }
			} else if(loc.add(1, 0, 1).getBlock().getType() == m) {
				if(loc != loc2) { return loc; }
			} else if(loc.subtract(0, 0, 2).getBlock().getType() == m) {
				if(loc != loc2) { return loc; }
			}
		}
		return null;
	}

	/**
	 * Spawnでの文字をEntityTypeに変換します
	 * @param list mやcなどの特定文字
	 * @return EntityTypeのListを返します
	 */
	public static List<EntityType> StoE(String list) {
		char[] clist = list.toCharArray();
		List<EntityType> Elist = new ArrayList<>();
		for (char c : clist) {
			switch (c) {
			case 'm':
				Elist.add(EntityType.MINECART);
				break;
			case 'p':
				Elist.add(EntityType.MINECART_FURNACE);
				break;
			case 's':
				Elist.add(EntityType.MINECART_CHEST);
				break;
			case 't':
				Elist.add(EntityType.MINECART_TNT);
				break;
			case 'h':
				Elist.add(EntityType.MINECART_HOPPER);
				break;
			}
		}
		return Elist;
	}

	/**
	 * MineCartsをSpawnさせます
	 * @param Facing 進ませたい方向
	 * @param loc1 Spawnさせる座標
	 * @param e SpawnさせるEntityTypeのList
	 */
	public static void spawnTrain(String Facing,Location loc1,List<EntityType> e) {
		Vector v = new Vector();
		switch(Facing) {
		case "NORTH":
			v.setZ(1);
			break;
		case "EAST":
			v.setX(-1);
			break;
		case "SOUTH":
			v.setZ(-1);
			break;
		case "WEST":
			v.setX(1);
			break;
		}
		for (EntityType ent : e) {
			 new BukkitRunnable() {
				 @Override
				 public void run() {
					 loc1.getWorld().spawnEntity(loc1, ent).setVelocity(v);
				 }
			 }.runTaskLater(instance, (int)2);
		}
	}

	public static void Station(Entity cart, Location sign) {
		String signs = ((Sign) sign.getBlock().getState()).getLine(3);
		int tick = 0;
		try{ tick = (Integer.valueOf(((Sign) sign.getBlock().getState()).getLine(2))) * 20; } catch(NumberFormatException e) {}
		Vector v1 = cart.getVelocity();
		cart.setVelocity(new Vector(0,0,0));
		if (signs.equalsIgnoreCase("left")) {
			String Facing = ((org.bukkit.material.Sign)sign.getBlock().getState().getData()).getFacing().toString();
			switch(Facing) {
			case "NORTH":
				if(v1.getX() <= 0) {
					v1.setX(v1.getX() * -1);
				}
				break;
			case "EAST":
				if(v1.getZ() >= 0) {
					v1.setZ(v1.getZ() * -1);
				}
				break;
			case "SOUTH":
				if(v1.getX() >= 0) {
					v1.setX(v1.getX() * -1);
				}
				break;
			case "WEST":
				if(v1.getZ() <= 0) {
					v1.setZ(v1.getZ() * -1);
				}
				break;
			}
		} else if (signs.equalsIgnoreCase("right")) {
			String Facing = ((org.bukkit.material.Sign)sign.getBlock().getState().getData()).getFacing().toString();
			switch(Facing) {
			case "NORTH":
				if(v1.getX() >= 0) {
					v1.setX(v1.getX() * -1);
				}
				break;
			case "EAST":
				if(v1.getZ() <= 0) {
					v1.setZ(v1.getZ() * -1);
				}
				break;
			case "SOUTH":
				if(v1.getX() <= 0) {
					v1.setX(v1.getX() * -1);
				}
				break;
			case "WEST":
				if(v1.getZ() >= 0) {
					v1.setZ(v1.getZ() * -1);
				}
				break;
			}
		} else if (signs.equalsIgnoreCase("continue")) {
		} else if (signs.equalsIgnoreCase("reverse")) {
			v1.setX(v1.getX() * -1);
			v1.setZ(v1.getZ() * -1);
		} else { return; }
		 new BukkitRunnable() {
			 @Override
			 public void run() {
				 cart.setVelocity(v1);
			 }
		 }.runTaskLater(instance, tick);
	}

	public static void Spawn(Block block) {
		Location loc = block.getLocation();
		String Facing = ((org.bukkit.material.Sign)block.getState().getData()).getFacing().toString();
		switch(Facing) {
		case "NORTH":
			loc.add(0, 0, 1.0);
			break;
		case "EAST":
			loc.subtract(0.6, 0, 0);
			break;
		case "SOUTH":
			loc.subtract(0, 0, 0.6);
			break;
		case "WEST":
			loc.add(1.0, 0, 0);
			break;
		}
		loc.add(0, 1, 0);
		Pattern p = Pattern.compile("RAIL"); Matcher match = p.matcher(loc.getBlock().getType().toString());
		if(!(match.find())) { return; }
		List<EntityType> Elist = StoE(((org.bukkit.block.Sign) block.getState()).getLine(2));
		if (!(Elist.isEmpty()))  { spawnTrain(Facing,loc,Elist); }
	}
	public static void Destroy(Entity cart) {
		 new BukkitRunnable() {
			 @Override
			 public void run() {
				 cart.remove();
			 }
		 }.runTaskLater(instance, 5);
	}
}
