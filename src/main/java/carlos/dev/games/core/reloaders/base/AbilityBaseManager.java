package carlos.dev.games.core.reloaders.base;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.ability.AbilityBase;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AbilityBaseManager {

    private final Map<String, AbilityBase> abilityMap;
    private final MinecraftRPG plugin;

    public AbilityBaseManager(MinecraftRPG plugin) {
        this.abilityMap = new HashMap<>();
        this.plugin = plugin;
        loadBase();
    }

    private void loadBase(){
        File folder = new File(plugin.getDataFolder(), "abilities");
        if (!folder.exists()){
            folder.mkdirs();
            plugin.getLogger().warning("Ability - Creating Folder");
        }

        File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {
            if (!file.getName().endsWith(".yml")) return;
            register(file);
        }
    }

    private void register(File file){
        String id = file.getName();

        if (abilityMap.containsKey(id)) return;
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        abilityMap.put(id, new AbilityBase(config));
        plugin.getLogger().info("Ability > " + id + " loaded");
    }

    public AbilityBase getBase(String id){
        if (!abilityMap.containsKey(id)) return null;
        return abilityMap.get(id);
    }

    public Collection<AbilityBase> getAllBases(){
        return this.abilityMap.values();
    }

}
