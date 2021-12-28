package fr.edencraft.quickshoplimiter.lang;

import fr.edencraft.quickshoplimiter.QuickShopLimiter;

import java.util.List;

public interface Language {

    String prefix = QuickShopLimiter.getINSTANCE().getPluginPrefix();

    /**
     * This message is called when one or more files has been reloaded.
     *
     * @param filesName The list reloaded files.
     * @return The message in a specific language.
     */
    String getReloadFiles(List<String> filesName);

    /**
     * This message is called when trying to use an unknown configuration file.
     *
     * @param name The name of unknown configuration file.
     * @return The message in a specific language.
     */
    String getUnknownConfigFile(String name);

}
