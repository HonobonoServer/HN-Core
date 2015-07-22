package so.wktk.honobonoserver.hncore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import so.wktk.honobonoserver.hncore.util.announceEvent;

public class HNCore extends JavaPlugin {
	static Plugin instance;

	@Override
	public void onEnable() {
		// 準備
		instance = this;
		getLogger().info("HN-Coreを起動しました");
		announce();
		// config読み込み
		this.saveDefaultConfig();
		/*
		try {
			new File(instance.getDataFolder(), "PortableChest.yml").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		//config.create("lang.yml");
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
		getCommand("swp").setExecutor(new wp());
		getCommand("twp").setExecutor(new wp());
		getCommand("lwp").setExecutor(new wp());
		getCommand("home").setExecutor(new home());
		getCommand("hnannounce").setExecutor(new announce());
		//getCommand("hnopen").setExecutor(new PortableItems());

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
		//getServer().getPluginManager().registerEvents(new PortableItems(), this);

		/*
		ShapedRecipe pocketcrafter = new ShapedRecipe(Item.PocketCrafter()).shape(new String[] { "*" }).setIngredient('*', Material.WORKBENCH);
		this.getServer().addRecipe(pocketcrafter);
		ShapedRecipe pocketanvil = new ShapedRecipe(Item.PocketAnvil()).shape(new String[] { "*" }).setIngredient('*', Material.ANVIL);
		this.getServer().addRecipe(pocketanvil);
		ShapedRecipe pocketenchanter = new ShapedRecipe(Item.PocketEnchanter()).shape(new String[] { "*" }).setIngredient('*', Material.ENCHANTMENT_TABLE);
		this.getServer().addRecipe(pocketenchanter);
		ShapedRecipe pocketfurnace = new ShapedRecipe(Item.PocketFurnace()).shape(new String[] { "*" }).setIngredient('*', Material.FURNACE);
		this.getServer().addRecipe(pocketfurnace);
		ShapedRecipe pocketchest = new ShapedRecipe(Item.PortableChest()).shape(new String[] { "*" }).setIngredient('*', Material.CHEST);
		this.getServer().addRecipe(pocketchest);
		*/
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
}