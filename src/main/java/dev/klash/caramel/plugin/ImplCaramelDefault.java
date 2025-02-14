package dev.klash.caramel.plugin;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import com.samjakob.spigui.menu.SGMenu;
import dev.klash.caramel.Caramel;
import dev.klash.caramel.commands.CaramelCommand;
import dev.klash.caramel.commands.CaramelCommandDetail;
import dev.klash.caramel.CaramelUtility;
import dev.klash.caramel.currency.CaramelCurrency;
import dev.klash.caramel.gui.CaramelGui;
import dev.klash.caramel.items.CaramelFactory;
import dev.klash.caramel.items.CaramelItem;
import dev.klash.caramel.items.CaramelItemDetail;
import dev.klash.caramel.items.ClickType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.*;

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

    public static class CaramelBaseDefaultCurrency extends CaramelCurrency {
        public CaramelBaseDefaultCurrency() {
            super(Caramel.getInstance(), "Caramel Coins", "caramelcoin", 10);
        }
    }
    public static class CaramelBaseDefaultCommand implements CaramelCommand {

        @Override
        public CaramelCommandDetail getDetails() {
            return new CaramelCommandDetail("caramel","caramel.commands.base",Caramel.getInstance());
        }

        @Override
        public List<String> complete(String[] args) {
            if(args.length == 1) {
                return CaramelUtility.tabComplete(args[0], Arrays.asList("help", "version", "commands", "items", "currency", "reload", "guis"));
            }
            if(args.length == 2) {
                if(args[0].equalsIgnoreCase("currency")) {
                    return CaramelUtility.tabComplete(args[1], Arrays.asList("list", "give", "set", "get"));
                }
            }
            return Collections.emptyList();
        }

        public void whenAnyRun(CommandSender sender, List<String> args) {
            if(!args.isEmpty()) {
                if(args.get(0).equalsIgnoreCase("help")) {
                    sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Valid subcommands are <red>reload<green>, <red>help<green>, <red>version<green>, <red>commands<green>, <red>items<green>, <red>currency"));
                }
                if(args.get(0).equalsIgnoreCase("reload")) {
                    sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " This will fail if PlugManX is not installed!"));
                    List<String> plugins = new ArrayList<>();
                    for(Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
                        if(!plugin.getDescription().getDepend().contains("Caramel")) continue;
                        if(!plugins.contains(plugin.getName())) {
                            plugins.add(plugin.getName());
                        }
                    }
                    sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Click ").append(
                            Component.text("here").hoverEvent(HoverEvent.showText(Component.text("/caramel _reload " + String.join(" ", plugins)))).clickEvent(ClickEvent.runCommand("/caramel _reload " + String.join(" ", plugins))).color(TextColor.color(0x0000FF))
                    ).append(CaramelUtility.colorcomp("<green> to finish the job.")));

                    Bukkit.getServer().dispatchCommand(sender, "plugman reload Caramel");
                }
                if(args.get(0).equalsIgnoreCase("_reload")) {
                    for(String plugin : args.subList(1, args.size())) {
                        Bukkit.getServer().dispatchCommand(sender, "plugman reload " + plugin);
                    }
                }
                if(args.get(0).equalsIgnoreCase("version")) {
                    sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Running at version " + Caramel.getInstance().getDescription().getVersion()));
                }
                if(args.get(0).equalsIgnoreCase("commands")) {
                    sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Total command list:"));
                    for(CaramelCommand cmd : Caramel.getInstance().commands.getCommandList()) {
                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " <red>/" + cmd.getDetails().label() +" <green>from <red>" + cmd.getDetails().plugin().getName()));
                    }
                }
                if(args.get(0).equalsIgnoreCase("currency")) {
                    if(args.size() == 1) {
                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Valid subcommands are <red>list<green>, <red>give<green>, <red>set<green>, <red>get"));
                    }else {
                        switch(args.get(1)) {
                            case "list":
                                sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Listing currencies"));
                                for(CaramelCurrency currency : Caramel.getInstance().currencies.getCurrencyList()) {
                                    sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + "<green> ID <red>" + currency.id +" <green>with name <red>" + currency.name + " <green>from <red>" + currency.plugin.getName()));
                                }
                                break;
                            case "give":
                                if(args.size() == 5) {
                                    if (sender instanceof Player) {
                                        UUID target = Bukkit.getOfflinePlayer(args.get(2)).getUniqueId();
                                        CaramelCurrency currency = Caramel.getInstance().currencies.find(args.get(3));
                                        if (currency != null) {
                                            int amount = Integer.parseInt(args.get(4));
                                            currency.addValue(target, amount);
                                            sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Gave <red>" + amount + " <green>of currency <red>" + currency.name + " <green>to user!"));
                                        } else {
                                            sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Invalid currency!"));
                                        }
                                    } else {
                                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " This command can only be run by a player!"));
                                    }
                                }
                                break;
                            case "get":
                                if(args.size() == 4) {
                                    if (sender instanceof Player) {
                                        UUID target = Bukkit.getOfflinePlayer(args.get(2)).getUniqueId();
                                        CaramelCurrency currency = Caramel.getInstance().currencies.find(args.get(3));
                                        if (currency != null) {
                                            sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " User has <red>" + currency.getValue(target) + " <green>of currency <red>" + currency.name + "<green>!"));
                                        } else {
                                            sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Invalid currency!"));
                                        }
                                    } else {
                                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " This command can only be run by a player!"));
                                    }
                                }
                                break;
                            case "set":
                                if(args.size() == 5) {
                                    if (sender instanceof Player) {
                                        UUID target = Bukkit.getOfflinePlayer(args.get(2)).getUniqueId();
                                        CaramelCurrency currency = Caramel.getInstance().currencies.find(args.get(3));
                                        if (currency != null) {
                                            int amount = Integer.parseInt(args.get(4));
                                            if (amount != -1) {
                                                currency.setValue(target, amount);
                                                sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Set user's amount to <red>" + amount + " <green>of currency <red>" + currency.name + "<green>!"));
                                            } else {
                                                sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Invalid amount!"));
                                            }
                                        } else {
                                            sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Invalid currency!"));
                                        }
                                    } else {
                                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " This command can only be run by a player!"));
                                    }
                                }
                                break;
                        }
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
                    } else {
                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Listing items"));
                        for(CaramelItem item : Caramel.getInstance().items.getItemList()) {
                            sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + "<green> ID <red>" + item.getDetails().id() +" <green> with name <red>" + item.getDetails().itemName()));
                        }
                    }
                }
                if(args.get(0).equalsIgnoreCase("guis")) {
                    if(sender instanceof Player) {
                        SGMenu menu = Caramel.getInstance().spigui.create("&c&lCaramel Guis", 5);
                        menu.setAutomaticPaginationEnabled(true);
                        for(CaramelGui gui : Caramel.getInstance().guis.getGuis()) {
                            menu.addButton(new SGButton(new ItemBuilder(Material.GRASS_BLOCK).name(gui.title).build()).withListener((event)->{
                                event.getWhoClicked().closeInventory();
                                gui.open((Player) event.getWhoClicked());
                            }));
                        }
                        ((Player)sender).openInventory(menu.getInventory());
                    } else {
                        sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Listing guis"));
                        for(CaramelGui gui : Caramel.getInstance().guis.getGuis()) {
                            sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + "<green> Title <red>" + gui.title +" <green> with override <red>" + (gui.override==null?"none":gui.override.name())));
                        }
                    }
                }
            }else {
                sender.sendMessage(CaramelUtility.colorcomp(Caramel.getInstance().getPrefix() + " Valid subcommands are <red>reload<green>, <red>help<green>, <red>version<green>, <red>commands<green>, <red>items<green>, <red>currency"));
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
