package co.honobono.hncore.util;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;


public class sendPacket {

	/**
	 * PlayerにPacketを送信します。サーバーには送信しません
	 * @param player 送るPlayerを指定します
	 * @param packet 送るpacketを指定します。 Packetはこのクラス内のメソッドより作成できます。
	 */
	@SuppressWarnings("rawtypes")
	public static void sendPlayer(Player player,Packet packet) {
		((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
	}

	public static void sendActionBar(Player player, String message) {
		IChatBaseComponent message1 = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(message1, (byte)2);
		sendPlayer(player, packet);
	}
}
