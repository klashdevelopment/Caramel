package dev.klash.caramel.items;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface CaramelItem {
    public CaramelItemDetail getDetails();
    public ItemStack modifyBeforeFinalizing(ItemStack item);
    public void onItemUse(ClickType type, ItemStack item, PlayerInteractEvent event);
}
