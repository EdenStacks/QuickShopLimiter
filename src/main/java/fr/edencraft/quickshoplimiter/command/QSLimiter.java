package fr.edencraft.quickshoplimiter.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.lang.Language;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import fr.edencraft.quickshoplimiter.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.maxgamer.quickshop.api.QuickShopAPI;
import org.maxgamer.quickshop.shop.Shop;

import java.util.Collections;

@CommandAlias("qslimiter|qsl|quickshoplimiter")
public class QSLimiter extends BaseCommand {

    private final static Language LANGUAGE = QuickShopLimiter.getINSTANCE().getLanguage();
    private final static ConfigurationManager CM = QuickShopLimiter.getINSTANCE().getConfigurationManager();

    private static final String basePermission = "quickshoplimiter.command";

    /*
    TODO: see below :

    <> : argument obligatoire
    [] : argument optionnel

    /qsl list [page] -> Liste de tout les limited shop dans Shops.yml. Avec précisé les infos importante de chacun.
                    Faire un système de pagination.
    /qsl see [shopID] [player] -> si player est vide alors on affiche la liste des player qui on trade sur ce shop et leur
                    tradeAmount. Sinon on affiche la valeur pour le player demandé.
     */

    
    @Default
    @CommandPermission(basePermission)
    public static void onCommand(CommandSender sender) {
        onAbout(sender);
    }

    @Subcommand("modify timingtype")
    @CommandPermission(basePermission + ".modify.timingtype")
    @CommandCompletion("@listLimitedShopID DAY|MONTH|YEAR")
    public static void onModifyInterval(CommandSender sender, String shopID, TimingType timingType) {
        if (!CommandCompletionUtils.getAllLimitedShopID().contains(shopID)) {
            sender.sendMessage(LANGUAGE.getUnknownLimitedShopID(shopID));
            return;
        }

        LimitedShop limitedShop = ConfigurationUtils.getLimitedShop(shopID);
        assert limitedShop != null;

        if (limitedShop.getTimingType().equals(timingType)) {
            sender.sendMessage(LANGUAGE.getAlreadyThisTimingType(limitedShop, timingType));
            return;
        }

        FileConfiguration shopsCFG = CM.getConfigurationFile("Shops.yml");
        shopsCFG.set("shops." + shopID + ".reset.timing-type", timingType.name());
        CM.saveFile("Shops.yml");

        sender.sendMessage(LANGUAGE.getTimingTypeModified(limitedShop, limitedShop.getTimingType(), timingType));
    }

    @Subcommand("modify interval")
    @CommandPermission(basePermission + ".modify.interval")
    @CommandCompletion("@listLimitedShopID interval")
    public static void onModifyInterval(CommandSender sender, String shopID, int interval) {
        if (!CommandCompletionUtils.getAllLimitedShopID().contains(shopID)) {
            sender.sendMessage(LANGUAGE.getUnknownLimitedShopID(shopID));
            return;
        }

        LimitedShop limitedShop = ConfigurationUtils.getLimitedShop(shopID);
        assert limitedShop != null;

        if (limitedShop.getInterval() == interval) {
            sender.sendMessage(LANGUAGE.getAlreadyThisInterval(limitedShop, interval));
            return;
        }

        FileConfiguration shopsCFG = CM.getConfigurationFile("Shops.yml");
        shopsCFG.set("shops." + shopID + ".reset.interval", interval);
        CM.saveFile("Shops.yml");

        sender.sendMessage(LANGUAGE.getIntervalModified(limitedShop, limitedShop.getInterval(), interval));
    }

    @Subcommand("modify limitamount")
    @CommandPermission(basePermission + ".modify.limitamount")
    @CommandCompletion("@listLimitedShopID <limit_amount>")
    public static void onModifyLimitAmount(CommandSender sender, String shopID, int limitAmount) {
        if (!CommandCompletionUtils.getAllLimitedShopID().contains(shopID)) {
            sender.sendMessage(LANGUAGE.getUnknownLimitedShopID(shopID));
            return;
        }

        LimitedShop limitedShop = ConfigurationUtils.getLimitedShop(shopID);
        assert limitedShop != null;

        if (limitedShop.getLimitAmount() == limitAmount) {
            sender.sendMessage(LANGUAGE.getAlreadyThisLimitAmount(limitedShop, limitAmount));
            return;
        }

        FileConfiguration shopsCFG = CM.getConfigurationFile("Shops.yml");
        shopsCFG.set("shops." + shopID + ".limit.limit-amount", limitAmount);
        CM.saveFile("Shops.yml");

        sender.sendMessage(LANGUAGE.getLimitAmountModified(limitedShop, limitedShop.getLimitAmount(), limitAmount));
    }

    @Subcommand("modify limitationtype")
    @CommandPermission(basePermission + ".modify.limitationtype")
    @CommandCompletion("@listLimitedShopID PLAYER|SERVER")
    public static void onModifyLimitationType(CommandSender sender, String shopID, LimitationType limitationType) {
        if (!CommandCompletionUtils.getAllLimitedShopID().contains(shopID)) {
            sender.sendMessage(LANGUAGE.getUnknownLimitedShopID(shopID));
            return;
        }

        LimitedShop limitedShop = ConfigurationUtils.getLimitedShop(shopID);
        assert limitedShop != null;

        if (limitedShop.getLimitationType().equals(limitationType)) {
            sender.sendMessage(LANGUAGE.getAlreadyThisLimitationType(limitedShop, limitationType));
            return;
        }

        FileConfiguration shopsCFG = CM.getConfigurationFile("Shops.yml");
        shopsCFG.set("shops." + shopID + ".limit.limitation-type", limitationType.name());
        CM.saveFile("Shops.yml");

        sender.sendMessage(LANGUAGE.getLimitationTypeModified(
                limitedShop,
                limitedShop.getLimitationType(),
                limitationType)
        );
    }

    @Subcommand("modify")
    @CommandPermission(basePermission + ".modify")
    public static void onModify(CommandSender sender) {
        sender.sendMessage(LANGUAGE.getModifyCommandSyntax());
    }

    @Subcommand("info")
    @CommandPermission(basePermission + ".info")
    @CommandCompletion("@listLimitedShopID")
    public static void onInfo(CommandSender sender, @Optional String shopID) {
        if (!(sender instanceof Player)) {
            if (shopID == null || shopID.isEmpty()) {
                sender.sendMessage(LANGUAGE.getConsoleNeedToGiveShopID());
                return;
            }
        }
        if (sender instanceof Player player && (shopID == null || shopID.isEmpty())) {
            Block targetBlock = player.getTargetBlock(5);
            if (!isValidTargetBlock(player, targetBlock)) return;
            assert targetBlock != null;

            shopID = getLimitedShopID(player, targetBlock);

            if (shopID == null) return;
        }

        if (!CommandCompletionUtils.getAllLimitedShopID().contains(shopID)) {
            sender.sendMessage(LANGUAGE.getUnknownLimitedShopID(shopID));
            return;
        }

        LimitedShop limitedShop = ConfigurationUtils.getLimitedShop(shopID);
        assert limitedShop != null;

        sender.sendMessage(LANGUAGE.getLimitedShopInfo(limitedShop));
    }

    @Subcommand("reset")
    @CommandPermission(basePermission + ".reset")
    @CommandCompletion("@listLimitedShopID @players")
    public static void onReset(Player player, String shopID, @Optional String playerName) {
        if (!CommandCompletionUtils.getAllLimitedShopID().contains(shopID)) {
            player.sendMessage(LANGUAGE.getUnknownLimitedShopID(shopID));
            return;
        }

        FileConfiguration storageCFG = CM.getConfigurationFile("Storage.yml");
        LimitedShop limitedShop = ConfigurationUtils.getLimitedShop(shopID);
        assert limitedShop != null;

        if (playerName == null || playerName.isEmpty()) {
            storageCFG.set("storage." + shopID, null);
            player.sendMessage(LANGUAGE.getLimitedShopReset(limitedShop, null));
        } else {
            if (limitedShop.getLimitationType().equals(LimitationType.SERVER)) {
                player.sendMessage(LANGUAGE.getCanNotResetForThisLimitationType(
                        limitedShop,
                        limitedShop.getLimitationType())
                );
                return;
            }

            OfflinePlayer playerToReset = Bukkit.getOfflinePlayer(playerName);
            if (!playerToReset.hasPlayedBefore()) {
                player.sendMessage(LANGUAGE.getPlayerNeverPlayed(playerName));
                return;
            }

            if (!storageCFG.contains("storage." + shopID + "." + playerToReset.getUniqueId().toString() + ".traded-amount")) {
                player.sendMessage(LANGUAGE.getPlayerDoesNotHaveTradeYet(limitedShop, playerToReset));
                return;
            } else {
                storageCFG.set("storage." + shopID + "." + playerToReset.getUniqueId().toString(), null);
            }
            player.sendMessage(LANGUAGE.getLimitedShopReset(limitedShop, playerToReset));
        }

        CM.saveFile("Storage.yml");
    }

    @Subcommand("delete")
    @CommandPermission(basePermission + ".delete")
    @CommandCompletion("@listLimitedShopID")
    public static void onDelete(Player player, @Optional String shopID) {
        if (shopID == null || shopID.isEmpty()) {
            Block targetBlock = player.getTargetBlock(5);
            if (!isValidTargetBlock(player, targetBlock)) return;
            assert targetBlock != null;

            shopID = getLimitedShopID(player, targetBlock);

            if (shopID == null) return;
        }

        if (!CommandCompletionUtils.getAllLimitedShopID().contains(shopID)) {
            player.sendMessage(LANGUAGE.getUnknownLimitedShopID(shopID));
            return;
        }

        FileConfiguration shopsCFG = CM.getConfigurationFile("Shops.yml");
        FileConfiguration storageCFG = CM.getConfigurationFile("Storage.yml");

        shopsCFG.set("shops." + shopID, null);
        storageCFG.set("storage." + shopID, null);

        CM.saveFile("Shops.yml");
        CM.saveFile("Storage.yml");
    }

    @Subcommand("create")
    @CommandPermission(basePermission + ".create")
    @CommandCompletion("PLAYER|SERVER <limit_amount> <interval_of_DAY_or_MOUNTH_or_YEAR> DAY|MONTH|YEAR")
    public static void onCreateLimit(Player player,
                                     LimitationType limitationType,
                                     int limitAmount,
                                     int interval,
                                     TimingType timingType) {
        Block targetBlock = player.getTargetBlock(5);
        if (!isValidTargetBlock(player, targetBlock)) return;
        assert targetBlock != null;
        if (targetBlock.getState() instanceof Sign) targetBlock = getAttachedChest((Sign) targetBlock.getState());
        assert targetBlock != null;

        assert QuickShopAPI.getShopAPI() != null;
        Shop shop = QuickShopAPI.getShopAPI().getShop(targetBlock).get();
        if (ConfigurationUtils.isLimitedShop(shop)) {
            player.sendMessage(LANGUAGE.getShopAlreadyHasLimit());
            return;
        }

        FileConfiguration shopsCFG = CM.getConfigurationFile("Shops.yml");
        ConfigurationSection shopsSection = shopsCFG.getConfigurationSection("shops");
        assert shopsSection != null;

        int id = 1;
        while (shopsSection.contains(String.valueOf(id))) id++;
        ConfigurationSection shopSection = shopsSection.createSection(String.valueOf(id));

        shopSection.set("limit.limitation-type", limitationType.name());
        shopSection.set("limit.limit-amount", limitAmount);
        shopSection.set("reset.timing-type", timingType.name());
        shopSection.set("reset.interval", interval);
        shopSection.set("reset.last-reset", System.currentTimeMillis());
        shopSection.set("location.world", targetBlock.getLocation().getWorld().getName());
        shopSection.set("location.x", targetBlock.getLocation().getBlockX());
        shopSection.set("location.y", targetBlock.getLocation().getBlockY());
        shopSection.set("location.z", targetBlock.getLocation().getBlockZ());

        CM.saveFile("Shops.yml");

        player.sendMessage(LANGUAGE.getLimitedShopCreated());
    }

    @Subcommand("reload|rl")
    @Syntax("[fileName]")
    @CommandCompletion("@quickshoplimiterreload")
    @CommandPermission(basePermission + ".reload")
    public static void onReload(CommandSender sender, @Optional String fileName){
        if (fileName != null && !fileName.isEmpty()) {
            if (CM.getConfigurationFile(fileName) != null) {
                CM.reloadFile(fileName);
                sender.sendMessage(LANGUAGE.getReloadFiles(Collections.singletonList(fileName)));
            } else {
                sender.sendMessage(LANGUAGE.getUnknownConfigFile(fileName));
            }
        } else {
            CM.reloadFiles();
            sender.sendMessage(LANGUAGE.getReloadFiles(CommandCompletionUtils.getConfigurationFilesName()));
        }
    }

    @Subcommand("about")
    @CommandPermission(basePermission + ".about")
    public static void onAbout(CommandSender sender) {
        StringBuilder cmdMessage = new StringBuilder();
        cmdMessage.append("\n");
        cmdMessage.append("╔══════════════════╗\n");
        cmdMessage.append("║ QuickShopLimiter ║\n");
        cmdMessage.append("╟──────────────────╢\n");
        cmdMessage.append("║ Version: 1.0.1   ║\n");
        cmdMessage.append("║                  ║\n");
        cmdMessage.append("║ Made with &4♥&r      ║\n");
        cmdMessage.append("║ &rby NayeOne.      ║\n");
        cmdMessage.append("╚══════════════════╝\n");

        StringBuilder playerMessage = new StringBuilder();
        playerMessage.append("&aQuickShopLimiter\n");
        playerMessage.append("&fVersion: &e1.0.1\n");
        playerMessage.append("&fBy: &eNayeOne\n");

        if (sender instanceof Player){
            sender.sendMessage(new ColoredText(playerMessage.toString()).treat());
        } else {
            assert sender != null;
            sender.sendMessage(new ColoredText(cmdMessage.toString()).treat());
        }
    }

    /**
     * @param sign The sign attached to a chest.
     * @return The block attached to the sign. Null if it's not a chest.
     */
    @Nullable
    private static Block getAttachedChest(Sign sign) {
        Block signBlock = sign.getBlock();
        WallSign signData = (WallSign) signBlock.getState().getBlockData();
        BlockFace attached = signData.getFacing().getOppositeFace();

        Block blockAttached = signBlock.getRelative(attached);
        if (blockAttached.getBlockData().getMaterial().equals(Material.CHEST)) {
            return blockAttached;
        }
        return null;
    }

    /**
     * @param targetBlock The targetBlock to check.
     * @return true if it correspond to a valid {@link org.maxgamer.quickshop.shop.Shop} else false.
     */
    private static boolean isValidTargetBlock(Player player, Block targetBlock) {
        if (targetBlock == null) {
            player.sendMessage(LANGUAGE.getLookChestOrSign());
            return false;
        }
        if (!(targetBlock.getState() instanceof Sign) &&
                !targetBlock.getBlockData().getMaterial().equals(Material.CHEST)) {
            player.sendMessage(LANGUAGE.getLookChestOrSign());
            return false;
        }

        if (targetBlock.getState() instanceof Sign) {
            targetBlock = getAttachedChest((Sign) targetBlock.getState());

            assert QuickShopAPI.getShopAPI() != null;
            if (QuickShopAPI.getShopAPI().getShop(targetBlock).isEmpty()) {
                player.sendMessage(LANGUAGE.getLookShopSign());
                return false;
            }
        }
        return true;
    }

    /**
     * This method get the ID of the {@link LimitedShop} associated to the given {@link Block}.
     * If the given block has not associated {@link LimitedShop} it will return null.
     *
     * @param player The player who are executing the command.
     * @param block The block where is located the {@link LimitedShop}.
     * @return The <b>Limited Shop Unique ID</b> or <b>null</b> if it is not a <b>Limited Shop</b>
     */
    @Nullable
    private static String getLimitedShopID(Player player, Block block) {
        if (block.getState() instanceof Sign) {
            block = getAttachedChest((Sign) block.getState());
        }

        assert QuickShopAPI.getShopAPI() != null;
        boolean isEmpty = QuickShopAPI.getShopAPI().getShop(block).isEmpty();

        if (isEmpty) {
            player.sendMessage(LANGUAGE.getUnableToLoadShop());
            return null;
        }

        Shop shop = QuickShopAPI.getShopAPI().getShop(block).get();
        if (!ConfigurationUtils.isLimitedShop(shop)) {
            player.sendMessage(LANGUAGE.getNotALimitedShop());
            return null;
        }

        return ConfigurationUtils.getLimitedShopID(shop);
    }

}
