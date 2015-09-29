package co.honobono.hncore.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class Japanese {

	/**
	 * ローマ字を日本語にします。
	 *
	 * @param Roman
	 *            もとのローマ字列
	 * @param katakana
	 *            カタカナにする場合にはtrue
	 * @return 変換後の文字列
	 */
	public static String toJP(String Roman, boolean katakana, boolean ime) {
		String str = Roman;
		if (str.startsWith("\"") && str.endsWith("\"")) {
			return str.substring(1, str.length() - 1);
		}
		str = str.replaceAll("ltu", "っ");
		str = str.replaceAll("xtu", "っ");
		str = str.replaceAll("nn", "ん");

		str = str.replaceAll("xa", "ぁ");
		str = str.replaceAll("xi", "ぃ");
		str = str.replaceAll("xu", "ぅ");
		str = str.replaceAll("xe", "ぇ");
		str = str.replaceAll("xo", "ぉ");

		str = str.replaceAll("xwa", "ゎ");
		str = str.replaceAll("xya", "ゃ");
		str = str.replaceAll("xyu", "ゅ");
		str = str.replaceAll("xyo", "ょ");
		str = str.replaceAll("xye", "ぇ");
		str = str.replaceAll("xtu", "っ");
		str = str.replaceAll("xka", "ヵ");

		str = str.replaceAll("lwa", "ゎ");
		str = str.replaceAll("lya", "ゃ");
		str = str.replaceAll("lyu", "ゅ");
		str = str.replaceAll("lyo", "ょ");
		str = str.replaceAll("lye", "ぇ");
		str = str.replaceAll("ltu", "っ");
		str = str.replaceAll("lka", "ヵ");

		str = str.replaceAll("la", "ぁ");
		str = str.replaceAll("li", "ぃ");
		str = str.replaceAll("lu", "ぅ");
		str = str.replaceAll("le", "ぇ");
		str = str.replaceAll("lo", "ぉ");

		str = str.replaceAll("kkya", "っきゃ");
		str = str.replaceAll("kkyi", "っきぃ");
		str = str.replaceAll("kkyu", "っきゅ");
		str = str.replaceAll("kkye", "っきぇ");
		str = str.replaceAll("kkyo", "っきょ");

		str = str.replaceAll("kya", "きゃ");
		str = str.replaceAll("kyi", "きぃ");
		str = str.replaceAll("kyu", "きゅ");
		str = str.replaceAll("kye", "きぇ");
		str = str.replaceAll("kyo", "きょ");

		str = str.replaceAll("qya", "くゃ");
		str = str.replaceAll("qyi", "くぃ");
		str = str.replaceAll("qyu", "くゅ");
		str = str.replaceAll("qye", "くぇ");
		str = str.replaceAll("qyo", "くょ");

		str = str.replaceAll("sya", "しゃ");
		str = str.replaceAll("syi", "しぃ");
		str = str.replaceAll("syu", "しゅ");
		str = str.replaceAll("sye", "しぇ");
		str = str.replaceAll("syo", "しょ");

		str = str.replaceAll("tya", "ちゃ");
		str = str.replaceAll("tyi", "ちぃ");
		str = str.replaceAll("tyu", "ちゅ");
		str = str.replaceAll("tye", "ちぇ");
		str = str.replaceAll("tyo", "ちょ");

		str = str.replaceAll("cya", "ちゃ");
		str = str.replaceAll("cyi", "ちぃ");
		str = str.replaceAll("cyu", "ちゅ");
		str = str.replaceAll("cye", "ちぇ");
		str = str.replaceAll("cyo", "ちょ");

		str = str.replaceAll("nya", "にゃ");
		str = str.replaceAll("nyi", "にぃ");
		str = str.replaceAll("nyu", "にゅ");
		str = str.replaceAll("nye", "にぇ");
		str = str.replaceAll("nyo", "にょ");

		str = str.replaceAll("hya", "ひゃ");
		str = str.replaceAll("hyi", "ひぃ");
		str = str.replaceAll("hyu", "ひゅ");
		str = str.replaceAll("hye", "ひぇ");
		str = str.replaceAll("hyo", "ひょ");

		str = str.replaceAll("fya", "ふふゃ");
		str = str.replaceAll("fyi", "ふぃ");
		str = str.replaceAll("fyu", "ふゅ");
		str = str.replaceAll("fye", "ふぇ");
		str = str.replaceAll("fyo", "ふょ");

		str = str.replaceAll("mya", "みゃ");
		str = str.replaceAll("myi", "みぃ");
		str = str.replaceAll("myu", "みゅ");
		str = str.replaceAll("mye", "みぇ");
		str = str.replaceAll("myo", "みょ");

		str = str.replaceAll("rya", "りゃ");
		str = str.replaceAll("ryi", "りぃ");
		str = str.replaceAll("ryu", "りゅ");
		str = str.replaceAll("rye", "りぇ");
		str = str.replaceAll("ryo", "りょ");

		str = str.replaceAll("gya", "ぎゃ");
		str = str.replaceAll("gyi", "ぎぃ");
		str = str.replaceAll("gyu", "ぎゅ");
		str = str.replaceAll("gye", "ぎぇ");
		str = str.replaceAll("gyo", "ぎょ");

		str = str.replaceAll("jya", "じゃ");
		str = str.replaceAll("jyi", "じぃ");
		str = str.replaceAll("jyu", "じゅ");
		str = str.replaceAll("jye", "じぇ");
		str = str.replaceAll("jyo", "じょ");

		str = str.replaceAll("zya", "じゃ");
		str = str.replaceAll("zyi", "じぃ");
		str = str.replaceAll("zyu", "じゅ");
		str = str.replaceAll("zye", "じぇ");
		str = str.replaceAll("zyo", "じょ");

		str = str.replaceAll("ppya", "っぴゃ");
		str = str.replaceAll("ppyi", "っぴぃ");
		str = str.replaceAll("ppyu", "っぴゅ");
		str = str.replaceAll("ppye", "っぴぇ");
		str = str.replaceAll("ppyo", "っぴょ");

		str = str.replaceAll("pya", "ぴゃ");
		str = str.replaceAll("pyi", "ぴぃ");
		str = str.replaceAll("pyu", "ぴゅ");
		str = str.replaceAll("pye", "ぴぇ");
		str = str.replaceAll("pyo", "ぴょ");

		str = str.replaceAll("dya", "ぢゃ");
		str = str.replaceAll("dyi", "ぢぃ");
		str = str.replaceAll("dyu", "ぢゅ");
		str = str.replaceAll("dye", "ぢぇ");
		str = str.replaceAll("dyo", "ぢょ");

		str = str.replaceAll("dha", "でゃ");
		str = str.replaceAll("dhi", "でぃ");
		str = str.replaceAll("dhu", "でゅ");
		str = str.replaceAll("dhe", "でぇ");
		str = str.replaceAll("dho", "でょ");

		str = str.replaceAll("bya", "びゃ");
		str = str.replaceAll("byi", "びぃ");
		str = str.replaceAll("byu", "びゅ");
		str = str.replaceAll("bye", "びぇ");
		str = str.replaceAll("byo", "びょ");

		str = str.replaceAll("wha", "うぁ");
		str = str.replaceAll("whi", "うぃ");
		str = str.replaceAll("whu", "う");
		str = str.replaceAll("whe", "うぇ");
		str = str.replaceAll("who", "うぉ");

		str = str.replaceAll("qwa", "くぁ");
		str = str.replaceAll("qwi", "くぃ");
		str = str.replaceAll("qwu", "くぅ");
		str = str.replaceAll("qwe", "くぇ");
		str = str.replaceAll("qwo", "くぉ");

		str = str.replaceAll("swa", "すぁ");
		str = str.replaceAll("swi", "すぃ");
		str = str.replaceAll("swu", "すぅ");
		str = str.replaceAll("swe", "すぇ");
		str = str.replaceAll("swo", "すぉ");

		str = str.replaceAll("twa", "とぁ");
		str = str.replaceAll("twi", "とぃ");
		str = str.replaceAll("twu", "とぅ");
		str = str.replaceAll("twe", "とぇ");
		str = str.replaceAll("two", "とぉ");

		str = str.replaceAll("fwa", "ふぁ");
		str = str.replaceAll("fwi", "ふぃ");
		str = str.replaceAll("fwu", "ふぅ");
		str = str.replaceAll("fwe", "ふぇ");
		str = str.replaceAll("fwo", "ふぉ");

		str = str.replaceAll("gwa", "ぐぁ");
		str = str.replaceAll("gwi", "ぐぃ");
		str = str.replaceAll("gwu", "ぐぅ");
		str = str.replaceAll("gwe", "ぐぇ");
		str = str.replaceAll("gwo", "ぐぉ");

		str = str.replaceAll("dwa", "どぁ");
		str = str.replaceAll("dwi", "どぃ");
		str = str.replaceAll("dwu", "どぅ");
		str = str.replaceAll("dwe", "どぇ");
		str = str.replaceAll("dwo", "どぉ");

		str = str.replaceAll("qa", "くぁ");
		str = str.replaceAll("qi", "くぃ");
		str = str.replaceAll("qu", "くぅ");
		str = str.replaceAll("qe", "くぇ");
		str = str.replaceAll("qo", "くぉ");

		str = str.replaceAll("kwa", "くぁ");

		str = str.replaceAll("tsa", "つぁ");
		str = str.replaceAll("tsi", "つぃ");
		str = str.replaceAll("tsu", "つ");
		str = str.replaceAll("tse", "つぇ");
		str = str.replaceAll("tso", "つぉ");

		str = str.replaceAll("ffa", "っふぁ");
		str = str.replaceAll("ffi", "っふぃ");
		str = str.replaceAll("ffu", "っふ");
		str = str.replaceAll("ffe", "っふぇ");
		str = str.replaceAll("ffo", "っふぉ");

		str = str.replaceAll("fa", "ふぁ");
		str = str.replaceAll("fi", "ふぃ");
		str = str.replaceAll("fu", "ふ");
		str = str.replaceAll("fe", "ふぇ");
		str = str.replaceAll("fo", "ふぉ");

		str = str.replaceAll("va", "ヴぁ");
		str = str.replaceAll("vi", "ヴぃ");
		str = str.replaceAll("vu", "ヴ");
		str = str.replaceAll("ve", "ヴぇ");
		str = str.replaceAll("vo", "ヴぉ");

		str = str.replaceAll("sha", "しぁ");
		str = str.replaceAll("shi", "し");
		str = str.replaceAll("shu", "しぅ");
		str = str.replaceAll("she", "しぇ");
		str = str.replaceAll("sho", "しぉ");

		str = str.replaceAll("jja", "っじゃ");
		str = str.replaceAll("jji", "っじ");
		str = str.replaceAll("jju", "っじゅ");
		str = str.replaceAll("jje", "っじぇ");
		str = str.replaceAll("jjo", "っじょ");

		str = str.replaceAll("ja", "じゃ");
		str = str.replaceAll("ji", "じ");
		str = str.replaceAll("ju", "じゅ");
		str = str.replaceAll("je", "じぇ");
		str = str.replaceAll("jo", "じょ");

		str = str.replaceAll("cha", "ちゃ");
		str = str.replaceAll("chi", "ち");
		str = str.replaceAll("chu", "ちゅ");
		str = str.replaceAll("che", "ちぇ");
		str = str.replaceAll("cho", "ちょ");

		str = str.replaceAll("kka", "っか");
		str = str.replaceAll("kki", "っき");
		str = str.replaceAll("kku", "っく");
		str = str.replaceAll("kke", "っけ");
		str = str.replaceAll("kko", "っこ");

		str = str.replaceAll("ssa", "っさ");
		str = str.replaceAll("ssi", "っし");
		str = str.replaceAll("ssu", "っす");
		str = str.replaceAll("sse", "っせ");
		str = str.replaceAll("sso", "っそ");

		str = str.replaceAll("tta", "った");
		str = str.replaceAll("tti", "っち");
		str = str.replaceAll("ttu", "っつ");
		str = str.replaceAll("tte", "って");
		str = str.replaceAll("tto", "っと");

		str = str.replaceAll("nna", "っな");
		str = str.replaceAll("nni", "っに");
		str = str.replaceAll("nnu", "っぬ");
		str = str.replaceAll("nne", "っね");
		str = str.replaceAll("nno", "っの");

		str = str.replaceAll("hha", "っは");
		str = str.replaceAll("hhi", "っひ");
		str = str.replaceAll("hhu", "っふ");
		str = str.replaceAll("hhe", "っへ");
		str = str.replaceAll("hho", "っほ");

		str = str.replaceAll("mma", "っま");
		str = str.replaceAll("mmi", "っみ");
		str = str.replaceAll("mmu", "っむ");
		str = str.replaceAll("mme", "っめ");
		str = str.replaceAll("mmo", "っも");

		str = str.replaceAll("yya", "っや");
		str = str.replaceAll("yyi", "っい");
		str = str.replaceAll("yyu", "っゆ");
		str = str.replaceAll("yye", "っえ");
		str = str.replaceAll("yyo", "っよ");

		str = str.replaceAll("rra", "っら");
		str = str.replaceAll("rri", "っり");
		str = str.replaceAll("rru", "っる");
		str = str.replaceAll("rre", "っれ");
		str = str.replaceAll("rro", "っろ");

		str = str.replaceAll("wwa", "っわ");
		str = str.replaceAll("wwi", "っゐ");
		str = str.replaceAll("wwu", "っう");
		str = str.replaceAll("wwe", "っゑ");
		str = str.replaceAll("wwo", "っを");

		str = str.replaceAll("ffa", "っふぁ");
		str = str.replaceAll("ffi", "っふぃ");
		str = str.replaceAll("ffu", "っふ");
		str = str.replaceAll("ffe", "っふぇ");
		str = str.replaceAll("ffo", "っふぉ");

		str = str.replaceAll("gga", "っが");
		str = str.replaceAll("ggi", "っぎ");
		str = str.replaceAll("ggu", "っぐ");
		str = str.replaceAll("gge", "っげ");
		str = str.replaceAll("ggo", "っご");

		str = str.replaceAll("zza", "っざ");
		str = str.replaceAll("zzi", "っじ");
		str = str.replaceAll("zzu", "っず");
		str = str.replaceAll("zze", "っぜ");
		str = str.replaceAll("zzo", "っぞ");

		str = str.replaceAll("ppa", "っぱ");
		str = str.replaceAll("ppi", "っぴ");
		str = str.replaceAll("ppu", "っぷ");
		str = str.replaceAll("ppe", "っぺ");
		str = str.replaceAll("ppo", "っぽ");

		str = str.replaceAll("ffa", "っふぁ");
		str = str.replaceAll("ffi", "っふぃ");
		str = str.replaceAll("ffu", "っふ");
		str = str.replaceAll("ffe", "っふぇ");
		str = str.replaceAll("ffo", "っふぉ");

		str = str.replaceAll("gga", "っが");
		str = str.replaceAll("ggi", "っぎ");
		str = str.replaceAll("ggu", "っぐ");
		str = str.replaceAll("gge", "っげ");
		str = str.replaceAll("ggo", "っご");

		str = str.replaceAll("zza", "っざ");
		str = str.replaceAll("zzi", "っじ");
		str = str.replaceAll("zzu", "っず");
		str = str.replaceAll("zze", "っぜ");
		str = str.replaceAll("zzo", "っぞ");

		str = str.replaceAll("bba", "っば");
		str = str.replaceAll("bbi", "っび");
		str = str.replaceAll("bbu", "っぶ");
		str = str.replaceAll("bbe", "っべ");
		str = str.replaceAll("bbo", "っぼ");

		str = str.replaceAll("ya", "や");
		str = str.replaceAll("yi", "い");
		str = str.replaceAll("yu", "ゆ");
		str = str.replaceAll("ye", "いぇ");
		str = str.replaceAll("yo", "よ");

		str = str.replaceAll("xxya", "っや");
		str = str.replaceAll("xxyi", "っぃ");
		str = str.replaceAll("xxyu", "っゅ");
		str = str.replaceAll("xxye", "っぇ");
		str = str.replaceAll("xxyo", "っょ");

		str = str.replaceAll("llya", "っゃ");
		str = str.replaceAll("llyi", "っぃ");
		str = str.replaceAll("llyu", "っゅ");
		str = str.replaceAll("llye", "っぇ");
		str = str.replaceAll("llyo", "っょ");

		str = str.replaceAll("ka", "か");
		str = str.replaceAll("ki", "き");
		str = str.replaceAll("ku", "く");
		str = str.replaceAll("ke", "け");
		str = str.replaceAll("ko", "こ");

		str = str.replaceAll("sa", "さ");
		str = str.replaceAll("si", "し");
		str = str.replaceAll("su", "す");
		str = str.replaceAll("se", "せ");
		str = str.replaceAll("so", "そ");

		str = str.replaceAll("ta", "た");
		str = str.replaceAll("ti", "ち");
		str = str.replaceAll("tu", "つ");
		str = str.replaceAll("te", "て");
		str = str.replaceAll("to", "と");

		str = str.replaceAll("na", "な");
		str = str.replaceAll("ni", "に");
		str = str.replaceAll("nu", "ぬ");
		str = str.replaceAll("ne", "ね");
		str = str.replaceAll("no", "の");

		str = str.replaceAll("ha", "は");
		str = str.replaceAll("hi", "ひ");
		str = str.replaceAll("hu", "ふ");
		str = str.replaceAll("he", "へ");
		str = str.replaceAll("ho", "ほ");

		str = str.replaceAll("ma", "ま");
		str = str.replaceAll("mi", "み");
		str = str.replaceAll("mu", "む");
		str = str.replaceAll("me", "め");
		str = str.replaceAll("mo", "も");

		str = str.replaceAll("ya", "や");
		str = str.replaceAll("yi", "い");
		str = str.replaceAll("yu", "ゆ");
		str = str.replaceAll("ye", "え");
		str = str.replaceAll("yo", "よ");

		str = str.replaceAll("ra", "ら");
		str = str.replaceAll("ri", "り");
		str = str.replaceAll("ru", "る");
		str = str.replaceAll("re", "れ");
		str = str.replaceAll("ro", "ろ");

		str = str.replaceAll("wa", "わ");
		str = str.replaceAll("wi", "うぃ");
		str = str.replaceAll("wu", "う");
		str = str.replaceAll("we", "うぇ");
		str = str.replaceAll("wo", "を");

		str = str.replaceAll("fa", "ふぁ");
		str = str.replaceAll("fi", "ふぃ");
		str = str.replaceAll("fu", "ふ");
		str = str.replaceAll("fe", "ふぇ");
		str = str.replaceAll("fo", "ふぉ");

		str = str.replaceAll("ga", "が");
		str = str.replaceAll("gi", "ぎ");
		str = str.replaceAll("gu", "ぐ");
		str = str.replaceAll("ge", "げ");
		str = str.replaceAll("go", "ご");

		str = str.replaceAll("za", "ざ");
		str = str.replaceAll("zi", "じ");
		str = str.replaceAll("zu", "ず");
		str = str.replaceAll("ze", "ぜ");
		str = str.replaceAll("zo", "ぞ");

		str = str.replaceAll("ba", "ば");
		str = str.replaceAll("bi", "び");
		str = str.replaceAll("bu", "ぶ");
		str = str.replaceAll("be", "べ");
		str = str.replaceAll("bo", "ぼ");

		str = str.replaceAll("pa", "ぱ");
		str = str.replaceAll("pi", "ぴ");
		str = str.replaceAll("pu", "ぷ");
		str = str.replaceAll("pe", "ぺ");
		str = str.replaceAll("po", "ぽ");

		str = str.replaceAll("fa", "ふぁ");
		str = str.replaceAll("fi", "ふぃ");
		str = str.replaceAll("fu", "ふ");
		str = str.replaceAll("fe", "ふぇ");
		str = str.replaceAll("fo", "ふぉ");

		str = str.replaceAll("ga", "が");
		str = str.replaceAll("gi", "ぎ");
		str = str.replaceAll("gu", "ぐ");
		str = str.replaceAll("ge", "げ");
		str = str.replaceAll("go", "ご");

		str = str.replaceAll("za", "ざ");
		str = str.replaceAll("zi", "じ");
		str = str.replaceAll("zu", "ず");
		str = str.replaceAll("ze", "ぜ");
		str = str.replaceAll("zo", "ぞ");

		str = str.replaceAll("ba", "ば");
		str = str.replaceAll("bi", "び");
		str = str.replaceAll("bu", "ぶ");
		str = str.replaceAll("be", "べ");
		str = str.replaceAll("bo", "ぼ");

		str = str.replaceAll("da", "だ");
		str = str.replaceAll("di", "ぢ");
		str = str.replaceAll("du", "づ");
		str = str.replaceAll("de", "で");
		str = str.replaceAll("do", "ど");

		str = str.replaceAll("a", "あ");
		str = str.replaceAll("i", "い");
		str = str.replaceAll("u", "う");
		str = str.replaceAll("e", "え");
		str = str.replaceAll("o", "お");

		str = str.replaceAll("-", "ー");
		str = str.replaceAll(",", "、");
		str = str.replaceAll("~", "～");

		str = str.replaceAll("mjd", "マジで");
		str = str.replaceAll("mjk", "マジか");
		str = str.replaceAll("mj", "マジ");
		str = str.replaceAll("dsyn", "ですよね");
		str = str.replaceAll("tmp", "ちんぽ");
		str = str.replaceAll("tnp", "ちんぽ");
		str = str.replaceAll("wtf", "what the fuck");
		str = str.replaceAll("fml", "fuck my life");
		str = str.replaceAll("fm", "ふむ");
		str = str.replaceAll("srn", "それな");
		str = str.replaceAll("np", "no problem");
		str = str.replaceAll("n", "ん");
		str = str.replaceAll("んo problem", "no problem");
		str = str.replaceAll("thx", "thanks");

		Other.color(str, null);

		if (ime == true) {
			str = toIME(str);
			katakana = false;
		}

		if (katakana == true) {
			str = toKata(str);
		}
		return str;
	}

	/**
	 * ひらがなをカタカナに変換します
	 *
	 * @param kana 元となるひらがな
	 * @return カタカナの文字列
	 */
	public static String toKata(String kana) {
		StringBuffer sb = new StringBuffer(kana);
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c >= 'ぁ' && c <= 'ん') {
				sb.setCharAt(i, (char) (c - 'ぁ' + 'ァ'));
			}
		}
		return sb.toString();
	}

	/**
	 * googleIMEを介して変換します
	 *
	 * @param kana
	 *            ひらがな列
	 * @return googleIME第一変換リスト
	 * @author kssrさん
	 */
	@SuppressWarnings("rawtypes")
	public static String toIME(String kana) {
		URL url;
		try {
			url = new URL(
					"http://google.co.jp/transliterate?langpair=ja-Hira%7cja&text=" + URLEncoder.encode(kana, "UTF8"));
			InputStream in = url.openStream();
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF8"));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				Gson gson = new Gson();
				Type listType = new TypeToken<List>() {
				}.getType();
				List posts = (List) gson.fromJson(sb.toString(), listType);
				StringBuilder strb = new StringBuilder();

				for (Object o : posts) {
					List list = (List) o;
					List koho = (List) list.get(1);
					strb.append(koho.get(0).toString());
				}
				return strb.toString();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
