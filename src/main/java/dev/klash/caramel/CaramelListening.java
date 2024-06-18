package dev.klash.caramel;

import dev.klash.caramel.gui.CaramelGui;
import dev.klash.caramel.items.CaramelFactory;
import dev.klash.caramel.items.CaramelItem;
import dev.klash.caramel.items.ClickType;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

public class CaramelListening implements Listener {
    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        // check item in main hand for all the CaramelItems. If one of them matches, call its onItemUse method.
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            for(CaramelGui gui : Caramel.getInstance().guis.getGuis()) {
                if(gui.override != null) {
                    if(gui.override.getMaterial() == event.getClickedBlock().getType()) {
                        if(gui.finalCheck.apply(event.getPlayer())) {
                            event.setCancelled(true);
                            event.getPlayer().closeInventory();
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Caramel.getInstance(), () -> gui.open(event.getPlayer()), 1L);
                        }
                    }
                }
            }
        }
        if(event.getItem().getItemMeta().getPersistentDataContainer().has(Caramel.getInstance().isCaramelKey, PersistentDataType.BOOLEAN)) {
            for (CaramelItem item : Caramel.getInstance().items.getItemList()) {
                if (event.getItem() == null || event.getItem().getItemMeta() == null) continue;
                String itemID = item.getDetails().id();
                String heldItemID = event.getItem().getItemMeta().getPersistentDataContainer().get(Caramel.getInstance().caramelIDKey, PersistentDataType.STRING);
                if (event.getItem() != null && heldItemID.equalsIgnoreCase(itemID)) {
                    item.onItemUse(event.getAction().isLeftClick() ? ClickType.LEFT_CLICK : ClickType.RIGHT_CLICK, event.getItem(), event);
                    if (item.cancelEvent()) event.setCancelled(true);
                }
            }
        }
    }
}