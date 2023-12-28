package dev.klash.caramel;

import com.samjakob.spigui.SpiGUI;
import dev.klash.caramel.commands.CaramelCommand;
import dev.klash.caramel.plugin.ImplCaramelDefault;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public final class Caramel extends JavaPlugin {

    public static Caramel getInstance() {
        return getPlugin(Caramel.class);
    }
    public CaramelCommandList commands;
    public CaramelItemList items;
    public SpiGUI spigui;

    public String getPrefix() {
        return getConfig().getString("messages.caramel-prefix");
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        commands = new CaramelCommandList();
        items = new CaramelItemList();
        spigui = new SpiGUI(this);
        getServer().getPluginManager().registerEvents(new CaramelListening(), this);

        commands.register(new ImplCaramelDefault.CaramelBaseDefaultCommand());
        items.register(new ImplCaramelDefault.CaramelBaseDefaultItem());
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        for(CaramelCommand cmd : commands.getCommandList()) {
            if(cmd.getDetails().label().equalsIgnoreCase(command.getLabel())) {
                return cmd.complete(args);
            }
        }
        return null;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return commands.onCommand(sender, label, args);
    }
}
