package dev.klash.caramel.recipe;

import org.bukkit.Material;

public abstract class CaramelRecipeValue<T> {
    private char id;
    protected CaramelRecipeValue(char id){this.id=id;};
    public char getId(){return id;};

    public abstract T getValue();
}