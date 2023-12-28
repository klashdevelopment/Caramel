package dev.klash.caramel;

import dev.klash.caramel.items.CaramelItem;

import java.util.ArrayList;
import java.util.List;

public class CaramelItemList {
    protected CaramelItemList() {}
    private List<CaramelItem> itemList = new ArrayList<CaramelItem>();

    public void register(CaramelItem item) {
        itemList.add(item);
    }
    public CaramelItem find(String id) {
        for(CaramelItem cmd : itemList) {
            if(cmd.getDetails().id() == id) {
                return cmd;
            }
        }
        return null;
    }
    public void derail(String id) {
        for(CaramelItem cmd : itemList) {
            if(cmd.getDetails().id() == id) {
                itemList.remove(cmd);
            }
        }
    }

    public List<CaramelItem> getItemList() {
        return itemList;
    }

}
