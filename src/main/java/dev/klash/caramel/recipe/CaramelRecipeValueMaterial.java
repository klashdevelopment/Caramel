package dev.klash.caramel.recipe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CaramelRecipeValueMaterial extends CaramelRecipeValue<Material> {

    Material stack;
    public CaramelRecipeValueMaterial(char id, Material material) {
        super(id);
        this.stack = material;
    }
    @Override
    public Material getValue() {
        return stack;
    }
}
