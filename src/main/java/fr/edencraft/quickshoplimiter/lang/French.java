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

public class French implements Language {

	@Override
	public String getUpdatedShopPermission(String oldPermission, String newPermission) {
		return prefix + new ColoredText(
				"&aLa permission &c" + oldPermission + " &aa été remplacé par &e" + newPermission + "&a."
		).treat();
	}

	@Override
	public String getRemovedShopPermission(String oldPermission) {
		return prefix + new ColoredText(
				"&aLa permission &e" + oldPermission + " &aa été supprimé de ce shop."
		).treat();
	}

	@Override
	public String getShopAlreadyHasThisPermission(String permission) {
		return prefix + new ColoredText(
				"&6Ce shop à déjà la permission &e" + permission + " &6active."
		).treat();
	}

	@Override
	public String getNeededPermission(String permission) {
		return prefix + new ColoredText(
				"&cVous n'avez pas la permission d'utiliser ce shop. &8(&7" + permission + "&8)"
		).treat();
	}

	@Override
	public String getLimitTracker(int tradeLeft) {
		if (tradeLeft == 0) {
			return prefix + new ColoredText(
					"&cVous ne pouvez plus trade avec ce shop &8(&7vous avez atteint la limite&8)&c."
			).treat();
		} else {
			return prefix + new ColoredText(
					"&eVous pouvez encore trade &6" + tradeLeft + " &edans ce shop."
			).treat();
		}
	}

	@Override
	public String getReloadFiles(List<String> filesName) {
		if (filesName.size() == 1) {
			return prefix + new ColoredText(
					"&aLe fichier de configuration &7(&f" + filesName.stream().findFirst().get() + "&7) " +
							"&aa été reload !"
			).treat();
		} else {
			return prefix + new ColoredText(
					"&aTout les fichiers de configuration ont été reload !"
			).treat();
		}
	}

	@Override
	public String getUnknownConfigFile(String name) {
		return prefix + new ColoredText(
				"&cLe fichier de configuration &7(&f" + name + "&7) &cn'existe pas !"
		).treat();
	}

	@Override
	public String getTradeExceedLimitation(int ongoingTrade, int tradedAmount, int limitAmount) {
		return prefix + new ColoredText(
				"&cVous allez dépasser votre limite pour ce shop ! &f(&6" + tradedAmount + "&7+&6" + ongoingTrade +
						"&7=&4" + (tradedAmount + ongoingTrade) + " &7> &c" + limitAmount + "&f)"
		).treat();
	}

	@Override
	public String getUnableToLoadShop() {
		return prefix + new ColoredText(
				"&cOups! Ce shop n'a pas pu être chargé..."
		).treat();
	}

	@Override
	public String getNotALimitedShop() {
		return prefix + new ColoredText(
				"&cCe shop ne possède pas de limite."
		).treat();
	}

	@Override
	public String getUnknownLimitedShopID(String shopID) {
		return prefix + new ColoredText(
				"&cL'id &6" + shopID + " &cn'est pas valide."
		).treat();
	}

	@Override
	public String getLookChestOrSign() {
		return prefix + new ColoredText(
				"&cVous devez regarder un coffre ou un panneau associé à un shop pour cette commande."
		).treat();
	}

	@Override
	public String getLookShopSign() {
		return prefix + new ColoredText(
				"&cVous devez regarder un panneau associé à un shop valide."
		).treat();
	}

	@Override
	public String getShopAlreadyHasLimit() {
		return prefix + new ColoredText(
				"&6Ce shop possède déjà une limite."
		).treat();
	}

	@Override
	public String getLimitedShopCreated() {
		return prefix + new ColoredText(
				"&aShop crée avec succès."
		).treat();
	}

	@Override
	public String getCanNotResetForThisLimitationType(LimitedShop limitedShop, LimitationType limitationType) {
		return prefix + new ColoredText(
				"&cVous ne pouvez pas reset la limite d'un joueur pour le shop n°&6" + limitedShop.getShopID()
						+ " &ccar il est du" + "type &6" + limitationType.name() + "&c."
		).treat();
	}

	@Override
	public String getPlayerDoesNotHaveTradeYet(LimitedShop limitedShop, OfflinePlayer playerToReset) {
		return prefix + new ColoredText(
				"&6Vous ne pouvez pas reset la limite de &e" + playerToReset.getName() + " &6car il n'a pas encore " +
						"trade avec le shop n°&e" + limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getLimitedShopReset(LimitedShop limitedShop, OfflinePlayer playerToReset) {
		String playerName = playerToReset == null ? "all" : playerToReset.getName();

		return prefix + new ColoredText(
				"&aVous avez reset la limite de &e" + playerName + " &apour le shop n°&e" +
						limitedShop.getShopID() + "&a."
		).treat();
	}

	@Override
	public String getPlayerNeverPlayed(String playerName) {
		return prefix + new ColoredText(
				"&cLe joueur &6" + playerName + " &cn'a jamais joué sur le serveur."
		).treat();
	}

	@Override
	public String getLimitedShopInfo(LimitedShop limitedShop) {
		Date lastReset = new Date(limitedShop.getLastReset());
		StringBuilder stringBuilder = new StringBuilder();
		String permission = limitedShop.getPermission() == null ? "none" : limitedShop.getPermission();

		stringBuilder.append("&d&m                                                                     \n");
		stringBuilder.append("&a• ID: &e").append(limitedShop.getShopID()).append("\n");
		stringBuilder.append("&a• Infos de limite:\n");
		stringBuilder.append("  &6→ Permission: &e").append(permission).append("\n");
		stringBuilder.append("  &6→ Type: &e").append(limitedShop.getLimitationType().name()).append("\n");
		stringBuilder.append("  &6→ Montant: &e").append(limitedShop.getLimitAmount()).append("\n");
		stringBuilder.append("&a• Infos de remise à zéro:\n");
		stringBuilder.append("  &6→ Tout les  &e").append(limitedShop.getInterval()).append(" ")
				.append(limitedShop.getTimingType().name()).append("\n");
		stringBuilder.append("  &6→ Dernière remise à zéro: &e").append(lastReset.toString()).append("\n");
		stringBuilder.append("&a• Info sur la location:\n");
		stringBuilder.append("  &6→ Monde: &e").append(limitedShop.getShop().getLocation().getWorld().getName())
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
				"&cEn tant que console vous devez spécifier un shop ID."
		).treat();
	}

    /*
    |Pour modifier le shop n°X, vous pouvez utiliser :
    |  → /qsl modify X limitationtype <LimitationType>
    |  → /qsl modify X limitamount <Amount>
    |  → /qsl modify X interval <Interval>
    |  → /qsl modify X timingtype <TimingType>
     */

	@Override
	public String getModifyCommandSyntax() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("&aPour modifer un shop limité, vous pouvez utiliser:\n");
		stringBuilder.append("  &6→ /qsl modify &elimitationtype &d<shopID> <LimitationType>\n");
		stringBuilder.append("  &6→ /qsl modify &elimitamount &d<shopID> <Amount>\n");
		stringBuilder.append("  &6→ /qsl modify &einterval &d<shopID> <Interval>\n");
		stringBuilder.append("  &6→ /qsl modify &etimingtype &d<shopID> <TimingType>\n");

		return new ColoredText(stringBuilder.toString()).treat();
	}

	@Override
	public String getAlreadyThisLimitationType(LimitedShop limitedShop, LimitationType newLimitationType) {
		return prefix + new ColoredText(
				"&6Cette limite de shop est déjà en mode &e" + newLimitationType.name() + " &6pour le shop n°&e" +
						limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getLimitationTypeModified(LimitedShop limitedShop,
											LimitationType oldLimitationType,
											LimitationType newLimitationType) {
		return prefix + new ColoredText(
				"&aLe type de limite pour le shop n°&e" + limitedShop.getShopID() + " &aa été modifié pour &e" +
						newLimitationType.name() + " &aau lieu de &c" + oldLimitationType.name() + "&a."
		).treat();
	}

	@Override
	public String getAlreadyThisLimitAmount(LimitedShop limitedShop, int limitAmount) {
		return prefix + new ColoredText(
				"&6Cette limite de shop est déjà fixé à un montant de &e" + limitAmount + " &6pour le shop n°&e" +
						limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getLimitAmountModified(LimitedShop limitedShop, int oldLimitAmount, int newLimitAmount) {
		return prefix + new ColoredText(
				"&aLe montant de la limite pour le shop n°&e" + limitedShop.getShopID() + " &aa été modifié par &e" +
						newLimitAmount + " &aau lieu de &c" + oldLimitAmount + "&a."
		).treat();
	}

	@Override
	public String getAlreadyThisInterval(LimitedShop limitedShop, int interval) {
		return prefix + new ColoredText(
				"&6Cette limite de shop a déjà un interval de &e" + interval + " &6pour le shop n°&e" +
						limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getIntervalModified(LimitedShop limitedShop, int oldInterval, int newInterval) {
		return prefix + new ColoredText(
				"&aL'interval pour le shop n°&e" + limitedShop.getShopID() + " &aa été modifié pour &e" +
						oldInterval + " &aau lieu de &c" + newInterval + "&a."
		).treat();
	}

	@Override
	public String getAlreadyThisTimingType(LimitedShop limitedShop, TimingType timingType) {
		return prefix + new ColoredText(
				"&6Cette limite de shop a déjà le timing type &e" + timingType.name() + " &6pour le shop n°&e" +
						limitedShop.getShopID() + "&6."
		).treat();
	}

	@Override
	public String getTimingTypeModified(LimitedShop limitedShop, TimingType oldTimingType, TimingType newTimingType) {
		return prefix + new ColoredText(
				"&aLe type de timing pour le shop n°&e" + limitedShop.getShopID() + " &aa été modifié pour &e" +
						newTimingType.name() + " &aau lieu de &c" + oldTimingType.name() + "&a."
		).treat();
	}

	@Override
	public String getCommandForbidden(CommandSender commandSender, String command) {
		if (!(commandSender instanceof Player)) {
			command = "/" + command;
		}

		return prefix + new ColoredText(
				"&cVous ne pouvez pas utiliser &e" + command + " &ccar celle-ci est dangereuse pour les limites de shop."
		).treat();
	}
}
