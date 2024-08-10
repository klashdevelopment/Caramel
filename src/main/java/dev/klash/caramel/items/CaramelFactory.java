package dev.klash.caramel.items;

import com.samjakob.spigui.item.ItemBuilder;
import dev.klash.caramel.Caramel;
import dev.klash.caramel.CaramelUtility;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
import org.bukkit.persistence.PersistentDataType;

public class CaramelFactory {
    public static ItemStack build(CaramelItem item) {
        ItemStack i = new ItemStack(item.getDetails().itemBase());
        ItemMeta m = i.getItemMeta();
        m.lore(CaramelUtility.formatLoreString(item.getDetails().lore()));
        m.displayName(item.getDetails().itemName());
        m.setCustomModelData(item.getDetails().modelData());

        if(item.getDetails().food() != null) {
            FoodComponent food = m.getFood();
            food.setCanAlwaysEat(item.getDetails().food().canAlwaysEat());
            food.setNutrition(item.getDetails().food().nutrition());
            food.setSaturation(item.getDetails().food().saturation());
            food.setEatSeconds(item.getDetails().food().eatTime());
            for(FoodEffectProperties fep : item.getDetails().food().effects()) {
                food.addEffect(fep.effect(), fep.chance());
            }
            m.setFood(food);
        }

        m.getPersistentDataContainer().set(Caramel.getInstance().isCaramelKey, PersistentDataType.BOOLEAN, true);
        m.getPersistentDataContainer().set(Caramel.getInstance().caramelIDKey, PersistentDataType.STRING, item.getDetails().id());

        i.setItemMeta(m);
        i.setAmount(item.getDetails().defaultStack());
        return item.modifyBeforeFinalizing(i);
    }
}
