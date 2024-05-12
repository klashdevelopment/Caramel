package dev.klash.caramel.recipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CaramelRecipeValueItem extends CaramelRecipeValue<ItemStack> {

    ItemStack stack;
    public CaramelRecipeValueItem(char id, ItemStack material) {
        super(id);
        this.stack = material;
    }
    @Override
    public ItemStack getValue() {
        return stack;
    }
}
