package carlos.dev.games.api.source;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
public abstract class SourceProvider<BT extends BaseTriggers, VS extends ValueSource> implements SourceBase {

    private final FileConfiguration config;

    public SourceProvider(FileConfiguration config){
        this.config = config;
    }

    @NotNull
    public abstract Map<BT, VS> getMap();

    public abstract void loadValues();

    @NotNull
    public ConfigurationSection getSection(@NotNull ConfigurationSection root, @NotNull String path) {
        ConfigurationSection section = root.getConfigurationSection(path);
        if (section == null) {
            section = root.createSection(path);
        }
        return section;
    }
}
