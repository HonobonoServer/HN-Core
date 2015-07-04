package so.wktk.honobonoserver.hncore.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.entity.Player;

public class Other {

	/**
	 * 現在時間を日本時間で出力します
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
	 * @param fl 書き込むファイルを指定します。
	 * @param csv 書き込む内容を指定します。
	 */
	public static void filewrite(File fl, String data) {
		try{
		fl.createNewFile();
		FileWriter fw = new FileWriter(fl, fl.exists());
		fw.write(data);
		fw.close();
		} catch (IOException e) {
		}
	}

	/**
	 * &(0-9a-fk-or)をBukkit向けに変更します。
	 * @param text 置き換える文章を指定します。
	 * @param player playerがあった場合、playerより名前を取得後置き換えます。
	 * @return &(0-9a-fk-or) と playerを置き換えたメッセージを返します。
	 */
	public static String color(String text , Player player) {
		text = text.replaceAll("&([0-9a-fk-or])", "§" + "$1");
		if (player != null) { text = text.replaceAll("<player>", player.getDisplayName()); }
		return text;
	}

	/**
	 * &(0-9a-fk-or)をBukkit向けに変更します。文字化けする場合はこちらを使用してください。
	 * @param text 置き換える文章を指定します。
	 * @param player playerがあった場合、playerより名前を取得後置き換えます。
	 * @return &(0-9a-fk-or) と playerを置き換えたメッセージを返します。
	 */
	public static String figuration(String text , Player player) {
		text = UTF8(text);
		text = color(text,player);
		return text;
	}

	/**
	 * Unicodeに変換します。
	 * @param text もとの文章
	 * @return Unicode(\\####)の形が返ってきます
	 */
	public static String Unicode(String text)
	{
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < text.length(); i++) {
	        sb.append(String.format("\\u%04X", Character.codePointAt(text, i)));
	    }
	    String unicode = sb.toString();
	    return unicode;
	}

	/**
	 * UTF-8に変換します
	 * @param text もとの文章
	 * @return UTF-8に変換した文字列を返します。
	 */
	public static String UTF8(String text) {
		try {
			String text1 = new String(text.getBytes(), "UTF-8");
			return text1;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * UTF-16に変換します
	 * @param text もとの文章
	 * @return UTF-16に変換した文字列を返します。
	 */
	public static String UTF16(String text) {
		try {
			String text1 = new String(text.getBytes(), "UTF-16");
			return text1;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * doubleを小数点一桁まで切り捨てます
	 * @param data もとの小数
	 * @return 小数点第一位まで切り捨てたもの
	 */
	public static double cut(double data) {
		BigDecimal a = new BigDecimal(data);
		BigDecimal a2 = a.setScale(1, BigDecimal.ROUND_DOWN);
		double a3 = a2.doubleValue();
		a3 = ((int)(a3 * 10)) % 10;
		return a3 /10;
	}

	/**
	 * 文字列にmatchが含まれているか確かめます
	 * @param data もとの文字列
	 * @param match 詮索する文字列
	 * @return あった場合にはtrue,ない場合にはfalseを返します
	 */
	public static boolean Scut(String data,String match) {
		String Sdata = String.valueOf(data);
		System.out.println(Sdata);
		Matcher m = Pattern.compile(match).matcher(Sdata);
		return m.find();
	}
}
