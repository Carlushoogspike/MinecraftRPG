package carlos.dev.games.core.reloaders.base;

import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.api.skill.SkillBase;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SkillBaseManager {

    private final Map<String, SkillBase> skillBaseMap;
    private final MinecraftRPG plugin;

    public SkillBaseManager(MinecraftRPG plugin) {
        this.skillBaseMap = new HashMap<>();
        this.plugin = plugin;
        loadBase();
    }

    private void loadBase(){
        File folder = new File(plugin.getDataFolder(), "skills");
        if (!folder.exists()){
            folder.mkdirs();
            plugin.getLogger().warning("Skills - Creating Folder");
        }

        File[] files = folder.listFiles();

        if (files == null) return;

        for (File file : files) {
            if (!file.getName().endsWith(".yml")) return;
            register(file);
        }
    }

    private void register(File file){
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        String id = config.getString("id");
        if (skillBaseMap.containsKey(id)) return;

        SkillBase skill = new SkillBase(config);
        System.out.println(id);
        skillBaseMap.put(id, skill);
        plugin.getLogger().info("Skills > " + id + " loaded");

        plugin.getSkillLoader().register(skill);
    }

    public SkillBase getBase(String id){
        System.out.println("called " + id);
        if (!skillBaseMap.containsKey(id)) return null;
        return skillBaseMap.get(id);
    }

    public Collection<SkillBase> getAllBases(){
        return this.skillBaseMap.values();
    }

}
