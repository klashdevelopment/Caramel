package dev.klash.caramel.gui;

import dev.klash.caramel.Caramel;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class CaramelPrompt {
    public static class CaramelAnvilPromptBuilder {
        private CaramelAnvilPromptBuilder() {}
        private String title = "Input...";
        private String text = "";
        private ItemStack leftItem = null;
        private ItemStack rightItem = null;
        private ItemStack resultItem = null;
        private Consumer<AnvilGUI.StateSnapshot> closeCallback = null;
        private AnvilGUI.ClickHandler asyncClickCallback = null;
        private BiFunction<Integer, AnvilGUI.StateSnapshot, List<AnvilGUI.ResponseAction>> clickCallback = null;

        public CaramelAnvilPromptBuilder title(String title) {
            this.title = title;
            return this;
        }
        public CaramelAnvilPromptBuilder text(String text) {
            this.text = text;
            return this;
        }
        public CaramelAnvilPromptBuilder leftItem(ItemStack item) {
            this.leftItem = item;
            return this;
        }
        public CaramelAnvilPromptBuilder rightItem(ItemStack item) {
            this.rightItem = item;
            return this;
        }
        public CaramelAnvilPromptBuilder resultItem(ItemStack item) {
            this.resultItem = item;
            return this;
        }
        public CaramelAnvilPromptBuilder onClose(Consumer<AnvilGUI.StateSnapshot> callback) {
            this.closeCallback = callback;
            return this;
        }
        public CaramelAnvilPromptBuilder onClickAsync(AnvilGUI.ClickHandler callback) {
            this.asyncClickCallback = callback;
            return this;
        }
        public void open(Player player) {
            AnvilGUI.Builder b = new AnvilGUI.Builder();
            if(title != null)
                b.title(title);
            if(text != null)
                b.text(text);
            if(leftItem != null)
                b.itemLeft(leftItem);
            if(rightItem != null)
                b.itemRight(rightItem);
            if(resultItem != null)
                b.itemOutput(resultItem);
            if(closeCallback != null)
                b.onClose(closeCallback);
            if(asyncClickCallback != null)
                b.onClickAsync(asyncClickCallback);
            if(clickCallback != null)
                b.onClick(clickCallback);
            b.plugin(Caramel.getInstance());

            b.open(player);
        }
    }
    public static CaramelAnvilPromptBuilder anvil() {
        return new CaramelAnvilPromptBuilder();
    }
}
