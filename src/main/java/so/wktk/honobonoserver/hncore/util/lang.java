package so.wktk.honobonoserver.hncore.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import so.wktk.honobonoserver.hncore.HNCore;

public class lang {
	private static Plugin instance = HNCore.getInstance();

	public static void create() {
		File lang = new File(instance.getDataFolder(), "lang.yml");
		if(lang.exists()) { return; }
		InputStream is = null;
		OutputStream os = null;
		try {
			is = instance.getResource("lang.yml");
			os = new FileOutputStream(lang);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		OutputStreamWriter osw = new OutputStreamWriter(os);
		PrintWriter pw = new PrintWriter(osw);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw.write(sb.toString());
		pw.close();
	}

	public static FileConfiguration getLang() {
		FileConfiguration lang = new YamlConfiguration();
		Reader rd;
		try {
			rd = new InputStreamReader(instance.getResource("lang.yml"),"SJIS");
			lang.load(rd);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return lang;
	}
}
