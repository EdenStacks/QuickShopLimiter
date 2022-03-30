package fr.edencraft.quickshoplimiter.lang;

import fr.edencraft.quickshoplimiter.utils.ColoredText;
import fr.edencraft.quickshoplimiter.utils.LimitationType;
import fr.edencraft.quickshoplimiter.utils.LimitedShop;
import fr.edencraft.quickshoplimiter.utils.TimingType;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.List;

public class English implements Language {

	@Override
	public String getUpdatedShopPermission(String oldPermission, String newPermission) {
		return prefix + new ColoredText(
				"&aThe permission &c" + oldPermission + " &ahas been replaced by &e" + newPermission + "&a."
		).treat();
	}

	@Override
	public String getRemovedShopPermission(String oldPermission) {
		return prefix + new ColoredText(
				"&aThe permission &e" + oldPermission + " &ahas been deleted for this shop."
		).treat();
	}

	@Override
	public String getShopAlreadyHasThisPermission(String permission) {
		return prefix + new ColoredText(
				"&6This shop already has the permission &e" + permission + " &6active."
		).treat();
	}

	@Override
	public String getNeededPermission(String permission) {
		return prefix + new ColoredText(
				"&cYou don't have the permission to use this shop. &8(&7" + permission + "&8)"
		).treat();
	}

	@Override
	public String getLimitTracker(int tradeLeft) {
		if (tradeLeft == 0) {
			return prefix + new ColoredText(
					"&cYou can't trade with this shop &8(&7you have reached the limit&8)&c."
			).treat();
		} else {
			return prefix + new ColoredText(
					"&eYou have &6" + tradeLeft + " &etrade left in this shop."
			).treat();
		}
	}

	@Override
	public String getReloadFiles(List<String> filesName) {
		if (filesName.size() == 1) {
			return prefix + new ColoredText(
					"&aThe configuration file &7(&f" + filesName.stream().findFirst().get() + "&7) " +
							"&ahas been reloaded !"
			).treat();
		} else {
			return prefix + new ColoredText(
					"&aAll configuration file has been reloaded !"
			).treat();
		}
	}

	@Override
	public String getUnknownConfigFile(String name) {
		return prefix + new ColoredText(
				"&cThe configuration file &7(&f" + name + "&7) &cdoes'nt exist !"
		).treat();
	}

	@Override
	public String getTradeExceedLimitation(int ongoingTrade, int tradedAmount, int limitAmount) {
		return prefix + new ColoredText(
				"&cYou will exceed the limitation for this shop ! &f(&6" + tradedAmount + "&7+&6" + ongoingTrade +
						"&7=&4" + (tradedAmount + ongoingTrade) + " &7> &c" + limitAmount + "&f)"
		).treat();
	}

	@Override
	public String getUnableToLoadShop() {
		return prefix + new ColoredText(
				"&cOups! This shop can't be load..."
		).treat();
	}

	@Override
	public String getNotALimitedShop() {
		return prefix + new ColoredText(
				"&cThis shop doesn't have limit."
		).treat();
	}

	@Override
	public String getUnknownLimitedShopID(String shopID) {
		return prefix + new ColoredText(
				"&cThe id &6" + shopID + " &cis not valid."
		).treat();
	}

	@Override
	public String getLookChestOrSign() {
		return prefix + new ColoredText(
				"&cYou need to look a chest or a sign linked to a shop to run this command."
		).treat();
	}

	@Override
	public String getLookShopSign() {
		return prefix + new ColoredText(
				"&cYou need to look a sign linked to a valid shop."
		).treat();
	}

	@Override
	public String getShopAlreadyHasLimit() {
		return prefix + new ColoredText(
				"&6Thsi shop already has a limit."
		).treat();
	}

	@Override
	public String getLimitedShopCreated() {
		return prefix + new ColoredText(
				"&aShop created with success."
		).treat();
	}

	@Override
	public String getCanNotResetForThisLimitationType(LimitedShop limitedShop, LimitationType limitationType) {
		return prefix + new ColoredText(
				"&cYou can't reset player limit for shop n°&6" + limitedShop.getShopID() +
						" &cbecause his limitation type is &6" + limitationType.name() + "&c."
		).treat();
	}

	@Override
	public String getPlayerDoesNotHaveTradeYet(LimitedShop limitedShop, OfflinePlayer playerToReset) {
		return prefix + new ColoredText(
				"&6You can't reset limit of &e" + playerToReset.getName() + " &6because he has not trade with the shop" +
						"n°&e" + limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getLimitedShopReset(LimitedShop limitedShop, OfflinePlayer playerToReset) {
		String playerName = playerToReset == null ? "all" : playerToReset.getName();

		return prefix + new ColoredText(
				"&aYou had reset limit for &e" + playerName + " &afor the shop n°&e" +
						limitedShop.getShopID() + "&a."
		).treat();
	}

	@Override
	public String getPlayerNeverPlayed(String playerName) {
		return prefix + new ColoredText(
				"&cPlayer &6" + playerName + " &chas never played on the server."
		).treat();
	}

	@Override
	public String getLimitedShopInfo(LimitedShop limitedShop) {
		Date lastReset = new Date(limitedShop.getLastReset());
		StringBuilder stringBuilder = new StringBuilder();
		String permission = limitedShop.getPermission() == null ? "none" : limitedShop.getPermission();

		stringBuilder.append("&d&m                                                                     \n");
		stringBuilder.append("&a• ID: &e").append(limitedShop.getShopID()).append("\n");
		stringBuilder.append("&a• Limit infos:\n");
		stringBuilder.append("  &6→ Permission: &e").append(permission).append("\n");
		stringBuilder.append("  &6→ Type: &e").append(limitedShop.getLimitationType().name()).append("\n");
		stringBuilder.append("  &6→ Amount: &e").append(limitedShop.getLimitAmount()).append("\n");
		stringBuilder.append("&a• Reset infos:\n");
		stringBuilder.append("  &6→ Every  &e").append(limitedShop.getInterval()).append(" ")
				.append(limitedShop.getTimingType().name()).append("\n");
		stringBuilder.append("  &6→ Last reset: &e").append(lastReset.toString()).append("\n");
		stringBuilder.append("&a• Location info:\n");
		stringBuilder.append("  &6→ World: &e").append(limitedShop.getShop().getLocation().getWorld().getName())
				.append("\n");
		stringBuilder.append("  &6→ x: &e").append(limitedShop.getShop().getLocation().getBlockX()).append("\n");
		stringBuilder.append("  &6→ y: &e").append(limitedShop.getShop().getLocation().getBlockY()).append("\n");
		stringBuilder.append("  &6→ z: &e").append(limitedShop.getShop().getLocation().getBlockZ()).append("\n");
		stringBuilder.append("&d&m                                                                     ");

		return new ColoredText(stringBuilder.toString()).treat();
	}

	@Override
	public String getConsoleNeedToGiveShopID() {
		return prefix + new ColoredText(
				"&cAs console a shop ID need to be specified."
		).treat();
	}

	@Override
	public String getModifyCommandSyntax() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("&aTo modify a limited shop, you can use:\n");
		stringBuilder.append("  &6→ /qsl modify &elimitationtype &d<shopID> <LimitationType>\n");
		stringBuilder.append("  &6→ /qsl modify &elimitamount &d<shopID> <Amount>\n");
		stringBuilder.append("  &6→ /qsl modify &einterval &d<shopID> <Interval>\n");
		stringBuilder.append("  &6→ /qsl modify &etimingtype &d<shopID> <TimingType>\n");

		return new ColoredText(stringBuilder.toString()).treat();
	}

	@Override
	public String getAlreadyThisLimitationType(LimitedShop limitedShop, LimitationType newLimitationType) {
		return prefix + new ColoredText(
				"&6This limit is already in &e" + newLimitationType.name() + " &6mode for the shop n°&e" +
						limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getLimitationTypeModified(LimitedShop limitedShop,
											LimitationType oldLimitationType,
											LimitationType newLimitationType) {
		return prefix + new ColoredText(
				"&aThe limitation type for the shop n°&e" + limitedShop.getShopID() + " &6aas been modified to &e" +
						newLimitationType.name() + " &ainstead of &c" + oldLimitationType.name() + "&6."
		).treat();
	}

	@Override
	public String getAlreadyThisLimitAmount(LimitedShop limitedShop, int limitAmount) {
		return prefix + new ColoredText(
				"&6This limit is already set to a limit amount of &e" + limitAmount + " &6for the shop n°&e" +
						limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getLimitAmountModified(LimitedShop limitedShop, int oldLimitAmount, int newLimitAmount) {
		return prefix + new ColoredText(
				"&aThe limit amount of the shop n°&e" + limitedShop.getShopID() + " &ahas been modified to &e" +
						newLimitAmount + "&ainstead of &c" + oldLimitAmount + "&a."
		).treat();
	}

	@Override
	public String getAlreadyThisInterval(LimitedShop limitedShop, int interval) {
		return prefix + new ColoredText(
				"&6This limit already has an interval of &e" + interval + " &6for the shop n°&e" +
						limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getIntervalModified(LimitedShop limitedShop, int oldInterval, int newInterval) {
		return prefix + new ColoredText(
				"&aThe interval for the shop n°&e" + limitedShop.getShopID() + " &ahas been modified to &e" +
						oldInterval + " &ainstead of &c" + newInterval + "&a."
		).treat();
	}

	@Override
	public String getAlreadyThisTimingType(LimitedShop limitedShop, TimingType timingType) {
		return prefix + new ColoredText(
				"&6the limit already has the timing type &e" + timingType.name() + " &6for the shop n°&e" +
						limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getTimingTypeModified(LimitedShop limitedShop, TimingType oldTimingType, TimingType newTimingType) {
		return prefix + new ColoredText(
				"&aThe timing type for the shop n°&e" + limitedShop.getShopID() + " &ahas been modified to &e" +
						newTimingType.name() + " &ainstead of &c" + oldTimingType.name() + "&a."
		).treat();
	}

	@Override
	public String getCommandForbidden(CommandSender commandSender, String command) {
		if (!(commandSender instanceof Player)) {
			command = "/" + command;
		}

		return prefix + new ColoredText(
				"&cYou can't use &e" + command + " &cbecause this one is dangerous for all limited shops."
		).treat();
	}

}
