package carlos.dev.games.api.skill;

import lombok.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
public class SkillBase implements Skill{

    private String identifier;
    private boolean enabled;
    private String fancyName;
    private List<String> description;
    private List<String> abilities;
    private int startLevel;
    private int maxLevel;
    private double multiplier;
    private double baseValue;
    private String formula;

    public SkillBase(@NotNull FileConfiguration config){
        this.identifier = config.getString("id");
        this.enabled = config.getBoolean("enabled");
        this.fancyName = config.getString("display-name");
        this.description = config.getStringList("description");

        this.startLevel = config.getInt("level.start-level");
        this.maxLevel = config.getInt("level.max-level");

        this.multiplier = config.getDouble("base.multiplier");
        this.baseValue = config.getDouble("base.base-value");
        this.formula = config.getString("base.formula");
    }

    @Override
    public String getFancyName(boolean formatted) {
        return "";
    }

    @Override
    public List<String> getDescription(boolean formatted) {
        return List.of();
    }
}
