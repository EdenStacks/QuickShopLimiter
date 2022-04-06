package fr.edencraft.quickshoplimiter.utils;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.api.shop.Shop;

import java.util.UUID;
import java.util.logging.Level;

/**
 * This class define a {@link LimitedShop}.
 * It is basically a {@link Shop} with additional values.
 */
public class LimitedShop {

    /**
     * The {@link Shop} assigned as a {@link LimitedShop}.
     */
    private final Shop shop;
    /**
     * The configuration section associated to the {@link LimitedShop}.
     */
    private final ConfigurationSection shopSection;

    private LimitationType limitationType;
    private final int limitAmount;
    private TimingType timingType;
    private final int interval;
    private final long lastReset;

    /**
     * Build a {@link LimitedShop}.
     * It is useful because it combines {@link Shop} and the limit system of this plugin.
     *
     * @param shop The {@link Shop} who has a limit.
     * @param shopSection The {@link ConfigurationSection} of the {@link LimitedShop}.
     */
    public LimitedShop(Shop shop, ConfigurationSection shopSection) {
        this.shop = shop;
        this.shopSection = shopSection;

        try {
            this.limitationType = LimitationType.valueOf(shopSection.getString("limit.limitation-type"));
        } catch (IllegalArgumentException exception) {
            QuickShopLimiter.getINSTANCE().log(
                    Level.SEVERE,
                    "Error occurred to set the LimitationType of the LimitedShop n°" +
                            shopSection.getName() + "."
            );
        }

        this.limitAmount = shopSection.getInt("limit.limit-amount");

        try {
            this.timingType = TimingType.valueOf(shopSection.getString("reset.timing-type"));
        } catch (IllegalArgumentException exception) {
            QuickShopLimiter.getINSTANCE().log(
                    Level.SEVERE,
                    "Error occurred to set the TimingType of the LimitedShop n°" +
                            shopSection.getName() + "."
            );
        }

        this.interval = shopSection.getInt("reset.interval");
        this.lastReset = shopSection.getLong("reset.last-reset");
    }

    /**
     * @return the permission to use this {@link LimitedShop} or null if no permission needed.
     */
    @Nullable
    public String getPermission() {
        return shopSection.getString("permission");
    }

// --Commented out by Inspection START (06/04/2022 23:42):
//    /**
//     * @return A list of {@link UUID} corresponding to all players who has trade with this {@link LimitedShop}.
//     */
//    public List<UUID> getAllTraders() {
//        ConfigurationSection storageSection = getStorageSection();
//        assert storageSection != null;
//
//        List<UUID> uuidList = new ArrayList<>();
//
//        storageSection.getKeys(false).forEach(s -> uuidList.add(UUID.fromString(s)));
//        return uuidList;
//    }
// --Commented out by Inspection STOP (06/04/2022 23:42)

    /**
     * Don't panic, if UUID isn't present in this {@link LimitedShop} it'll return 0.
     *
     * @param player The UUID of the player.
     * @return The traded amount of the player in this {@link LimitedShop}.
     */
    public int getTradeAmountForPlayer(UUID player) {
        ConfigurationSection storageSection = getStorageSection();
        return storageSection.getInt(player.toString() + ".traded-amount");
    }

    /**
     * Be careful, this method will return a wrong value of the {@link LimitationType}
     * is not <b>SERVER</b>.
     *
     * @return The traded amount by the server in this {@link LimitedShop}.
     */
    public int getTradeAmountServer() {
        ConfigurationSection storageSection = getStorageSection();
        return storageSection.getInt("traded-amount");
    }

    /**
     * @return The storage section of this {@link LimitedShop}.
     */
    private ConfigurationSection getStorageSection() {
        ConfigurationManager cm = QuickShopLimiter.getINSTANCE().getConfigurationManager();
        FileConfiguration cfg = cm.getConfigurationFile("Storage.yml");

        if (!cfg.isConfigurationSection("storage." + getShopID())) {
            cfg.createSection("storage." + getShopID());
        }

        return cfg.getConfigurationSection("storage." + getShopID());
    }

    public Shop getShop() {
        return shop;
    }

    /**
     * @return The shop section in Shops.yml.
     */
    public ConfigurationSection getShopSection() {
        return shopSection;
    }

    /**
     * @return The id of this {@link LimitedShop}.
     */
    public String getShopID() {
        return shopSection.getName();
    }

    public LimitationType getLimitationType() {
        return limitationType;
    }

    public int getLimitAmount() {
        return limitAmount;
    }

    public TimingType getTimingType() {
        return timingType;
    }

    public int getInterval() {
        return interval;
    }

    public long getLastReset() {
        return lastReset;
    }
}
