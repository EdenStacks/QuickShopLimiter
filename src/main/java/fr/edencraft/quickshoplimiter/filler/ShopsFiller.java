package fr.edencraft.quickshoplimiter.filler;

import fr.edencraft.quickshoplimiter.utils.CFGFiller;
import org.bukkit.configuration.file.FileConfiguration;

public class ShopsFiller implements CFGFiller {

    @Override
    public void fill(FileConfiguration fileConfiguration) {
        fileConfiguration.createSection("shops");
    }

}
