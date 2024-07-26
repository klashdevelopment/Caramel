# Recipes

## Making each recipe
First, get started by making a new class for your recipe, and implementing all the methods:
```java
public class MyFirstRecipe extends CaramelRecipe {
  public ItemStack getResult() {return ...;};
  public CaramelRecipeDetail getDetails() {return ...;};
  public NamespacedKey getKey() {return ...;};
}
```

Result needs to be an ItemStack to return as the output.

Key needs to be a NamespacedKey with your Plugin/Identifier and the recipe's identifier.

The details are where we define the actual recipe:
```java
CaramelRecipeDetail.of(
  new CaramelRecipeValue[] {},
  new String[] {
    "   ",
    "   ",
    "   "
  }
);
```

The array of values are your mappings for your pattern. See the values section below.

The array of strings is your pattern, and should be 3x tripple-letter strings.


<br/><br/>


## Recipe Values
A recipe value is a mapping of character <--> an itemstack or material to be used in the crafting grid.

You can create these by the `CaramelRecipeValueItem` and `CaramelRecipeValueMaterial` constructors:

```java
// A material value is only the material.
new CaramelRecipeValueMaterial('D', Material.DIAMOND)

// A item value is for more specific recipe values.
new CaramelRecipeValueItem('D', new ItemStack(Material.DIAMOND))
```


<br/><br/>


## Registering your recipes
Recipes are registered through RecipeLists. You can create these directly from the instance of Caramel:

```java
// Define your list in your class:
CaramelRecipeList myRecipes;

// Inside your onEnable:
recipeList = Caramel.createRecipeList(
  myFirstRecipe,
  mySecondRecipe
);
recipeList.registerAll();

// Inside your onDisable:
recipeList.cleanup();
```
