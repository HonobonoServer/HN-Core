package co.honobono.hncore;

import java.io.IOException;

import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import co.honobono.hncore.constructor.Config;
import co.honobono.hncore.util.Item;

public class HNCore extends JavaPlugin {
	static Plugin instance;
	private PluginManager pm = getServer().getPluginManager();
	public static Config Inventory = null;

	@Override
	public void onEnable() {
		// 準備
		instance = this;
		// config読み込み
		try {
			Inventory = new Config("Inventory.yml", this);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		this.saveDefaultConfig();
		// コマンドの設定
		getCommand("hnget").setExecutor(new Get());
		getCommand("show").setExecutor(new show_hide());
		getCommand("hide").setExecutor(new show_hide());
		getCommand("mute").setExecutor(new mute());
		getCommand("remove").setExecutor(new ItemRemove());
		getCommand("freeze").setExecutor(new freeze());
		getCommand("unfreeze").setExecutor(new freeze());
		getCommand("report").setExecutor(new report());
		getCommand("hnlook").setExecutor(new look());
		getCommand("home").setExecutor(new home());
		// getCommand("food").setExecutor(new AddFood());
		getCommand("a").setExecutor(new AdminChat());
		// getCommand("test").setExecutor(new test());

		// Listener
		pm.registerEvents(new Get(), this);
		// pm.registerEvents(new elevator(), this); //
		pm.registerEvents(new LoginMessage(), this);
		pm.registerEvents(new ChangeMotd(), this);
		pm.registerEvents(new freeze(), this);
		// pm.registerEvents(new Chat(), this); //
		pm.registerEvents(new ShowCommand(), this);
		pm.registerEvents(new PocketItem(), this);
		// pm.registerEvents(new AddFood(), this);
		pm.registerEvents(new AdminChat(), this);
		pm.registerEvents(new Boat(), this);
		pm.registerEvents(new SignView(), this);
		pm.registerEvents(new test(), this);

		ShapedRecipe workbench = new ShapedRecipe(Item.WorkBench()).shape(new String[] { "AW", "SA" })
				.setIngredient('W', Material.WORKBENCH).setIngredient('S', Material.STICK);
		this.getServer().addRecipe(workbench);
	}

	@Override
	public void onDisable() {
		getLogger().info("HN-Coreを終了しました");
	}

	public static Plugin getInstance() {
		return instance;
	}
}