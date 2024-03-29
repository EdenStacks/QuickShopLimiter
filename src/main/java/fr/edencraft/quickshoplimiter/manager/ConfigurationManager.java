package fr.edencraft.quickshoplimiter.manager;

import fr.edencraft.quickshoplimiter.filler.ConfigFiller;
import fr.edencraft.quickshoplimiter.filler.ShopsFiller;
import fr.edencraft.quickshoplimiter.filler.StorageFiller;
import fr.edencraft.quickshoplimiter.utils.CFGFiller;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

public class ConfigurationManager {

    private final Plugin plugin;
    private final Map<File, FileConfiguration> filesMap = new HashMap<>();

    public ConfigurationManager(Plugin plugin){
        this.plugin = plugin;
    }

    public void setupFiles(){
        // Setup DataFolder for plugin
        if (!plugin.getDataFolder().exists()){
            if(plugin.getDataFolder().mkdir()){
                plugin.getLogger().log(Level.INFO, "DataFolder has been created.");
            }else{
                plugin.getLogger().log(Level.SEVERE, "DataFolder can't be created.");
            }
        }else{
            plugin.getLogger().log(Level.INFO, "DataFolder loaded successful.");
        }
        initNewFile("Shops.yml", new ShopsFiller());
        initNewFile("Storage.yml", new StorageFiller());
        initNewFile("config.yml", new ConfigFiller());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void initNewFile(String fileName, CFGFiller filler){
        File file = getFile(fileName);
        FileConfiguration fileCFG = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
                fileCFG = YamlConfiguration.loadConfiguration(file);
                if (filler != null) {
                    fillCFGFile(filler, fileCFG);
                    fileCFG.save(file);
                }
            } catch (IOException ignored) { }
        } else {
            fileCFG = YamlConfiguration.loadConfiguration(file);
        }
        filesMap.put(file, fileCFG);
    }

    public void saveFiles(){
        filesMap.forEach((file, fileConfiguration) -> {
            try {
                fileConfiguration.save(file);
            } catch (IOException ignored) {}
        });
    }

    public void saveFile(String name) {
        if (getFile(name).exists()) {
            filesMap.forEach((file, fileConfiguration) -> {
                if (file.getName().equalsIgnoreCase(name)) {
                    try {
                        fileConfiguration.save(getFile(name));
                    } catch (IOException ignored) {}
                }
            });
        }
    }

    public void reloadFiles(){
        filesMap.forEach((file, fileConfiguration) -> {
            try {
                fileConfiguration.load(getFile(file.getName()));
            } catch (IOException | InvalidConfigurationException ignored) {}
        });
    }

    public void reloadFile(String name) {
        if (getFile(name).exists()) {
            filesMap.forEach((file, fileConfiguration) -> {
                if (file.getName().equalsIgnoreCase(name)) {
                    try {
                        fileConfiguration.load(getFile(name));
                    } catch (IOException | InvalidConfigurationException ignored) {}
                }
            });
        }
    }

    private File getFile(String fileName) {
        return new File(plugin.getDataFolder(), fileName);
    }

    private void fillCFGFile(CFGFiller filler, FileConfiguration fileConfiguration){
        filler.fill(fileConfiguration);
    }

    public FileConfiguration getConfigurationFile(String name){
        AtomicReference<FileConfiguration> fileCFG = new AtomicReference<>(null);
        filesMap.forEach((file, fileConfiguration) -> {
            if (file.getName().equalsIgnoreCase(name)) {
                fileCFG.set(fileConfiguration);
            }
        });
        return fileCFG.get();
    }

    public Map<File, FileConfiguration> getFilesMap() {
        return filesMap;
    }
}

