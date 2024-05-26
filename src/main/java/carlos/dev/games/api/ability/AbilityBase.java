package carlos.dev.games.api.ability;

import carlos.dev.games.api.skill.Skill;
import lombok.Data;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Data
public class AbilityBase implements Ability{

    private String id;

    private Skill skill;

    private String fancyName;
    private List<String> description;

    private int maxLevel;
    private int levelUp;

    private boolean isConsumable;

    private double health;
    private double mana;
    private double stamina;

    public AbilityBase(@NotNull FileConfiguration config){
        this.id = config.getString("id");
        this.fancyName = config.getString("display-name");
        this.description = config.getStringList("description");
        this.isConsumable = config.getBoolean("consumable.enable");
        this.health = config.getDouble("consumable.health");
        this.mana = config.getDouble("consumable.mana");
        this.stamina = config.getDouble("consumable.stamina");
    }

}
