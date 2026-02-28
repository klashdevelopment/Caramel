# Holograms

Caramel holograms are lightweight wrappers around Minecraft `TextDisplay` entities, designed for easy multi-line floating text.
They use holograms

---

## Creating a hologram

You can create a hologram either empty, or pre-filled with lines.

```java
// Empty hologram as a base
CaramelHologram hologram = new CaramelHologram();

// Hologram with initial lines
CaramelHologram hologram = new CaramelHologram(List.of(
        new CaramelHologramLine(CaramelUtility.colorcomp("Hello")),
        new CaramelHologramLine(CaramelUtility.colorcomp("World"))
));
````

Before spawning, you **must** set a location:

```java
hologram.setLocation(location);
```

---

<br/><br/>

## Managing lines

You can fully control the hologram’s lines after creation.

```java
hologram.addLine(new CaramelHologramLine(Component.text("New Line")));
hologram.addFront(new CaramelHologramLine(Component.text("Top Line")));
hologram.addBack(new CaramelHologramLine(Component.text("Bottom Line")));
```

Removing lines:

```java
hologram.removeLine(0); // via index
hologram.removeLine(line); // via direct line object
hologram.clear(); // removes all lines, keeps it spawned if it is
```

Replacing all lines:

```java
hologram.setLines(List.of(
  new CaramelHologramLine(Component.text("Replaced"))
));
```

Retrieving lines:

```java
List<CaramelHologramLine> lines = hologram.getLines();
```

---

<br/><br/>

## Line spacing

Line spacing is controlled via `lineHeight`.

```java
hologram.setLineHeight(0.25);
double spacing = hologram.getLineHeight();
```

Values are in **world units (blocks)**, not pixels.
Typical values range from `0.22` to `0.30`.

---

<br/><br/>

## Showing and hiding

Once configured, the hologram can be shown or hidden without despawning:

```java
hologram.show();
hologram.hide();
```

* `show()` spawns the hologram if needed and makes it visible
* `hide()` visually hides it without removing entities

---

<br/><br/>

## Updating holograms

If line content or formatting changes, call `update()` to reapply settings:

```java
hologram.update();
```

This reapplies all `CaramelHologramLine` settings to their backing `TextDisplay` entities.

---

<br/><br/>

## Destroying holograms

To fully remove the hologram and clean up entities:

```java
hologram.destroy();
```

This removes all spawned `TextDisplay` entities and clears internal state.

---

<br/><br/>

## Accessing entities

If you need direct access to the underlying displays:

```java
List<TextDisplay> entities = hologram.getEntities();
```

This can be useful for advanced effects or debugging.

---

<br/><br/>

## Summary

Typical usage flow:

```java
CaramelHologram hologram = new CaramelHologram();
hologram.setLocation(location);

hologram.addLine(new CaramelHologramLine(Component.text("Line 1")));
hologram.addLine(new CaramelHologramLine(Component.text("Line 2")));

hologram.setLineHeight(0.25);
hologram.show();
```

That’s all you need for clean, stable holograms.

```
