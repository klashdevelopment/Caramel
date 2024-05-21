# Commands
## First steps

Do these steps the **first time** you add Caramel to your plugin.
- Add listeners pointing to Caramel.
In your main class, add the following to your file (assuming you haven't already made listeners for these):
```java
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    return Caramel.getInstance().onCommand(sender, command, label, args);
}
public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
    return Caramel.getInstance().onTabComplete(sender, command, alias, args);
}
```
<details>
  <summary>
    Code with IntelliJ Annotations
  </summary>

```java
@Override
public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    return Caramel.getInstance().onCommand(sender, command, label, args);
}
@Override
public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
    return Caramel.getInstance().onTabComplete(sender, command, alias, args);
}
```
  
</details>


## A new command
First, lets make a brand new class and have it implement a Caramel Command. For this tutorial, I'll be using the class name "MyCommand" and main file "MyPlugin".
```java
public class MyCommand implements CaramelCommand {
    @Override
    public CaramelCommandDetail getDetails() {
        return ...;
    }

    @Override
    public void onPlayer(Player player, List<String> args) {
        player.sendMessage("Hello Player!");
    }

    @Override
    public void onConsole(CommandSender sender, List<String> args) {
        sender.sendMessage("Hello Console!");
    }
}
```

After this, fill in the getDetails function like below:
```java
public CaramelCommandDetail getDetails() {
    return new CaramelCommandDetail(
                "label", // Must be the same as in plugin.yml
                "plugin.permission", // Can be any permission, you should define your perms in plugin.yml tho
                MyPlugin.getPlugin(MyPlugin.class)
    );
}
```

# Registering
1. Make sure your command is added to your `plugin.yml` file under `commands`. It should have the basic layout of `commands > my-command-name > description+usage`.
2. Add a new variable in your main class to store your command:
```java
public class MyPlugin extends JavaPlugin {
    public CaramelCommand myCommand;
    
    // rest of your code below
}
```
3. Let caramel know about your command by registering & de-registering.
```java
    public void onEnable() {
        Caramel.getInstance().commands.register(myCommand = new MyCommand());
    }
    
    public void onDisable() {
        Caramel.getInstance().commands.getCommandList().remove(myCommand);
    }
```
4. All set! Your command is now registered.
