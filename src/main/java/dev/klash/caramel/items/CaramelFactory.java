package dev.klash.caramel.items;

import com.samjakob.spigui.item.ItemBuilder;
import dev.klash.caramel.Caramel;
import dev.klash.caramel.CaramelUtility;
import dev.klash.caramel.items.components.CIComponent;
import io.papermc.paper.datacomponent.DataComponentTypes;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import io.papermc.paper.datacomponent.item.FoodProperties;
import org.bukkit.persistence.PersistentDataType;

public class CaramelFactory {
    // Allow using <T> parameter
    private static <T> void setDataHelper(ItemStack i, CIComponent.CIComponentValued<T> component) {
        i.setData(component.getComponent(), component.getValue());
    }
    public static ItemStack build(CaramelItem item) {
        ItemStack i = new ItemStack(item.getDetails().itemBase());
        ItemMeta m = i.getItemMeta();
        m.lore(CaramelUtility.formatLoreString(item.getDetails().lore()));
        m.displayName(item.getDetails().itemName());
        m.setCustomModelData(item.getDetails().modelData());

        if(item.getDetails().food() != null) {
            FoodProperties food = FoodProperties.food()
                    .canAlwaysEat(item.getDetails().food().canAlwaysEat())
                    .nutrition(item.getDetails().food().nutrition())
                    .saturation(item.getDetails().food().saturation())
                    .build();
            i.setData(DataComponentTypes.FOOD, food);
        }
        for (CIComponent component : item.getDetails().components()) {
            if(component instanceof CIComponent.CIComponentValued<?> valuedComponent) {
                setDataHelper(i, valuedComponent);
            } else {
                i.setData(((CIComponent.CIComponentNonvalued) component).getComponent());
            }
        }

        m.getPersistentDataContainer().set(Caramel.getInstance().isCaramelKey, PersistentDataType.BOOLEAN, true);
        m.getPersistentDataContainer().set(Caramel.getInstance().caramelIDKey, PersistentDataType.STRING, item.getDetails().id());

        i.setItemMeta(m);
        i.setAmount(item.getDetails().defaultStack());
        return item.modifyBeforeFinalizing(i);
    }
}
