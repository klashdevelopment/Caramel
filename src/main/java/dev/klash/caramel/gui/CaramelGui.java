package dev.klash.caramel.gui;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.buttons.SGButtonListener;
import com.samjakob.spigui.menu.SGMenu;
import dev.klash.caramel.Caramel;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Function;

public abstract class CaramelGui {

    public static Function<Player, Boolean> alwaysTrue = (player) -> true;
    public Function<Player, Boolean> finalCheck = alwaysTrue;
    private SGMenu menu;
    public CaramelGuiOverrides override = null;

    public String title;
    int rows;
    public CaramelGui(String title, int rows) {
        this.title = title;
        this.rows = rows;
    }
    public abstract void setup(Player player);
    public void setOverride(CaramelGuiOverrides override, Function<Player, Boolean> finalCheck) {
        this.override = override;
        this.finalCheck = finalCheck;
    }
    public SGButton[] makeLine(int length, ItemStack item) {
        SGButton[] buttons = new SGButton[length];
        for(int i = 0; i < length; i++) {
            buttons[i] = new SGButton(item);
        }
        menu.addButtons(buttons);
        return buttons;
    }
    public void slot(int slot, ItemStack item) {
        menu.setButton(slot, new SGButton(item));
    }
    public void slot(int slot, ItemStack item, SGButtonListener listener) {
        menu.setButton(slot, new SGButton(item).withListener(listener));
    }
    public void add(ItemStack item) {
        menu.addButton(new SGButton(item));
    }
    public void add(ItemStack item, SGButtonListener listener) {
        menu.addButton(new SGButton(item).withListener(listener));
    }
    public void open(Player player) {
        menu = Caramel.getInstance().spigui.create(title, rows);
        menu.setAutomaticPaginationEnabled(false);
        setup(player);
        player.openInventory(menu.getInventory());
    }
}
