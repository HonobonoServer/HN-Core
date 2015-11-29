package co.honobono.hncore.util;

import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class FoodItemStack {
	private ItemStack Item;
	private int FoodLevel;
	private List<PotionEffect> potions;

	public FoodItemStack(ItemStack item, int Foodlevel, List<PotionEffect> p) {
		this.Item = item;
		this.FoodLevel = Foodlevel;
		this.potions = p;
	}

	@Override
	public String toString() {
		return "FoodItemStack [Item=" + Item + ", FoodLevel=" + FoodLevel + ", potions=" + potions + "]";
	}

	public ItemStack getItem() {
		return Item;
	}

	public int getFoodLevel() {
		return FoodLevel;
	}

	public List<PotionEffect> getPotions() {
		return potions;
	}
}
