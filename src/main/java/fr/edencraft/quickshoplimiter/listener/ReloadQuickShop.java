package fr.edencraft.quickshoplimiter.listener;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.lang.Language;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class ReloadQuickShop implements Listener {

    private final Language language = QuickShopLimiter.getINSTANCE().getLanguage();

    /**
     * We listen this event because after the reload the {@link org.maxgamer.quickshop.api.QuickShopAPI} is null.
     * So we cancel this event.
     *
     * @param event ServerCommandEvent.
     */
    @EventHandler
    public void onQuickShopReloadServerCommand(ServerCommandEvent event) {
        if (event.getCommand().equalsIgnoreCase("qs reload")) {
            CommandSender sender = event.getSender();
            sender.sendMessage(language.getCommandForbidden(sender, event.getCommand()));
            event.setCancelled(true);
        }
    }

    /**
     * We listen this event because after the reload the {@link org.maxgamer.quickshop.api.QuickShopAPI} is null.
     * So we cancel this event.
     *
     * @param event PlayerCommandPreprocessEvent.
     */
    @EventHandler
    public void onQuickShopReloadPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/qs reload")) {
            CommandSender sender = event.getPlayer();
            sender.sendMessage(language.getCommandForbidden(sender, event.getMessage()));
            event.setCancelled(true);
        }
    }

}
