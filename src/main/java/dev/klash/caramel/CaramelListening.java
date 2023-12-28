package dev.klash.caramel;

import dev.klash.caramel.items.CaramelFactory;
import dev.klash.caramel.items.CaramelItem;
import dev.klash.caramel.items.ClickType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class CaramelListening implements Listener {
    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        // check item in main hand for all the CaramelItems. If one of them matches, call its onItemUse method.
        for (CaramelItem item : Caramel.getInstance().items.getItemList()) {
            if (event.getItem() != null && event.getItem().isSimilar(CaramelFactory.build(item))) {
                item.onItemUse(event.getAction().isLeftClick() ? ClickType.LEFT_CLICK : ClickType.RIGHT_CLICK, event.getItem(), event);
            }
        }
    }
}
