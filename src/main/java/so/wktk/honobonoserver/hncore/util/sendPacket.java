package so.wktk.honobonoserver.hncore.util;

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

	/**
	 * ActionBarPacketを作成します。(ActionBarとは乗り物などでのLSHIFT(ryのようなメッセージのことです
	 * @param message 表示するMessageを指定します
	 * @return sendPacketで使えるpacketを返します
	 */
	public static PacketPlayOutChat setActionBar(String message) {
		IChatBaseComponent message1 = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}");
		PacketPlayOutChat packet = new PacketPlayOutChat(message1, (byte)2);
		return packet;
	}
}
