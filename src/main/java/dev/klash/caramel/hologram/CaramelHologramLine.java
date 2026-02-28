package dev.klash.caramel.hologram;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.entity.Display;
import org.bukkit.entity.TextDisplay;

public record CaramelHologramLine(TextDisplay.TextAlignment alignment, Display.Billboard billboard, Color background, Component text, int lineWidth, boolean shadow, boolean seeThrough, byte textOpacity) {
    protected void apply(TextDisplay display) {
        display.setAlignment(alignment);
        display.text(text);
        display.setSeeThrough(seeThrough);
        if(textOpacity != -1) display.setTextOpacity(textOpacity);
        display.setShadowed(shadow);
        display.setBackgroundColor(background);
        if(lineWidth != -1) display.setLineWidth(lineWidth);
        display.setBillboard(billboard);
    }

    public static CaramelHologramLine gapLine() {
        return new CaramelHologramLine(TextDisplay.TextAlignment.CENTER, Display.Billboard.CENTER, null, Component.empty(), -1, false, false, (byte)0);
    }

    public CaramelHologramLine withAlignment(TextDisplay.TextAlignment alignment) {
        return new CaramelHologramLine(alignment, billboard, background, text, lineWidth, shadow, seeThrough, textOpacity);
    }
    public CaramelHologramLine withBillboard(Display.Billboard billboard) {
        return new CaramelHologramLine(alignment, billboard, background, text, lineWidth, shadow, seeThrough, textOpacity);
    }
    public CaramelHologramLine withBackground(Color background) {
        return new CaramelHologramLine(alignment, billboard, background, text, lineWidth, shadow, seeThrough, textOpacity);
    }
    public CaramelHologramLine withText(Component text) {
        return new CaramelHologramLine(alignment, billboard, background, text, lineWidth, shadow, seeThrough, textOpacity);
    }
    public CaramelHologramLine withLineWidth(int lineWidth) {
        return new CaramelHologramLine(alignment, billboard, background, text, lineWidth, shadow, seeThrough, textOpacity);
    }
    public CaramelHologramLine withShadow(boolean shadow) {
        return new CaramelHologramLine(alignment, billboard, background, text, lineWidth, shadow, seeThrough, textOpacity);
    }
    public CaramelHologramLine withSeeThrough(boolean seeThrough) {
        return new CaramelHologramLine(alignment, billboard, background, text, lineWidth, shadow, seeThrough, textOpacity);
    }
    public CaramelHologramLine withTextOpacity(byte textOpacity) {
        return new CaramelHologramLine(alignment, billboard, background, text, lineWidth, shadow, seeThrough, textOpacity);
    }

    public CaramelHologramLine(Component component) {
        this(TextDisplay.TextAlignment.CENTER, Display.Billboard.CENTER, null, component, Integer.MAX_VALUE, false, false, (byte)-1);
    }
    public CaramelHologramLine() {
        this(Component.empty());
    }
}
