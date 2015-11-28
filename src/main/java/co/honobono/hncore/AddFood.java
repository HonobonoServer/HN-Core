package co.honobono.hncore;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class AddFood implements Listener {
	private static Plugin instance = HNCore.getInstance();

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

	private static Map<ItemStack, Integer> cfood = new HashMap<>();{
		String s1[];
		for(String str : instance.getConfig().getStringList("Food")) {
			s1 = str.split(":");
			cfood.put(Build(s1[0], s1[1]), Integer.valueOf(s1[2]));
		}
	}

	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
		ItemStack im = event.getItem();
		if(!cfood.containsKey(im)) return;
		Player player = event.getPlayer();
		player.setFoodLevel(player.getFoodLevel() + (cfood.get(im) - food.get(im.getType())));
	}

	private static ItemStack Build(String material, String Durability) {
		ItemStack i = new ItemStack(Material.getMaterial(material));
		i.setDurability(Short.valueOf(Durability));
		return i;
	}
}
