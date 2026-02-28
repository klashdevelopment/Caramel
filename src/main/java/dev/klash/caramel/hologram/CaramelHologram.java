package dev.klash.caramel.hologram;

import org.bukkit.Location;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CaramelHologram implements ICaramelHologram {

    float lineHeight = 0.25f;
    List<CaramelHologramLine> lines = new ArrayList<>();
    List<TextDisplay> entities;
    HashMap<UUID, CaramelHologramLine> entityLines;
    public boolean visible;
    public Location location;

    public CaramelHologram(List<CaramelHologramLine> lines) {
        this.lines.addAll(lines);
        this.entities = new ArrayList<>();
        this.entityLines = new HashMap<>();
    }
    public CaramelHologram() {
        this.entities = new ArrayList<>();
        this.entityLines = new HashMap<>();
    }

    public void setLineHeight(float lineHeight) {
        this.lineHeight = lineHeight;
    }
    public float getLineHeight() {
        return lineHeight;
    }

    public void setLocation(Location location) {
        this.location = location;
        update();
    }

    @Override
    public List<CaramelHologramLine> getLines() {
        return lines;
    }

    @Override
    public void setLines(List<CaramelHologramLine> lines) {
        this.lines = lines;
        update();
    }

    @Override
    public void addLine(CaramelHologramLine line) {
        lines.add(line);
        update();
    }

    public void addFront(CaramelHologramLine line) {
        lines.add(0, line);
        update();
    }
    public void addBack(CaramelHologramLine line) {
        lines.add(line);
        update();
    }

    @Override
    public void removeLine(int index) {
        lines.remove(index);
        update();
    }

    @Override
    public void removeLine(CaramelHologramLine line) {
        lines.remove(line);
        update();
    }

    @Override
    public void clear() {
        lines.clear();
        update();
    }

    @Override
    public void show() {
        this.visible = true;
        if(this.entities.isEmpty()) {
            spawn();
        }
        this.entities.forEach((TextDisplay entity) -> {
            entity.setTransformation(
                    new Transformation(
                            entity.getTransformation().getTranslation(),
                            entity.getTransformation().getLeftRotation(),
                            new Vector3f(1f, 1f, 1f),
                            entity.getTransformation().getRightRotation()
                    )
            );
        });
    }

    @Override
    public void hide() {
        this.visible = false;
        if(this.entities.isEmpty()) {
            spawn();
        }
        this.entities.forEach((TextDisplay entity) -> {
            entity.setTransformation(
                    new Transformation(
                            entity.getTransformation().getTranslation(),
                            entity.getTransformation().getLeftRotation(),
                            new Vector3f(0f, 0f, 0f),
                            entity.getTransformation().getRightRotation()
                    )
            );
        });
    }

    @Override
    public void destroy() {
        this.entities.forEach(TextDisplay::remove);
        this.entities.clear();
        this.visible = false;
    }

    @Override
    public void update() {
        for(TextDisplay entity : entities) {
            entity.setTransformation(new Transformation(
                    location.toVector().toVector3f().add(0f, lines.indexOf(entityLines.get(entity.getUniqueId())) * -lineHeight, 0f),
                    entity.getTransformation().getLeftRotation(),
                    entity.getTransformation().getScale(),
                    entity.getTransformation().getRightRotation()
            ));
            entity.teleport(location.clone());
            CaramelHologramLine line = entityLines.get(entity.getUniqueId());
            if(lines.contains(line))
                if(line != null) {
                    line.apply(entity);
                }
            else {
                entity.remove();
                entities.remove(entity);
                entityLines.remove(entity.getUniqueId());
            }
        }
    }

    public void spawn() {
        Location loc = location.clone();
        int i = 0;
        for(CaramelHologramLine line : lines) {
            TextDisplay display = loc.getWorld().spawn(loc, TextDisplay.class);
            line.apply(display);
            display.setTransformation(new Transformation(
                    new Vector3f(0f, -i * lineHeight, 0f),
                    display.getTransformation().getLeftRotation(),
                    display.getTransformation().getScale(),
                    display.getTransformation().getRightRotation()
            ));
            entities.add(display);
            entityLines.put(display.getUniqueId(), line);
            i++;
        }
    }

    @Override
    public List<TextDisplay> getEntities() {
        return entities;
    }
}
