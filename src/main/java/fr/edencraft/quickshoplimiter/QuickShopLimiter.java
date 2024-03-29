package fr.edencraft.quickshoplimiter;

import fr.edencraft.quickshoplimiter.lang.Language;
import fr.edencraft.quickshoplimiter.manager.CommandManager;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import fr.edencraft.quickshoplimiter.manager.ListenerManager;
import fr.edencraft.quickshoplimiter.runnable.ResetChecker;
import fr.edencraft.quickshoplimiter.utils.ColoredText;
import fr.edencraft.quickshoplimiter.utils.ConfigurationUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.maxgamer.quickshop.api.QuickShopAPI;

import java.util.logging.Level;

public final class QuickShopLimiter extends JavaPlugin {

    // VARS
    private boolean resetCheckerTriggered;

    // PLUGIN PREFIX
    private final String pluginPrefix = new ColoredText("&dEden&eLimit &f&l» &r").treat();

    // MANAGER
    private ConfigurationManager configurationManager;

    // INSTANCE
    private static QuickShopLimiter INSTANCE;

    // API
    private QuickShopAPI quickShopAPI;

    @Override
    public void onEnable() {

        long delay = System.currentTimeMillis();

        INSTANCE = this;

        Plugin plugin = Bukkit.getPluginManager().getPlugin("QuickShop");
        if (plugin == null) {
            log(Level.SEVERE, "QuickShop is not installed or QuickShopLimiter can not communicate with it. " +
                    "QuickShopLimiter can not run, disabling ...");
            onDisable();
        } else {
            this.quickShopAPI = (QuickShopAPI) plugin;
            String version = plugin.getDescription().getVersion();
            String name = plugin.getName();
            log(Level.INFO, "Connected to " + name + " " + version);
        }

        this.configurationManager = new ConfigurationManager(this);
        this.configurationManager.setupFiles();

        new CommandManager(this);
        new ListenerManager(this);

        new ResetChecker().runTaskTimerAsynchronously(this, 0, 20 * 60);
        this.resetCheckerTriggered = false;

        log(Level.INFO, "QuickShopLimiter enabled. (took " + (System.currentTimeMillis() - delay) + "ms)");

    }

    @Override
    public void onDisable() {
        configurationManager.saveFiles();
    }

    public void log(Level level, String message) {
        Bukkit.getLogger().log(level, "[" + getPlugin(QuickShopLimiter.class).getName() + "] " + message);
    }

    public static QuickShopLimiter getINSTANCE() {
        return INSTANCE;
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

    public boolean isResetCheckerTriggered() {
        return resetCheckerTriggered;
    }

    public void setResetCheckerTriggered(boolean resetCheckerTriggered) {
        this.resetCheckerTriggered = resetCheckerTriggered;
    }

    public QuickShopAPI getQuickShopAPI() {
        return quickShopAPI;
    }
}
