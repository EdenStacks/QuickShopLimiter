package fr.edencraft.quickshoplimiter.listener;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.command.QSLimiter;
import fr.edencraft.quickshoplimiter.lang.Language;
import fr.edencraft.quickshoplimiter.utils.ConfigurationUtils;
import fr.edencraft.quickshoplimiter.utils.LimitedShop;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.maxgamer.quickshop.api.QuickShopAPI;
import org.maxgamer.quickshop.api.shop.Shop;

public class LimitedShopClick implements Listener {

	private final static Language LANGUAGE = QuickShopLimiter.getINSTANCE().getLanguage();
	private final static QuickShopAPI QUICK_SHOP_API = QuickShopLimiter.getINSTANCE().getQuickShopAPI();

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction().isRightClick()) return;
		if (event.getClickedBlock() == null) return;
		if (!isShopBlock(event.getClickedBlock())) return;

		Block clickedBlock = event.getClickedBlock();
		if (clickedBlock.getState() instanceof Sign)
			clickedBlock = QSLimiter.getAttachedChest((Sign) clickedBlock.getState());
		if (clickedBlock == null) return;

		Shop shop = QUICK_SHOP_API.getShopManager().getShop(clickedBlock.getLocation());
		if (shop == null) return;
		if (!ConfigurationUtils.isLimitedShop(shop)) return;

		ConfigurationSection limitedShopSection = ConfigurationUtils.getConfigurationSection("Shops.yml", "shops." + ConfigurationUtils.getLimitedShopID(shop));
		assert limitedShopSection != null;

		LimitedShop limitedShop = new LimitedShop(shop, limitedShopSection);
		Player player = event.getPlayer();

		String permission = limitedShop.getPermission();
		if (permission != null) {
			if (!player.hasPermission(permission)) {
				player.sendMessage(LANGUAGE.getNeededPermission(permission));
				return;
			}
		}

		int tradeLeft = limitedShop.getLimitAmount() - limitedShop.getTradeAmountForPlayer(player.getUniqueId());
		player.sendMessage(LANGUAGE.getLimitTracker(tradeLeft));
	}

	/**
	 * @param block block to check.
	 * @return true if the block is a {@link org.maxgamer.quickshop.api.shop.Shop} block.
	 */
	private boolean isShopBlock(Block block) {
		return block.getState() instanceof Sign || block.getType().equals(Material.CHEST);
	}

}
