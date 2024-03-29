package fr.edencraft.quickshoplimiter.listener.quickshop;

import fr.edencraft.quickshoplimiter.event.LimitedShopPurchaseEvent;
import fr.edencraft.quickshoplimiter.utils.ConfigurationUtils;
import fr.edencraft.quickshoplimiter.utils.LimitedShop;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.maxgamer.quickshop.api.event.ShopPurchaseEvent;
import org.maxgamer.quickshop.api.shop.Shop;

import java.util.UUID;

public class ShopPurchase implements Listener {

    @EventHandler
    public void onShopPurchaseEvent(ShopPurchaseEvent event) {
        Shop shop = event.getShop();
        if (!ConfigurationUtils.isLimitedShop(shop)) return;

        ConfigurationSection limitedShopSection = ConfigurationUtils.getConfigurationSection(
                "Shops.yml",
                "shops." + ConfigurationUtils.getLimitedShopID(shop)
        );
        assert limitedShopSection != null;

        LimitedShop limitedShop = new LimitedShop(shop, limitedShopSection);
        UUID purchaser = event.getPurchaser();

        LimitedShopPurchaseEvent limitedShopPurchaseEvent = new LimitedShopPurchaseEvent(purchaser, limitedShop, event);
        Bukkit.getPluginManager().callEvent(limitedShopPurchaseEvent);

        if (limitedShopPurchaseEvent.isCancelled()) event.setCancelled(true);
    }

}
