package co.honobono.hncore.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
	private Plugin instance;
	private String name = "";
	private File config = null;
	private FileConfiguration f = null;

	public Config(String filename, Plugin plugin) throws IOException, InvalidConfigurationException {
		this.name = filename;
		this.instance = plugin;
		this.config = new File(instance.getDataFolder(), this.name);
		InputStream im = null;
		OutputStream os = null;
		try {
			if (!this.config.exists()) {
				this.config.createNewFile();
				im = instance.getResource(this.name);
				if (im == null) {
					return;
				}
				os = new FileOutputStream(this.config);
				int c = 0;
				while ((c = im.read()) != -1) {
					os.write(c);
				}
				im.close();
				os.close();
			}
		} finally {
			if (im != null)
				im.close();
			if (os != null)
				os.close();
		}
		this.f = new YamlConfiguration();
		f.load(this.config);
	}

	public FileConfiguration get() {
		return f;
	}

	@Deprecated
	public void save() throws IOException {
		f.save(config);
	}
}
