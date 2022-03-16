package fr.edencraft.quickshoplimiter.listener.quickshoplimiter;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.event.LimitedShopPurchaseEvent;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import fr.edencraft.quickshoplimiter.utils.LimitationType;
import fr.edencraft.quickshoplimiter.utils.LimitedShop;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.maxgamer.quickshop.api.event.ShopPurchaseEvent;

import java.util.UUID;

public class LimitedShopPurchase implements Listener {

    @EventHandler
    public void onLimitedShopPurchaseEvent(LimitedShopPurchaseEvent event) {
        UUID purchaser = event.getPurchaser();
        LimitedShop limitedShop = event.getLimitedShop();
        ShopPurchaseEvent shopPurchaseEvent = event.getShopPurchaseEvent();

        int tradedAmount = 0;
        if (limitedShop.getLimitationType().equals(LimitationType.PLAYER)) {
            tradedAmount = limitedShop.getTradeAmountForPlayer(purchaser);
        } else if (limitedShop.getLimitationType().equals(LimitationType.SERVER)) {
            tradedAmount = limitedShop.getTradeAmountServer();
        }

        int ongoingTrade = shopPurchaseEvent.getAmount();
        int shopLimit = limitedShop.getLimitAmount();

        if (tradedAmount + ongoingTrade > shopLimit) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(purchaser);
            if (offlinePlayer.isOnline()) {
                Player player = (Player) offlinePlayer;
                player.sendMessage(QuickShopLimiter.getINSTANCE().getLanguage().getTradeExceedLimitation(
                        ongoingTrade,
                        tradedAmount,
                        limitedShop.getLimitAmount()
                ));
            }
            event.setCancelled(true);
            return;
        }

        ConfigurationManager cm = QuickShopLimiter.getINSTANCE().getConfigurationManager();
        FileConfiguration cfg = cm.getConfigurationFile("Storage.yml");

        if (limitedShop.getLimitationType().equals(LimitationType.PLAYER)) {
            cfg.set(
                    "storage." + limitedShop.getShopID() + "." + purchaser + ".traded-amount",
                    tradedAmount + ongoingTrade
            );
            cfg.set(
                    "storage." + limitedShop.getShopID() + "." + purchaser + ".name",
                    Bukkit.getOfflinePlayer(purchaser).getName()
            );
        } else if (limitedShop.getLimitationType().equals(LimitationType.SERVER)) {
            cfg.set(
                    "storage." + limitedShop.getShopID() + ".traded-amount",
                    tradedAmount + ongoingTrade
            );
        }
        cm.saveFile("Storage.yml");
    }

}
