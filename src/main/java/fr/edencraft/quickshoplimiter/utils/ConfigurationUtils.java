package fr.edencraft.quickshoplimiter.utils;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.lang.English;
import fr.edencraft.quickshoplimiter.lang.French;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;

/**
 * This class regroup all request that can be done with all configuration files
 * used by RandomTP plugin.
 */
public class ConfigurationUtils {

    private static final ConfigurationManager CM = QuickShopLimiter.getINSTANCE().getConfigurationManager();

    /**
     * This method return a configuration section depending on a file configuration and a path.
     * The return can be null !
     *
     * @param fileConfigurationName The name of the configuration file.
     * @param path The path to the configuration section.
     * @return The requested configuration section found by the given path.
     */
    @Nullable
    public static ConfigurationSection getConfigurationSection(String fileConfigurationName, String path)
    {
        FileConfiguration fg = CM.getConfigurationFile(fileConfigurationName);
        if (fg == null) return null;

        return fg.getConfigurationSection(path);
    }

    /**
     * @return The language selected in the RandomTP configuration file.
     */
    public static Object getLanguage(){
        String language = CM.getConfigurationFile("config.yml").getString("language");
        assert language != null;

        return switch (language) {
            case "fr" -> new French();
            case "en" -> new English();
            default -> throw new IllegalStateException("Unexpected value: " + language);
        };
    }

}
