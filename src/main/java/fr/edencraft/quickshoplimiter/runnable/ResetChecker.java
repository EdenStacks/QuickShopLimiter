package fr.edencraft.quickshoplimiter.runnable;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import fr.edencraft.quickshoplimiter.utils.ConfigurationUtils;
import fr.edencraft.quickshoplimiter.utils.LimitedShop;
import fr.edencraft.quickshoplimiter.utils.TimingType;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;

/**
 * /!\ This Runnable need to bee run one time every minute only ! /!\
 *
 * It basically check if it's time to reset shops limit.
 * For now it try to clear at 6AM all shops that can be cleared.
 */
public class ResetChecker extends BukkitRunnable {

    private final ConfigurationManager configurationManager = QuickShopLimiter.getINSTANCE().getConfigurationManager();

    @Override
    public void run() {
        if (!isTimeToReset() || QuickShopLimiter.getINSTANCE().isResetCheckerTriggered()) return;
        QuickShopLimiter.getINSTANCE().setResetCheckerTriggered(true);

        List<LimitedShop> allLimitedShop = ConfigurationUtils.getAllLimitedShop();
        allLimitedShop.forEach(limitedShop -> {
            long lastReset = limitedShop.getLastReset();
            long actualTime = Calendar.getInstance(getTimeZone()).getTime().getTime();

            int interval = limitedShop.getInterval();
            TimingType timingType = limitedShop.getTimingType();
            Date actualDate = new Date(actualTime);

            boolean isReset = false;

            switch (timingType) {

                case DAY -> {
                    long millsDayValue = 1000 * 60 * 60 * 24;
                    long timeToWait = millsDayValue * interval - 60000;

                    if (lastReset + timeToWait <= actualTime) {
                        resetLimitedShopLimit(limitedShop);
                        isReset = true;
                    }
                }
                case MONTH -> {
                    Date dateOfReset = new Date(lastReset);
                    dateOfReset.setMonth(dateOfReset.getMonth() + interval);
                    if (dateOfReset.getTime() <= actualDate.getTime()) {
                        resetLimitedShopLimit(limitedShop);
                        isReset = true;
                    }
                }
                case YEAR -> {
                    Date dateOfReset = new Date(lastReset);
                    dateOfReset.setYear(dateOfReset.getYear() + interval);
                    if (dateOfReset.getTime() <= actualDate.getTime()) {
                        resetLimitedShopLimit(limitedShop);
                        isReset = true;
                    }
                }

            }

            if (isReset) {
                QuickShopLimiter.getINSTANCE().log(
                        Level.INFO,
                        "Shop n°" + limitedShop.getShopID() + " has been reset after " + interval + " " +
                                timingType.name() + "."
                );
            }
        });

    }

    /**
     * This method trigger the reset of shop that can be reset if it is 6AM.
     * If the {@link TimeZone} in config.yml is wrong it'll use default time zone of the server.
     *
     * @return true if all shops limit need to be cleared.
     */
    private boolean isTimeToReset() {
        TimeZone timeZone = getTimeZone();
        Calendar calendar = Calendar.getInstance(timeZone);

        return calendar.get(Calendar.HOUR_OF_DAY) == 6;
    }

    /**
     * @return The {@link TimeZone} in config.yml.
     */
    private TimeZone getTimeZone() {
        FileConfiguration cfg = configurationManager.getConfigurationFile("config.yml");
        String timeZoneName = cfg.getString("time-zone");

        return TimeZone.getTimeZone(timeZoneName);
    }

    /**
     * This method remove trade logs in Storage.yml.
     * It basically set to null the shop section and update the last reset value in Shops.yml.
     *
     * @param limitedShop The limited shop where remove limit.
     */
    private void resetLimitedShopLimit(LimitedShop limitedShop) {
        FileConfiguration storageCFG = configurationManager.getConfigurationFile("Storage.yml");
        storageCFG.set("storage." + limitedShop.getShopID(), null);
        configurationManager.saveFile("Storage.yml");

        limitedShop.getShopSection().set("reset.last-reset", System.currentTimeMillis());
        configurationManager.saveFile("Shops.yml");
    }

}
