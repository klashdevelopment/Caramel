# Commands
## First time
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

## Creating your command class

## Registering
