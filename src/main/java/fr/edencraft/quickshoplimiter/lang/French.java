package fr.edencraft.quickshoplimiter.lang;

import fr.edencraft.quickshoplimiter.utils.ColoredText;
import fr.edencraft.quickshoplimiter.utils.LimitationType;
import fr.edencraft.quickshoplimiter.utils.LimitedShop;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class French implements Language {

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
}
