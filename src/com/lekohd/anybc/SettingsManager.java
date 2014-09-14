package com.lekohd.anybc;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;


public class SettingsManager {
	static SettingsManager instance = new SettingsManager();
	AnyBC p;
	FileConfiguration config;
	File cfile;
	FileConfiguration data;
	File dfile;
	

	public static SettingsManager getInstance() {
		return instance;
	}

	public void setup(AnyBC pl) {
		//this.cfile = p.getConfig();
        p = pl;
		this.config = p.getConfig();

		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
        this.config.options().copyDefaults(true);
        p.saveConfig();

		this.dfile = new File(p.getDataFolder(), "permissions.yml");

		if (!this.dfile.exists()) {
			try {
				this.dfile.createNewFile();
				MessageManager.getInstance().log("Data successfully created!");
			} catch (IOException e) {
				Bukkit.getServer().getLogger()
						.severe(ChatColor.RED + "Could not create permissions.yml!");
			}
		}

		this.data = YamlConfiguration.loadConfiguration(this.dfile);
	}

	public FileConfiguration getData() {
		return this.data;
	}

	public void saveData() {
		try {
			this.data.save(this.dfile);
			MessageManager.getInstance().log("Data successfully saved!");
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.RED + "Could not save permissions.yml!");
		}
	}
	

	public void reloadData() {
		this.data = YamlConfiguration.loadConfiguration(this.dfile);
	}

	public FileConfiguration getConfig() {
		return this.config;
	}

	public void saveConfig() {
		/*try {
			this.config.save(this.cfile);
			MessageManager.getInstance().log("Config successfully saved!");
		} catch (IOException e) {
			Bukkit.getServer().getLogger()
					.severe(ChatColor.RED + "Could not save config.yml!");
		}*/
        p.saveConfig();
	}

	public void reloadConfig() {
        p.reloadConfig();
		this.config = p.getConfig();
	}

	public PluginDescriptionFile getDesc() {
		return this.p.getDescription();
	}
}