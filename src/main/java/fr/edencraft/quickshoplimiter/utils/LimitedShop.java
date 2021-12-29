package fr.edencraft.quickshoplimiter.utils;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import org.bukkit.configuration.ConfigurationSection;
import org.maxgamer.quickshop.shop.Shop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

/**
 * This class define a {@link LimitedShop}.
 * It basically a {@link Shop} with additional values.
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
     * It useful because it combine {@link Shop} and the limit system of this plugin.
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
     * @return A list of {@link UUID} corresponding to all players who has trade with this {@link LimitedShop}.
     */
    public List<UUID> getAllTraders() {
        ConfigurationSection storageSection = getStorageSection();
        assert storageSection != null;

        List<UUID> uuidList = new ArrayList<>();

        storageSection.getKeys(false).forEach(s -> uuidList.add(UUID.fromString(s)));
        return uuidList;
    }

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
     * @return The storage section of this {@link LimitedShop}.
     */
    private ConfigurationSection getStorageSection() {
        return ConfigurationUtils.getConfigurationSection(
                "Storage.yml",
                "storage." + getShopID()
        );
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
