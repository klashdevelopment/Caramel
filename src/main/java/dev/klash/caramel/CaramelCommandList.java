package dev.klash.caramel;

import dev.klash.caramel.commands.CaramelCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CaramelCommandList {
    protected CaramelCommandList() {}
    private List<CaramelCommand> commandList = new ArrayList<CaramelCommand>();

    /**
     * Registers a new command.
     *
     * @param command The command to register.
     */
    public void register(CaramelCommand command) {
        commandList.add(command);
    }
    /**
     * Finds a command by its label.
     *
     * @param label The label of the command to find.
     * @return The found command, or null if no command with the given label exists.
     * @deprecated This method is deprecated and may be removed in future versions. Please set your own variable refrences to your commands.
     */
    @Deprecated
    public CaramelCommand find(String label) {
        for(CaramelCommand cmd : commandList) {
            if(cmd.getDetails().label() == label) {
                return cmd;
            }
        }
        return null;
    }
    /**
     * Executes a command. (Override with this)
     *
     * @param sender The sender of the command.
     * @param label The label of the command.
     * @param args The arguments of the command.
     * @return true if the command was executed successfully, false otherwise.
     */
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        for(CaramelCommand cmd : commandList) {
            if (cmd.getDetails().label().equalsIgnoreCase(label) || Arrays.asList(cmd.getDetails().aliases()).contains(label.toLowerCase())) {
                List<String> argList = Arrays.asList(args);
                if(sender instanceof Player) {
                    if(sender.hasPermission(cmd.getDetails().permission())) {
                        cmd.onPlayer((Player) sender, argList);
                    }else{
                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getConfig().getString("messages.no-permission")));
                    }
                }else {
                    cmd.onConsole(sender, argList);
                }
                return true;
            }
        }
        return false;
    }
    /**
     * Provides tab completion for a command.
     *
     * @param command The command to provide tab completion for.
     * @param args The arguments of the command.
     * @return A list of possible completions for the command.
     */
    public List<String> onTabComplete(@NotNull Command command, @NotNull String[] args) {
        for(CaramelCommand cmd : getCommandList()) {
            if(cmd.getDetails().label().equalsIgnoreCase(command.getLabel()) || Arrays.asList(cmd.getDetails().aliases()).contains(command.getLabel().toLowerCase())) {
                return cmd.complete(args);
            }
        }
        return Collections.emptyList();
    }

    @Deprecated
    public void removeAll(Plugin plugin) {
        List<CaramelCommand> toRemove = new ArrayList<CaramelCommand>();
        for(CaramelCommand cmd : commandList) {
            if(cmd.getDetails().plugin() == plugin) {
                toRemove.add(cmd);
            }
        }
        commandList.removeAll(toRemove);
    }
    /**
     * Returns the list of registered commands.
     *
     * @return The list of registered commands.
     */
    public List<CaramelCommand> getCommandList() {
        return commandList;
    }

}
