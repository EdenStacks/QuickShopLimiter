package fr.edencraft.quickshoplimiter.filler;

import fr.edencraft.quickshoplimiter.utils.CFGFiller;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class StorageFiller implements CFGFiller {

    @Override
    public void fill(FileConfiguration fileConfiguration) {
        ConfigurationSection storageSection = fileConfiguration.createSection("storage");
    }

}
