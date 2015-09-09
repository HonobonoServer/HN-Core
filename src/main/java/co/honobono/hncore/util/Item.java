package co.honobono.hncore.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

	public static ItemStack apple() {
		ItemStack myitem = new ItemStack(Material.SEEDS);
		ItemMeta im = myitem.getItemMeta();
		im.setDisplayName("§R§4Apple§R Seeds");
		myitem.setItemMeta(im);
		return myitem;
	}

	public static ItemStack flyboots() {
		ItemStack myitem = new ItemStack(Material.DIAMOND_BOOTS);
		ItemMeta im = myitem.getItemMeta();
		im.setDisplayName("§R§bFly§aBoots");
		myitem.setItemMeta(im);
		return myitem;
	}

	public static ItemStack Wallet() {
		ItemStack i = new ItemStack(Material.LEATHER);
		ItemMeta im =  i.getItemMeta();
		i.setDurability((short) 1);
		im.setDisplayName("§R§R§R§BWallet ( )");
		i.setItemMeta(im);
		return i;
	}
}
