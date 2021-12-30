package fr.edencraft.quickshoplimiter.lang;

import fr.edencraft.quickshoplimiter.utils.ColoredText;

import java.util.List;

public class English implements Language {

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

}
