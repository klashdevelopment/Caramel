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

    public List<CaramelItem> getItemList() {
        return itemList;
    }

}
