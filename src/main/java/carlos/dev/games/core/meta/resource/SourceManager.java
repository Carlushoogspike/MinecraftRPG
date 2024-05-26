package carlos.dev.games.core.meta.resource;

import carlos.dev.games.api.source.SourceBase;
import carlos.dev.games.core.MinecraftRPG;
import carlos.dev.games.core.meta.resource.impl.BlockSource;
import carlos.dev.games.api.source.SourceProvider;
import carlos.dev.games.api.source.BaseTriggers;
import carlos.dev.games.api.source.ValueSource;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SourceManager {

    private final MinecraftRPG plugin;
    private final File folder;
    private List<SourceBase> sources;

    public SourceManager(MinecraftRPG plugin){
        this.plugin = plugin;

        this.folder = new File(plugin.getDataFolder(), "sources");
        if (!folder.exists()) folder.mkdirs();
        this.sources = new ArrayList<>();
    }

    public void register(){
        FileConfiguration blocks = genFile("blocks");
        BlockSource blockSource = new BlockSource(blocks);

        put(blockSource);
    }

    private void put(SourceBase base){
        sources.add(base);
        Bukkit.getLogger().warning("Loaded Source " + base.getNamespace());
    }

    private FileConfiguration genFile(String name){
        File file = new File(folder, name + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }

    public Map<? extends BaseTriggers, ? extends ValueSource> retrieveData(Class<? extends SourceProvider> sourceClass) {
        for (SourceBase source : sources) {
            if (source instanceof SourceProvider && sourceClass.isAssignableFrom(source.getClass())) {
                SourceProvider<?, ?> sourceProvider = (SourceProvider<?, ?>) source;
                Map<? extends BaseTriggers, ? extends ValueSource> map = sourceProvider.getMap();
                return map;
            }
        }

        return null;
    }

}
