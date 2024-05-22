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

**Function Guide**

| Function | Description | Args | Example |
| --- | --- | --- | --- |
| slot | Set a slot | Integer slot, ItemStack icon | slot(1, new ItemStack(Material.DIRT)) |
| slot | Set a slot with click | Integer slot, ItemStack icon, Consumer<InventoryClickEvent> action | slot(1, new ItemStack(Material.DIRT), (event)->{/* Do something */}) |
| add | Add to first air slot | ItemStack icon | add(new ItemStack(Material.DIRT)) |
| add | Add to first air slot with click | ItemStack icon, Consumer<InventoryClickEvent> action | add(new ItemStack(Material.DIRT), (event)->{/* Do something */}) |
| makeLine | Bulk add() | Integer amount, ItemStack icon | makeLine(9, new ItemStack(Material.DIRT)) |
| --- | --- | --- | --- |

### Override Vanilla Menus

Inside our constructor, we can choose if we'd like to override vanilla menus.

```java
public MyGUI() {
    super(...);
    
    // Example - Override crafting table menu no matter what 
    setOverride(CaramelGuiOverrides.CRAFTING_TABLE, alwaysTrue); // Caramel provides an alwaysTrue for Always overriding

    // Example - Override crafting table menu if player is in lobby
    setOverride(CaramelGuiOverrides.CRAFTING_TABLE, (player) -> player.getWorld().getName().equals("lobby"));
}
```

### Pagintation

This feature is **on its way** (90% done)

### Anvil and Sign Input Prompts

This feature is **on its way** (50% done)
