package fr.edencraft.quickshoplimiter;

import fr.edencraft.quickshoplimiter.lang.Language;
import fr.edencraft.quickshoplimiter.manager.CommandManager;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import fr.edencraft.quickshoplimiter.manager.ListenerManager;
import fr.edencraft.quickshoplimiter.utils.ColoredText;
import fr.edencraft.quickshoplimiter.utils.ConfigurationUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.maxgamer.quickshop.api.QuickShopAPI;

import java.util.logging.Level;

public final class QuickShopLimiter extends JavaPlugin {

    // PLUGIN PREFIX
    private final String pluginPrefix = new ColoredText("&dEden&eLimit &f&l» &r").treat();

    // API
    private QuickShopAPI quickShopAPI;

    // MANAGER
    private ConfigurationManager configurationManager;

    // INSTANCE
    private static QuickShopLimiter INSTANCE;

    @Override
    public void onEnable() {

        long delay = System.currentTimeMillis();

        INSTANCE = this;

        this.configurationManager = new ConfigurationManager(this);
        this.configurationManager.setupFiles();

        new CommandManager(this);
        new ListenerManager(this);

        Plugin quickShopPlugin = Bukkit.getPluginManager().getPlugin("QuickShop");
        if (quickShopPlugin != null && quickShopPlugin.isEnabled()) {
            QuickShopAPI quickshopApi = (QuickShopAPI)quickShopPlugin;
        }

        log(Level.INFO, "QuickShopLimiter enabled. (took " + (System.currentTimeMillis() - delay) + "ms)");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void log(Level level, String message) {
        Bukkit.getLogger().log(level, "[" + getPlugin(QuickShopLimiter.class).getName() + "] " + message);
    }

    public static QuickShopLimiter getINSTANCE() {
        return INSTANCE;
    }

    public QuickShopAPI getQuickShopAPI() {
        return quickShopAPI;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public String getPluginPrefix() {
        return pluginPrefix;
    }

    public Language getLanguage() {
        return (Language) ConfigurationUtils.getLanguage();
    }
}
