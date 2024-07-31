package dev.klash.caramel;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class CaramelBlankConfig {
    private FileConfiguration data = null;
    private File dataFile = null;

    private Plugin plugin;
    private String fileName;

    public CaramelBlankConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
    }

    public void reload() {
        if(dataFile == null) {
            dataFile = new File(Caramel.getInstance().getDataFolder(), fileName);
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

    public void saveBlankConfig() {
        if(dataFile == null) {
            dataFile = new File(Caramel.getInstance().getDataFolder(), fileName);
        }
        if(!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
