package dev.klash.caramel.recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public abstract class CaramelRecipe {
    public abstract ItemStack getResult();
    public abstract CaramelRecipeDetail getDetails();
    public boolean isShaped() {return true;};
    public abstract NamespacedKey getKey();
    public Recipe createRecipe() {
        if(isShaped()) {
            ShapedRecipe recipe = new ShapedRecipe(getKey(), getResult());
            recipe.shape(getDetails().pattern());
            for(CaramelRecipeValue<?> value : getDetails().values()) {
                if(value.getValue() instanceof ItemStack) {
                    recipe.setIngredient(value.getId(), (ItemStack) value.getValue());
                } else {
                    recipe.setIngredient(value.getId(), (Material) value.getValue());
                }
            }
            return recipe;
        } else {
            ShapelessRecipe recipe = new ShapelessRecipe(getKey(), getResult());
            for(CaramelRecipeValue<?> value : getDetails().values()) {
                if(value.getValue() instanceof ItemStack) {
                    recipe.addIngredient((ItemStack) value.getValue());
                } else {
                    recipe.addIngredient((Material) value.getValue());
                }
            }
            return recipe;
        }
    };

    public void registerRecipe() {
        Bukkit.addRecipe(createRecipe());
    }
}
