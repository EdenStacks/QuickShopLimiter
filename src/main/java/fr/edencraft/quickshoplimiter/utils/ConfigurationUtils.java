package fr.edencraft.quickshoplimiter.utils;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.lang.English;
import fr.edencraft.quickshoplimiter.lang.French;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.shop.Shop;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * This class regroup all request that can be done with all configuration files
 * used by QuickShopLimiter plugin.
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
     * @return The language selected in the default configuration file.
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

    /**
     * This method check if a shop has active limit.
     * It basically check if the location of the {@link Shop} is in Shops.yml.
     *
     * @param shop The shop defined by {@link Shop} in {@link org.maxgamer.quickshop.QuickShop}
     * @return Status of the check.
     */
    public static boolean isLimitedShop(Shop shop) {
        FileConfiguration cfg = CM.getConfigurationFile("Shops.yml");
        ConfigurationSection shopsSection = cfg.getConfigurationSection("shops");
        assert shopsSection != null;
        AtomicBoolean state = new AtomicBoolean(false);

        Stream<String> stream = shopsSection.getKeys(false).stream();
        stream.map(id -> shopsSection.getConfigurationSection(id + ".location")).forEach(locationSection -> {
            assert locationSection != null;

            String worldName = locationSection.getString("world");
            if (worldName == null) return;

            World world = Bukkit.getWorld(worldName);
            double x = locationSection.getDouble("x");
            double y = locationSection.getDouble("y");
            double z = locationSection.getDouble("z");

            Location location = new Location(world, x, y, z);

            if (shop.getLocation().equals(location)) state.set(true);
        });

        return state.get();
    }

    /**
     * Be careful, if the given {@link Shop} hasn't active limit it'll return null.
     *
     * @param shop The shop defined by {@link Shop} in {@link org.maxgamer.quickshop.QuickShop}
     * @return The id of the {@link LimitedShop}.
     */
    @Nullable
    public static String getLimitedShopID(Shop shop) {
        if (!isLimitedShop(shop)) return null;

        FileConfiguration cfg = CM.getConfigurationFile("Shops.yml");
        ConfigurationSection shopsSection = cfg.getConfigurationSection("shops");
        assert shopsSection != null;
        AtomicReference<String> id = new AtomicReference<>(null);

        Stream<String> stream = shopsSection.getKeys(false).stream();
        stream.forEach(s -> {
            ConfigurationSection shopSection = shopsSection.getConfigurationSection(s);
            assert shopSection != null;

            String worldName = shopSection.getString("location.world");
            if (worldName == null) return;

            World world = Bukkit.getWorld(worldName);
            double x = shopSection.getDouble("location.x");
            double y = shopSection.getDouble("location.y");
            double z = shopSection.getDouble("location.z");

            Location location = new Location(world, x, y, z);

            if (location.equals(shop.getLocation())) id.set(s);
        });
        return id.get();
    }


}
