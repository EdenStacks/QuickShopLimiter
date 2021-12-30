package fr.edencraft.quickshoplimiter.utils;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class CommandCompletionUtils {

    private static final ConfigurationManager CM = QuickShopLimiter.getINSTANCE().getConfigurationManager();

    /**
     * This method return into string list all configuration file available in this plugin.
     *
     * @return The list of available configuration file.
     */
    public static List<String> getConfigurationFilesName() {
        List<String> cfgList = new ArrayList<>();
        CM.getFilesMap().forEach((file, fileConfiguration) -> {
            cfgList.add(file.getName());
        });
        return cfgList;
    }

    /**
     * @return All limited shop id present in Shops.yml.
     */
    public static List<String> getAllLimitedShopID() {
        FileConfiguration cfg = CM.getConfigurationFile("Shops.yml");
        ConfigurationSection shopsSection = cfg.getConfigurationSection("shops");
        assert shopsSection != null;

        return new ArrayList<>(shopsSection.getKeys(false));
    }

}
