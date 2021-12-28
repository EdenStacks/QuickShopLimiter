package fr.edencraft.quickshoplimiter.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class LimitedShopPurchaseEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;

    private @NotNull final Player purchaser;

    /**
     * Build LimitedShop purchase event.
     * If this event is cancelled, i'll cancel the purchase.
     *
     * @param purchaser The player who purchase in a limited shop.
     */
    public LimitedShopPurchaseEvent(@NotNull Player purchaser) {
        this.purchaser = purchaser;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public @NotNull Player getPurchaser() {
        return purchaser;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }
}
