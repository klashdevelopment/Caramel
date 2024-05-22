# Commands
### First steps

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

### A new command
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

### Registering
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

<br/><br/><br/>

## The garnish on top
### (OPTIONAL) Tab Completion
Step 1 - Dead simple - Add this code to your command file:
```java
    @Override
    public List<String> complete(String[] args) {
        ...
    }
```

#### Tab completion (the boring way)
Simply `return` a List<> of your choices.
```java
return Arrays.asList("my", "choices");
```

#### With a little spice
Caramel comes bundled with a tab completion handler for some no-hassle satisfying tab completion.
```java
// Both of these example only work for one argument, as we only use "args[0]". See below for more.

return CaramelUtility.tabComplete(args[0], Arrays.asList("example", "choices"));

// OR, use Caramel's tabCompletePlayers option.

return CaramelUtility.tabCompletePlayers(args[0], Bukkit.getOnlinePlayers());
```
This will shorten the tab completion options based on how much you have already typed (like autocomplete). To further the complexity (aka if you have multiple args) you can handle it based on what argument you are on. Example:

Lets say you have a command with the usage /command <pet> <name> <player>.
Your args would be ["pet", "name", "player"] (0,1,2). Lets try this:

```java
if(args.length == 1) { // first argument - pet
    String arg = args[0]; // [0] is the first argument in an array.
    return CaramelUtility.tabComplete(arg, Arrays.asList("cat", "dog"));
} else if(args.length == 2) { // next argument - name, no tab completion
    return Collections.emptyList();
} else if(args.length == 3) { // last argument - player, player completion
    String arg = args[2];
    return CaramelUtility.tabCompletePlayers(arg, Bukkit.getOnlinePlayers());
}
```

Nice!
