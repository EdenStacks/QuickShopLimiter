package fr.edencraft.quickshoplimiter.lang;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;

import java.util.List;

public interface Language {

    String prefix = QuickShopLimiter.getINSTANCE().getPluginPrefix();

    /**
     * This message is called when one or more files has been reloaded.
     *
     * @param filesName The list reloaded files.
     * @return The message in a specific language.
     */
    String getReloadFiles(List<String> filesName);

    /**
     * This message is called when trying to use an unknown configuration file.
     *
     * @param name The name of unknown configuration file.
     * @return The message in a specific language.
     */
    String getUnknownConfigFile(String name);

    /**
     * <em>
     * Sum of <b>ongoingTrade</b> and <b>tradedAmount</b> is superior to the limit
     * of the {@link fr.edencraft.quickshoplimiter.utils.LimitedShop}.
     * </em>
     *
     * @param ongoingTrade The trade that player is trying to do.
     * @param tradedAmount The traded amount that have done the player before this trade.
     * @param limitAmount The limit of the {@link fr.edencraft.quickshoplimiter.utils.LimitedShop}.
     * @return The message in a specific language.
     */
    String getTradeExceedLimitation(int ongoingTrade, int tradedAmount, int limitAmount);

    /**
     * This message is called when an error occurred when trying to load a {@link org.maxgamer.quickshop.shop.Shop}.
     *
     * @return The message in a specific language.
     */
    String getUnableToLoadShop();

    /**
     * This message is called when the specified shop is not a {@link fr.edencraft.quickshoplimiter.utils.LimitedShop}.
     *
     * @return The message in a specific language.
     */
    String getNotALimitedShop();

    /**
     * This message is called when the specified <b>LimitedShop ID</b> doesn't exist in Shops.yml.
     *
     * @param shopID The unknown shop ID.
     * @return The message in a specific language.
     */
    String getUnknownLimitedShopID(String shopID);

    /**
     * This message is called when the player doesn't look a sign or a chest.
     *
     * @return The message in a specific language.
     */
    String getLookChestOrSign();

    /**
     * This message is called when a player doesn't look a sign attached to a
     * valid {@link org.maxgamer.quickshop.shop.Shop}.
     *
     * @return The message in a specific language.
     */
    String getLookShopSign();

    /**
     * This message is called when a shop is already registered in Shops.yml as a limited shop.
     *
     * @return The message in a specific language.
     */
    String getShopAlreadyHasLimit();

    /**
     * This messages is called when a {@link fr.edencraft.quickshoplimiter.utils.LimitedShop} has been created.
     *
     * @return The message in a specific language.
     */
    String getLimitedShopCreated();

}
