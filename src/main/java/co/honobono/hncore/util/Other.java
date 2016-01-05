package co.honobono.hncore.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import co.honobono.hncore.HNCore;

public class Other {
	private static Plugin instance = HNCore.getInstance();

	/**
	 * 現在時間を日本時間で出力します
	 *
	 * @return String - 現在時間を日本時間で出力します
	 */
	public static String stamp() {
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String time = sdf1.format(date);
		return time;
	}

	/**
	 * ファイルに追記します
	 *
	 * @param fl
	 *            書き込むファイルを指定します。
	 * @param csv
	 *            書き込む内容を指定します。
	 */
	public static void filewrite(String filename, String data) {
		try {
			File fl = new File(instance.getDataFolder(), filename);
			fl.createNewFile();
			FileWriter fw = new FileWriter(fl, fl.exists());
			fw.write(data);
			fw.close();
		} catch (IOException e) {
		}
	}

	/**
	 * &(0-9a-fk-or)をBukkit向けに変更します。
	 *
	 * @param text
	 *            置き換える文章を指定します。
	 * @param player
	 *            playerがあった場合、playerより名前を取得後置き換えます。 playerがnullの場合には変換しません。
	 * @return &(0-9a-fk-or) と playerを置き換えたメッセージを返します。
	 */
	public static String color(String text, Player player) {
		if (player != null) text = text.replaceAll("<player>", player.getName());
		return ChatColor.translateAlternateColorCodes('&', text);
	}

	/**
	 * 文字列内に2Byte文字があるかどうか判定します
	 *
	 * @param text
	 *            元の字列
	 * @return 全角:true 半角:false
	 */
	public static boolean isZenkaku(String text) {
		byte[] bytes = text.getBytes();
		if (text.length() != bytes.length) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * doubleを小数点一桁まで切り捨てます
	 *
	 * @param data
	 *            もとの小数
	 * @return 小数点第一位まで切り捨てたもの
	 */
	public static double cut(double data) {
		BigDecimal a = new BigDecimal(data);
		BigDecimal a2 = a.setScale(1, BigDecimal.ROUND_DOWN);
		double a3 = a2.doubleValue();
		a3 = ((int) (a3 * 10)) % 10;
		return a3 / 10;
	}

	/**
	 * 文字列にmatchが含まれているか確かめます
	 *
	 * @param data
	 *            もとの文字列
	 * @param match
	 *            詮索する文字列
	 * @return あった場合にはtrue,ない場合にはfalseを返します
	 */
	public static boolean Scut(String data, String match) {
		String Sdata = String.valueOf(data);
		System.out.println(Sdata);
		Matcher m = Pattern.compile(match).matcher(Sdata);
		return m.find();
	}

	/**
	 * Inventoryをファイルに保存します
	 *
	 * @param inv
	 *            保存するインベントリーです
	 * @param PlayerName
	 *            インベントリーの持ち主
	 * @throws IOException
	 */
	public static void saveInventory(Inventory inv, UUID UUID, File path) throws IOException {
		path = new File(path, UUID.toString() + ".yml");
		path.createNewFile();
		YamlConfiguration c = YamlConfiguration.loadConfiguration(path);
		c.set("contents", inv.getContents());
		c.save(path);
	}

	/**
	 * ファイルに保存したInventoryを復元します
	 *
	 * @param PlayerName
	 *            インベントリーの持ち主
	 * @return 復元したInventoryです
	 */
	@SuppressWarnings("unchecked")
	public static Inventory restoreInventory(UUID UUID, File path) {
		try {
			path = new File(path, UUID.toString() + ".yml");
			YamlConfiguration c = YamlConfiguration.loadConfiguration(path);
			List<ItemStack> items = (List<ItemStack>) c.get("contents");
			ItemStack[] content = items.toArray(new ItemStack[0]);
			Inventory inv = Bukkit.getServer().createInventory(null, InventoryType.CHEST,
					Bukkit.getPlayer(UUID).getName());
			inv.setContents(content);
			path.delete();
			return inv;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Location.toStringした値をLocationに戻します。
	 *
	 * @param s
	 *            toStringした値
	 * @return 復元したLocation
	 */
	public static Location toLocation(String s) {
		String[] loc = s.split(",");
		World w = Bukkit.getWorld(loc[0].substring(31, loc[0].length() - 1));
		double x = Double.valueOf(loc[1].substring(2, loc[1].length()));
		double y = Double.valueOf(loc[2].substring(2, loc[2].length()));
		double z = Double.valueOf(loc[3].substring(2, loc[3].length()));
		float pitch = Float.valueOf(loc[4].substring(6, loc[4].length()));
		float yaw = Float.valueOf(loc[5].substring(4, loc[5].length() - 1));
		Location loc1 = new Location(w, x, y, z, yaw, pitch);
		return loc1;
	}

	public static boolean include(Object o1, Object... obj) {
		for (Object o : obj) {
			if (o1.equals(o))
				return true;
		}
		return false;
	}

	private static Map<String, String> achievements = new HashMap<String, String>();

	static {
		achievements.put("ACQUIRE_IRON", "金属を手に入れる");
		achievements.put("BAKE_CAKE", "the cake is a lie");
		achievements.put("BOOKCASE", "司書");
		achievements.put("BREED_COW", "種の繁栄");
		achievements.put("BREW_POTION", "町の薬屋さん");
		achievements.put("BUILD_BETTER_PICKAXE", "アップグレード");
		achievements.put("BUILD_FURNACE", "ホットトピック");
		achievements.put("BUILD_HOE", "いざ農業！");
		achievements.put("BUILD_PICKAXE", "いざ採掘！");
		achievements.put("BUILD_SWORD", "いざ突撃！");
		achievements.put("BUILD_WORKBENCH", "土台作り");
		achievements.put("COOK_FISH", "美味しい魚");
		achievements.put("DIAMONDS_TO_YOU", "ダイアモンドをあなたに！");
		achievements.put("ENCHANTMENTS", "エンチャントの使い手");
		achievements.put("END_PORTAL", "おしまい？");
		achievements.put("EXPLORE_ALL_BIOMES", "冒険の時間");
		achievements.put("FLY_PIG", "ブタさんの飛ぶ頃に");
		achievements.put("FULL_BEACON", "ビーコン使い");
		achievements.put("GET_BLAZE_ROD", "炎の中へ");
		achievements.put("GET_DIAMONDS", "ダイアモンド！");
		achievements.put("GHAST_RETURN", "宛先不明");
		achievements.put("KILL_COW", "牛転がし");
		achievements.put("KILL_ENEMY", "モンスターハンター");
		achievements.put("KILL_WITHER", "はじまり。");
		achievements.put("MAKE_BREAD", "パンを焼く");
		achievements.put("MINE_WOOD", "木を手に入れる");
		achievements.put("NETHER_PORTAL", "さらなる深みへ");
		achievements.put("ON_A_RAIL", "線路の上で");
		achievements.put("OPEN_INVENTORY", "所持品の確認");
		achievements.put("OVERKILL", "オーバーキル");
		achievements.put("OVERPOWERED", "圧倒的な力");
		achievements.put("SNIPE_SKELETON", "スナイパー対決");
		achievements.put("SPAWN_WITHER", "はじまり？");
		achievements.put("THE_END", "おしまい。");
	}

	public static String AchievementtoJP(Achievement achieve) {
		return achievements.get(achieve.name());
	}

	/**
	 * Stringの配列からItemStackに変換します。
	 * @param a Material.toString():Meta:Amountに整形された文字列
	 * @return アイテムリスト
	 */
	public static List<ItemStack> toItemStack(List<String> a) {
		List<ItemStack> b = new ArrayList<>();
		for(String c : a) {
			String[] d = c.split(":");
			Material e = Material.getMaterial(d[0]);
			Short f = Short.valueOf(d[1]);
			int g = Integer.valueOf(d[2]);
			ItemStack h = new ItemStack(e);
			h.setDurability(f);
			h.setAmount(g);
			b.add(h);
		}
		return b;
	}

	/**
	 * Class typeの型にObject objをcastします
	 * @param obj
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T as(Object obj, Class<T> type) {
		  return (type.isInstance(obj) ? (T)obj : null);
	}
}