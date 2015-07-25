package co.honobono.hncore.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;

public class Title {

	/**
	 * タイトルを送信します
	 * @param player 表示するPlayer
	 * @param fadeInTick 表示までの時間を[tick]で指定します
	 * @param stayTick 表示中の時間を[tick]で指定します
	 * @param fadeOutTick 表示終了までの時間を[tick]で指定します
	 * @param MainTitle 大きく表示される文字列を指定します
	 * @param SubTitle 下に表示される文字列を指定します
	 */
	public static void sendFullTitle(Player player, int fadeInTick, int stayTick, int fadeOutTick, String MainTitle, String SubTitle) {
		sendTitle(player, fadeInTick, stayTick, fadeOutTick, MainTitle, SubTitle);
	}

	/**
	 * 大きいタイトルを送信します
	 * @param player 表示するPlayer
	 * @param fadeInTick 表示までの時間を[tick]で指定します
	 * @param stayTick 表示中の時間を[tick]で指定します
	 * @param fadeOutTick 表示終了までの時間を[tick]で指定します
	 * @param MainTitle 大きく表示される文字列を指定します
	 */
	public static void sendMainTitle(Player player, int fadeInTick, int stayTick, int fadeOutTick, String MainTitle) {
		sendTitle(player, fadeInTick, stayTick, fadeOutTick, MainTitle, null);
	}

	/**
	 * 小さいタイトルを送信します
	 * @param player 表示するPlayer
	 * @param fadeInTick 表示までの時間を[tick]で指定します
	 * @param stayTick 表示中の時間を[tick]で指定します
	 * @param fadeOutTick 表示終了までの時間を[tick]で指定します
	 * @param SubTitle 下に表示される文字列を指定します
	 */
	public static void sendSubTitle(Player player, int fadeInTick, int stayTick, int fadeOutTick, String SubTitle) {
		sendTitle(player, fadeInTick, stayTick, fadeOutTick, null, SubTitle);
	}

	private static void sendTitle(Player player, int fadeInTick, int stayTick, int fadeOutTick, String MainTitle, String SubTitle) {

		PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeInTick, stayTick, fadeOutTick);
		sendPacket.sendPlayer(player,packetPlayOutTimes);

		if (SubTitle != null) {
			SubTitle = SubTitle.replaceAll("%player%", player.getDisplayName());
			SubTitle = ChatColor.translateAlternateColorCodes('&', SubTitle);
			IChatBaseComponent subtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + SubTitle + "\"}");
			PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitle);
			sendPacket.sendPlayer(player,packetPlayOutSubTitle);
		}

		if (MainTitle != null) {
			MainTitle = MainTitle.replaceAll("%player%", player.getDisplayName());
			MainTitle = ChatColor.translateAlternateColorCodes('&', MainTitle);
			IChatBaseComponent maintitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + MainTitle + "\"}");
			PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, maintitle);
			sendPacket.sendPlayer(player,packetPlayOutTitle);
		}
	}
}
