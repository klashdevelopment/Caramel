package dev.klash.caramel.gui;

import org.bukkit.Material;

public enum CaramelGuiOverrides {

    ANVIL(Material.ANVIL),
    BARREL(Material.BARREL),
    BEACON(Material.BEACON),
    BELL(Material.BELL),
    BLAST_FURNACE(Material.BLAST_FURNACE),
    BREWING_STAND(Material.BREWING_STAND),
    CARTOGRAPHY_TABLE(Material.CARTOGRAPHY_TABLE),
    COMPOSTER(Material.COMPOSTER),
    CRAFTING_TABLE(Material.CRAFTING_TABLE),
    ENCHANTING_TABLE(Material.ENCHANTING_TABLE),
    FURNACE(Material.FURNACE),
    GRINDSTONE(Material.GRINDSTONE),
    LECTERN(Material.LECTERN),
    LOOM(Material.LOOM),
    SMITHING_TABLE(Material.SMITHING_TABLE),
    SMOKER(Material.SMOKER),
    STONECUTTER(Material.STONECUTTER),
    HOPPER(Material.HOPPER),
    SHULKER_BOX(Material.SHULKER_BOX),
    CHEST(Material.CHEST),
    ENDER_CHEST(Material.ENDER_CHEST),
    TRAPPED_CHEST(Material.TRAPPED_CHEST),
    DISPENSER(Material.DISPENSER),
    DROPPER(Material.DROPPER);

    public final Material mat;

    public Material getMaterial() {
        return mat;
    }

    CaramelGuiOverrides(Material mat) {
        this.mat = mat;
    }
}
