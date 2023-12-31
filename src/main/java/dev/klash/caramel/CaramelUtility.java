package dev.klash.caramel;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CaramelUtility {
    public static Component colorcomp(String text) {
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
        List<String> res = new ArrayList<>();
        for(String a : choices) {
            if(a.toLowerCase().startsWith(arg.toLowerCase()))
                res.add(a);
        }
        return res;
    }

    public static List<Component> formatLoreString(List<String> pre) {
        List<Component> res = new ArrayList<>();
        for(String s : pre) {
            res.add(colorcomp(s));
        }
        return res;
    }
}
