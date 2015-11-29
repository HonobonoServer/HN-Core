package co.honobono.hncore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import co.honobono.hncore.util.FoodItemStack;

public class AddFood implements Listener {
	// private static Plugin instance = HNCore.getInstance();

	private static Map<Material, Integer> food = new HashMap<>(); {
		food.put(Material.APPLE, 4);
		food.put(Material.BAKED_POTATO, 5);
		food.put(Material.BREAD, 5);
		food.put(Material.CAKE, 2);
		food.put(Material.CARROT, 3);
		food.put(Material.COOKED_CHICKEN, 6);
		food.put(Material.COOKED_MUTTON, 6);
		food.put(Material.GRILLED_PORK, 8);
		food.put(Material.COOKED_RABBIT, 5);
		food.put(Material.COOKIE, 2);
		food.put(Material.GOLDEN_CARROT, 6);
		food.put(Material.MELON, 2);
		food.put(Material.MUSHROOM_SOUP, 6);
		food.put(Material.POISONOUS_POTATO, 2);
		food.put(Material.POTATO, 1);
		food.put(Material.PUMPKIN_PIE, 8);
		food.put(Material.RABBIT_STEW, 10);
		food.put(Material.RAW_BEEF, 3);
		food.put(Material.RAW_CHICKEN, 2);
		food.put(Material.MUTTON, 2);
		food.put(Material.PORK, 3);
		food.put(Material.RABBIT, 3);
		food.put(Material.ROTTEN_FLESH, 4);
		food.put(Material.SPIDER_EYE, 2);
		food.put(Material.COOKED_BEEF, 8);
	}

	public static List<FoodItemStack> cfood = new ArrayList<>();{
		FileConfiguration f = HNCore.Food.get();
		for(Map.Entry<Material, Integer> e : food.entrySet()) {
			for(Map<?, ?> m : f.getMapList("Food." + e.getKey().name())) {
				ItemStack i = Build(e.getKey(), m.get("Meta"));
				int foodlevel = ((Integer)m.get("Food"));
				List<PotionEffect> p = new ArrayList<>();
				@SuppressWarnings("unchecked")
				List<String> s = (List<String>)m.get("Effect");
				if(s != null) {
				for(String potion : s) {
					String[] s1 = potion.split(":");
					p.add(new PotionEffect(PotionEffectType.getByName(s1[0]), Integer.valueOf(s1[1]), Integer.valueOf(s1[2])));
				}
				} else {
					s = new ArrayList<>();
				}
				cfood.add(new FoodItemStack(i, foodlevel, p));
			}
		}
	}
	private static ItemStack Build(Material material, Object Durability) {
		ItemStack i = new ItemStack(material);
		i.setDurability((Durability instanceof Integer) ? ((Integer)Durability).shortValue() : 0);
		return i;
	}
	private FoodItemStack getFood(ItemStack i) {
		for(FoodItemStack fi : cfood) if(fi.getItem().getType() == i.getType() && fi.getItem().getDurability() == i.getDurability()) return fi;
		return null;
	}

	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
		ItemStack im = event.getItem();
		FoodItemStack food1 = getFood(im);
		if(food1 == null) return;
		Player player = event.getPlayer();
		int pluslevel = food1.getFoodLevel() - food.get(im.getType());
		player.setFoodLevel(player.getFoodLevel() + pluslevel);
		player.addPotionEffects(food1.getPotions());
	}
}
