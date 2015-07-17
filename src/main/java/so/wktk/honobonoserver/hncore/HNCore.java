package so.wktk.honobonoserver.hncore;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import so.wktk.honobonoserver.hncore.util.announceEvent;

public class HNCore extends JavaPlugin {
	static Plugin instance;
	static final private Charset CONFIG_CHAREST=StandardCharsets.UTF_8;
	static FileConfiguration conf;

	@Override
	public void onEnable() {
		//ConfigをUTF8で読み込む
		saveDefaultConfig();
		String confFilePath=getDataFolder() + File.separator + "config.yml";
		try(
			Reader reader=new InputStreamReader(new FileInputStream(confFilePath),CONFIG_CHAREST)){
			conf=new YamlConfiguration();
			conf.load(reader);
		}catch(Exception e){
			System.out.println(e);
			onDisable();
		}
		//準備
		instance = this;
		getLogger().info("HN-Coreを起動しました");
		announce();
		// config読み込み
		this.saveDefaultConfig();
		// コマンドの設定
		getCommand("hnconfig").setExecutor(new hnconfig());
		getCommand("hnreload").setExecutor(new hnreload());
		getCommand("hnget").setExecutor(new hnget());
		getCommand("hntp").setExecutor(new hntp());
		getCommand("show").setExecutor(new show_hide());
		getCommand("hide").setExecutor(new show_hide());
		getCommand("mute").setExecutor(new mute());
		getCommand("unmute").setExecutor(new mute());
		getCommand("mutelist").setExecutor(new mute());
		getCommand("a").setExecutor(new adminchat());
		getCommand("ir").setExecutor(new itemremove());
		getCommand("al").setExecutor(new itemremove());
		getCommand("freeze").setExecutor(new freeze());
		getCommand("unfreeze").setExecutor(new freeze());
		getCommand("swp").setExecutor(new wp());
		getCommand("twp").setExecutor(new wp());
		getCommand("lwp").setExecutor(new wp());
		getCommand("home").setExecutor(new home());
		getCommand("hnannounce").setExecutor(new announce());

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
	}

	@Override
	public void onDisable() {
		getLogger().info("HN-Coreを終了しました");
	}

	public static Plugin getInstance() {
		return instance;
	}

	public static FileConfiguration getConf() {
		return conf;
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
		}, instance.getConfig().getInt("announcement.interval") *20);
	}
}