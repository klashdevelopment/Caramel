package dev.klash.caramel;

import com.samjakob.spigui.buttons.SGButton;
import dev.klash.caramel.gui.CaramelGui;
import dev.klash.caramel.gui.CaramelGuiOverrides;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.logging.log4j.util.TriConsumer;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class CaramelUtility {
    public static Component colorcomp(String text) {
        return MiniMessage.miniMessage().deserialize("<italic:false>"+text);
    }
    public static Component stripItalics(String text) {
        return MiniMessage.miniMessage().deserialize(text).decoration(TextDecoration.ITALIC, false);
    }
    public static List<String> tabComplete(String arg, List<String> choices) {
        List<String> res = new ArrayList<>();
        for(String a : choices) {
            if(a.toLowerCase().startsWith(arg.toLowerCase()))
                res.add(a);
        }
        return res;
    }
    public static List<String> tabCompletePlayers(String arg, Collection<? extends Player> players) {
        List<String> choices = new ArrayList<>();
        for(Player p : players) {
            choices.add(p.getName());
        }
        return tabComplete(arg, choices);
    }
    public static void quickGui(Player player, String title, Function<Integer, ItemStack> setup, BiConsumer<Integer, InventoryClickEvent> click, int rows, boolean paginated) {
        CaramelGui gui = new CaramelGui(title, rows, paginated) {
            @Override
            public void setup(Player player) {
                for(int i = 0; i < rows * 9; i++) {
                    ItemStack button = setup.apply(i);
                    if (button != null) {
                        int finalI = i;
                        this.slot(i, button, (event) -> {
                            click.accept(finalI, event);
                        });
                    }
                    else
                        this.slot(i, new ItemStack(Material.AIR));
                }
            }
        };
        gui.open(player);
    }
    public record QuickDialogButton(String name, String tooltip, DialogAction action) {
        public QuickDialogButton(String name, DialogAction action) {
            this(name, "", action);
        }
    }
    public static Dialog quickDialog(String title, String prompt, QuickDialogButton optionA, QuickDialogButton optionB) {
        Dialog dialog = Dialog.create(builder -> builder.empty()
                .base(DialogBase.builder(MiniMessage.miniMessage().deserialize(title))
                        .body(List.of(
                                DialogBody.plainMessage(MiniMessage.miniMessage().deserialize(prompt))
                        ))
                        .build())
                .type(DialogType.confirmation(
                        ActionButton.builder(MiniMessage.miniMessage().deserialize(optionA.name())).tooltip(MiniMessage.miniMessage().deserialize(optionA.tooltip())).action(optionA.action()).build(),
                        ActionButton.builder(MiniMessage.miniMessage().deserialize(optionB.name())).tooltip(MiniMessage.miniMessage().deserialize(optionB.tooltip())).action(optionB.action()).build()
                ))
        );
        return dialog;
    }
    public static List<Component> formatLoreString(List<String> pre) {
        List<Component> res = new ArrayList<>();
        for(String s : pre) {
            res.add(colorcomp(s));
        }
        return res;
    }
}
