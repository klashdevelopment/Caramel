package dev.klash.caramel.plugin;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.menu.SGMenu;
import dev.klash.caramel.Caramel;
import dev.klash.caramel.commands.CaramelCommand;
import dev.klash.caramel.commands.CaramelCommandDetail;
import dev.klash.caramel.CaramelUtility;
import dev.klash.caramel.items.CaramelFactory;
import dev.klash.caramel.items.CaramelItem;
import dev.klash.caramel.items.CaramelItemDetail;
import dev.klash.caramel.items.ClickType;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ImplCaramelDefault {
    public static class CaramelBaseDefaultItem implements CaramelItem {

        @Override
        public CaramelItemDetail getDetails() {
            return CaramelItemDetail.builder()
                    .id("caramel_test_item")
                    .defaultStack(5)
                    .itemBase(Material.PAPER)
                    .itemName(CaramelUtility.colorcomp("<gold><bold>Caramel Test Item"))
                    .lore(Arrays.asList("<purple>This is a test item", "<yellow>for the Caramel plugin"))
                    .build();
        }

        @Override
        public ItemStack modifyBeforeFinalizing(ItemStack item) {
            return item;
        }

        @Override
        public void onItemUse(ClickType type, ItemStack item, PlayerInteractEvent event) {
            event.getPlayer().sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " You used the test item!"));
        }
    }
    public static class CaramelBaseDefaultCommand implements CaramelCommand {

        @Override
        public CaramelCommandDetail getDetails() {
            return new CaramelCommandDetail("caramel","caramel.commands.base",Caramel.getInstance());
        }

        @Override
        public List<String> complete(String[] args) {
            if(args.length > 0) {
                return CaramelUtility.tabComplete(args[0], Arrays.asList("version", "commands", "items"));
//                return Arrays.asList("version", "commands");
            }
            return Collections.emptyList();
        }

        public void whenAnyRun(CommandSender sender, List<String> args) {
            if(!args.isEmpty()) {
                if(args.get(0).equalsIgnoreCase("version")) {
                    sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Running at version " + Caramel.getInstance().getDescription().getVersion()));
                }
                if(args.get(0).equalsIgnoreCase("commands")) {
                    sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Listing commands"));
                    for(CaramelCommand cmd : Caramel.getInstance().commands.getCommandList()) {
                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " <red>/" + cmd.getDetails().label() +" <green>from plugin <red>" + cmd.getDetails().plugin().getName()));
                    }
                }
                if(args.get(0).equalsIgnoreCase("items")) {
                    if(sender instanceof Player) {
                        SGMenu menu = Caramel.getInstance().spigui.create("&c&lCaramel Items", 5);
                        menu.setAutomaticPaginationEnabled(true);
                        for(CaramelItem item : Caramel.getInstance().items.getItemList()) {
                            menu.addButton(new SGButton(CaramelFactory.build(item)).withListener((event)->{
                                event.getWhoClicked().getInventory().addItem(CaramelFactory.build(item));
                                event.getWhoClicked().closeInventory();
                            }));
                        }
                        ((Player)sender).openInventory(menu.getInventory());
                    }else {
                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Listing items"));
                        for(CaramelItem item : Caramel.getInstance().items.getItemList()) {
                            sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + "<green> ID <red>" + item.getDetails().id() +" <green> with name <red>" + item.getDetails().itemName()));
                        }
                    }
                }
            }else {
                sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Running at version " + Caramel.getInstance().getDescription().getVersion()));
            }
        }

        @Override
        public void onPlayer(Player player, List<String> args) {
            whenAnyRun(player, args);
        }

        @Override
        public void onConsole(CommandSender sender, List<String> args) {
            whenAnyRun(sender, args);
        }
    }
}
