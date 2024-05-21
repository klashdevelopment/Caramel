# Commands
<details>
  <summary><h2>First time</h2></summary>

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
</details>

<details>
  <summary><h2>Creating a command class</h2></summary>

    First, lets make a brand new class and have it implement a Caramel Command. For this tutorial, I'll be using the class name "MyCommand" and main file "MyPlugin".
    ```java
    public class MyCommand implements CaramelCommand {}
    ```
    
</details>

<details>
  <summary><h2>Registering a command class</h2></summary>
</details>
