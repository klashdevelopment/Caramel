package dev.klash.caramel.items;

import com.samjakob.spigui.item.ItemBuilder;
import dev.klash.caramel.Caramel;
import dev.klash.caramel.CaramelUtility;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class CaramelFactory {
    public static ItemStack build(CaramelItem item) {
        ItemStack i = new ItemStack(item.getDetails().itemBase());
        ItemMeta m = i.getItemMeta();
        m.lore(CaramelUtility.formatLoreString(item.getDetails().lore()));
        m.displayName(item.getDetails().itemName());
        m.setCustomModelData(item.getDetails().modelData());


        m.getPersistentDataContainer().set(Caramel.isCaramelKey, PersistentDataType.BOOLEAN, true);
        m.getPersistentDataContainer().set(Caramel.caramelIDKey, PersistentDataType.STRING, item.getDetails().id());

        i.setItemMeta(m);
        i.setAmount(item.getDetails().defaultStack());
        return item.modifyBeforeFinalizing(i);
    }
}
