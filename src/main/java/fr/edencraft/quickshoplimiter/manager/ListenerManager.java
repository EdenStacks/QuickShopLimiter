package fr.edencraft.quickshoplimiter.manager;

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

    }
}