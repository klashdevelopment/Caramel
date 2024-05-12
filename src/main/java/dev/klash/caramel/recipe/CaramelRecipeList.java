package dev.klash.caramel.recipe;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class CaramelRecipeList {
    public CaramelRecipeList() {}
    private List<CaramelRecipe> recipeList = new ArrayList<CaramelRecipe>();

    public void register(CaramelRecipe recipe) {
        recipeList.add(recipe);
    }

    public List<CaramelRecipe> getRecipeList() {
        return recipeList;
    }

    public void cleanup() {
        for(CaramelRecipe recipe : recipeList) {
            Bukkit.removeRecipe(recipe.getKey());
        }
        recipeList.clear();
    }

    public void registerAll() {
        for(CaramelRecipe recipe : recipeList) {
            recipe.registerRecipe();
        }
    }
}
