package dev.klash.caramel;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class CaramelConfig {
    private FileConfiguration data = null;
    private File dataFile = null;
    
    private Plugin plugin;
    private String fileName;

    public CaramelConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
    }

    public void reload() {
        if(dataFile == null) {
            dataFile = new File(plugin.getDataFolder(), fileName);
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
        InputStream defaultStream = plugin.getResource(fileName);
        if(defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            data.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getData() {
        if(data==null) reload();
        return data;
    }
    public void saveData() {
        if(data==null || dataFile==null) return;
        try {
            getData().save(dataFile);
        } catch (Exception e) {
            // log using logger as severe
            plugin.getLogger().log(Level.SEVERE, "CaramelConfig Could not save data to " + dataFile, e);
        }
    }

    public void saveDefaultConfig() {
        if(dataFile == null) {
            dataFile = new File(plugin.getDataFolder(), fileName);
        }
        if(!dataFile.exists()) {
            plugin.saveResource(fileName, false);
        }
    }
}
