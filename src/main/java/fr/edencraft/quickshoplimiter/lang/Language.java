package fr.edencraft.quickshoplimiter.lang;

import co.aikar.commands.annotation.Optional;
import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.utils.LimitationType;
import fr.edencraft.quickshoplimiter.utils.LimitedShop;
import fr.edencraft.quickshoplimiter.utils.TimingType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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

    /**
     * This messages is called when player try to reset a player log whereas the limitation type of this
     * limited shop is SERVER.
     *
     * @param limitedShop Concerned limited shop.
     * @param limitationType Limitation type of the limited shop.
     * @return The message in a specific language.
     */
    String getCanNotResetForThisLimitationType(LimitedShop limitedShop, LimitationType limitationType);

    /**
     * This messages is called when player try to reset a player log that have not trade with the limited shop.
     *
     * @param limitedShop Concerned limited shop.
     * @param playerToReset Player that have not trade with the limited shop.
     * @return The message in a specific language.
     */
    String getPlayerDoesNotHaveTradeYet(LimitedShop limitedShop, OfflinePlayer playerToReset);

    /**
     * This messages is called when player has reset a player log of the limited shop.
     *
     * @param limitedShop Concerned limited shop.
     * @param playerToReset Player who has been reset.
     * @return The message in a specific language.
     */
    String getLimitedShopReset(LimitedShop limitedShop, @Optional OfflinePlayer playerToReset);

    /**
     * This message is called when a player try to do an action with a player that have never played before on the
     * server.
     *
     * @param playerName Name of the player that have never played on the server.
     * @return The message in a specific language.
     */
    String getPlayerNeverPlayed(String playerName);

    /**
     * This message is called when a player want to display information about a {@link LimitedShop}.
     *
     * @param limitedShop The limited shop to display.
     * @return The message in a specific language.
     */
    String getLimitedShopInfo(LimitedShop limitedShop);

    /**
     * This message is called when console try to use a command without specified a shop ID.
     *
     * @return The message in a specific language.
     */
    String getConsoleNeedToGiveShopID();

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} enter an incomplete command
     * on <b>/qsl modify</b>.
     *
     * @return The message in a specific language.
     */
    String getModifyCommandSyntax();

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} try to replace the {@link LimitationType}
     * of a shop by the same {@link LimitationType}.
     *
     * @param limitedShop Concerned limited shop.
     * @param newLimitationType The new LimitationType.
     * @return The message in a specific language.
     */
    String getAlreadyThisLimitationType(LimitedShop limitedShop, LimitationType newLimitationType);

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} has modified with success the
     * {@link LimitationType} of a {@link LimitedShop}.
     *
     * @param limitedShop Concerned limited shop.
     * @param oldLimitationType The old LimitationType.
     * @param newLimitationType The new LimitationType.
     * @return The message in a specific language.
     */
    String getLimitationTypeModified(LimitedShop limitedShop,
                                     LimitationType oldLimitationType,
                                     LimitationType newLimitationType);

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} try to replace the
     * <b>limit amount</b> of a shop by the same <b>limit amount</b>.
     *
     * @param limitedShop Concerned limited shop.
     * @param limitAmount The new limit amount.
     * @return The message in a specific language.
     */
    String getAlreadyThisLimitAmount(LimitedShop limitedShop, int limitAmount);

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} has modified with success the
     * <b>limit amount</b> of {@link LimitedShop}.
     *
     * @param limitedShop Concerned limited shop.
     * @param oldLimitAmount The old limit amount.
     * @param newLimitAmount The new limit amount.
     * @return The message in a specific language.
     */
    String getLimitAmountModified(LimitedShop limitedShop, int oldLimitAmount, int newLimitAmount);

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} try to replace the <b>interval</b> of
     * a shop by the same <b>interval</b>.
     *
     * @param limitedShop Concerned limited shop.
     * @param interval The {@link LimitedShop } interval.
     * @return The message in a specific language.
     */
    String getAlreadyThisInterval(LimitedShop limitedShop, int interval);

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} has modified with success the
     * <b>interval</b> of a {@link LimitedShop}.
     *
     * @param limitedShop  Concerned limited shop.
     * @param oldInterval The old interval.
     * @param newInterval The new interval.
     * @return The message in a specific language.
     */
    String getIntervalModified(LimitedShop limitedShop, int oldInterval, int newInterval);

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} try to replace the {@link TimingType} of a
     * shop by the same {@link TimingType}.
     * 
     * @param limitedShop Concerned limited shop.
     * @param timingType The old timing type.
     * @return The message in a specific language.
     */
    String getAlreadyThisTimingType(LimitedShop limitedShop, TimingType timingType);

    /**
     * This message is called when a {@link org.bukkit.command.CommandSender} has modified with success the
     * {@link TimingType} of a {@link LimitedShop}.
     *
     * @param limitedShop Concerned limited shop.
     * @param oldTimingType The old timing type.
     * @param newTimingType The new timing type.
     * @return The message in a specific language.
     */
    String getTimingTypeModified(LimitedShop limitedShop, TimingType oldTimingType, TimingType newTimingType);

}
