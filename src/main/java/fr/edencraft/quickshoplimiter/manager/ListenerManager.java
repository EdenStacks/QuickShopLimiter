package fr.edencraft.quickshoplimiter.manager;

import fr.edencraft.quickshoplimiter.listener.LimitedShopClick;
import fr.edencraft.quickshoplimiter.listener.ReloadQuickShop;
import fr.edencraft.quickshoplimiter.listener.quickshop.ShopDelete;
import fr.edencraft.quickshoplimiter.listener.quickshop.ShopPurchase;
import fr.edencraft.quickshoplimiter.listener.quickshoplimiter.LimitedShopPurchase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {

    public ListenerManager(Plugin plugin) {

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ShopPurchase(), plugin);
        pm.registerEvents(new LimitedShopPurchase(), plugin);
        pm.registerEvents(new ShopDelete(), plugin);
        pm.registerEvents(new ReloadQuickShop(), plugin);
        pm.registerEvents(new LimitedShopClick(), plugin);

    }
}
