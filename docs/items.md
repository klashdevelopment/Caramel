# Items

The documentation for items are WIP, but here's a simple implementation while you wait:

```java
    public class MyItem implements CaramelItem {

        @Override
        public CaramelItemDetail getDetails() {
            return CaramelItemDetail.builder()
                    .id("item_identifier") // Required
                    .defaultStack(1) // Optional
                    .modelData(0) // Optional - Custom Model Data
                    .itemBase(Material.PAPER) // Required
                    .itemName(CaramelUtility.colorcomp("<gold><bold>Supports miniMessage")) // Required
                    .lore(Arrays.asList("Lore <red>is cool<yellow>!")) // Required
					.components(...) // Optional, see below
                    .build();
        }

        @Override
        public ItemStack modifyBeforeFinalizing(ItemStack item) {
            // You can add custom modifications (attributes) here.
            // Return the item back to keep it the same.
            return item;
        }

        @Override
        public void onItemUse(ClickType type, ItemStack item, PlayerInteractEvent event) {
            // Handle interactions here.
            // ClickType is a dev.klash.caramel.items, not bukkit.
            event.getPlayer().sendMessage(CaramelUtility.colorcomp("Hello!"));
        }
    }
```

Please see Commands for registering, but use `Caramel.getInstance().items` and define it as a `CaramelItem`.


### Item components
As of caramel 1.3.1 (1.21.4) you can add item components!

```java
/// builder stuff
.components(
	CIComponent.of(DataComponentType.EXAMPLE, example), // valued
	CIComponent.of(DataComponentType.EXAMPLE) // non valued
)
```