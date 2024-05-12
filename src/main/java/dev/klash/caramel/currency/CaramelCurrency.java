package dev.klash.caramel.currency;

import dev.klash.caramel.Caramel;
import dev.klash.caramel.CaramelBlankConfig;
import dev.klash.caramel.CaramelConfig;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public abstract class CaramelCurrency {
    CaramelBlankConfig _saveDataFile;
    public String name, id;
    public double defaultAmount;
    public Plugin plugin;

    public CaramelCurrency(Plugin plugin, String name, String id, double defaultAmount) {
        this.plugin = plugin;
        this.name = name;
        this.id = id;
        this.defaultAmount = defaultAmount;

         _saveDataFile = new CaramelBlankConfig(plugin, "currency-" + id + ".yml");
    }

    public CaramelBlankConfig getSaveDataFile() {
        return _saveDataFile;
    }

    public double getValue(UUID uuid) {
        return _saveDataFile.getData().getDouble(uuid.toString() + ".value", defaultAmount);
    }
    public double setValue(UUID uuid, double value) {
        _saveDataFile.getData().set(uuid.toString() + ".value", value);
        _saveDataFile.saveData();
        return value;
    }
    public double addValue(UUID uuid, double value) {
        return setValue(uuid, getValue(uuid) + value);
    }
    public void setupUserIfNotAlready(UUID uuid) {
        if(!_saveDataFile.getData().contains(uuid.toString())) {
            setValue(uuid, defaultAmount);
        }
    }

}
