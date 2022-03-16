package fr.edencraft.quickshoplimiter.listener.quickshop;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import fr.edencraft.quickshoplimiter.utils.ConfigurationUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.maxgamer.quickshop.api.event.ShopDeleteEvent;
import org.maxgamer.quickshop.api.shop.Shop;

public class ShopDelete implements Listener {

    @EventHandler
    public void onShopDeleteEvent(ShopDeleteEvent event) {
        Shop shop = event.getShop();

        if (ConfigurationUtils.isLimitedShop(shop)) {
            String shopID = ConfigurationUtils.getLimitedShopID(shop);
            ConfigurationManager cm = QuickShopLimiter.getINSTANCE().getConfigurationManager();

            FileConfiguration shopsCFG = cm.getConfigurationFile("Shops.yml");
            FileConfiguration storageCFG = cm.getConfigurationFile("Storage.yml");

            shopsCFG.set("shops." + shopID, null);
            storageCFG.set("storage." + shopID, null);

            cm.saveFile("Shops.yml");
            cm.saveFile("Storage.yml");
        }
    }

}
