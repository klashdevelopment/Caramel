package dev.klash.caramel;

import dev.klash.caramel.currency.CaramelCurrency;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CaramelCurrencyList {
    protected CaramelCurrencyList() {}
    private final List<CaramelCurrency> itemList = new ArrayList<>();

    public void register(CaramelCurrency currency) {
        itemList.add(currency);
        currency.getSaveDataFile().saveBlankConfig();
        for(Player player : Bukkit.getOnlinePlayers()) {
            currency.setupUserIfNotAlready(player.getUniqueId());
        }
    }
    public CaramelCurrency find(String id) {
        for(CaramelCurrency currency : itemList) {
            if(currency.id.equals(id)) {
                return currency;
            }
        }
        return null;
    }

    public List<CaramelCurrency> getCurrencyList() {
        return itemList;
    }
}
