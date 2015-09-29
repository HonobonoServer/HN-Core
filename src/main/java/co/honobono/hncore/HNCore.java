package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import co.honobono.hncore.util.Item;
import co.honobono.hncore.util.announceEvent;

public class HNCore extends JavaPlugin {
	static Plugin instance;
	private PluginManager pm = getServer().getPluginManager();

	@Override
	public void onEnable() {
		// 準備
		instance = this;
		getLogger().info("HN-Coreを起動しました");
		Twitters.runServer();
		announce();
		// config読み込み
		this.saveDefaultConfig();
		// コマンドの設定
		getCommand("hnget").setExecutor(new hnget());
		getCommand("show").setExecutor(new show_hide());
		getCommand("hide").setExecutor(new show_hide());
		getCommand("mute").setExecutor(new mute());
		getCommand("a").setExecutor(new adminchat());
		getCommand("al").setExecutor(new itemremove());
		getCommand("freeze").setExecutor(new freeze());
		getCommand("unfreeze").setExecutor(new freeze());
		getCommand("hnannounce").setExecutor(new announce());
		getCommand("report").setExecutor(new report());
		getCommand("hnlook").setExecutor(new look());
		getCommand("home").setExecutor(new home());
		getCommand("tweet").setExecutor(new Twitters());
		getCommand("test").setExecutor(new test());
		// Listener
		pm.registerEvents(new hnget(), this);
		pm.registerEvents(new elevator(), this);
		pm.registerEvents(new LoginMessage(), this);
		pm.registerEvents(new ChangeMotd(), this);
		pm.registerEvents(new adminchat(), this);
		pm.registerEvents(new sign(), this);
		pm.registerEvents(new freeze(), this);
		pm.registerEvents(new Chat(), this);
		pm.registerEvents(new ShowCommand(), this);
		pm.registerEvents(new Wallet(), this);
		pm.registerEvents(new test(), this);
		pm.registerEvents(new Twitters(), this);
		pm.registerEvents(new fishing(), this);
		pm.registerEvents(new home(), this);
		pm.registerEvents(new Bomb(), this);
		pm.registerEvents(new DustBox(), this);
		pm.registerEvents(new PocketItem(), this);

		// 財布レシピの追加
		ShapedRecipe wallet = new ShapedRecipe(Item.Wallet()).shape(new String[] { "***", "*^*", "***" })
				.setIngredient('*', Material.LEATHER).setIngredient('^', Material.GOLD_NUGGET);
		instance.getServer().addRecipe(wallet);
		ShapedRecipe workbench = new ShapedRecipe(Item.WorkBench()).shape(new String[] { "AW", "SA" })
				.setIngredient('W', Material.WORKBENCH).setIngredient('S', Material.STICK);
		this.getServer().addRecipe(workbench);
	}

	@Override
	public void onDisable() {
		Twitters.closeServer();
		getLogger().info("HN-Coreを終了しました");
	}

	public void announce() {
		announceEvent event = new announceEvent();
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(this, new Runnable() {
			@Override
			public void run() {
				Bukkit.getServer().getPluginManager().callEvent(event);
			}
		}, instance.getConfig().getInt("announcement.interval") * 20);
	}

	public static Plugin getInstance() {
		return instance;
	}
}