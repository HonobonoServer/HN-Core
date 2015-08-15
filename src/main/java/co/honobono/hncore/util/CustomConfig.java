package co.honobono.hncore.util;

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

public class CustomConfig {

	private String f;
	private Plugin instance;
	private FileConfiguration FileC;

	public CustomConfig(String filename, Plugin instance) {
		if(filename.endsWith(".yml")) {
			filename = filename + ".yml";
		}
		this.f = filename;
		this.instance = instance;
	}

	public void Create() {
		File config = new File(instance.getDataFolder(), f);
		if(config.exists()) { return; }
		InputStream is = null;
		OutputStream os = null;
		try {
			is = instance.getResource(f);
			os = new FileOutputStream(config);
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

	public void save() {
		try {
			FileC.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileConfiguration getConfig() {
		Reader rd;
		FileC = new YamlConfiguration();
		try {
			rd = new InputStreamReader(instance.getResource(f),"SJIS");
			FileC.load(rd);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		return FileC;
	}
}
