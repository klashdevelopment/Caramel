package dev.klash.caramel.commands;

import org.bukkit.plugin.Plugin;

public record CaramelCommandDetail(String label, String permission, Plugin plugin, String... aliases) {
    public CaramelCommandDetail(String label, String permission, Plugin plugin) {
        this(label, permission, plugin, new String[0]);
    }
}