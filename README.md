# Caramel


## Quickstart
Add it to your maven.

pom.xml
```xml
<repository>
   <id>klashdevelopment</id>
   <url>https://raw.githubusercontent.com/klashdevelopment/mvn/main/repository/</url>
</repository>

<dependency>
   <groupId>dev.klash</groupId>
   <artifactId>Caramel</artifactId>
   <version>1.0.0</version>
   <scope>provided</scope>
</dependency>
```
plugin.yml
```yml
depend: [Caramel]
```

## Command
# DOCS BELOW ARE DEPRECATED
Create a command by:
- Create a new class implementing CaramelCommand and add in the methods.
- Make getDetails return a new CaramelCommandDetails(label, permission, plugin)
- Fill in onPlayer and onConsole
- Add the command label to your plugin.yml > commands

Register in your onEnable. Make sure to store your commands as variables!
```java
    CaramelCommand myCoolCommand;
    
    public void onEnable() {
        Caramel caramel = Caramel.getInstance();
        caramel.commands.register(myCoolCommand = new MyCommand());
    }
```
When you disable your plugin, let Caramel know.
```java
    public void onDisable() {
        Caramel.getInstance().commands.getCommandList().remove(cmd);
    }
```
Let caramel know when you recieve a command!
```java
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return Caramel.getInstance().commands.onCommand(sender, label, args);
    }
```
