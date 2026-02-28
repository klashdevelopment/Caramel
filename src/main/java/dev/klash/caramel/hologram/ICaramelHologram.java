package dev.klash.caramel.hologram;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.TextDisplay;

import java.util.List;

public interface ICaramelHologram {
    List<CaramelHologramLine> getLines();
    void setLines(List<CaramelHologramLine> lines);
    void addLine(CaramelHologramLine line);
    void removeLine(int index);
    void removeLine(CaramelHologramLine line);
    void clear();
    void show();
    void hide();
    void destroy();
    void update();
    List<TextDisplay> getEntities();
}
