package dev.klash.caramel.recipe;

/**
 * Represents a recipe detail.
 * @param values The values of the recipe.
 * @param pattern Leave blank if the recipe is shapeless.
 */
public record CaramelRecipeDetail(CaramelRecipeValue[] values, String[] pattern) {
    public CaramelRecipeDetail of(CaramelRecipeValue[] values, String[] pattern) {
        return new CaramelRecipeDetail(values, pattern);
    }
}
