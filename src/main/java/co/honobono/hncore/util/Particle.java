package co.honobono.hncore.util;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumParticle;

public class Particle {

	/**
	 * 自然に消えるパーティクルを出します
	 * @param player それを見せるPlayer
	 * @param particle パーティクルの種類
	 * @param loc 出すLocation
	 * @param num どれだけの量を出すか
	 */
	public static void normal(Player player , EnumParticle particle , Location loc , int num) {
		((CraftWorld) player.getWorld()).getHandle().a(particle, loc.getX(), loc.getY()+2, loc.getZ(), num, 0.32D, 0.32D, 0.32D, 0);
	}

	/**
	 * 円形のパーティクルを表示します
	 * @param player 見せるPlayer
	 * @param loc 出すLocation
	 * @param particle パーティクルの種類
	 * @param Radius locを中心とした半径
	 * @param height Y座標に+する数
	 */
	public static void circle(Player player,Location loc,EnumParticle particle,double Radius,double height) {
		for(float i=0;i<360;i=(float) (i+0.5)){
			((CraftWorld) player.getWorld()).getHandle().a(particle , loc.getX()+Math.sin(Math.toRadians(i))*Radius, loc.getY()+height, loc.getZ()+Math.cos(Math.toRadians(i))*Radius, 1, 0, 0, 0, 0);
		}
	}

	/**
	 * 直線のパーティクルを出します
	 * @param player 見せるPlayer
	 * @param particle 出すパーティクルの種類
	 * @param loc1 直線の起点
	 * @param loc2 直線の終点
	 */
	public static void beam(Player player,EnumParticle particle,Location loc1,Location loc2) {
		Location l= new Location(loc1.getWorld(),loc1.getX(),loc1.getY(),loc1.getZ());
		loc1.add(0,1.2,0);
		loc2.add(0,0.5,0);
		l.add(0,1.2,0);
		int _distance=(int) loc2.distance(loc1)*20;
		loc2.subtract(loc1.getX(), loc1.getY(), loc1.getZ());
		double d=1D/_distance;
		double x=loc2.getX()*d;
		double y=loc2.getY()*d;
		double z=loc2.getZ()*d;
		for(int i=0;i<_distance;i++){
			((CraftWorld) player.getWorld()).getHandle().a(particle, l.getX()+x*i, l.getY()+y*i,l.getZ()+z*i, 10, 0,0,0,0);
		}
	}
}
