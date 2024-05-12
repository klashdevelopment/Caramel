package dev.klash.caramel.gui;

import java.util.ArrayList;
import java.util.List;

public class CaramelGuiList {
    private List<CaramelGui> guis = new ArrayList<>();
    public void add(CaramelGui gui) {
        guis.add(gui);
    }
    public List<CaramelGui> getGuis() {
        return guis;
    }
}
