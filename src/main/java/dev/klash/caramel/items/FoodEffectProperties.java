package dev.klash.caramel.items;

import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Experimental
/*
 * This class is a WIP and will be added in a future update
 * Do not use this in production, it wont apply to the item as of Caramel 1.21.4 (we swapped to data components)
 */
public record FoodEffectProperties(PotionEffect effect, float chance) {
}
