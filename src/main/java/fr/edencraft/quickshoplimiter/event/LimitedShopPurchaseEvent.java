package fr.edencraft.quickshoplimiter.event;

import fr.edencraft.quickshoplimiter.utils.LimitedShop;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.maxgamer.quickshop.event.ShopPurchaseEvent;

import java.util.UUID;

public class LimitedShopPurchaseEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean isCancelled = false;

    private @NotNull final UUID purchaser;
    private final LimitedShop limitedShop;
    private final ShopPurchaseEvent shopPurchaseEvent;


    /**
     * Build LimitedShop purchase event.
     * If this event is cancelled, i'll cancel the purchase.
     *
     * @param purchaser The player's {@link UUID} who purchase in a {@link LimitedShop}.
     * @param limitedShop The {@link LimitedShop}.
     * @param shopPurchaseEvent The {@link ShopPurchaseEvent}.
     */
    public LimitedShopPurchaseEvent(@NotNull UUID purchaser,
                                    LimitedShop limitedShop,
                                    ShopPurchaseEvent shopPurchaseEvent) {
        this.purchaser = purchaser;
        this.limitedShop = limitedShop;
        this.shopPurchaseEvent = shopPurchaseEvent;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public @NotNull UUID getPurchaser() {
        return purchaser;
    }

    public LimitedShop getLimitedShop() {
        return limitedShop;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        isCancelled = cancel;
    }

    public ShopPurchaseEvent getShopPurchaseEvent() {
        return shopPurchaseEvent;
    }
}
