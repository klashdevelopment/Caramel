# Caramel GUIs

Caramel GUIs provide a quick and easy way to make interactive GUIs.

## A fresh menu

Lets start by creating our own GUI. Make a new class - for this tutorial, I'll call the class "MyGUI" and plugin "MyPlugin".

```java
public class MyGUI extends CaramelGui {
    public MyGUI() {
        super("Title", rows); // Replace rows with # of rows
    }
    public void setup(Player player) {
        ...
    }
}
```

### Setup Function

Inside the `setup#` function, we can add and set items using functions ( see function guide below ). Heres an example:

```java
    makeLine(9, new ItemStack(Material.STONE)); // add() 9 blocks of stone automatically
    add((new ItemBuilder(Material.DIAMOND)).name("&bDiamond!").build());
    add((new ItemBuilder(Material.EMERALD)).name("&aClick me").build(), (player) -> {
        player.sendMessage("Item clicked!");
    });
```

### Override Vanilla Menus

Inside our constructor, we can choose if we'd like to override vanilla menus.

```java
public MyGUI() {
    super(...);
    
    // Example - Override crafting table menu no matter what
    setOverride(CaramelGuiOverrides.CRAFTING_TABLE, alwaysTrue);

    // Example - Override crafting table menu if player is op
    setOverride(CaramelGuiOverrides.CRAFTING_TABLE, (player) -> player.isOP());
}
```

### Pagintation

This feature is **on its way** (Functionality is coded and possible, but still combining with other features)

### Anvil and Sign Input Prompts

This feature is **on its way** (Anvil prompts are almost done, sign prompts are still WIP)
