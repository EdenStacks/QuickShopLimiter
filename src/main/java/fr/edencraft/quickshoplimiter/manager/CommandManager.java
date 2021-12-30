package fr.edencraft.quickshoplimiter.manager;

import co.aikar.commands.PaperCommandManager;
import fr.edencraft.quickshoplimiter.command.QSLimiter;
import fr.edencraft.quickshoplimiter.utils.CommandCompletionUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CommandManager {

    public CommandManager(Plugin plugin) {
        PaperCommandManager commandManager = new PaperCommandManager(plugin);
        commandManager.enableUnstableAPI("help");
        commandManager.registerCommand(new QSLimiter());
        commandManager.getCommandCompletions().registerAsyncCompletion(
                "quickshoplimiterreload",
                context -> {
                    if (context.getPlayer() != null) {
                        Player player = context.getPlayer();
                        player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 1, 1);
                    }
                    return CommandCompletionUtils.getConfigurationFilesName();
                }
        );
        commandManager.getCommandCompletions().registerAsyncCompletion(
                "listLimitedShopID",
                context -> {
                    if (context.getPlayer() != null) {
                        Player player = context.getPlayer();
                        player.playSound(player.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 1, 1);
                    }
                    return CommandCompletionUtils.getAllLimitedShopID();
                }
        );
    }

}
