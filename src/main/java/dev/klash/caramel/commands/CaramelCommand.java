package dev.klash.caramel.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public interface CaramelCommand {
    CaramelCommandDetail getDetails();
    default List<String> complete(String[] args) {
        return Collections.emptyList();
    }
    void onPlayer(Player player, List<String> args);
    void onConsole(CommandSender sender, List<String> args);
}
