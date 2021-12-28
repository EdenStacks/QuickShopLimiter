package fr.edencraft.quickshoplimiter.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import fr.edencraft.quickshoplimiter.QuickShopLimiter;
import fr.edencraft.quickshoplimiter.lang.Language;
import fr.edencraft.quickshoplimiter.manager.ConfigurationManager;
import fr.edencraft.quickshoplimiter.utils.ColoredText;
import fr.edencraft.quickshoplimiter.utils.CommandCompletionUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;

@CommandAlias("qslimiter|qsl")
public class QSLimiter extends BaseCommand {

    private final static Language LANGUAGE = QuickShopLimiter.getINSTANCE().getLanguage();
    private final static ConfigurationManager CONFIGURATION_MANAGER =
            QuickShopLimiter.getINSTANCE().getConfigurationManager();

    private static final String basePermission = "quickshoplimiter.command";

    @Default
    @CommandPermission(basePermission)
    public static void onCommand(CommandSender sender) {
        onAbout(sender);
    }

    @Subcommand("reload|rl")
    @Syntax("[fileName]")
    @CommandCompletion("@quickshoplimiterreload")
    @CommandPermission(basePermission + ".reload")
    public static void onReload(CommandSender sender, @Optional String fileName){
        if (fileName != null && !fileName.isEmpty()) {
            if (CONFIGURATION_MANAGER.getConfigurationFile(fileName) != null) {
                CONFIGURATION_MANAGER.reloadFile(fileName);
                sender.sendMessage(LANGUAGE.getReloadFiles(Collections.singletonList(fileName)));
            } else {
                sender.sendMessage(LANGUAGE.getUnknownConfigFile(fileName));
            }
        } else {
            CONFIGURATION_MANAGER.reloadFiles();
            sender.sendMessage(LANGUAGE.getReloadFiles(CommandCompletionUtils.getConfigurationFilesName()));
        }
    }

    @Subcommand("about")
    @CommandPermission(basePermission + ".about")
    public static void onAbout(CommandSender sender) {
        StringBuilder cmdMessage = new StringBuilder();
        cmdMessage.append("\n");
        cmdMessage.append("╔══════════════════╗\n");
        cmdMessage.append("║ QuickShopLimiter ║\n");
        cmdMessage.append("╟──────────────────╢\n");
        cmdMessage.append("║ Version: 1.0.0   ║\n");
        cmdMessage.append("║                  ║\n");
        cmdMessage.append("║ Made with &4♥&r      ║\n");
        cmdMessage.append("║ &rby NayeOne.      ║\n");
        cmdMessage.append("╚══════════════════╝\n");

        StringBuilder playerMessage = new StringBuilder();
        playerMessage.append("&aQuickShopLimiter\n");
        playerMessage.append("&fVersion: &e1.0.0\n");
        playerMessage.append("&fBy: &eNayeOne\n");

        if (sender instanceof Player){
            sender.sendMessage(new ColoredText(playerMessage.toString()).treat());
        } else {
            assert sender != null;
            sender.sendMessage(new ColoredText(cmdMessage.toString()).treat());
        }
    }

}
