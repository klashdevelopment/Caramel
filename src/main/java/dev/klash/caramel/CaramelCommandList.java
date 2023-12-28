package dev.klash.caramel;

import dev.klash.caramel.commands.CaramelCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CaramelCommandList {
    protected CaramelCommandList() {}
    private List<CaramelCommand> commandList = new ArrayList<CaramelCommand>();

    public void register(CaramelCommand command) {
        commandList.add(command);
    }
    public CaramelCommand find(String label) {
        for(CaramelCommand cmd : commandList) {
            if(cmd.getDetails().label() == label) {
                return cmd;
            }
        }
        return null;
    }
    public boolean onCommand(CommandSender sender, String label, String[] args) {
        for(CaramelCommand cmd : commandList) {
            if(cmd.getDetails().label().equalsIgnoreCase(label)) {
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
    public void derail(String label) {
        for(CaramelCommand cmd : commandList) {
            if(cmd.getDetails().label() == label) {
                commandList.remove(cmd);
            }
        }
    }

    public List<CaramelCommand> getCommandList() {
        return commandList;
    }

}
