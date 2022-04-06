package fr.edencraft.quickshoplimiter.utils;

import org.bukkit.ChatColor;

public record ColoredText(String coloredText) {

    public String treat() {
        return ChatColor.translateAlternateColorCodes('&', this.coloredText);
    }

}
