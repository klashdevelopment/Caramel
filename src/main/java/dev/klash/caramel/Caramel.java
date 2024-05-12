package dev.klash.caramel;

import com.samjakob.spigui.SpiGUI;
import dev.klash.caramel.commands.CaramelCommand;
import dev.klash.caramel.gui.CaramelGuiList;
import dev.klash.caramel.plugin.ImplCaramelDefault;
import dev.klash.caramel.recipe.CaramelRecipe;
import dev.klash.caramel.recipe.CaramelRecipeList;
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
    public CaramelCurrencyList currencies;
    public SpiGUI spigui;
    public CaramelGuiList guis;

    public String getPrefix() {
        return getConfig().getString("messages.caramel-prefix");
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        spigui = new SpiGUI(this);

        commands = new CaramelCommandList();
        items = new CaramelItemList();
        currencies = new CaramelCurrencyList();
        guis = new CaramelGuiList();

        getServer().getPluginManager().registerEvents(new CaramelListening(), this);

        commands.register(new ImplCaramelDefault.CaramelBaseDefaultCommand());
        items.register(new ImplCaramelDefault.CaramelBaseDefaultItem());
        currencies.register(new ImplCaramelDefault.CaramelBaseDefaultCurrency());
    }

    public CaramelRecipeList createRecipeList(CaramelRecipe... recipes) {
        CaramelRecipeList recipeList = new CaramelRecipeList();
        for(CaramelRecipe recipe : recipes) {
            recipeList.register(recipe);
        }
        return recipeList;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return commands.onTabComplete(command, args);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return commands.onCommand(sender, label, args);
    }
}
