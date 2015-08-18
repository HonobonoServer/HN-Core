package co.honobono.hncore;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import co.honobono.hncore.util.CustomConfig;
import co.honobono.hncore.util.announceEvent;

public class HNCore extends JavaPlugin {
	static Plugin instance;
	static CustomConfig cWay;
	static CustomConfig cLight;

	@Override
	public void onEnable() {
		//準備
		instance = this;
		getLogger().info("HN-Coreを起動しました");
		announce();
		// config読み込み
		this.saveDefaultConfig();
		CustomConfig cLang = new CustomConfig("lang.yml", instance);
		cWay = new CustomConfig("waypoint.yml", instance);
		cLight = new CustomConfig("light.yml", instance);
		try{
			cLang.create();
			cWay.create();
			cLight.create();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// コマンドの設定
		getCommand("hnconfig").setExecutor(new hnconfig());
		getCommand("hnreload").setExecutor(new hnreload());
		getCommand("hnget").setExecutor(new hnget());
		getCommand("hntp").setExecutor(new hntp());
		getCommand("show").setExecutor(new show_hide());
		getCommand("hide").setExecutor(new show_hide());
		getCommand("mute").setExecutor(new mute());
		getCommand("a").setExecutor(new adminchat());
		getCommand("ir").setExecutor(new itemremove());
		getCommand("al").setExecutor(new itemremove());
		getCommand("freeze").setExecutor(new freeze());
		getCommand("unfreeze").setExecutor(new freeze());
		getCommand("home").setExecutor(new home());
		getCommand("hnannounce").setExecutor(new announce());
		getCommand("test").setExecutor(new test());
		getCommand("wp").setExecutor(new waypoint());
		getCommand("light").setExecutor(new light());

		// Listener
		getServer().getPluginManager().registerEvents(new blockreplace(), this);
		getServer().getPluginManager().registerEvents(new elevator(), this);
		getServer().getPluginManager().registerEvents(new LoginMessage(), this);
		getServer().getPluginManager().registerEvents(new ChangeMotd(), this);
		getServer().getPluginManager().registerEvents(new adminchat(), this);
		getServer().getPluginManager().registerEvents(new sign(), this);
		getServer().getPluginManager().registerEvents(new freeze(), this);
		getServer().getPluginManager().registerEvents(new Chat(), this);
		getServer().getPluginManager().registerEvents(new announce(), this);
		getServer().getPluginManager().registerEvents(new ShowCommand(), this);
		getServer().getPluginManager().registerEvents(new light(), this);
	}


	@Override
	public void onDisable() {
		getLogger().info("HN-Coreを終了しました");
	}

	public static Plugin getInstance() {
		return instance;
	}

	public void announce() {
		announceEvent event = new announceEvent();
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.getServer().getPluginManager().callEvent(event);
				announce();
			}
		}, instance.getConfig().getInt("announcement.interval") * 20);
	}

	public static CustomConfig getWaypoint() {
		return cWay;
	}

	public static CustomConfig getLight() {
		return cLight;
	}
}