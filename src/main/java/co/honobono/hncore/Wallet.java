package co.honobono.hncore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class Wallet implements Listener{
	private static Plugin instance = HNCore.getInstance();
	private static Economy econ = null;
	static {
		RegisteredServiceProvider<Economy> rsp = instance.getServer().getServicesManager().getRegistration(Economy.class);
		econ = rsp.getProvider();
	}
	private static boolean found = false;
	static {
		if (instance.getServer().getPluginManager().getPlugin("Vault") != null) {
			found = true;
		}
	}

	public static String getMoney(Player player) {
		if(!found) {
			return "Vaultを導入してください。";
		}
		OfflinePlayer offp = Bukkit.getOfflinePlayer(player.getUniqueId());
		return String.valueOf(econ.getBalance(offp) + econ.currencyNameSingular());
	}

	public static String getMoney(OfflinePlayer player) {
		if(!found) {
			return "Vaultを導入してください。";
		}
		return String.valueOf(econ.getBalance(player) + econ.currencyNameSingular());
	}

	@EventHandler
	public void UpdateWallet(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			return;
		}
		ItemStack i = event.getItem();
		if(i == null) {
			return;
		}
		if(i.getType() != Material.LEATHER && i.getDurability() != (short) 1) {
			return;
		}
		ItemMeta im = i.getItemMeta();
		if(im.getDisplayName() == null) {
			return;
		}
		if(!im.getDisplayName().startsWith("§R§R§R§BWallet")) {
			return;
		}
		im.setDisplayName("§R§R§R§BWallet (" + Wallet.getMoney(player) + ")");
		i.setItemMeta(im);
		player.setItemInHand(i);
	}
}
