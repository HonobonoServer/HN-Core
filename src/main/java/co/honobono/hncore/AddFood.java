package co.honobono.hncore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
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

@SuppressWarnings("unchecked")
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
		Map<String, List<Map<String, Object>>> map = (Map<String, List<Map<String, Object>>>) f.get("Food");
		for(Map.Entry<String, List<Map<String, Object>>> e : map.entrySet()) {
			for(Map<String, Object> map1 : e.getValue()) {
				ItemStack item = Build(e.getKey(), map1.get("Meta"));
				int food = (int)map1.get("Food");
				List<PotionEffect> p = new ArrayList<>();
				if(map1.get("Effect") != null) {
					for(String s : (List<String>)map1.get("Effect")) {
						String[] s1 = s.split(":");
						p.add(new PotionEffect(PotionEffectType.getByName(s1[0]), Integer.valueOf(s1[1]), Integer.valueOf(s1[2])));
					}
				}
				cfood.add(new FoodItemStack(item, food, p));
				Bukkit.broadcastMessage("追加！");
			}
		}
	}
	private static ItemStack Build(String material, Object Durability) {
		ItemStack i = new ItemStack(Material.getMaterial(material));
		i.setDurability((short) Durability);
		return i;
	}
	private FoodItemStack getFood(ItemStack i) {
		for(FoodItemStack fi : cfood) {
			if(fi.getItem() == i) return fi;
		}
		return null;
	}

	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
		ItemStack im = event.getItem();
		FoodItemStack food1 = getFood(im);
		if(food1 == null) return;
		Player player = event.getPlayer();
		player.setFoodLevel(player.getFoodLevel() + (food1.getFoodLevel() - food.get(im.getType())));
		player.addPotionEffects(food1.getPotions());
	}
}
